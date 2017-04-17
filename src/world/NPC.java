package world;

import misc.Interactable;
import graphics.PixelSprite;
import graphics.SpriteInfo;

public class NPC extends PixelSprite implements Interactable{

	String nearComment;
	String farComment;
	SpriteInfo spriteinfo;
	int currentFrame;
	int x;
	int y;
	
	public NPC(int x, int y, String nearComment, String farComment, SpriteInfo spriteinfo){
		this.x = x;
		this.y = y;
		this.nearComment = nearComment;
		this.farComment = farComment;
		this.spriteinfo = spriteinfo;
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
		
	}
	
	public void moveDown(){
		
	}
	
	public void moveLeft(){
		
	}

	public void moveRight(){
	
	}
}
