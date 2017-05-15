package gamecomponents;

import graphics.Screen;

import java.util.ArrayList;

import core.GameStage;
import world.World;
import audio.PlaylistManager;

public class GameLogic {

	GameStage gamestage;
	public GameLogic(GameStage gamestage) {
		this.gamestage = gamestage;
	}

	public void start(){
		ArrayList<String> playlist = new ArrayList<String>();
		playlist.add("JAPONSKI"); // maciej zolnowski
		playlist.add("FIELD_FORCE");// sawsquarenoise
		PlaylistManager.loadPlaylist(playlist);
		
		Screen screen = new World("MANSION");
		gamestage.setGameStage(screen);
	}
}
