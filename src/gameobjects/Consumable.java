package gameobjects;

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
		System.out.println(effect);
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
}
