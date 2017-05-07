package world;

import resources.ImageResourceManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import misc.CommandExecutionManager;
import misc.Interactable;
import graphics.MapSprite;
import graphics.SpriteInfo;

public class NPC extends MapSprite implements Interactable{

	String nearComment;
	public String farComment;
	String npcname;
	String portraitName = "";
	Image sprite;
	int currentFrame;
	public int x;
	public int y;
	World world;
	
	public NPC(World world, int x, int y, String nearComment, String farComment, String npcname){
		this.x = x;
		this.y = y;
		this.nearComment = nearComment;
		this.farComment = farComment;
		this.npcname = npcname;
		this.world = world;
		setSprite(npcname.trim().toUpperCase()+"_SPRITE");
	}
	
	public void setPortrait(String s){
		portraitName = s;
	}
	
	public void setSprite(String s){
		sprite = ImageResourceManager.getImage(s);
	}

	@Override
	public void nearInteract() {
		CommandExecutionManager.execute(
				"say"+";"+
				npcname+";"+
				portraitName+";"+
				nearComment
				);
	}

	@Override
	public void farInteract() {
		CommandExecutionManager.execute(
				"say"+";"+
				npcname+";"+
				""+";"+
				farComment
				);
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
