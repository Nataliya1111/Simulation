package main;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import entity.*;

public class WorldMap {
	
	private final int width;
	private final int height;
	private final int quantityOfCells;
	public final static int DEFAULT_WIDTH = 15;
	public final static int DEFAULT_HEIGHT = 15;
	private final HashMap<Coordinates, Entity> entities = new HashMap<>();	
	
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

	public int getQuantityOfCells() {
		return quantityOfCells;
	}
	
	public Entity getEntity(Coordinates coordinates) throws EntityNotFoundException{
		if (this.isCellEmpty(coordinates)) {
			throw new EntityNotFoundException("Entity by coordinates is not found");
		}
		return entities.get(coordinates);
	}
	
	public void setEntity(Coordinates coordinates, Entity entity) {
		if (isCoordinatesValid(coordinates)) {
			entities.put(coordinates, entity);
		}		
	}
	
	public Coordinates getCoordinatesByEntity(Entity entity) throws EntityNotFoundException {
		for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
			Entity entryEntity = entry.getValue();			
			if (entryEntity.equals(entity)) {
				return entry.getKey();
			}
		}
        throw new EntityNotFoundException("Coordinates of Entity are not found");
    }
	
	public List<Entity> getListOfEntities(){
		List<Entity> listOfEntities = new ArrayList<>();
		for (Map.Entry<Coordinates, Entity> entry : (entities).entrySet()) {
			listOfEntities.add(entry.getValue());
		}
		return listOfEntities;
	}
	
	public void removeEntity(Entity entity) throws EntityNotFoundException {
		Coordinates entityCoordinates = getCoordinatesByEntity(entity);
		if (this.isCellEmpty(entityCoordinates)) {
			throw new EntityNotFoundException("Entity by coordinates is not found for removing");
		}
		entities.remove(entityCoordinates);
	}
	
	public boolean isCellEmpty(Coordinates coordinates) {
        return !this.entities.containsKey(coordinates);
    }
	
	public boolean isCoordinatesValid(Coordinates coordinates) {
		int x = coordinates.getX();
		int y = coordinates.getY();
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }
	
	public boolean areCoordinatesAvailableForMove(Coordinates coordinates) {
        return isCellEmpty(coordinates) && isCoordinatesValid(coordinates);
    }
 
}
