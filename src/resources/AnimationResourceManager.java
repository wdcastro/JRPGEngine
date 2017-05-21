package resources;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Hashtable;

public class AnimationResourceManager {
	public final static String resourceLocation = "res/resourcemanagerfiles/ANIMATIONS.resource";
	static Hashtable<String, String> animations = new Hashtable<String, String>();
	
	public static HashMap<String, Integer[]> getAnimations(String name){
		if(!animations.containsKey(name)){
			return null;
		}
		HashMap<String, Integer[]> animationMap = new HashMap<String, Integer[]>();
		try{
			byte[] bytes = FileReader.readBytesFromFile(animations.get(name));
			String[] lines = new String(bytes, "UTF-8").split("\n");
			for(int i = 0; i<lines.length; i++){
				String currentLine = lines[i].trim();
				System.out.println("spritedata: currentLine: "+currentLine);
				String animname = currentLine.substring(0, currentLine.indexOf("(")).trim();
				String[] values = currentLine.substring(currentLine.indexOf("(")+1, currentLine.indexOf(")")).trim().split(",");
				System.out.println(animname);
				Integer[] frames = new Integer[values.length];
				for(int j = 0; j<values.length; j++){
					frames[j] = Integer.parseInt(values[j]);
					System.out.print(frames[j]);
					System.out.print(",");
				}
				System.out.println();
				animationMap.put(animname, frames);
			}
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		System.out.println(animationMap.keySet());
		return animationMap;
		
	}
	
	public static void loadResourcesFromFile(){
		File file = new File("res/animations");
		File[] dirlist = file.listFiles();
		for(int i = 0; i < dirlist.length; i++){
			animations.put(dirlist[i].getName(), dirlist[i].getPath());
			System.out.println(dirlist[i].getName()+": "+animations.get(dirlist[i].getName()));
		}
	}
}
