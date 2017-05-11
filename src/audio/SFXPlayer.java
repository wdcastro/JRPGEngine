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
		try {
			byte[] bytes = FileReader.readBytesFromFile(resourceLocation);
			String[] lines = new String(bytes, "UTF-8").split("\n");
			for(int i = 0; i<lines.length; i++){
				String[] currentLine = lines[i].trim().split(";;");
				if(currentLine[0].startsWith("#")){
					continue;
				} else {
					soundfiles.put(currentLine[0].trim(), currentLine[1].trim());
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
		
}
