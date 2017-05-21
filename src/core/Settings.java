package core;

import java.io.File;
import java.io.UnsupportedEncodingException;

import audio.BGMPlayer;
import resources.FileReader;

public class Settings {
	static String windowtype = "WINDOWED";
	static String language = "EN";
	static int width = 1280;
	static int height = 720;
	
	public static void loadSettings(){
		try{
			byte[] bytes = FileReader.readBytesFromFile("res/config.ini");
			String content = new String(bytes,"UTF-8");
			String[] lines = content.split("\n");
			for(int i = 0; i < lines.length; i++){
				String[] commands = lines[i].split(";;");
				switch(commands[0]){
				case "LANG":
					language = commands[1].trim();
					break;
				case "SCREEN":
					windowtype = commands[1].trim();
					break;
				case "WIDTH":
					width = Integer.parseInt(commands[1].trim());
					break;
				case "HEIGHT":
					height = Integer.parseInt(commands[1].trim());
					break;
				case "BGM":
					BGMPlayer.setVolume(Double.parseDouble(commands[1].trim()));
				default:
					System.out.println("Settings: loadSettings: "+commands[0]);
				}
			}
		} catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
}
