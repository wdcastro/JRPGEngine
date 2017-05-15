package audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BGMPlayer extends Thread{
	
	static MediaPlayer mediaplayer = null;
	
	public static void setSong(Media media){
		mediaplayer = new MediaPlayer(media);
		mediaplayer.setAutoPlay(true);
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

}
