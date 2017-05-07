package world;

import resources.ImageResourceManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import gamecomponents.Game;
import graphics.MapSprite;
import graphics.SpriteInfo;

public class PlayerSprite extends MapSprite{
	
	SpriteInfo spriteinfo;
	Image sprite;
	World world;
	int x;
	int y;
	double lastMoveTimer = System.currentTimeMillis();
	float moveCd = 100;
	boolean isControllable = true;
	
	public PlayerSprite(World world, int x, int y){
		this.world = world;
		sprite = ImageResourceManager.getImage("CHIBI_DRAGOON");
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
		if(y<world.map.height && !world.isCollidableAt(x, y+1)){
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
		if(x<world.map.width && !world.isCollidableAt(x+1, y)){
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
	public void playAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//check for key press
		//movement cd timer
		if(System.currentTimeMillis()-lastMoveTimer>moveCd && isControllable){
			if(Game.keyhandler.isKeyDown("W")){
				moveUp();
			}
			if(Game.keyhandler.isKeyDown("S")){
				moveDown();
			}
			if(Game.keyhandler.isKeyDown("D")){
				moveRight();
			}
			if(Game.keyhandler.isKeyDown("A")){
				moveLeft();
			}
			lastMoveTimer = System.currentTimeMillis();
		}
		
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(sprite, (x-world.camera.left)*world.map.tilewidth, (y-world.camera.up)*world.map.tileheight, 64, 64);
		
	}

}

