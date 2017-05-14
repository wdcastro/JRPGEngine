package misc;

import resources.InventoryResourceManager;
import gamecomponents.Game;

public class CommandExecutionManager {
	
	public static void execute(String command){
		String[] commands = command.split(";");
		if(commands == null || commands.length == 0){
			return;
		}
		switch(commands[0]){
		case "say":
			Game.dialogbox.say(commands[1], commands[2], commands[3]);
			break;
		case "item":
			InventoryResourceManager.addToInventory(commands[1], Integer.parseInt(commands[2]));
			break;
		default:
			System.out.println("Executing command: "+commands[0]);
			break;
			
		}
	}
}
