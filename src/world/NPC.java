package world;

import resources.ImageResourceManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import misc.Interactable;
import graphics.MapSprite;
import graphics.SpriteInfo;

public class NPC extends MapSprite implements Interactable{

	String nearComment;
	public String farComment;
	String spriteName;
	Image sprite;
	int currentFrame;
	int x;
	int y;
	World world;
	
	public NPC(World world, int x, int y, String nearComment, String farComment, String spriteName){
		this.x = x;
		this.y = y;
		this.nearComment = nearComment;
		this.farComment = farComment;
		this.spriteName = spriteName;
		this.world = world;
		sprite = ImageResourceManager.getImage(spriteName);
	}

	@Override
	public void nearInteract() {
		System.out.println(nearComment);
	}

	@Override
	public void farInteract() {
		System.out.println(farComment);
	}
	
	public void update(){
		// TODO: go through sprite set animation
		// update x and y
	}
	
	public void moveUp(){
		y--;
	}
	
	public void moveDown(){
		y++;
	}
	
	public void moveLeft(){
		
	}

	public void moveRight(){
	
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		if(x < world.camera.left || x > world.camera.left+world.camera.width || y< world.camera.up || y > world.camera.up+world.camera.height){
			return;
		}
		gc.drawImage(sprite, (x-world.camera.left)*world.map.tilewidth, (y-world.camera.up)*world.map.tileheight, 64, 64);
		
		
	}
}
