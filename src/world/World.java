package world;

import javafx.scene.canvas.GraphicsContext;
import resources.TiledMapReader;
import tilemap.TiledTileMap;
import gamecomponents.Game;
import graphics.Screen;

public class World extends Screen{
	
	//CameraFrame cameraFrame = new CameraFrame();

	//characters and units on world
	
	TiledTileMap map;

	Camera camera = new Camera(0, 0, Game.SCREEN_WIDTH/64, Game.SCREEN_HEIGHT/64);
	
	public World(){
		double time = System.nanoTime();
		System.out.println("--------------------------------------------------");
		System.out.println("World loading complete. Time taken : "+((System.nanoTime()-time)/Game.MILLIS_TO_NANOS)+" ms");
		TiledMapReader tmr = new TiledMapReader();
		map = tmr.read("res/mapdata/test city.tmx");
		if(map == null){
			System.err.println("World(): Map returned null");
			System.exit(1);
		} else {
			drawables.add(map);
		}
		System.out.println("Camera coords: "+camera.left+", "+camera.up+", "+camera.width+", "+camera.height);
		System.out.println("--------------------------------------------------");
		
	}
	
	public void update(){
		map.update(camera);
	}
	
	public void draw(GraphicsContext gc){
		map.draw(gc);
		
	}
	
}
