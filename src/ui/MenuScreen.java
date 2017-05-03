package ui;

import gamecomponents.Game;
import graphics.CGImage;
import graphics.Drawable;
import graphics.Screen;

import java.util.ArrayList;

import resources.ImageResourceManager;

public class MenuScreen extends Screen {
	public String background = "MAIN_MENU_BG";
	//css
	
	public MenuScreen(){
		drawables = new ArrayList<Drawable>();
		drawables.add(new CGImage(ImageResourceManager.getImage(background), 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT));
	}
}