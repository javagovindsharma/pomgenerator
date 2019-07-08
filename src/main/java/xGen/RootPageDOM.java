package xGen;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import jWriter.JSONWriter;
import starter.Main;

public class RootPageDOM {

	public static void rootPageXpathMAp(String URL, WebDriver wd) {

		LinkedHashMap<String, String> rootMap = new LinkedHashMap<>();

		List<WebElement> eList = wd.findElements(By.cssSelector("*"));

		for (WebElement elm : eList) {

			if (elm != null && elm.getTagName().equalsIgnoreCase("a")) {
				rootMap.put(elm.getText(), XPathGenerator.generateXpath(elm));

			}
		}

		// get one By one Sub Menu

		/*
		 * for(Map.Entry<String, String> entry : rootMap.entrySet()) { String key =
		 * entry.getKey(); String value = entry.getValue();
		 * 
		 * }
		 */

		Object[] mapstrArr = rootMap.keySet().toArray();
		// get 1 menu SubEmnu
		subMenuPgaeXPath(rootMap.get(mapstrArr[2]), wd);

	}

	public static void subMenuPgaeXPath(String rootMenuXpath, WebDriver wd) {

		wd.findElement(By.xpath(rootMenuXpath)).click();
		XPathGenerator.waitForPageLoaded(wd);
		Main.programSleep(3000);
		System.out.println("******************SUBMENU**************************************");

		LinkedHashMap<String, String> subMenuMap = new LinkedHashMap<>();

		List<WebElement> eList = wd.findElements(By.cssSelector("*"));

		for (WebElement elm : eList) {

			if (elm != null && elm.getTagName().equalsIgnoreCase("a")) {
				subMenuMap.put(elm.getText(), XPathGenerator.generateXpath(elm));

			}
		}

		System.out.println("MAP--->>"+subMenuMap.toString());
		
		// get one By one Sub Menu xPath
        int subMenuNo=0;
		for (Map.Entry<String, String> entry : subMenuMap.entrySet()) {
			System.out.println("Key-->>"+entry.getKey()+" %%%%  values--->> "+entry.getValue());
			try {	subMenuNo=Integer.parseInt(entry.getKey().substring(0, (entry.getKey().length()-(entry.getKey().length()-3))));  }catch (Exception e) {}
			System.out.println("subMenuNo--------->>>>>>"+subMenuNo);
			if(subMenuNo>0) {
			   getPageXpathGenerator(entry.getValue(),wd);
			   Main.programSleep(4000);
			}
		}

		
		
	}

	public static void getPageXpathGenerator(String subMenuXpath, WebDriver wd) {
		System.out.println("******************getPageXpathGenerator start**************************************"+subMenuXpath);
		synchronized (wd) {	
     	wd.findElement(By.xpath(subMenuXpath)).click();
		XPathGenerator.waitForPageLoaded(wd);
	
		Main.programSleep(2500);
	
		System.out.println("\nPage Loaded... Generating XPath for " + wd.getCurrentUrl());
		Instant start = Instant.now();
		List<WebElement> eList = wd.findElements(By.cssSelector("*"));

		LinkedHashMap<String, LinkedHashMap<String, String>> xMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

		// Limiting the generation for selected tags - 'INPUT', 'BUTTON', 'SELECT',
		// 'TEXTAREA' ---- REMOVED 'A'

		for (WebElement e : eList) {
			if (Stream.of("INPUT", "BUTTON", "SELECT", "TEXTAREA").anyMatch(e.getTagName()::equalsIgnoreCase)) {
				if (e.getTagName().equalsIgnoreCase("input") && e.getAttribute("type").equalsIgnoreCase("checkbox")
						&& e.getAttribute("id").equals(""))
					continue;
				else {
					String n = e.getAttribute("name");

					String id = e.getAttribute("id");

					String l = e.getAttribute("label");

					// Adding filter for ID > NAME > LABEL

					if ((n != null && !n.equals("")) || (id != null && !id.equals(""))
							|| (l != null && !l.equals(""))) {
						String x = XPathGenerator.generateXpath(e);

						if (e.getAttribute("type").equalsIgnoreCase("checkbox")) {
							x = x.split("tbody\\[1\\]")[1].split("\\/div\\[1\\]")[0].replaceAll("/", "//");
						}
						if (e.getTagName().equalsIgnoreCase("input") && e.getAttribute("type") != null
								&& e.getAttribute("type").equalsIgnoreCase("submit")) {
							if (xMap.containsKey("button"))
								xMap.get("button").put(
										((id != null && !id.equals("")) ? id : (n != null && !n.equals("")) ? n : l),
										x);
							else {
								xMap.put("button", new LinkedHashMap<String, String>());
								xMap.get("button").put(
										((id != null && !id.equals("")) ? id : (n != null && !n.equals("")) ? n : l),
										x);
							}
						} else if (xMap.containsKey(e.getTagName()))
							xMap.get(e.getTagName()).put(
									((id != null && !id.equals("")) ? id : (n != null && !n.equals("")) ? n : l), x);
						else {
							xMap.put(e.getTagName(), new LinkedHashMap<String, String>());
							xMap.get(e.getTagName()).put(
									((id != null && !id.equals("")) ? id : (n != null && !n.equals("")) ? n : l), x);
						}

					}
				}
			}
		}

		// Map not allowing duplicate values as keys so skipping tags

		Instant finish = Instant.now();

		long timeElapsed = Duration.between(start, finish).getSeconds(); // in millis

		String cURL = "locators";

		String wdcURL = wd.getCurrentUrl();

		if (wdcURL.length() - wdcURL.replaceAll("/", "").length() >= 3)
			cURL = wdcURL.split("/")[3];

		System.out.println("\nTotal XPath generated : " + xMap.values().stream().mapToInt(LinkedHashMap::size).sum()
				+ " in " + timeElapsed + " seconds.\n ");

		//wd.close();
		JSONWriter.writer(xMap, cURL); // Writing to JSON file
		System.out.println(xMap.toString());
		String f3buttonXpath=xMap.get("button").get("fKey-03");
		System.out.println("f3buttonXpath===>>>"+f3buttonXpath);
		if(f3buttonXpath!=null) {
			wd.findElement(By.xpath(f3buttonXpath)).click();
			XPathGenerator.waitForPageLoaded(wd);
		}
			
		}//close synchronized block
		System.out.println("******************getPageXpathGenerator End**************************************"+subMenuXpath);
	}
}
