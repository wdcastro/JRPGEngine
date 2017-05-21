package screens;

import graphics.Drawable;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;

public abstract class Screen {

	protected ArrayList<Drawable> drawables = new ArrayList<Drawable>();

	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}
	
	public abstract String getScreenType();

	public abstract void update();

	public abstract void handleMousePress(MouseEvent e);
	
	public abstract void hide();
	
}
