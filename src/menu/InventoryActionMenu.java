package menu;

import gameobjects.Consumable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class InventoryActionMenu extends VBox{
	Consumable selected;
	boolean isShowing = false;
	public InventoryActionMenu(Consumable selected, double x, double y){
		System.out.println("New InventoryActionMenu at "+x+", "+y);
		this.selected = selected;
		setLayoutX(x);
		setLayoutY(y);
		loadOptions();
	}
	
	public InventoryActionMenu(){
		loadOptions();
	}
	
	public void setShowing(boolean s){
		isShowing = s;
	}
	
	public boolean getShowing(){
		return isShowing;
	}
	
	public void setPosition(double x, double y){
		setLayoutX(x);
		setLayoutY(y);
	}
	
	public void setSelection(Consumable selected){
		this.selected = selected;
	}
	
	public void loadOptions(){
		//use
		//delete
		//details
		getChildren().add(new Button("Use"));
		getChildren().add(new Button("Delete"));
		getChildren().add(new Button("Details"));
		
	}
	
	public void doOption(String option){
		
	}
}
