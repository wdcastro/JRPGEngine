package resources;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

public class StyleSheetResourceManager {
	public final static String resourceLocation = "res/resourcemanagerfiles/STYLESHEETS.resource";
	static Hashtable<String, String> cssstrings = new Hashtable<String, String>();

	public static String getStyleSheet(String name){
		if(cssstrings.containsKey(name)){
			return cssstrings.get(name);
		} else {
			System.err.println("Invalid stylesheet request: "+ name);
			return "";
		}
		
	}
	
	public static void loadResourcesFromFile(){
		try {
			byte[] bytes = FileReader.readBytesFromFile(resourceLocation);
			String[] lines = new String(bytes, "UTF-8").split("\n");
			for(int i = 0; i<lines.length; i++){
				String[] currentLine = lines[i].trim().split(";;");
				if(currentLine[0].startsWith("#")){
					continue;
				} else {
					cssstrings.put(currentLine[0].trim(), currentLine[1].trim());
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
