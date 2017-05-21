package menu;

import gamecomponents.GameLogic;
import graphics.CGImage;
import graphics.Drawable;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import audio.SFXPlayer;
import core.Game;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import resources.FileReader;
import resources.ImageResourceManager;
import resources.StyleSheetResourceManager;
import screens.Screen;

public class MainMenu extends Screen {
	public String background = "background.jpg";
	public String menufile = "res/en/menu/MAIN_MENU.txt";
	VBox menuBox = null;
	//css
	
	public MainMenu(){
		drawables = new ArrayList<Drawable>();
		drawables.add(new CGImage(ImageResourceManager.getImage(background), 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT));
		menuBox = getMenu();
		Game.root.getChildren().add(menuBox);
	}

	@Override
	public String getScreenType() {
		return "MENU";
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMousePress(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public VBox getMenu(){
		VBox menu = new VBox();
		menu.setMinWidth(Game.SCREEN_WIDTH);
		menu.setMinHeight(Game.SCREEN_HEIGHT);
		menu.getStylesheets().add(new File(StyleSheetResourceManager.getStyleSheet("mainmenu.css")).toURI().toString());
		menu.setSpacing(20);
		menu.setAlignment(Pos.CENTER);
		loadMenu(menu);
		return menu;
	}
	
	private void loadMenu(VBox v){
		try {
			byte[] file = FileReader.readBytesFromFile(menufile);
			String[] lines = new String(file, "UTF-8").split("\n");
			for(int i = 0; i < lines.length; i++){
				Button button = new Button(lines[i].trim());
				button.setMinWidth(v.getMinWidth());
				button.setOnMouseReleased(new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent e) {
						SFXPlayer.playSound("correct.wav");
						openMenu(button.getText());
					}
					
				});
				v.getChildren().add(button);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void openMenu(String string){
		switch(string){
		case "New Game":
			GameLogic.start();
			break;
		case "Load Game":
			GameLogic.loadChapter(1);
			break;
		case "Settings":
			System.out.println("Opening settings");
			break;
		case "Exit":
			System.exit(0);
			break;
		}
	}

	@Override
	public void hide() {
		Game.root.getChildren().remove(menuBox);
	}
}
