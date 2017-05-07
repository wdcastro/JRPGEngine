package gamecomponents;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerData {
//inventory, party, etc
	HashMap<String, Integer> inventory = new HashMap<String, Integer>();
	HashMap<String, Boolean> flags = new HashMap<String, Boolean>();
	ArrayList<Character> party = new ArrayList<Character>();
}
