package action;

import entity.Entity;
import entity.Predator;
import main.WorldMap;

public class PredatorSpawnAction extends EntitySpawnAction {	

	public PredatorSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.quantityOfCellsForSpawning = worldMap.getQuontityOfCells()*5/100;
	}	
	
	@Override
	protected Entity getSpawningEntity() {
		return new Predator();
	}
	
}
