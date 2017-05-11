package resources;

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
		try {
			byte[] bytes = FileReader.readBytesFromFile(resourceLocation);
			String[] lines = new String(bytes, "UTF-8").split("\n");
			for(int i = 0; i<lines.length; i++){
				String[] currentLine = lines[i].trim().split(";;");
				if(currentLine[0].startsWith("#")){
					continue;
				} else {
					mapnames.put(currentLine[0].trim(), currentLine[1].trim());
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
