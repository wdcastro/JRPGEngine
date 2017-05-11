package resources;

import graphics.SpriteInfo;

import java.io.File;
import java.util.Hashtable;

import javafx.scene.image.Image;

public class ImageResourceManager {
	
	static Hashtable<String, SpriteInfo> filenames = new Hashtable<String, SpriteInfo>();

	public ImageResourceManager(){
	}
	
	public static Image getImage(String imagename){
		if(filenames.containsKey(imagename)){
			return new Image(new File(filenames.get(imagename).getImageName()).toURI().toString());
		} else {
			//return new Image(new File(filenames.get("DEFAULT").getImageName()).toURI().toString());
			return null;
		}
	}
	
	public static void loadResourcesFromFile(){
		filenames.put("MAIN_MENU_BG", new SpriteInfo("res/background/background.png", 0, 0, 1280, 720));
		filenames.put("CHIBI_DRAGOON", new SpriteInfo("res/misc/dragoon chibi.png", 0, 0, 64, 64));

		filenames.put("GHOST_GIRL", new SpriteInfo("res/character/ghost_girl_strip.png", 0, 0, 64, 64));

		filenames.put("DEFAULT", new SpriteInfo("res/misc/dragoon chibi.png", 0, 0, 64, 64));

		filenames.put("ELDER_PORTRAIT", new SpriteInfo("res/misc/dragoon chibi.png", 0, 0, 64, 64));

		filenames.put("ELDER_SPRITE", new SpriteInfo("res/misc/dragoon chibi.png", 0, 0, 64, 64));
	}
	
	public static boolean hasImage(String s){
		return filenames.contains(s);
	}
	
	public static Image getImageFromFile(String path){
		return new Image(new File(path).toURI().toString());
	}
	
	//TODO: function for loading strings into hash table from external file
}
