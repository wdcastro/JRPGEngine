package screens;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import core.Game;
import core.PlayerData;
import javafx.animation.AnimationTimer;
import javafx.scene.input.MouseEvent;
import resources.FileReader;
import resources.InventoryResourceManager;
import world.Camera;
import world.NPC;
import gamecomponents.GameLogic;

public class Cutscene extends Screen{
	
	World world = null;
	Camera camera = null;
	HashMap<String, NPC> npcs = new HashMap<String, NPC>();
	ArrayList<String> script = new ArrayList<String>();
	int currentMarker = 0;
	long startDelay = 3000;
	long defaultForwardDelay = 1000;
	String currentAction = "";
	boolean isPlaying = false;
	
	public Cutscene(String path){
		loadCutscene(path);

	}
	
	public void loadCutscene(String path){
		String[] lines = parseCutsceneFile(path);

		
		for(int i = 0; i<lines.length; i++){
			if(lines[i].startsWith("#")){
				continue;
			} else {
				String[] commands = lines[i].split(";");
				
				switch(commands[0].trim()){
				case "map":
					world = new World(commands[1].trim(), "");
					break;
				case "camera":
					if(world == null){
						System.err.println("Failed to load cutscene: "+path);
						System.err.println("Camera is created before world is created");
						System.exit(1);
					}
					if(commands[1].trim().equals("default")){
						world.setCamera(null);
						continue;
					} else if (commands[1].trim().equals("center")){
						if(commands[2].trim().equals("player")){
							world.setCamera(null);
						} else {
							world.centerCamera(npcs.get(commands[2].trim()));
						}
					} else {
						camera = new Camera(
								world,
								Integer.parseInt(commands[1].trim()),
								Integer.parseInt(commands[2].trim()),
								Integer.parseInt(commands[3].trim()),
								Integer.parseInt(commands[4].trim())
								);
						world.setCamera(camera);
					}
					break;
				case "npc":
					//make new npc
					npcs.put(commands[7].trim(), new NPC(
							world, 
							Integer.parseInt(commands[1].trim()),
							Integer.parseInt(commands[2].trim()),
							commands[3].trim(),
							commands[4].trim(),
							commands[5].trim(), commands[6].trim()));
					break;
				default:
					String line = lines[i].trim();
					if(!line.isEmpty()){
						script.add(line);
						//System.out.println(lines[i].trim());
					}
					break;
				}
			}
		}
		world.loadNPCs(new ArrayList<NPC>(npcs.values()));
		world.player.setControllable(false);
		this.drawables.addAll(world.getDrawables());
		//System.out.println("-----------------SCRIPT----------------");
		//System.out.println(script);
		//System.out.println("----------------END SCRIPT---------------");
	}
	
	public String[] parseCutsceneFile(String path){
		byte[] bytes = FileReader.readBytesFromFile(path);
		try {
			String[] commands = new String(bytes, "UTF-8").split("\n");
			return commands;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void play(){
		//loop script
		//process, animate, add to inventory, show dialogue
		//forward();
		new AnimationTimer(){
			long time = System.nanoTime();
			@Override
			public void handle(long now) {
				if(now - time >= startDelay*Game.MILLIS_TO_NANOS){
					isPlaying = true;
					processLine();
					this.stop();
				}
			}
		}.start();
	}
	
	public void processLine(){
		String line = script.get(currentMarker);
		String commands[] = line.split(";");
		currentAction = commands[0].trim();
		switch(commands[0].trim()){
		case "action":
			if(commands[1].trim().equals("player")){
				world.player.doAction(commands[2].trim());
			} else {
				npcs.get(commands[1].trim()).doAction(commands[2].trim());
			}
			if(commands.length == 4){
				startForwardTimer(Long.parseLong(commands[3].trim()));
			} else {
				startForwardTimer(defaultForwardDelay);
			}
			break;
		case "item":
			InventoryResourceManager.addToInventory(commands[1].trim(), Integer.parseInt(commands[2].trim()));
			break;
		case "dialog":
			if(commands.length == 4){
				Game.dialogbox.say(commands[2].trim(), npcs.get(commands[1].trim()).getName(), commands[3].trim());
			} else {
				Game.dialogbox.say(commands[2].trim(), npcs.get(commands[1].trim()).getName(), "");
			}			
			break;
		case "flag":
			if(PlayerData.flags.containsKey(commands[1].trim())){
				PlayerData.flags.put(commands[1].trim(), true);
				System.out.println("Flag set: "+commands[1].trim());
			} else {
				System.err.println("Cutscene: processLine: No flag found: "+commands[1].trim());
			}
			startForwardTimer(0);
			break;
		case "moveCamera":
			if(commands.length == 2){
				moveCamera(commands[1].trim());
			} else {
				moveCamera(
						Integer.parseInt(commands[1].trim()),
						Integer.parseInt(commands[2].trim()),
						Integer.parseInt(commands[3].trim()),
						Integer.parseInt(commands[4].trim()));
			}
			if(commands.length == 3){
				startForwardTimer(Long.parseLong(commands[2].trim()));
			} else {
				startForwardTimer(defaultForwardDelay);
			}
		default:
			System.out.println(line);
		}
	}
	
	public void forward(){
		if(currentMarker>=script.size()-1){
			System.out.println("End of cutscene");
			isPlaying = false;
			GameLogic.cutsceneFinishedCallback();
		} else {
			currentMarker++;
			processLine();
		}
	}

	@Override
	public String getScreenType() {
		return "CUTSCENE";
	}

	@Override
	public void update() {
		world.update();
	}

	@Override
	public void handleMousePress(MouseEvent e) {
		if(isPlaying && !Game.dialogbox.isTyping() && (currentAction.equals("item") || currentAction.equals("dialog"))){
			forward();
		}
	}
	
	public void moveCamera(String name){
		if(name.equals("player")){
			world.setCamera(null);
		} else {
			world.centerCamera(npcs.get(name));
		}
	}
	
	public void moveCamera(int up, int left, int width, int height){
		camera = new Camera(world, up, left, width, height);
		world.setCamera(camera);	
	}
	
	public void startForwardTimer(long delay){
		new AnimationTimer(){
			
			long time = System.nanoTime();

			@Override
			public void handle(long now) {
				if(now-time > delay*Game.MILLIS_TO_NANOS){
					forward();
					this.stop();	
				}
			}
			
		}.start();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
