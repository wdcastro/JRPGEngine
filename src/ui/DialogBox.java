package ui;

import gamecomponents.Game;

import java.io.File;
import java.util.ArrayDeque;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class DialogBox extends VBox{
	
	ArrayDeque<String> content = new ArrayDeque<String>();
	String currentString;

	Label textDisplay = new Label();
	int index = 0;
	String substring;
	
	float timer = 0;
	float timePerChar = 50;
	
	boolean isShowing = false;
	boolean isTyping = false;
	
	public DialogBox(){
		getStylesheets().add(new File("res/stylesheets/dialogbox.css").toURI().toString());
		setMinHeight(Game.SCREEN_HEIGHT*0.25);
		setMinWidth(Game.SCREEN_WIDTH*0.90);
		setSpacing(10);
		setLayoutX(Game.SCREEN_WIDTH*0.05);
		setLayoutY(Game.SCREEN_HEIGHT*0.70);
		getStyleClass().add("hidden");
		textDisplay.setText("");
		textDisplay.setTextFill(Color.WHITE);
		textDisplay.setFont(Font.font("Vernada",20));
		textDisplay.toFront();
        getChildren().add(textDisplay);
        setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				if(isTyping){
					skip();
				} else {
					//TODO: need a way to go to next line of dialogue for multiple lines
					if(!content.isEmpty()){
						show();
					} else {
						hide();
					}
				}
			}

			
        	
        });
	}
	
	private void skip(){
		textDisplay.setText(currentString);
		index = currentString.length();
		isTyping = false;
	}
	
	public void show(){ 
		index = 0;
		timer = 0;
		isTyping = true;
		isShowing = true;
		getStyleClass().clear();
		getStyleClass().add("dialogbox");
		currentString = content.pop();
		
	}
	
	public void hide(){
		getStyleClass().clear();
		getStyleClass().add("hidden");
	}
	
	public void setContent(String content){
		this.content.clear();
		this.content.addLast(content);
	}
	
	public void setContent(String[] content){
		this.content.clear();
		for(int i = 0; i<content.length;i++){
			this.content.addLast(content[i]);
		}
	}

	public void update(){
		if(isTyping){
		timer += Game.delta_time/Game.MILLIS_TO_NANOS;
		}
		
		if(index <= currentString.length() && timer >= timePerChar){
			substring = currentString.substring(0, index);
			textDisplay.setText(substring);
			index++;
			timer = 0;
		}
		
		if(index > currentString.length()){
			isTyping = false;
		}
	}
	
	public boolean isShowing(){
		return isShowing;
	}
	
	public boolean isTyping(){
		return isTyping;
	}
	
}

