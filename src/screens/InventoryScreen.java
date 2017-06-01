package screens;


import java.util.ArrayList;

import menu.InventoryActionMenu;
import core.Game;
import core.GameStage;
import core.PlayerData;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InventoryScreen extends Screen{
	

	VBox box = new VBox();
	InventoryActionMenu actionmenu = new InventoryActionMenu(this);
	public InventoryScreen(){
		box.setLayoutX(300);
		Game.root.getChildren().add(box);
		refresh();
	}

	@Override
	public String getScreenType() {
		// TODO Auto-generated method stub
		return "MENU";
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMousePress(MouseEvent e) {
		if(e.getButton().toString().equals("SECONDARY")){
			GameStage.backToPrevScreen();
		}
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Game.root.getChildren().remove(box);
	}
	
	public void loadItemList(){
		box.getChildren().clear();
		ArrayList<String> inventory = new ArrayList<String>();
		inventory.addAll(PlayerData.inventory.keySet());
		for(int i = 0; i<inventory.size(); i++){
			Button button = new Button(inventory.get(i));
			
			button.setTextFill(Color.RED);
			button.setFont(Font.font("Vernada",20));
			button.toFront();
			button.setOnMouseClicked(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent e) {
					if(actionmenu.getShowing()){
						actionmenu.setPosition(e.getSceneX(), e.getSceneY());
						actionmenu.setSelection(PlayerData.inventory.get(button.getText()));
					} else {
						actionmenu.setPosition(e.getSceneX(), e.getSceneY());
						actionmenu.setSelection(PlayerData.inventory.get(button.getText()));
						Game.root.getChildren().add(actionmenu);
						actionmenu.setShowing(true);
					}
					
				}
				
			});
			box.getChildren().add(button);
		}
	}
	
	public void refresh(){
		
		loadItemList();
	}

}
