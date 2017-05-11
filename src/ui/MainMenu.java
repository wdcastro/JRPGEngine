package ui;

import gamecomponents.Game;
import graphics.CGImage;
import graphics.Drawable;
import graphics.Screen;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import resources.ImageResourceManager;

public class MainMenu extends Screen {
	public String background = "MAIN_MENU_BG";
	//css
	
	public MainMenu(){
		drawables = new ArrayList<Drawable>();
		drawables.add(new CGImage(ImageResourceManager.getImage(background), 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT));
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
}
