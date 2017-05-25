package resources;

import gameobjects.Consumable;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import core.Game;
import core.PlayerData;

public class InventoryResourceManager {
	public final static String resourceLocation = "res/en/ITEMS.resource";
	static HashMap<String, Consumable> items = new HashMap<String, Consumable>();
	
	public static void addToInventory(String name, Integer quantity){
		String item = name.toUpperCase().trim();
		if(items.containsKey(item)){
			System.out.println("Adding "+ item+ " x"+quantity+" to inventory");
			if(PlayerData.inventory.containsKey(item)){
				PlayerData.inventory.get(item).addQuantity(quantity);	
			} else {
				PlayerData.inventory.put(item, items.get(item));	
				PlayerData.inventory.get(item).addQuantity(quantity);
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
					items.put(currentLine[0].trim(), new Consumable(currentLine[0].trim(),currentLine[1].trim(), currentLine[2].trim()));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e){
			System.err.println("InventoryResourceManager: loadResourcesFromFile(): Insufficient arguments in ITEMS.resource");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
