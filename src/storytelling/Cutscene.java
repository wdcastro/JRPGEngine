package storytelling;


import java.util.ArrayList;

import world.NPC;
import world.World;
import graphics.Screen;
import graphics.SpriteInfo;

public class Cutscene extends Screen{
//contains lines, animations, input, choices?, tbd, cg?
	//do it megaman style, have its own inputhandler, controller and dialog box

	
	public Cutscene(String filename){
		//load from file
		//create cast ie npc list
		World w = new World();
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		for(int i = 0; i<3; i++){
			npcs.add(new NPC(w,i,i,"Hello","HELLOOOO",new SpriteInfo("CHIBI_DRAGOON", 0,0,64,64)));
		}
		//create world
		//load cast into world
		//animate etc etc etc
	}
	
	public void play(){
		//add to drawables
		//animate with timer
	}

	@Override
	public String getScreenType() {
		return "CUTSCENE";
	}
}
