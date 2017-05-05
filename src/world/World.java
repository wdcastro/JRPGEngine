package world;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import resources.MapResourceManager;
import resources.TiledMapReader;
import tilemap.Tile;
import tilemap.TiledTileMap;
import gamecomponents.Game;
import graphics.Screen;
import graphics.SpriteInfo;

public class World extends Screen{
	
	//CameraFrame cameraFrame = new CameraFrame();

	//characters and units on world
	
	public TiledTileMap map;
	public PlayerSprite player;

	Camera camera;
	public ArrayList<NPC> npcs = new ArrayList<NPC>();
	
	public World(String mapname){
		player = new PlayerSprite(this, 0, 0);
		double time = System.nanoTime();
		System.out.println("World loading complete. Time taken : "+((System.nanoTime()-time)/Game.MILLIS_TO_NANOS)+" ms");
		TiledMapReader tmr = new TiledMapReader();
		map = tmr.read(MapResourceManager.getMap(mapname));
		if(map == null){
			System.err.println("World(): Map returned null");
			System.exit(1);
		} else {
			setCamera(null);
			drawables.add(map);
			drawables.add(player);
		}
		System.out.println("Camera co-ordinates: "+camera.left+", "+camera.up+", "+camera.width+", "+camera.height);
		System.out.println("--------------------------------------------------");
	}
	
	@Override
	public void update(){
		map.update(camera);
		player.update();
		for(int i = 0; i < npcs.size(); i++){
			npcs.get(i).update();
		}
	}

	//public boolean itemExistsAt(int x, int y){ //check if an item exists on map on world coordinates
		//ArrayList
	//}
	
	public void loadNPC(NPC npc){
		this.npcs.add(npc);
	}
	
	public void loadNPCs(ArrayList<NPC> npcs){
		this.npcs.addAll(npcs);
		drawables.addAll(npcs);
	}
	
	public boolean isCollidableAt(int x, int y){
		if(x < 0 || x > map.width || y < 0 || y > map.height){
			return false;
		}
		for(int i = 0; i < npcs.size(); i++){
			if(npcs.get(i).x == x && npcs.get(i).y == y){
				return true;
			}
		}
		return map.isCollidableAt((y*map.width) + x); //TODO: add npc check
	}
	
	public void setCamera(Camera camera){
		if(camera == null){
			this.camera = new Camera(this, 0, 0, Game.SCREEN_WIDTH/map.tilewidth, Game.SCREEN_HEIGHT/map.tileheight);
		} else {
			this.camera = camera;
		}
	}

	@Override
	public String getScreenType() {
		return "WORLD";
	}
	
}
