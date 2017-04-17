package graphics;

public class SpriteInfo {
	
	String imagename;
	float x; // x of the spritesheet
	float y; // y of the spritesheet
	float frameWidth;
	float frameHeight;
	
	public SpriteInfo(String imagename, float x, float y, float frameWidth, float frameHeight) {
		this.imagename = imagename;
		this.x = x;
		this.y = y;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
	}
	
	public String getImageName(){
		return imagename;
	}
	
	public float spritesheetX(){
		return x;
	}
	
	public float spritesheetY(){
		return y;
	}
	
	public float frameWidth(){
		return frameWidth;
	}
	
	public float frameHeight(){
		return frameHeight;
	}

}
