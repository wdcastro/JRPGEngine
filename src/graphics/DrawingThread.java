package graphics;

import gamecomponent.GameStage;
import javafx.scene.canvas.GraphicsContext;

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
	
	public DrawingThread(GraphicsContext gc, GameStage gs){
		this.gc = gc;
	}
	
	
	public void run(){
		isRunning = true;
		while(isRunning){
			draw();
		}
	}
	
	public void draw(){
		//get list of drawables from gamestage
		//loop and draw
	}
}
