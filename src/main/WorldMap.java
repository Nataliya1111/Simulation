package main;

import java.util.HashMap;

public class WorldMap {
	public static final String ANSI_RESET = "\033[0m"; 
	public static final String RED = "\033[0;31m"; 
	public static final String ANSI_BLACK_BACKGROUND = "\033[40m"; 
	public static final String ANSI_RED_BACKGROUND = "\033[41m";   
	public static final String ANSI_GREEN_BACKGROUND = "\033[42m"; 
	public static final String ANSI_YELLOW_BACKGROUND = "\033[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\033[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\033[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\033[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\033[47m";
	
	private final int height;
	private final int width;
	private final static int DEFAULT_HEIGHT = 10;
	private final static int DEFAULT_WIDTH = 10;
	private HashMap<Coordinates, String> entities = new HashMap<>();	
	
	public WorldMap() {
		this(DEFAULT_HEIGHT, DEFAULT_WIDTH);		
	}
	
	public WorldMap(int height, int width) {
		this.height = height;
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
//	public HashMap<Coordinates, String> getEntities(){
//		return this.entities;
//	}
	
	public String getEntity(Coordinates coordinates) {
		return entities.get(coordinates);
	}
	
	public void setEntity(Coordinates coordinates, String str) {
		entities.put(coordinates, str);;
	}
	
	public boolean isCellEmty(Coordinates coordinates) {
		if (this.entities.containsKey(coordinates)){
			return false;
		}
		return true;
	}
 
}
