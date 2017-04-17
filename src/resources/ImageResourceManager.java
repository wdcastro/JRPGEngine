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
		return new Image(new File(filenames.get(imagename).getImageName()).toURI().toString());
	}
	
	public static void loadResourcesFromFile(){
		filenames.put("MAIN_MENU_BG", new SpriteInfo("res/background/background.png", 0, 0, 1280, 720));
	}
	
	//TODO: function for loading strings into hash table from external file
}
