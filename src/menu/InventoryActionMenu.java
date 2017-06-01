package menu;

import screens.InventoryScreen;
import core.Game;
import gameobjects.Consumable;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class InventoryActionMenu extends VBox{
	InventoryScreen screen;
	Consumable selected;
	boolean isShowing = false;
	public InventoryActionMenu(Consumable selected, double x, double y, InventoryScreen screen){
		System.out.println("New InventoryActionMenu at "+x+", "+y);
		this.selected = selected;
		this.screen = screen;
		setLayoutX(x);
		setLayoutY(y);
		loadOptions();
	}
	
	public InventoryActionMenu(InventoryScreen screen){
		this.screen=screen;
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
		System.out.println(selected.getName());
	}
	
	public void loadOptions(){
		//use
		//delete
		//details
		String[] options = {"Use", "Delete", "Details"};
		for(int i = 0; i<options.length; i++){
			Button button = new Button(options[i]);
			button.setOnMouseClicked(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent e) {
					doOption(button.getText());
					
				}
				
			});
			getChildren().add(button);
		}
		
	}
	
	public void doOption(String option){
		switch(option){
		case "Use":
			selected.consume();
			break;
		case "Delete":
			selected.delete();
			break;
		case "Details":
			System.out.println(selected.getDescription());
			break;
		}
		screen.refresh();
		Game.root.getChildren().remove(this);
		setShowing(false);
	}
}
