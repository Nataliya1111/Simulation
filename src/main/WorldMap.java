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
	private final static int DEFAULT_WIDTH = 10;
	private final static int DEFAULT_HEIGHT = 10;
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

	public int getQuontityOfCells() {
		return quantityOfCells;
	}
	
//	public HashMap<Coordinates,Entity> getEntities(){ //remove
//		return this.entities;
//	}
	
	public Entity getEntityByCoordinates(Coordinates coordinates) throws EntityNotFoundException{
		if (this.isCellEmty(coordinates)) {
			throw new EntityNotFoundException("Entity by coordinates is not found");
		}
		return entities.get(coordinates);
	}
	
	public void setEntity(Coordinates coordinates, Entity entity) {
		if (areCoordinatesValid(coordinates)) {
			entities.put(coordinates, entity);
		}		
	}
	
	public Coordinates getCoordinatesByEntity(Entity entity) throws EntityNotFoundException {
		Coordinates coordinates = null;
		for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {			
			Entity entryEntity = entry.getValue();			
			if (entryEntity.equals(entity)) {
				coordinates = entry.getKey();				
				break;
			}
		}
		if (coordinates==null) {
			throw new EntityNotFoundException("Coordinates of Entity are not found");
		}
		return coordinates;
	}
	
	private List<Entity> getListOfEntities(){
		List<Entity> Entities = new ArrayList<>();
		for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
			Entities.add(entry.getValue());
		}
		return Entities;
	}
	
	public List<Herbivore> getListOfHerbivores() {
		List<Herbivore> Herbivores = new ArrayList<>();
		for (Entity entity : this.getListOfEntities()) {			
			if (entity instanceof Herbivore) {
				Herbivores.add((Herbivore)entity);
			}
		}
		return Herbivores;		
	}
	
	public List<Predator> getListOfPredators() {
		List<Predator> Predators = new ArrayList<>();
		for (Entity entity : this.getListOfEntities()) {			
			if (entity instanceof Predator) {
				Predators.add((Predator)entity);
			}
		}
		return Predators;		
	}
	
	public void removeEntity(Coordinates coordinates) throws EntityNotFoundException {
		if (this.isCellEmty(coordinates)) {
			throw new EntityNotFoundException("Entity by coordinates is not found for removing");
		}
		entities.remove(coordinates);
	}
	
	public boolean isCellEmty(Coordinates coordinates) {
		if (this.entities.containsKey(coordinates)){
			return false;
		}
		return true;
	}
	
	public boolean areCoordinatesValid(Coordinates coordinates) { // !!! М.Б. ПРОЩЕ СОЗДАТЬ HASHMAP EMPTY SELLS
		int x = coordinates.getX();
		int y = coordinates.getY();
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
			return false;
		}
		return true;
	}
	
	public boolean areCoordinatesAvailableForMove(Coordinates coordinates) {
		if (isCellEmty(coordinates) && areCoordinatesValid(coordinates)) {
			return true;			
		}
		return false;
	}


 
}
