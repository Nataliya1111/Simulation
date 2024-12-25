package action.init;

import java.util.Random;
import java.util.function.Supplier;

import action.Action;
import entity.Entity;
import main.Coordinates;
import main.WorldMap;

public class EntitySpawnAction extends Action {

	private Supplier<Entity> entitySupplier;
	private int percentOfCellsForSpawning;
	private int quantityOfCellsForSpawning;

	public EntitySpawnAction(WorldMap worldMap, Supplier<Entity> entitySupplier) {
		super(worldMap);
		this.entitySupplier = entitySupplier;
	}

	public EntitySpawnAction(WorldMap worldMap, Supplier<Entity> entitySupplier, int percentOfCellsForSpawning) {
		this(worldMap, entitySupplier);
		this.percentOfCellsForSpawning = percentOfCellsForSpawning;
	}

		@Override
	public void execute() {
		Coordinates coordinates;
		while (true) {
			coordinates = getRandomCoordinates();
			if (worldMap.isCellEmpty(coordinates)) {
				worldMap.setEntity(coordinates, entitySupplier.get());
				return;
			}
		}		
	}		
	
	public void executeOnStart() {
		this.quantityOfCellsForSpawning = Math.round((float)worldMap.getQuantityOfCells()*this.percentOfCellsForSpawning/100);
		for (int i = 0; i < this.quantityOfCellsForSpawning; i++) {
			execute();
		}
	}

	//protected abstract Entity getSpawningEntity();

	protected Coordinates getRandomCoordinates() {		
		Random random = new Random();
		int randomX = random.nextInt(worldMap.getWidth());
		int randomY = random.nextInt(worldMap.getHeight());
		return new Coordinates(randomX, randomY);		
	}	

}
