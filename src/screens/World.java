package screens;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import audio.PlaylistManager;
import core.Game;
import menu.PauseMenu;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import resources.FileReader;
import resources.MapResourceManager;
import resources.TiledMapReader;
import tilemap.Tile;
import tilemap.TiledTileMap;
import world.Camera;
import world.NPC;
import world.PlayerSprite;
import graphics.Screen;

public class World extends Screen{
	
	//CameraFrame cameraFrame = new CameraFrame();

	//characters and units on world
	
	public TiledTileMap map;
	public PlayerSprite player;
	boolean inMenu = false;
	boolean enterDown = false;
	PauseMenu pausemenu = new PauseMenu();

	public Camera camera;
	public ArrayList<NPC> npcs = new ArrayList<NPC>();
	
	public World(String mapname, String worlddata){
		player = new PlayerSprite(this, 17, 14);
		double time = System.nanoTime();
		
		TiledMapReader tmr = new TiledMapReader();
		map = tmr.read(MapResourceManager.getMap(mapname));
		if(map == null){
			System.err.println("World(): Map returned null");
			System.exit(1);
		} else {
			drawables.add(map);
		}
		loadWorldData(worlddata);
		drawables.add(player);
		drawables.addAll(npcs);
		setCamera(null);
		System.out.println("Camera co-ordinates: "+camera.left+", "+camera.up+", "+camera.width+", "+camera.height);
		System.out.println("World loading complete. Time taken : "+((System.nanoTime()-time)/Game.MILLIS_TO_NANOS)+" ms");
		System.out.println("--------------------------------------------------");
		
	}
	
	private String[] loadWorld(String path) {
		byte[] bytes = FileReader.readBytesFromFile(path);
		try {
			String[] commands = new String(bytes, "UTF-8").split("\n");
			return commands;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
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
	
	private void loadWorldData(String path){
		if(path.isEmpty()){
			return;
		}
		String[] lines = loadWorld(path);
		
		for(int i = 0; i<lines.length; i++){
			if(lines[i].startsWith("#")){
				continue;
			} else {
				String[] commands = lines[i].split(";");
				switch(commands[0].trim()){
				case "player":
					player = new PlayerSprite(this, Integer.parseInt(commands[1].trim()), Integer.parseInt(commands[2].trim()));
					break;
				case "npc":
					//make new npc
					npcs.add(new NPC(
							this, 
							Integer.parseInt(commands[1].trim()),
							Integer.parseInt(commands[2].trim()),
							commands[3].trim(),
							commands[4].trim(),
							commands[5].trim(), commands[6].trim()));
					break;
				default:
					System.out.println("Unknown command: "+lines[i]);
					break;
				}
			}
		}
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
	
	public void loadNPCs(String path){
		
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
			this.camera.centerOn(player.x, player.y);
		} else {
			this.camera = camera;
		}
	}
	
	public void centerCamera(NPC npc) {
		if(camera == null){
			this.camera = new Camera(this, 0, 0, Game.SCREEN_WIDTH/map.tilewidth, Game.SCREEN_HEIGHT/map.tileheight);
		}
		this.camera.centerOn(npc.x, npc.y);
		
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
