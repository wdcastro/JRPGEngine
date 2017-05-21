package resources;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;


public class MapResourceManager {
	public final static String resourceLocation = "res/resourcemanagerfiles/MAPS.resource";
	static Hashtable<String, String> mapnames = new Hashtable<String, String>();
	
	public static String getMap(String name){
		if(mapnames.containsKey(name)){
			return mapnames.get(name);
		} else {
			System.err.println("Invalid map request: "+name);
			return "";
		}
	}
	
	public static void loadResourcesFromFile(){
		File file = new File("res/mapdata");
		File[] dirlist = file.listFiles();
		for(int i = 0; i < dirlist.length; i++){
			String filename = dirlist[i].getName();
			String ext = filename.substring(filename.lastIndexOf("."),filename.length());
			if(ext.equals(".tmx")){
				mapnames.put(filename, dirlist[i].getPath());
				System.out.println(filename+", "+mapnames.get(filename));
			}
		}
	}

}
