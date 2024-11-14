package action;

import entity.Entity;
import entity.Herb;
import main.WorldMap;

public class HerbSpawnAction  extends EntitySpawnAction {	

	public HerbSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.quantityOfCellsForSpawning = worldMap.getQuontityOfCells()*10/100;
	}	
	
	@Override
	protected Entity getSpawningEntity() {
		return new Herb();
	}
	
}