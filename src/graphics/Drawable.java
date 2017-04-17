package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Drawable {
	
	protected Image sprite;
	protected float x; //x on screen to draw
	protected float y; //y on screen to draw
	protected float width;
	protected float height;
	protected String drawable_type;

	public Image getSprite() {
		return sprite;
	}
	
	public abstract void draw(GraphicsContext gc);

}
