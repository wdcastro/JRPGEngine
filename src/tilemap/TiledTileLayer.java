package tilemap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import parser.Element;
import resources.ImageResourceManager;
import world.Camera;

public class TiledTileLayer{

	String name;
	int width;
	int height;
	float offsetx;
	float offsety;
	ArrayList<TiledProperty> properties = new ArrayList<TiledProperty>();
	
	ArrayList<Tile> data = new ArrayList<Tile>();
	
	public TiledTileLayer(String name, int width, int height, float offsetx, float offsety){
		this.name = name;
		this.width = width;
		this.height = height;
		this.offsetx = offsetx;
		this.offsety = offsety;
		if(width == 0 || height == 0){
			System.err.println("0 passed as paramter in TiledTileLayer: " + name);
		}
	}
	
	public void loadProperties(Element element){
		ArrayList<Element> children = element.getChildren();
		for(int i = 0; i < children.size(); i++){
			Element currentElement = children.get(i);
			if(!currentElement.getName().equals("property")){
				System.err.println("Trying to load a tag that isn't a properties in Tile: "+currentElement.getName());
			}
			HashMap<String, String> attributes = currentElement.getAttributes();
			TiledProperty property = new TiledProperty(attributes.get("name"), attributes.get("type"), attributes.get("value"));
			properties.add(property);
		}		
	}

	public void loadData(Element element, ArrayList<TiledTileSet> tilesets) {
		ArrayList<Element> children = element.getChildren();	
	

		System.out.println(tilesets.get(0).tiles.keySet());
		for(int i = 0; i < children.size(); i++){
			int gid = Integer.parseInt(children.get(i).getAttributes().get("gid"));

			for(int k = 0; k<tilesets.size();k++){
				if(tilesets.get(k).tiles.keySet().contains(gid)){
					System.out.println("added tile "+gid);
					data.add(tilesets.get(k).tiles.get(gid));
				} else {					
					data.add(new Tile(gid, "data", 0));	
				}
			}
		}
		
	}
	
	public void update(){
		for(int i = 0; i<data.size(); i++){
			data.get(i).update();
		}
	}
	
	public void draw(ArrayList<TiledTileSet> tilesets, GraphicsContext gc, Camera camera, TiledTileMap map){// camera

		for(int i = 0; i<data.size(); i++){

			Tile currentTile = data.get(i);
			if(currentTile.gid == -1){
				continue;
			}
			TiledTileSet currentTileset = tilesets.get(currentTile.tilesetIndex);
			if(i%map.width >= (camera.left+camera.width)){
				continue;
			}
			if(i%map.width < camera.left){
				continue;
			}
			if(i/map.width < camera.up){
				continue;
			}
			if(i/map.width > (camera.up+camera.height)){
				break;
			}
			gc.drawImage(
					tilesets.get(currentTile.tilesetIndex).image,
					(currentTile.gid % currentTileset.numTilesAcross) * currentTileset.tilewidth,
					(currentTile.gid/currentTileset.numTilesAcross) * currentTileset.tileheight,
					currentTileset.tilewidth,
					currentTileset.tileheight,
					((i-camera.left)%camera.width)*currentTileset.tilewidth,
					((i/map.width)-camera.up)*currentTileset.tileheight,
					currentTileset.tilewidth,
					currentTileset.tileheight
					);
		}
	}

}
