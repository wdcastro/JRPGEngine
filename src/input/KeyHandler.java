package input;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent>{
	
private ArrayList<String> keysDown = new ArrayList<String>();
	
	public void handleKeyPress(KeyEvent e){
		String key = e.getCode().toString();
		if(!keysDown.contains(key)){
			keysDown.add(key);
		}
	}
	
	public void handleKeyRelease(KeyEvent e){
		String key = e.getCode().toString();
		if(keysDown.contains(key)){
			keysDown.remove(key);
		}
	}

	@Override
	public void handle(KeyEvent e) {
		switch(e.getEventType().toString()){
		case "KEY_PRESSED":
			handleKeyPress(e);
			break;
		case "KEY_RELEASED":
			handleKeyRelease(e);
			break;
		default:
			break;
		}
		
	}
	
	public boolean isKeyDown(String key){
		return keysDown.contains(key);
	}
	
}
