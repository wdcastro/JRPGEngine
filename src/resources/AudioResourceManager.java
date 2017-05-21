package resources;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javafx.scene.media.Media;

public class AudioResourceManager {
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
		File file = new File("res/music");
		File[] dirlist = file.listFiles();
		for(int i = 0; i < dirlist.length; i++){
			songs.put(dirlist[i].getName(), dirlist[i].getPath());
			System.out.println(dirlist[i].getName()+", "+songs.get(dirlist[i].getName()));
		}
	}

}

	
