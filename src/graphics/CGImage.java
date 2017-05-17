package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CGImage extends Drawable{

	public CGImage(Image image, int x, int y, int width, int height) {
		this.sprite = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		drawable_type = "CG_IMAGE";
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(sprite, x, y, width, height);		
	}

}
