package tester;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import xGen.XPathGenerator;

public class XpathChecker {


	
	public static void main(String[] args) {
		LinkedHashMap<String, LinkedHashMap<String, String>> xMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

		System.setProperty("webdriver.chrome.driver", "D:\\tt\\chromedriver_win32\\chromedriver.exe");
		 WebDriver wd = new ChromeDriver();
		wd.manage().window().maximize();

		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // IMplicit Wait by using timeouts() on webdriver

		wd.get("http://192.168.172.21/main");
		
		System.out.println("^^^^^^^^start^^^^^^^^^^^^^^^^^");
		try {  Thread.sleep(3000);  }catch (Exception e) {}
		
		List<WebElement> links =wd.findElements(By.cssSelector("*"));
		for (WebElement e : links) {
			
			if (Stream.of("INPUT", "BUTTON", "SELECT", "TEXTAREA","A").anyMatch(e.getTagName()::equalsIgnoreCase)) {
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
						if(e.getTagName().equalsIgnoreCase("input")&&e.getAttribute("type")!=null&&e.getAttribute("type").equalsIgnoreCase("submit"))
						{
							if (xMap.containsKey("button"))
								xMap.get("button").put(
										((id != null && !id.equals("")) ? id : (n != null && !n.equals("")) ? n : l), x);
							else {
								xMap.put("button", new LinkedHashMap<String, String>());
								xMap.get("button").put(
										((id != null && !id.equals("")) ? id : (n != null && !n.equals("")) ? n : l), x);
							}
						}
						else if (xMap.containsKey(e.getTagName()))
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
		
		
		System.out.println("get");
		xMap.forEach((k,v)->{System.out.println("Item : " + k + " Count : " + v);});
		
 
	}

}
