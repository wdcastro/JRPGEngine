package storytelling;

import java.util.ArrayDeque;

import graphics.Screen;

public class Cutscene extends Screen{
//contains lines, animations, input, choices?, tbd, cg?
	//do it megaman style, have its own inputhandler, controller and dialog box
	ArrayDeque<String> lines = new ArrayDeque<String>();
	
	public Cutscene(){
		lines.addLast("abc");
		lines.addLast("def");
		lines.addLast("ghi");
	}
	
	public void play(){
		
	}
}
