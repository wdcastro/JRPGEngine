package menu;



import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import core.Game;
import audio.SFXPlayer;
import resources.FileReader;
import resources.StyleSheetResourceManager;
import resources.TextResourceManager;
import screens.Screen;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PauseMenu extends VBox{

	String menufile = "res/en/menu/PAUSE_MENU.txt";
	
	public PauseMenu(){
		getStylesheets().add(new File(StyleSheetResourceManager.getStyleSheet("pausemenu.css")).toURI().toString());
		setMinHeight(Game.SCREEN_HEIGHT);
		setMinWidth(Game.SCREEN_WIDTH*0.20);
		setSpacing(Game.SCREEN_HEIGHT/12);
		setLayoutX(0);
		setLayoutY(0);
		getStyleClass().add("pausemenu");
		loadMenuItems();
	}
	
	private void openMenu(String menuname){
		switch(menuname.trim().toUpperCase()){
		case "PARTY":
			//do something //creating duplicate screens but its ok
			break;
		default:
			System.out.println(menuname);
		}
	}
	
	private void loadMenuItems(){
		try {
			byte[] file = FileReader.readBytesFromFile(menufile);
			String[] lines = new String(file, "UTF-8").split("\n");
			for(int i = 0; i < lines.length; i++){
				Button button = new Button(lines[i].trim());
				button.setMinWidth(getMinWidth());
				button.setOnMouseReleased(new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent e) {
						SFXPlayer.playSound("BUTTON_OK");
						openMenu(button.getText());
					}
					
				});
				getChildren().add(button);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
