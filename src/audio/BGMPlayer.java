package audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BGMPlayer extends Thread{
	
	static MediaPlayer mediaplayer = null;
	static double volume = 1.0;
	
	public static void setSong(Media media){
		if(mediaplayer!=null){
			mediaplayer.stop();
		}
		mediaplayer = new MediaPlayer(media);
		mediaplayer.setAutoPlay(true);
		mediaplayer.setVolume(volume);
		System.out.println("Playing song, volume is "+volume);
		mediaplayer.setOnEndOfMedia(new Runnable(){

			@Override
			public void run() {
				PlaylistManager.nextSong();				
			}
		});
	}
	
	public static void playMusic(){
		if(mediaplayer!= null){
			mediaplayer.play();
		} else {
			System.err.println("BGMPlayer: playMusic: mediaplayer is null");
		}
	}
	
	public static void stopMusic(){
		if(mediaplayer!= null){
			mediaplayer.stop();
		} else {
			System.err.println("BGMPlayer: stopMusic: mediaplayer is null");
		}
	}
	
	public static void pauseMusic(){
		if(mediaplayer!= null){
			mediaplayer.pause();
		} else {
			System.err.println("BGMPlayer: pauseMusic: mediaplayer is null");
		}
	}
	
	public static void setVolume(double v){
		volume = v;
		System.out.println("Set volume to "+volume);
		if(mediaplayer!=null){
			mediaplayer.setVolume(volume);
		}
	}

}
