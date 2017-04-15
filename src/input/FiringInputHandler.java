package input;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FiringInputHandler implements EventHandler<InputEvent>{
	
	boolean MOUSE_PRIMARY_DOWN = false;
	boolean MOUSE_SECONDARY_DOWN = false;
	boolean MOUSE_MIDDLE_DOWN = false;
	ArrayList<Character> keysDown = new ArrayList<Character>();
	
	public void handleKeyPress(KeyEvent e){
		char key = e.getCode().toString().toLowerCase().charAt(0);
		if(!keysDown.contains(key)){
			keysDown.add(key);
		}
		//System.out.println(e.getCode().toString().toLowerCase()+ " PRESSED");
		System.out.println(keysDown);
	}
	
	public void handleKeyRelease(KeyEvent e){
		char key = e.getCode().toString().toLowerCase().charAt(0);
		if(keysDown.contains(key)){
			keysDown.remove(Character.valueOf(key));
		}
		
		//System.out.println(e.getCode().toString().toLowerCase() + " RELEASED");
		System.out.println(keysDown);
	}
	
	public void handleMousePress(MouseEvent e){
		if(e.getButton().toString() == "PRIMARY"){
			MOUSE_PRIMARY_DOWN = true;
		} else if (e.getButton().toString() == "SECONDARY"){
			MOUSE_SECONDARY_DOWN = true;
		} else if (e.getButton().toString() == "MIDDLE"){
			MOUSE_MIDDLE_DOWN = true;
		}
		System.out.println(e.getButton() + " PRESSED");
	}
	
	public void handleMouseRelease(MouseEvent e){
		if(e.getButton().toString() == "PRIMARY"){
			MOUSE_PRIMARY_DOWN = false;
		} else if (e.getButton().toString() == "SECONDARY"){
			MOUSE_SECONDARY_DOWN = false;
		} else if (e.getButton().toString() == "MIDDLE"){
			MOUSE_MIDDLE_DOWN = false;
		}
		System.out.println(e.getButton() + " RELEASED");
	}
	
	@Override
	public void handle(InputEvent e) {		
		switch(e.getEventType().toString()){
		case "KEY_PRESSED":
			handleKeyPress((KeyEvent) e);
			break;
		case "KEY_RELEASED":
			handleKeyRelease((KeyEvent) e);
			break;
		case "MOUSE_PRESSED":
			handleMousePress((MouseEvent) e);
			break;
		case "MOUSE_RELEASED":
			handleMouseRelease((MouseEvent) e);
			break;
		default:
			break;
		}
	}
	
	public boolean isKeyDown(char c){
		return keysDown.contains(Character.valueOf(c));
		
	}
}
