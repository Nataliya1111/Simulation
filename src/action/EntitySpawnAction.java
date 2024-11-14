package action;

import java.util.Random;

import entity.Entity;
import main.Coordinates;
import main.WorldMap;

public abstract class EntitySpawnAction extends Action {
	
	protected int quantityOfCellsForSpawning;
	
	public EntitySpawnAction(WorldMap worldMap) {
		super(worldMap);	
	}
	
	@Override
	public void execute() {
		Coordinates coordinates;
		while (true) {
			coordinates = getRandomCoordinates();
			if (worldMap.isCellEmty(coordinates)) {
				worldMap.setEntity(coordinates, getSpawningEntity());
				return;
			}
		}		
	}		
	
	public void executeOnStart() {
		for (int i = 0; i < this.quantityOfCellsForSpawning; i++) {
			execute();
		}
	}

	protected abstract Entity getSpawningEntity();

	protected Coordinates getRandomCoordinates() {		
		Random random = new Random();
		int randomX = random.nextInt(worldMap.getWidth());
		int randomY = random.nextInt(worldMap.getHeight());
		return new Coordinates(randomX, randomY);		
	}	

}
