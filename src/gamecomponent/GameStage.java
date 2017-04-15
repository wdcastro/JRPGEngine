package gamecomponent;

import graphics.DrawingThread;
import resources.ImageResourceManager;
import ui.MenuScreen;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class GameStage {
	//defines what is on screen right now - screen, combat, cutscene
	//set what controls do - scene.setOnClick(whateverHandler)
	//pass it to drawing thread
	
	ImageResourceManager irm = new ImageResourceManager();
	Image background;
	
	
	public GameStage(Canvas canvas){

		DrawingThread drawingthread = new DrawingThread(canvas.getGraphicsContext2D(), this);
		drawingthread.start();
		
	}
	
	public void setGameStage(MenuScreen ms){
		background = irm.getImage(ms.background);
		
	}
	
	
}
