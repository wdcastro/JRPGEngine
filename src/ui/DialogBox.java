package ui;

import java.io.File;
import java.util.ArrayDeque;

import core.Game;
import resources.ImageResourceManager;
import resources.StyleSheetResourceManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class DialogBox extends HBox{
	
	ArrayDeque<String> content = new ArrayDeque<String>();
	String currentString = "";
    ImageView portrait = new ImageView();

    VBox portraitBox = new VBox();

	Label textDisplay = new Label();
	Label nameplate = new Label("");
	int index = 0;
	String substring;
	
	float timer = 0;
	float timePerChar = 50;
	
	boolean isShowing = false;
	boolean isTyping = false;
	
	public DialogBox(){
		getStylesheets().add(new File(StyleSheetResourceManager.getStyleSheet("DIALOG_BOX")).toURI().toString());	 
		setMinHeight(Game.SCREEN_HEIGHT*0.25);
		setMinWidth(Game.SCREEN_WIDTH*0.90);
		setLayoutX(Game.SCREEN_WIDTH*0.05);
		setLayoutY(Game.SCREEN_HEIGHT*0.70);
		getStyleClass().add("hidden");
		textDisplay.setText("");
		textDisplay.setTextFill(Color.WHITE);
		textDisplay.setFont(Font.font("Vernada",20));
		textDisplay.toFront();
		portrait.setPreserveRatio(true);
		portrait.setFitHeight(this.getMinHeight());
       
		portraitBox.getChildren().add(nameplate);
		portraitBox.getChildren().add(portrait);
		portraitBox.setMinWidth(this.getMinHeight());
		portraitBox.setMinHeight(this.getMinHeight());
		portraitBox.setAlignment(Pos.CENTER);
        getChildren().add(portraitBox);
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
	
	private void show(){
		textDisplay.setText("");
		index = 0;
		timer = 0;
		isTyping = true;
		isShowing = true;
		getStyleClass().clear();
		getStyleClass().add("dialogbox");
		currentString = content.pop();
		
	}
	
	private void setSpeaker(Image image, String name){
		getChildren().clear();
		if(image != null){
			portrait.setImage(image);
			portraitBox.getChildren().remove(portrait);
			portraitBox.getChildren().add(portrait);
			nameplate.setText(name);
			getChildren().add(portraitBox);
		}
		getChildren().add(textDisplay);
		
	}
	
	private void hide(){
		isShowing = false;
		setSpeaker(null, "");
		getStyleClass().clear();
		getStyleClass().add("hidden");
		
	}
	
	public void setContent(String content){
		this.content.clear();
		this.content.addLast(content);
	}
	
	private void setContent(String[] content){
		this.content.clear();
		for(int i = 0; i<content.length;i++){
			this.content.addLast(content[i]);
		}
	}
	
	public void say(String content, String speaker, String portraitName){
		setSpeaker(ImageResourceManager.getImage(portraitName), speaker);
		setContent(content);
		show();		
	}

	
	public void say(String speaker, String portraitName, String[] content){
		setSpeaker(ImageResourceManager.getImage(portraitName), speaker);
		setContent(content);
		show();		
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

