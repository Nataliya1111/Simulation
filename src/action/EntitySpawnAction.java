package action;

import java.util.Random;

import entity.Entity;
import main.Coordinates;
import main.WorldMap;

public abstract class EntitySpawnAction extends Action{
	protected Entity entity;
	protected int quantityOfCellsForSpawning;
	
	public EntitySpawnAction(WorldMap worldMap) {
		super(worldMap);	
	}
	
	@Override
	public void perform() {
		Coordinates coordinates;
		while (true) {
			coordinates = getRandomCoordinates();
			if (worldMap.isCellEmty(coordinates)) {
				worldMap.setEntity(coordinates, entity);
				return;
			}
		}		
	}			
	
	public void performOnStart() {
		for (int i = 0; i < this.quantityOfCellsForSpawning; i++) {
			perform();
		}
	}
	
	private Coordinates getRandomCoordinates() {		
		Random random = new Random();
		int randomX = random.nextInt(worldMap.getWidth());
		int randomY = random.nextInt(worldMap.getHeight());
		return new Coordinates(randomX, randomY);		
	}	

}
