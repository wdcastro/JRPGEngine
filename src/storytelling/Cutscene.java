package storytelling;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.scene.input.MouseEvent;
import resources.FileReader;
import world.Camera;
import world.NPC;
import world.World;
import gamecomponents.Game;
import graphics.Screen;
import graphics.SpriteInfo;

public class Cutscene extends Screen{
	
	World world = null;
	Camera camera = null;
	HashMap<String, NPC> npcs = new HashMap<String, NPC>();
	ArrayList<String> script = new ArrayList<String>();
	
	public Cutscene(String filename){
		//load from file
		//create cast ie npc list
		String[] lines = loadCutscene(filename);

		
		for(int i = 0; i<lines.length; i++){
			if(lines[i].startsWith("#")){
				continue;
			} else {
				String[] commands = lines[i].split(";");
				switch(commands[0].trim()){
				case "map":
					world = new World(commands[1].trim());
					break;
				case "camera":
					if(world == null){
						System.err.println("Failed to load cutscene: "+filename);
						System.err.println("Camera is created before world is created");
						System.exit(1);
					}
					if(commands[1].trim().equals("default")){
						world.setCamera(null);
						continue;
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
					npcs.put(commands[6].trim(), new NPC(
							world, 
							Integer.parseInt(commands[1].trim()),
							Integer.parseInt(commands[2].trim()),
							commands[3].trim(),
							commands[4].trim(),
							commands[5].trim()));
					break;
				case "item":
					break;
				case "action":
					break;
				default:
					String line = lines[i].trim();
					if(!line.isEmpty()){
						script.add(line);
						System.out.println(lines[i].trim());
					}
					break;
					
				}
			}
		}
		world.loadNPCs(new ArrayList<NPC>(npcs.values()));
		world.player.setControllable(false);
		this.drawables.addAll(world.getDrawables());
		
		new AnimationTimer(){
			
			long lastTime = 0;
			int moveCount = 0;
			boolean direction = true;

			@Override
			public void handle(long now) {
				if((now - lastTime)/Game.MILLIS_TO_NANOS >= 500){
					lastTime = now;
					if(moveCount < 3){
						if(direction){
							if(!world.isCollidableAt(npcs.get("elder1").x, npcs.get("elder1").y-1)){
								npcs.get("elder1").moveUp();
								moveCount++;
							}
						} else {
							if(!world.isCollidableAt(npcs.get("elder1").x, npcs.get("elder1").y+1)){
								npcs.get("elder1").moveDown();
								moveCount++;
							}
						}
					}
					if(moveCount >= 3){
						moveCount++;
					}
					if(moveCount == 6){
						direction = !direction;
						moveCount = 0;
						world.player.setControllable(true);
					}
				}
			}
			
		}.start();
		//create world
		//load cast into world
		//animate etc etc etc
	}
	
	public String[] loadCutscene(String filename){
		byte[] bytes = FileReader.readBytesFromFile(filename);
		try {
			String[] commands = new String(bytes, "UTF-8").split("\n");
			return commands;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void play(){
		//add to drawables
		//animate with timer
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
		//forward();
		
	}
}
