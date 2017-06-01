package gameobjects;

import audio.SFXPlayer;
import core.PlayerData;

public class Consumable {
	
	String name;
	String description;
	String effect;
	int quantity = 0;
	
	public Consumable(String name, String description, String effect) {
		this.name = name;
		this.description = description;
		this.effect = effect;
	}

	public void consume(){
		quantity--;
		String[] commands = effect.split(" ");
		for(int i = 0; i<commands.length; i++){
			switch(commands[i].trim().toLowerCase()){
			case "heal":
				System.out.println("healed "+commands[i+1].trim());
				i++;
				break;
			case "sound":
				SFXPlayer.playSound(commands[i+1].trim());
				break;
			default:
				System.out.println(commands[i]);
			}
		}
		if(quantity == 0){
			delete();
		}
	}
	
	public String getName(){
		return name;
	}
	
	public void addQuantity(int i){
		quantity += i;
	}
	
	public int getQuantity(){
		return quantity;
	}

	public void delete() {
		PlayerData.inventory.remove(getName());
		
	}

	public String getDescription() {
		return description;
	}
}
