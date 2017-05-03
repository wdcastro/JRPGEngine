package tilemap;

public class TiledObject {
	
	int id;
	String name;
	String type;
	int x;
	int y;
	int width;
	int height;
	
	public TiledObject(int id, String name, String type, int x, int y, int width, int height){
		this.id = id;
		this.name = name;
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		if(width == 0 || height == 0){
			System.err.println("0 found for width or height in TiledObject with id: " + id + " and name: " + name);
		}
	}

}
