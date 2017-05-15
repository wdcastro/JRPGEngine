package world;

import java.util.HashMap;

import core.Game;
import resources.ImageResourceManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import graphics.MapSprite;
import graphics.SpriteInfo;

public class PlayerSprite extends MapSprite{
	
	SpriteInfo spriteinfo;
	HashMap<String, Integer[]> animations = new HashMap<String, Integer[]>();
	Integer[] currentAnimation;
	int currentFrame = 0;
	String facing = "down";
	String currentAnimationName = null;
	long frameDuration = 100;
	long lastFrameTimer = 0;
	Image sprite;
	World world;
	int x;
	int y;
	double lastMoveTimer = System.currentTimeMillis();
	float moveCooldown = 150;
	boolean isControllable = true;
	
	public PlayerSprite(World world, int x, int y){
		this.world = world;
		this.x = x;
		this.y = y;
		sprite = ImageResourceManager.getImage("GHOST_GIRL");
		Integer[] idlefront = new Integer[]{0};
		Integer[] idleback = new Integer[]{6};
		Integer[] idleright = new Integer[]{3};
		Integer[] idleleft = new Integer[]{11};
		Integer[] front = new Integer[]{1,0,2};
		Integer[] right = new Integer[]{4,3,5};
		Integer[] back = new Integer[]{7,6,8};
		Integer[] left = new Integer[]{9,11,10};
		currentAnimation = idlefront;
		animations.put("idlefront", idlefront);
		animations.put("idleback", idleback);
		animations.put("idleleft", idleleft);
		animations.put("idleright", idleright);
		animations.put("front", front);
		animations.put("right", right);
		animations.put("left", left);
		animations.put("back", back);
		
	}
	
	public void setControllable(boolean control){
		isControllable = control;
	}

	public void moveUp(){
		if(y > 0 && !world.isCollidableAt(x, y-1)){
			//collision check
			y--;
			world.camera.moveUp();
		}
	}
	
	public void moveDown(){
		if(y<world.map.height-1 && !world.isCollidableAt(x, y+1)){
			//collision check
			y++;
			world.camera.moveDown();
		}
		
	}
	
	public void moveLeft(){
		if(x > 0 && !world.isCollidableAt(x-1, y)){
			//collision check
			x--;
			world.camera.moveLeft();
		}
	}
	
	public void moveRight(){
		if(x<world.map.width-1 && !world.isCollidableAt(x+1, y)){
			//collision check
			x++;
			world.camera.moveRight();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playAnimation(String animation) {
		// TODO Auto-generated method stub
		
	}
	
	public void setAnimation(String s) {
		if(animations.containsKey(s)){
			currentFrame = 0;
			currentAnimationName = s;
			currentAnimation = animations.get(s);
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//check for key press
		//movement cd timer
		if(System.currentTimeMillis() - lastMoveTimer >= moveCooldown && isControllable){
			if(Game.keyhandler.isKeyDown("W")){
				if(facing == "back"){
					moveUp();
					if(currentAnimationName!="back"){
						setAnimation("back");
					}
				} else {
					facing = "back";
					if(currentAnimationName!="idleback"){
						setAnimation("idleback");
					}
				}
				
			} else if(Game.keyhandler.isKeyDown("S")){
				if(facing == "front"){
					moveDown();
					if(currentAnimationName!="front"){
						setAnimation("front");
					}
				} else {
					facing = "front";
					if(currentAnimationName!="idlefront"){
						setAnimation("idlefront");
					}
				}
				
			} else if(Game.keyhandler.isKeyDown("D")){
				if(facing == "right"){
					moveRight();
					if(currentAnimationName!="right"){
						setAnimation("right");
					}
				} else {
					facing = "right";
					if(currentAnimationName!="idleright"){
						setAnimation("idleright");
					}
				}
				
			} else if(Game.keyhandler.isKeyDown("A")){
				if(facing == "left"){
					moveLeft();
					if(currentAnimationName!="left"){
						setAnimation("left");
					}
				} else {
					facing = "left";
					if(currentAnimationName!="idleleft"){
						setAnimation("idleleft");
					}
				}
				
			} else {
				switch(facing){
				case "front":
					setAnimation("idlefront");
					break;
				case "back":
					setAnimation("idleback");
					break;
				case "left":
					setAnimation("idleleft");
					break;
				case "right":
					setAnimation("idleright");
					break;
				}
			}
			lastMoveTimer = System.currentTimeMillis();
		}
		
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		if(System.currentTimeMillis()-lastFrameTimer >= frameDuration){
			currentFrame++;
			if(currentFrame >= currentAnimation.length){
				currentFrame = 0;
			}
			lastFrameTimer = System.currentTimeMillis();
		}
		
		gc.drawImage(sprite,currentAnimation[currentFrame]*64,0,64,64, (x-world.camera.left)*world.map.tilewidth, (y-world.camera.up)*world.map.tileheight, 64, 64);
		
	}

}

