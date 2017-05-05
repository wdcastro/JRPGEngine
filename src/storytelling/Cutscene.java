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
		World w = new World("TEST_CITY");
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		for(int i = 0; i<3; i++){// keep track of which npc is which with index
			npcs.add(new NPC(w, i, i, "Hello", "HELLOOOO", "CHIBI_DRAGOON"));
		}
		w.loadNPCs(npcs);
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
