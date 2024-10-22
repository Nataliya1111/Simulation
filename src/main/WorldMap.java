package main;

import java.util.HashMap;
import entity.*;

public class WorldMap {
	
	private final int width;
	private final int height;
	private final int quantityOfCells;
	private final static int DEFAULT_WIDTH = 10;
	private final static int DEFAULT_HEIGHT = 10;
	private HashMap<Coordinates, Entity> entities = new HashMap<>();	
	
	public WorldMap() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);		
	}
	
	public WorldMap(int width, int height) {
		this.width = width;
		this.height = height;
		this.quantityOfCells = width * height;
	}
	
	public int getWidth() {
		return width;
	}	
	public int getHeight() {
		return height;
	}

	public int getQuontityOfCells() {
		return quantityOfCells;
	}
	
//	public HashMap<Coordinates, String> getEntities(){
//		return this.entities;
//	}
	
	public Entity getEntity(Coordinates coordinates) {
		return entities.get(coordinates);
	}
	
	public void setEntity(Coordinates coordinates, Entity entity) {
		entities.put(coordinates, entity);;
	}
	
	public boolean isCellEmty(Coordinates coordinates) {
		if (this.entities.containsKey(coordinates)){
			return false;
		}
		return true;
	}
	



 
}
