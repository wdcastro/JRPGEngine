package resources;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import world.Item;
import javafx.scene.image.Image;

public class ImageResourceManager {
	
	static Hashtable<String, String> filenames = new Hashtable<String, String>();
	public final static String resourceLocation = "res/resourcemanagerfiles/IMAGES.resource";

	public ImageResourceManager(){
	}
	
	public static Image getImage(String imagename){
		if(filenames.containsKey(imagename)){
			return new Image(new File(filenames.get(imagename)).toURI().toString());
		} else {
			//return new Image(new File(filenames.get("DEFAULT").getImageName()).toURI().toString());
			return null;
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
					filenames.put(currentLine[0].trim(), currentLine[1].trim());
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean hasImage(String s){
		return filenames.contains(s);
	}
	
	public static Image getImageFromFile(String path){
		return new Image(new File(path).toURI().toString());
	}
	
	//TODO: function for loading strings into hash table from external file
}
