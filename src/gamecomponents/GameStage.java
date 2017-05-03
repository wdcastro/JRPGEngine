package gamecomponents;



import resources.ImageResourceManager;
import ui.MenuScreen;
import world.World;
import graphics.CGImage;
import graphics.DrawingThread;
import graphics.Screen;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class GameStage {
	//defines what is on screen right now - screen, combat, cutscene
	//set what controls do - scene.setOnClick(whateverHandler)
	//pass it to drawing thread
	Image background;
	DrawingThread drawingthread;
	
	
	public GameStage(Canvas canvas){

		

		drawingthread = new DrawingThread(canvas.getGraphicsContext2D(), this);
		MenuScreen menuscreen = new MenuScreen();
		World w = new World();
		AnimationTimer updateLoop = new AnimationTimer(){

			@Override
			public void handle(long arg0) {
				w.update();
			}
			
		};
		setGameStage(w);

		updateLoop.start();
		drawingthread.start();
		System.out.println("DrawingThread started");
		
		
	}
	
	public void setGameStage(Screen s){
		//TODO: render css
		System.out.println("Game stage set to "+ s.getClass().toString());
		drawingthread.updateDrawables(s.getDrawables()); // sprites are drawables
		System.out.println("Starting drawing...");
		System.out.println("--------------------------------");
	}
	
	
}
