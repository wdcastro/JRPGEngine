package resources;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import world.Item;
import core.Game;
import core.PlayerData;

public class InventoryResourceManager {
	public final static String resourceLocation = "res/resourcemanagerfiles/ITEMS.resource";
	static HashMap<String, Item> items = new HashMap<String, Item>();
	
	public static void addToInventory(String name, Integer quantity){
		String item = name.toUpperCase();
		if(items.containsKey(item)){
			System.out.println("Adding "+ item+ " x"+quantity+" to inventory");
			if(PlayerData.inventory.containsKey(item)){
				PlayerData.inventory.put(items.get(item), PlayerData.inventory.get(item) + quantity);	
			} else {
				PlayerData.inventory.put(items.get(item), quantity);	
			}
			Game.dialogbox.say("Got "+quantity+"x "+item, "", "");
		} else {
			System.err.println("InventoryResourceManager: Item doesn't exist: "+item);
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
					items.put(currentLine[0].trim(), new Item(currentLine[0].trim(),""));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
