package world;

public class Camera {
	public volatile int up;
	public volatile int left;
	public volatile int width;
	public volatile int height; // number of tiles
	
	public Camera(int up, int left, int width, int height){
		this.up = up;
		this.left = left;
		this.width = width;
		this.height = height;
	}
}
