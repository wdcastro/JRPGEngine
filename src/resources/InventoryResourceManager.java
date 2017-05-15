package resources;

import java.util.HashMap;

import world.Item;
import core.PlayerData;

public class InventoryResourceManager {
	static HashMap<String, Item> items = new HashMap<String, Item>();
	
	public static void addToInventory(String item, Integer quantity){
		if(items.containsKey(item)){
			PlayerData.inventory.put(items.get(item), quantity);			
		} else {
			System.err.println("InventoryResourceManager: Item doesn't exist: "+item);
		}
		
	}
}
