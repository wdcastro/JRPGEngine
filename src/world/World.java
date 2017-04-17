package world;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;
import parser.Element;
import resources.ImageResourceManager;
import resources.MapFileResourceManager;
import graphics.Drawable;
import graphics.Screen;

public class World extends Screen{
	
	//CameraFrame cameraFrame = new CameraFrame();
	volatile int left = 0 ;
	volatile int right = 20;
	volatile int up = 0;
	volatile int down = 12;
	//characters and units on world
	
	public World(){
		ArrayList<Element> mapElements = MapFileResourceManager.getCity("Test City").get(0).getChildren();
		ArrayList<TileSet> tilesets = new ArrayList<TileSet>();
		ArrayList<Image> tilesetImages = new ArrayList<Image>();
		for(int i =0; i < mapElements.size(); i++){
			Element currentElement = mapElements.get(i);
			switch(currentElement.getName()){
			case "tileset":
				//System.out.println("Processing tileset");
				TileSet tileset = new TileSet(Integer.parseInt(currentElement.getAttributes().get("firstgid")),
						currentElement.getAttributes().get("name"),
						Integer.parseInt(currentElement.getAttributes().get("tilewidth")),
						Integer.parseInt(currentElement.getAttributes().get("tileheight")),
						Integer.parseInt(currentElement.getAttributes().get("tilecount")),
						Integer.parseInt(currentElement.getAttributes().get("columns")),
						currentElement.getChildren().get(0).getAttributes().get("source"),
						Integer.parseInt(currentElement.getChildren().get(0).getAttributes().get("width")),
						Integer.parseInt(currentElement.getChildren().get(0).getAttributes().get("height")));
				tilesets.add(tileset);
				tilesetImages.add(new Image(new File("res/mapdata/"+tileset.imagename).toURI().toString()));
				break;
			case "layer":
				String layerType = "TERRAIN";
				for(int j = 0; j < currentElement.getChildren().size(); j++){
					if(currentElement.getChildren().get(j).getName().equals("properties")){
						//System.out.println("Found properties tag");
						layerType = currentElement.getChildren().get(j).getChildren().get(0).getAttributes().get("value");
					}
				}
				if(layerType.equals("INTERACTABLE")){
					ArrayList<Element> properties = currentElement.getChildren();
					//for(int i = 0; i < properties.size(); i++){
					//	for later when you have multiple properties on one layer
					//}
					//layerType = properties.get(0).getAttributes().get("value");
					System.out.println("Interactable layer found");

					InteractableLayer layer = new InteractableLayer(mapElements.get(i), tilesets, tilesetImages, this);
					layer.processElement();
					drawables.add(layer);
				} else {
					if(tilesets == null){
						System.err.println("No tilesets found when rendering map.");
						break;
					}
					TileLayer layer = new TileLayer(mapElements.get(i), tilesets, tilesetImages, this);
					layer.processElement();
					drawables.add(layer);
				}
				break;
			default: 
				System.out.println("Unknown tag in map: " +  mapElements.get(i).getName());
			}
		}
	}
	
	public void update(){
		
	}

	
}
