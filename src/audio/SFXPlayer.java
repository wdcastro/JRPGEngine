package audio;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import resources.FileReader;
import javafx.scene.media.AudioClip;

public class SFXPlayer {
	
	static String resourceLocation = "res/resourcemanagerfiles/SFX.resource";
	static HashMap<String, String> soundfiles = new HashMap<String, String>();

	public static void playSound(String name){
		new Thread(){
			@Override
			public void run(){
				AudioClip clip = new AudioClip(new File(soundfiles.get(name)).toURI().toString());
				clip.play();
			}
		}.start();
	}
	
	public static void loadResourcesFromFile(){
		File file = new File("res/sound");
		File[] dirlist = file.listFiles();
		for(int i = 0; i < dirlist.length; i++){
			soundfiles.put(dirlist[i].getName(), dirlist[i].getPath());
			System.out.println(dirlist[i].getName()+", "+soundfiles.get(dirlist[i].getName()));
		}
	}
		
}
