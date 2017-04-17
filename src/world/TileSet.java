package world;

public class TileSet {
	public final int firstgid;
	public final String name;
	public final int tilewidth;
	public final int tileheight;
	public final int tilecount;
	public final int columns;
	public final String imagename;
	public final int imageWidth;
	public final int imageHeight;
	
	public final int tilesAcrossInSet;
	public final int tilesDownInSet;
	
	public TileSet(int firstgid, String name, int tilewidth, int tileheight, int tilecount, int columns, String imagename, int imageWidth, int imageHeight){
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.firstgid = firstgid;
		this.name = name;
		this.tilewidth = tilewidth;
		this.tileheight = tileheight;
		this.tilecount = tilecount;
		this.columns = columns;
		this.imagename = imagename;
		tilesAcrossInSet = imageWidth/tilewidth;
		tilesDownInSet = imageHeight/tileheight;
		System.out.println("Tile Set "+ name+" loaded");
	}
	
	public int[] getTile(int gid){
		return null;
	}
	
	public int getTileRange(){
		return firstgid+tilecount;
	}

}
