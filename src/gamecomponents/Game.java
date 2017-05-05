package gamecomponents;
import input.KeyHandler;
import input.MouseHandler;
import resources.ImageResourceManager;
import resources.MapResourceManager;
import resources.StyleSheetResourceManager;
import resources.TextResourceManager;
import ui.DialogBox;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Game extends Application{
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	public static long delta_time = 0;
	public static long last_time = System.nanoTime();
	public static final String newLine = System.getProperty("line.separator");
	

	public static final long MILLIS_TO_NANOS = 1000000;
	
	public static final KeyHandler keyhandler = new KeyHandler();
	public static final MouseHandler mousehandler = new MouseHandler();
	
	public static DialogBox dialogbox;
	
	public static void main(String[] args){
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Group root = new Group();
		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
		Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
		Label label = new Label();
		root.getChildren().add(canvas);
		root.getChildren().add(label);

		
		new AnimationTimer(){
			private long lastUpdate = 0;
			private final long[] frameTimes = new long[100];
		    private int frameTimeIndex = 0 ;
		    private boolean arrayFilled = false ;
		
		    @Override
			public void handle(long now) {
				// FPS Code and Delta Time
				delta_time = now - lastUpdate;
				lastUpdate = now;
				
				long oldFrameTime = frameTimes[frameTimeIndex] ;
	            frameTimes[frameTimeIndex] = now ;
	            frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
	            if (frameTimeIndex == 0) {
	                arrayFilled = true ;
	            }
	            
	            if (arrayFilled) {
	                long elapsedNanos = now - oldFrameTime ;
	                long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
	                double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
	                //System.out.println("Current frame rate: "+ frameRate);
	                label.setText(Double.toString(frameRate));
	
	                label.setTextFill(Color.RED);
	                label.setFont(Font.font("Vernada",20));
	                label.toFront();
	            }
		    }
		}.start();
		System.out.println("Image Resource loading started...");
		ImageResourceManager.loadResourcesFromFile();
		System.out.println("Image Resource loading complete");
		
		System.out.println("Text Resource loading started...");
		TextResourceManager.loadResourcesFromFile();
		System.out.println("Text Resource loading complete");
		
		System.out.println("Map Resource loading started...");
		MapResourceManager.loadResourcesFromFile();
		System.out.println("Map Resource loading complete");
		
		System.out.println("CSS Resource loading started...");
		StyleSheetResourceManager.loadResourcesFromFile();
		System.out.println("CSS Resource loading complete");
		
		dialogbox = new DialogBox();
		
		System.out.println("Adding dialog box...");
		root.getChildren().add(dialogbox);
		
		stage.setTitle(TextResourceManager.texts.get("GAME_TITLE"));
		stage.setResizable(false);
		stage.sizeToScene();
		
		stage.setScene(scene);
		stage.show();
		
		
		
		scene.setOnKeyPressed(keyhandler);
		scene.setOnKeyReleased(keyhandler);
		scene.setOnMousePressed(mousehandler);
		scene.setOnMouseReleased(mousehandler);

		System.out.println("--------------------------------------------------");
		System.out.println("Starting game");
		System.out.println("--------------------------------------------------");

		@SuppressWarnings("unused")
		GameStage gamestage = new GameStage(canvas);
		
	}

}
