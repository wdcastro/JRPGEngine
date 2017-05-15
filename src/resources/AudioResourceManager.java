package resources;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javafx.scene.media.Media;

public class AudioResourceManager {
	public final static String resourceLocation = "res/resourcemanagerfiles/MUSIC.resource";
	static HashMap<String, String> songs = new HashMap<String, String>();
	
	public static Media getSong(String name){
		if(songs.containsKey(name)){
			return new Media(new File(songs.get(name)).toURI().toString());
		} else {
			System.err.println("Invalid map request: "+name);
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
					songs.put(currentLine[0].trim(), currentLine[1].trim());
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

	
