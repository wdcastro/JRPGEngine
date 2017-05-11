package tilemap;

public class TiledProperty {
	
	public final String name;
	private final String type;
	private final String value;
	
	public TiledProperty(String name, String type, String value){
		this.name = name;
		this.value = value;
		if(type == null){
			this.type = "string";
		} else {
			this.type = type;
		}
		if(name == "" || type == ""){
			System.err.println("Passed empty string as parameter for TiledProperty");
		}
	}
	
	public Object getValue(){
		switch(type){
		case "string":
			return value;
		case "bool":
			if(value == "true"){
				return true;
			} else {
				return false;
			}
		case "int":
			return Integer.parseInt(value);
		case "float":
			return Float.parseFloat(value);
		case "color":
			return value;
		case "file":
			return value;
		default:
			return null;			
			
		}
	}

}
