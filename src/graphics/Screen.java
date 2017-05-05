package graphics;

import java.util.ArrayList;

public abstract class Screen {

	protected ArrayList<Drawable> drawables = new ArrayList<Drawable>();

	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}
	
	public abstract String getScreenType();

	public abstract void update();
	
}
