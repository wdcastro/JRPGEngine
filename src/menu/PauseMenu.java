package menu;



import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import audio.SFXPlayer;
import gamecomponents.Game;
import graphics.Screen;
import resources.FileReader;
import resources.StyleSheetResourceManager;
import resources.TextResourceManager;
import ui.MainMenu;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PauseMenu extends VBox{

	private int currentlySelected = 0;
	String menufile = "res/menu/PAUSE_MENU.txt";
	ArrayList<Button> buttons = new ArrayList<Button>();

	Screen screen = new MainMenu();
	
	public PauseMenu(){
		getStylesheets().add(new File(StyleSheetResourceManager.getStyleSheet("PAUSE_MENU")).toURI().toString());
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
			Game.gamestage.setGameStage(screen);
			break;
		case "EXIT":
			Game.gamestage.backToPrevScreen();
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
				buttons.add(button);
				getChildren().add(button);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
