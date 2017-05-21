package core;




import menu.MainMenu;
import screens.Screen;
import gamecomponents.GameLogic;
import graphics.DrawingThread;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class GameStage {
	//defines what is on screen right now - screen, combat, cutscene
	//set what controls do - scene.setOnClick(whateverHandler)
	//pass it to drawing thread
	Image background;
	static DrawingThread drawingthread;
	AnimationTimer updateLoop;
	public static Screen currentScreen;
	public static Screen prevScreen;
	
	public GameStage(Canvas canvas){
		

		PlayerData.loadDefaults();
		drawingthread = new DrawingThread(canvas.getGraphicsContext2D(), this);
		MainMenu menu = new MainMenu();
		//Screen screen = new Cutscene("res/cutscenes/intro cutscene.txt");
		updateLoop = new AnimationTimer(){

			@Override
			public void handle(long arg0) {
				currentScreen.update();
				Game.dialogbox.update();
			}
			
		};
		updateLoop.start();
		drawingthread.start();
		//GameLogic.start();
		setGameStage(menu);
		System.out.println("DrawingThread started");
		
		
	}
	
	public void backToPrevScreen(){
		Screen tempScreen = prevScreen;
		if(tempScreen != null){
			setGameStage(tempScreen);
			tempScreen = null;
		}
	}
	
	public static void setGameStage(Screen s){
		//TODO: render css
		if(currentScreen != null){
			currentScreen.hide();
		}
		prevScreen = currentScreen;
		currentScreen = s;
		System.out.println("Game stage set to "+ s.getScreenType());
		drawingthread.updateDrawables(s.getDrawables()); // sprites are drawables
		System.out.println("Starting drawing...");
		System.out.println("--------------------------------");
	}

	
}
