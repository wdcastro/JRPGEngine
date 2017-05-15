package graphics;

import javafx.scene.canvas.GraphicsContext;

public abstract class MapSprite extends Drawable {
	
	//currentFrame
	//spriteID
	//animation sequence
	private int x;
	private int y;
	//some form of behaviour
	
	public abstract void init();
	public abstract void playAnimation(String animation);
	public abstract void update();
	public abstract void draw(GraphicsContext gc);
	
}
