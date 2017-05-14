package graphics;

import gamecomponents.Game;
import gamecomponents.GameStage;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DrawingThread extends Thread{

	
	//loop through npc list, draw npc's sprite sheet image * frame at x,y + screen borders
	
	//loop through character list in battle instance and draw at location
	
	//render world
	//render cutscene
	//render combat
	//render menuscreen


	boolean isRunning = false;
	
	GraphicsContext gc;
	GameStage gs;
	
	ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	
	public DrawingThread(GraphicsContext gc, GameStage gs){
		this.gc = gc;
	}
	
	
	public void run(){
		new AnimationTimer(){

			@Override
			public void handle(long arg0) {
				draw();
				
			}
			
		}.start();
	}
	
	public void updateDrawables(ArrayList<Drawable> drawables){
		if(drawables != null){

			this.drawables = drawables;
		}
	}
	
	public void draw(){
		//get list of drawables from gamestage
		//loop and draw
		gc.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		for(int i = 0; i<drawables.size(); i++){
			Drawable d = drawables.get(i);
			d.draw(gc);
		}
		
	}
}
