package resources;

import java.io.File;
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
		File file = new File("res/stylesheets");
		File[] dirlist = file.listFiles();
		for(int i = 0; i < dirlist.length; i++){
			cssstrings.put(dirlist[i].getName(), dirlist[i].getPath());
			System.out.println(dirlist[i].getName()+", "+cssstrings.get(dirlist[i].getName()));
		}
	}
}
