package tilemap;

import java.util.ArrayList;
import java.util.HashMap;

import parser.Element;

public class TiledObjectGroup {
	ArrayList<TiledProperty> properties = new ArrayList<TiledProperty>();
	ArrayList<TiledObject> objects = new ArrayList<TiledObject>();
	
	String name = "";
	String draworder = "";
	float offsetx = 0;
	float offsety = 0;
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDrawOrder(String draworder){
		this.draworder = draworder;
	}
	
	public void setOffsetX(float offsetx){
		this.offsetx = offsetx;
	}
	
	public void setOffsetY(float offsety){
		this.offsety = offsety;
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

	public void loadObject(Element element) {
		HashMap<String, String> attributes = element.getAttributes();
		TiledObject object = new TiledObject(
				Integer.parseInt(attributes.get("id")),
				attributes.get("name"),
				attributes.get("type"),
				Integer.parseInt(attributes.get("x")),
				Integer.parseInt(attributes.get("y")),
				Integer.parseInt(attributes.get("width")),
				Integer.parseInt(attributes.get("height"))
				);
		objects.add(object);
		
	}
	
	
}
