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
			Tile tile = new Tile(Integer.parseInt(attributes.get("id"))+1, "tileset", tilesetIndex);
			//DEAR WILLIAM
			//THE TILE IDS ARE BEING PARSED INCORRECTLY
			//SO WHEN YOU LOAD THE THING IN TILE LAYER TO GET TILES
			//THE CORRECT ONES ARENT BEING RETURNED
			//TILED IS SCREWING WITH ME
			//			[209, 50, 211, 227, 52, 126, 127]
			//			[208, 49, 210, 226, 51, 125, 126]
			//you got gid and id wrong, id starts at 0, gid starts at 1
			for(int i = 0; i < children.size(); i++){
				Element currentElement = children.get(i);
				switch(currentElement.getName()){
				case "objectgroup":

					System.out.println("TiledTileSet: loading objectgroups");
					tile.loadObjectGroup(currentElement);

					tiles.put(tile.gid, tile);
					break;
				case "animation":

					System.out.println("TiledTileSet: loading animations");
					tile.loadAnimations(currentElement);

					tiles.put(tile.gid, tile);
					break;
				case "properties":

					System.out.println("TiledTileSet: loading properties");
					tile.loadProperties(currentElement);
					tiles.put(tile.gid, tile);
					break;
				default:
					System.err.println("loading unknown tag in TiledTileSet for Tile: "+currentElement.getName());					
				}
			}
		}
		
	}

}
