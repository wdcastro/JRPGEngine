package audio;

import java.util.ArrayList;

import resources.AudioResourceManager;

public class PlaylistManager {
	static ArrayList<String> playlist = new ArrayList<String>();
	static int currentSong = 0;
	static boolean isLooping = true;
	
	public static void loadPlaylist(ArrayList<String> newPlaylist){
		//TODO: load from file
		playlist.clear();
		playlist.addAll(newPlaylist);
		currentSong = 0;
		isLooping = true;
		BGMPlayer.setSong(AudioResourceManager.getSong(playlist.get(currentSong)));
	}
	
	public static void nextSong(){
		if(currentSong >= playlist.size()){
			if(isLooping){
				currentSong = 0;
			} else {
				BGMPlayer.stopMusic();
				return;
			}
		} else {
			currentSong++;
		}
		BGMPlayer.setSong(AudioResourceManager.getSong(playlist.get(currentSong)));
	}
}
