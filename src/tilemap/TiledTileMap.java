package tilemap;

import graphics.Drawable;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import parser.Element;
import world.Camera;

public class TiledTileMap extends Drawable{
	
	//properties: version, orientation, renderorder, width, height, tilewidth, tileheight, hexsidelength, staggeraxis, staggerindex, backgroundcolor, nextobjectid
	
	//tileset
	
	String version = "1.0.0";
	String orientation = "";
	String renderorder = "";
	public final int width;
	public final int height;
	public final int tilewidth;	
	public final int tileheight;
	Camera camera;
	
	ArrayList<TiledTileLayer> tilelayers = new ArrayList<TiledTileLayer>();
	ArrayList<TiledTileSet> tilesets = new ArrayList<TiledTileSet>();
	ArrayList<TiledObjectGroup> objectgroups = new ArrayList<TiledObjectGroup>();
	
	public TiledTileMap(int width, int height, int tilewidth, int tileheight){
		this.width = width;
		this.height = height;
		this.tilewidth = tilewidth;
		this.tileheight = tileheight;
		if(width == 0 || height == 0 || tilewidth == 0 || tileheight == 0){
			System.err.println("0 passed as parameter in TiledTileMap");
			System.exit(1);
		}
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public void setOrientation(String orientation){
		this.orientation = orientation;
	}
	
	public void setRenderOrder(String renderorder){
		this.renderorder = renderorder;
	}
	
	public void loadTileLayer(Element element){
		HashMap<String, String> attributes = element.getAttributes();
		ArrayList<Element> children = element.getChildren();
		TiledTileLayer layer;
		if(attributes.containsKey("offsetx") && attributes.containsKey("offsety")){
			layer = new TiledTileLayer(
					attributes.get("name"),
					Integer.parseInt(attributes.get("width")),
					Integer.parseInt(attributes.get("height")),
					Integer.parseInt(attributes.get("offsetx")),
					Integer.parseInt(attributes.get("offsety"))
					);
		} else {
			layer = new TiledTileLayer(
					attributes.get("name"),
					Integer.parseInt(attributes.get("width")),
					Integer.parseInt(attributes.get("height")),
					0,
					0
					);
		}
		
		// processing the layer
		// loop the children
		for(int i = 0; i < children.size(); i++){
			Element currentElement = children.get(i);
			switch(currentElement.getName()){
			case "properties":
				layer.loadProperties(currentElement);
				break;
			case "data":
				if(tilesets.isEmpty()){
					System.err.println("TiledTileMap: tried to load TiledTileLayer with empty tilesets");
				}
				layer.loadData(currentElement, tilesets);
				break;
			default:
				System.err.println("Tried to read unknown tag in TiledTileMap while loading TileLayer: "+currentElement.getName());
			}
		}
		tilelayers.add(layer);
		System.out.println("loaded tilelayer");
		
	}
	
	public void loadTileSet(Element element){
		HashMap<String, String> attributes = element.getAttributes();
		ArrayList<Element> children = element.getChildren();
		TiledTileSet set = new TiledTileSet(
				Integer.parseInt(attributes.get("firstgid")),
				attributes.get("name"),
				Integer.parseInt(attributes.get("tilewidth")),
				Integer.parseInt(attributes.get("tileheight")),
				Integer.parseInt(attributes.get("tilecount")),
				Integer.parseInt(attributes.get("columns")),
				tilesets.size()
				);
		for(int i = 0; i<children.size(); i++){
			Element currentElement = children.get(i);
			switch(currentElement.getName()){
			case "image":
				set.loadImage(currentElement);
				break;
			case "tile":
				set.loadTile(currentElement);
				break;
			default:
				System.err.println("Tried to load unknown tag in TiledTileMap while loading TileSet: "+currentElement.getName());
				
			}
			
		}
	//loop the children
	//set.loadImage();
	//set.loadTileInfo()?
		tilesets.add(set);
		System.out.println("loaded tileset");
	}
	
	public void loadObjectGroup(Element element){
		HashMap<String, String> attributes = element.getAttributes();
		ArrayList<Element> children = element.getChildren();

		TiledObjectGroup group = new TiledObjectGroup();
		if(attributes.containsKey("name")){
			group.setName(attributes.get("name"));
		}
		
		if (attributes.containsKey("draworder")){
			group.setName(attributes.get("draworder"));
		}
		
		if (attributes.containsKey("offsetx")){
			group.setOffsetX(Integer.parseInt(attributes.get("offsetx")));
		}
		
		if (attributes.containsKey("offsety")){
			group.setOffsetX(Integer.parseInt(attributes.get("offsety")));
		}
		// loop children add objects
		for(int i = 0; i<children.size(); i++){
			Element currentElement = children.get(i);
			switch(currentElement.getName()){
			case "properties":
				group.loadProperties(currentElement);
				break;
			case "object":
				group.loadObject(currentElement);
				break;
			default:
				System.err.println("Tried to load unknown tag while loading TiledTileMap for TileObject: "+currentElement.getName());
				
			}
		}
		
		objectgroups.add(group);
		System.out.println("loaded objectgroup");
	}
	
	public void update(Camera camera){
		this.camera = camera;
		for(int i = 0; i<tilelayers.size(); i++){
			tilelayers.get(i).update();
		}
	}
	
	@Override
	public void draw(GraphicsContext gc){//camera	
		for(int i = 0; i<tilelayers.size(); i++){
			tilelayers.get(i).draw(tilesets, gc, camera, this);
		}
	}


	

}
