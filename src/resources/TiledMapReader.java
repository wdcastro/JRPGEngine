package resources;

import java.util.ArrayList;
import java.util.HashMap;

import parser.Element;
import parser.XMLParser;
import parser.XMLParserException;
import tilemap.TiledTileMap;

public class TiledMapReader {
	
	public TiledTileMap read(String mapname){
		System.out.println("loading map "+mapname);
		XMLParser parser = new XMLParser();
		try {
			ArrayList<Element> document = parser.parse(mapname);
			Element mapelement = null;
			for(int i = 0; i < document.size(); i++){
				if(document.get(i).getName().equals("map")){
					System.out.println("Map found");
					mapelement = document.get(i);
					break;
				}
			}
			
			if(mapelement == null){
				System.err.println("No <map> found in file: "+ mapname);
				System.exit(1);
			} else {
				return createMap(mapelement);
			}
			
			
		} catch (XMLParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	private TiledTileMap createMap(Element mapelement) {
		ArrayList<Element> children = mapelement.getChildren();
		HashMap<String, String> attributes = mapelement.getAttributes();
		TiledTileMap map = new TiledTileMap(Integer.parseInt(attributes.get("width")), Integer.parseInt(attributes.get("height")), Integer.parseInt(attributes.get("tilewidth")), Integer.parseInt(attributes.get("tileheight")));
		
		for(int i = 0; i < children.size(); i++){
			Element currentChild = children.get(i);
			switch(currentChild.getName()){
			case "tileset":
				map.loadTileSet(currentChild);
				break;
			case "layer":
				map.loadTileLayer(currentChild);
				break;
			case "objectgroup":
				map.loadObjectGroup(currentChild);
				break;
			default:
				System.err.println("Unknown tag found in map: "+currentChild.getName());
				break;
			}
		}
		
		return map;
	}

}
