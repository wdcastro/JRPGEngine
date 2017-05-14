package gamecomponents;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import resources.FileReader;
import resources.Item;

public class PlayerData {
//inventory, party, etc
	public static HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();
	public static HashMap<String, Boolean> flags = new HashMap<String, Boolean>();
	public static ArrayList<Character> party = new ArrayList<Character>();
	
	static String flagsDefault = "res/defaults/flags.txt";
	
	public static void loadDefaults(){
		loadFlags("default");
	}
	
	private static void loadFlags(String property){
		try {
			byte[] flagsFile = null;
			if(property == "default"){
				flagsFile = FileReader.readBytesFromFile(flagsDefault);
			} else {
				flagsFile = FileReader.readBytesFromFile(flagsDefault);
			}
			String[] lines = new String(flagsFile, "UTF-8").split("\n");
			for(int i = 0; i < lines.length; i++){
				String[] values = lines[i].split(":");
				if(values[1].trim().equals("true")){
					flags.put(values[0].trim(), true);
				} else {
					flags.put(values[0].trim(), false);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
				
	}
	
	private void loadParty(String property){
		if(property == "default"){
			
		}
	}
	
	private void save(){
		
	}
	
	private void load(){
		
	}
}
