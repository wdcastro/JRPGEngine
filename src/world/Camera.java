package world;

import screens.World;
import javafx.animation.AnimationTimer;

public class Camera {
	public volatile int up;
	public volatile int left;
	public volatile int width;
	public volatile int height; // number of tiles
	private World world;
	
	private boolean cameraLocked = false;
	
	public Camera(World world, int up, int left, int width, int height){
		this.world = world;
		this.up = up;
		this.left = left;
		this.width = width;
		this.height = height;
	}

	public void moveUp(){// check for player screen lock
		if(up>0 && world.player.y<=up+(height/2)){
			up--;
		}
	}
	
	public void moveDown(){
		if(up+height<world.map.height && world.player.y>=up+(height/2)){
			up++;	
		}
		
	}
	
	public void moveLeft(){
		if(left>0 && world.player.x<=left+(width/2)){
			left--;
		}
	}
	
	public void moveRight(){
		if(left+width<world.map.width && world.player.x>=left+(width/2)){
			left++;
		}
	}
	
	public void centerOn(int x, int y){
		left = x-(width/2);
		up = y-(height/2);
		if(left < 0){
			left = 0;
		}
		if(up < 0){
			up = 0;
		}
		if(left+width >= world.map.width){
			left = world.map.width - width;
		}
		if(up+height >= world.map.height){
			up = world.map.height - height;
		}
		
	}
}
