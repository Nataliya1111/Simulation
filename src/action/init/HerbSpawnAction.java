package action.init;

import entity.Entity;
import entity.Herb;
import main.WorldMap;

public class HerbSpawnAction  extends EntitySpawnAction {	

	public HerbSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.percentOfCellsForSpawning = 5;
	}	
	
	@Override
	protected Entity getSpawningEntity() {
		return new Herb();
	}
	
}