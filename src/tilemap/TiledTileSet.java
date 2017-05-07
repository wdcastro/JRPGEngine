package tilemap;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;
import parser.Element;
import resources.ImageResourceManager;

public class TiledTileSet {
	
	int firstgid;
	String name;
	int tilewidth;
	int tileheight;
	int tilecount;
	int columns;
	Image image;
	int tilesetIndex;
	int numTilesAcross;
	
	HashMap<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	
	
	public TiledTileSet(int firstgid, String name, int tilewidth, int tileheight, int tilecount, int columns, int tilesetIndex){
		this.firstgid = firstgid;
		this.name = name;
		this.tilewidth = tilewidth;
		this.tileheight = tileheight;
		this.tilecount = tilecount;
		this.columns = columns;
		this.tilesetIndex = tilesetIndex;
		
	}

	
	public void loadImage(Element element){
		HashMap<String, String> attributes = element.getAttributes();
		this.image = ImageResourceManager.getImageFromFile("res/mapdata/"+attributes.get("source"));
		numTilesAcross = Integer.parseInt(attributes.get("width"))/tilewidth;
	}
	
	public void loadTile(Element element){
		HashMap<String, String> attributes = element.getAttributes();
		ArrayList<Element> children = element.getChildren();
		if(
			attributes.keySet().size() == 2 &&
			attributes.keySet().contains("terrain") &&
			attributes.keySet().contains("id") &&
			children.isEmpty()
		){
			return;
		} else {
			Tile tile = new Tile(Integer.parseInt(attributes.get("id")), "tileset", tilesetIndex);
			for(int i = 0; i < children.size(); i++){
				Element currentElement = children.get(i);
				switch(currentElement.getName()){
				case "objectgroup":
					tile.loadObjectGroup(currentElement);
					tile.setHasObject(true);
					break;
				case "animation":
					tile.loadAnimations(currentElement);
					tile.setAnimatedTile(true);
					break;
				case "properties":
					tile.loadProperties(currentElement);
					break;
				default:
					System.err.println("loading unknown tag in TiledTileSet for Tile: "+currentElement.getName());					
				}
				tiles.put(tile.id, tile);
			}
		}
		
	}

}
