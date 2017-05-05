package ui;

import gamecomponents.Game;

import java.io.File;
import java.util.ArrayDeque;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class DialogBox extends VBox{
	
	String content;

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
	}
	
	public void show(){ 
		index = 0;
		timer = 0;
		isTyping = true;
		isShowing = true;
		getStyleClass().clear();
		getStyleClass().add("dialogbox");
		
	}
	
	public void hide(){
		getStyleClass().clear();
		getStyleClass().add("hidden");
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public void toggleShow(){
		if(isShowing){
			hide();
		} else {
			show();
		}
	}
	
	public void update(){
		if(isTyping){
		timer += Game.delta_time/Game.MILLIS_TO_NANOS;
		}
		
		if(index <= content.length() && timer >= timePerChar){
			substring = content.substring(0, index);
			textDisplay.setText(substring);
			index++;
			timer = 0;
		}
		
		if(index > content.length()){
			isTyping = false;
		}
	}
	
	public void skip(){
		textDisplay.setText(content);
		index = content.length();
		isTyping = false;
	}
	
	public boolean isShowing(){
		return isShowing;
	}
	
	public boolean isTyping(){
		return isTyping;
	}
	
}

