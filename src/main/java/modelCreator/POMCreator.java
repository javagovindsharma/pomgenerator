package modelCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import org.antlr.stringtemplate.StringTemplate;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class POMCreator {

	public static void pomGenerateByFileName(String fileName) {

		String path = System.getProperty("user.dir");
	
		try {
			POMCreator stHelper = new POMCreator();

			InputStream is = new FileInputStream(path + "\\src\\main\\resources\\strtemplate.stg");
			@SuppressWarnings("resource")
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();
			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}
			String fileAsString = sb.toString();

			StringTemplate strTemplate = new StringTemplate(fileAsString);
			String resContent="";
           try {
        	 resContent = stHelper.processTemplate(strTemplate, fileName, path);
           }catch (Exception e) {
        	   e.printStackTrace();
        	   Thread.sleep(2000);
        	   resContent = stHelper.processTemplate(strTemplate, fileName, path);
	      	}
			FileOutputStream out = new FileOutputStream(path + "\\" + fileName + ".ts");
			out.write(resContent.getBytes());
			out.close();
			System.out.println("\nPOM File Created Successfully - "+fileName + ".ts");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	public String processTemplate(StringTemplate strTemplate, String fileName, String path) throws IOException {

		strTemplate.setAttribute("fileName", fileName);
		strTemplate.setAttribute("ClassName", fileName);
		String pp=path+"\\"+fileName+".json";
		File tempFile = new File(pp);
		boolean exists = tempFile.exists();
		System.out.println("File Exists "+exists);
		// JSON parser object to parse read file
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(path + "\\" + fileName + ".json")));
		} catch (IOException e) {
			e.printStackTrace();
			 throw new IOException();
		}
		System.out.println(content);
		JsonElement jelement = new JsonParser().parse(content);
		JsonObject jobjectRoot= jelement.getAsJsonObject();
		JsonObject jobjectBtn = jobjectRoot.getAsJsonObject("button");
		JsonObject jobjectInput = jobjectRoot.getAsJsonObject("input");
		StringBuilder sbBtn = new StringBuilder();
		
		StringBuilder sbVariable = new StringBuilder();
		StringBuilder sbInput = new StringBuilder();
		StringBuilder sbFiled = new StringBuilder();
		
	                             

	       /**
	        * Input Fields
	        */
	       try {
	       Set<Map.Entry<String, JsonElement>> entriesInput = jobjectInput.entrySet();// will return members of your object
			for (Map.Entry<String, JsonElement> entry : entriesInput) {
				sbFiled.append("public  var_"+entry.getKey()+": String; ");
				String strInput="\n 	 public async "+entry.getKey()+"MethodTxt("+entry.getKey()+"_txt: string) { \n";
			       strInput+="      await element(by.xpath(elem.input."+entry.getKey()+")).sendKeys("+entry.getKey()+"_txt);   \n";
			       strInput+="   }";

			       strInput+="\n     public async "+entry.getKey()+"MethodTxtValue() {    \n";
			       strInput+="      await element(by.xpath(elem.input."+entry.getKey()+")).getAttribute('value').then(async function (actrm) {   \n";
			       strInput+="                       this.var_"+entry.getKey()+" = actrm;  \n          });\n  }";     
				
			       sbInput.append(strInput);
			       sbVariable.append("private "+entry.getKey()+":any;\n");
			}
	       }catch (Exception e) {
			e.printStackTrace();
		    }
			
			/**
			 * Buuton Loop
			 */
		
		try {
		Set<Map.Entry<String, JsonElement>> entriesBtn = jobjectBtn.entrySet();// will return members of your object
		for (Map.Entry<String, JsonElement> entry : entriesBtn) {
			
			String strFun ="\n	public async clickAt" + entry.getKey().replace("-", "_").replace("#", "") + "Button() \n    {";
			//here use json file get locator
			//strFun += "\n       await element(by.xpath(" + entry.getValue() + ")).click(); \n";
			strFun += "\n       await element(by.xpath(elem.button." +entry.getKey().replace("-", "_").replace("#", "") + ")).click(); \n";
			strFun += "    }\n";

			sbBtn.append(strFun);
			
		}
		}catch (Exception e) {
		e.printStackTrace();
		}
		strTemplate.setAttribute("fieldVariable", sbVariable.toString());
		strTemplate.setAttribute("inputFunction", sbInput.toString());
		strTemplate.setAttribute("buttonFunction", sbBtn.toString());

		return strTemplate.toString();

	}
	
	public static void main(String[] args) {
		pomGenerateByFileName("wrkPlantEquipment");
	}
	
}
