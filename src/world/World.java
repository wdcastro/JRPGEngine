package world;

import java.util.ArrayList;

import menu.PauseMenu;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
	boolean inMenu = false;
	boolean enterDown = false;
	PauseMenu pausemenu = new PauseMenu();

	Camera camera;
	public ArrayList<NPC> npcs = new ArrayList<NPC>();
	
	public World(String mapname){
		player = new PlayerSprite(this, 3, 3);
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
		
		handleMenuKeys();
	}

	private void handleMenuKeys() {
		if(!enterDown && Game.keyhandler.isKeyDown("ENTER")){
			enterDown = true;
		}
		
		if(enterDown && !Game.keyhandler.isKeyDown("ENTER")){
			if(inMenu){
				//hide menu
				Game.root.getChildren().remove(pausemenu);
				System.out.println("menu closed");
			} else {
				Game.root.getChildren().add(pausemenu);
				System.out.println("menu open");
			}
			inMenu = !inMenu;
			enterDown = false;
		}
		
		
	}

	public boolean itemExistsAt(int x, int y){ //check if an item exists on map on world coordinates
		ArrayList<Tile> items = map.getTilesAt(y*map.width+x);
		for(int i = 0; i<items.size(); i++){
			if(items.get(i).isSpecialTile()){
				return true;
			}
		}
		return false;
	}
	
	public Tile interactableExistsAt(int x, int y){ //check if an item exists on map on world coordinates
		ArrayList<Tile> items = map.getTilesAt(y*map.width+x);
		for(int i = 0; i<items.size(); i++){
			if(items.get(i).isInteractableTile()){
				return items.get(i);
			}
		}
		return null;
	}
	
	public NPC npcExistsAt(int x, int y){
		for(int i = 0; i<npcs.size(); i++){
			if(npcs.get(i).x == x && npcs.get(i).y == y){
				return npcs.get(i);
			}
		}
		return null;
	}
	
	public void loadNPC(NPC npc){
		this.npcs.add(npc);
		drawables.add(npc);
	}
	
	public void loadNPCs(ArrayList<NPC> npcs){
		this.npcs.addAll(npcs);
		drawables.addAll(npcs);
	}
	
	public boolean isCollidableAt(int x, int y){
		if(x < 0 || x > map.width || y < 0 || y > map.height){
			return true;
		}
		if(player.x == x && player.y == y){
			return true;
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
	
	public boolean isPlayerAdjacent(int x, int y){
		return ((Math.abs(x - player.x) == 0 && Math.abs(y - player.y) == 1) || (Math.abs(x - player.x) == 1 && Math.abs(y - player.y) == 0));
	}

	@Override
	public void handleMousePress(MouseEvent e) {
		double mousex = e.getX();
		double mousey = e.getY();
		int worldx = (int) (mousex/map.tilewidth)+camera.left;
		int worldy = (int) (mousey/map.tileheight)+camera.up;
		NPC npc;
		Tile tile;
		if((npc = npcExistsAt(worldx, worldy)) != null){
			if(isPlayerAdjacent(worldx, worldy)){
				npc.nearInteract();
			} else {
				npc.farInteract();
			}
		} else if((tile = interactableExistsAt(worldx, worldy)) != null){
			
			if(isPlayerAdjacent(worldx, worldy)){
				tile.nearInteract();
			} else {
				tile.farInteract();
			}
		}
		
		
	}
	
}
