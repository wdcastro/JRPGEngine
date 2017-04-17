package resources;

import java.util.HashMap;

public class TextResourceManager {
	public static HashMap<String, String> texts = new HashMap<String, String>();
	
	public static void loadResourcesFromFile(){
		texts.put("GAME_TITLE", "JRPG Engine");
	}
}
