package world;

import gamecomponents.Game;
import graphics.Drawable;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import parser.Element;

public class InteractableLayer extends Drawable{
	
	
	Element layerElement;
	int[] tilemap;
	int width;
	int height;
	String name;
	ArrayList<TileSet> tilesets;
	ArrayList<Image> tilesetImages;
	World parentWorld;
	String layerType;
	
	public InteractableLayer(Element element, ArrayList<TileSet> tilesets, ArrayList<Image> tilesetImages, World w){
		parentWorld = w;
		this.tilesets = tilesets;
		this.tilesetImages = tilesetImages;
		layerElement = element;
		width = Integer.parseInt(element.getAttributes().get("width"));
		height = Integer.parseInt(element.getAttributes().get("height"));
		name = element.getAttributes().get("name");
		
		//System.out.println(name + ": w: "+width+" h: "+height);
		
		//get parent world
		//tile set
		//tile size
		//tile array
		
		
	}
	
	public void processElement(){ //TODO: add interactable array list
		double startTime = System.nanoTime();
		Element currentElement = null;

		for(int i = 0; i < layerElement.getChildren().size(); i++){
			if(layerElement.getChildren().get(i).getName().equals("data")){
				//System.out.println("Found data tag");
				currentElement = layerElement.getChildren().get(i);
			}
		}
		if(currentElement == null){
			System.err.println("Didn't find data tag");
			System.exit(1);
		}
		tilemap = new int[currentElement.getChildren().size()];
		for(int i = 0; i < currentElement.getChildren().size(); i++){
			//System.out.println("processElement: expected value: "+ currentElement.getChildren().get(i).getAttributes().get("gid"));
			int id = Integer.parseInt(currentElement.getChildren().get(i).getAttributes().get("gid"));
			tilemap[i] = id;
		}
		System.out.println("Tilemap process complete. " + tilemap.length + " entries added. Time taken: " + ((System.nanoTime()- startTime)/ Game.MILLIS_TO_NANOS) + " ms");
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//double startTime = System.nanoTime();
		//int tilesDrawn = 0;
		if(tilemap!=null){

			int firstIndex = parentWorld.up*width+ parentWorld.left;
			for(int i =  firstIndex; i <  parentWorld.down*width+ parentWorld.right; i++){
				if(tilemap[i] == 0){
					continue;
				}

				for (int j = 0; j < tilesets.size(); j++){
					TileSet currentTileset = tilesets.get(j);
					if(tilemap[i] < currentTileset.getTileRange()){
						//System.out.println("using tileset "+j);
						int numOfTilesAcrossOnScreen = Game.SCREEN_WIDTH/currentTileset.tilewidth;						

						
						gc.drawImage(
								tilesetImages.get(j), //image name
								((tilemap[i]-1)%currentTileset.tilesAcrossInSet)*currentTileset.tilewidth, //source x
								((tilemap[i]-1)/currentTileset.tilesAcrossInSet)*currentTileset.tileheight, //source y
								currentTileset.tilewidth, // source width
								currentTileset.tileheight, // source height
								((i-firstIndex)%numOfTilesAcrossOnScreen)*currentTileset.tilewidth, // dest x
								((i-firstIndex)/width)*currentTileset.tileheight, // dest y
								currentTileset.tilewidth, // dest width
								currentTileset.tileheight// dest height
								);
						if((i - firstIndex)%numOfTilesAcrossOnScreen +1 == parentWorld.right){ //
							i+=width-parentWorld.right;

							
						}
						//tilesDrawn++;
						
						break;
					}
				}
				//find which tileset to use
				//find x, y of tile using / and %
				//draw
			}
		} else {
			System.out.println("TileLayer "+name+" has an empty tilemap");
		}
		//System.out.println("Drew "+tilesDrawn+" tiles in "+ ((System.nanoTime()-startTime)/Game.MILLIS_TO_NANOS)+" ms");
	}

}
