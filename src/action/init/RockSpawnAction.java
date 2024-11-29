package action.init;

import entity.Entity;
import entity.Rock;
import main.WorldMap;

public class RockSpawnAction extends EntitySpawnAction {

	public RockSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.percentOfCellsForSpawning = 10;
	}	
	
	@Override
	protected Entity getSpawningEntity() {
		return new Rock();
	}
	
}