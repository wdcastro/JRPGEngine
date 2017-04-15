package resources;

import java.io.File;
import java.util.Hashtable;

import javafx.scene.image.Image;

public class ImageResourceManager {
	
	Hashtable<String, String> filenames = new Hashtable<String, String>();

	public ImageResourceManager(){
		filenames.put("MAIN_MENU_BG", "res/background/background.png");
	}
	public Image getImage(String imagename){
		return new Image(new File(filenames.get(imagename)).toURI().toString());
	}
	
	//TODO: function for loading strings into hash table from external file
}
