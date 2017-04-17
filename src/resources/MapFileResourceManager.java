package resources;

import java.util.ArrayList;
import java.util.HashMap;

import parser.Element;
import parser.XMLParser;
import parser.XMLParserException;

public class MapFileResourceManager {
	static HashMap<String, String> mapFiles = new HashMap<String, String>();
	
	public static void loadResourcesFromFile(){
		mapFiles.put("Test City", "res/mapdata/test city.tmx");
	}
	
	public static ArrayList<Element> getCity(String name){
		XMLParser xmlparser = new XMLParser();
		try {
			return xmlparser.parse(mapFiles.get(name));
		} catch (XMLParserException e) {
			e.printStackTrace();
		}
		System.err.println("error when getting city");
		return null;
		
	}
}
