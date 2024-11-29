package action.init;

import entity.Entity;
import entity.Herbivore;
import main.WorldMap;

public class HerbivoreSpawnAction extends EntitySpawnAction {	

	public HerbivoreSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.percentOfCellsForSpawning = 4;
	}

	@Override
	protected Entity getSpawningEntity() {
		return new Herbivore();
	}	

}
