package action.init;

import entity.Entity;
import entity.Predator;
import main.WorldMap;

public class PredatorSpawnAction extends EntitySpawnAction {	

	public PredatorSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.percentOfCellsForSpawning = 3;
	}	
	
	@Override
	protected Entity getSpawningEntity() {
		return new Predator();
	}
	
}
