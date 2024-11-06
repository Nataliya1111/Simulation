package action;

import entity.Entity;
import entity.Herbivore;
import main.WorldMap;

public class HerbivoreSpawnAction extends EntitySpawnAction {	

	public HerbivoreSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.quantityOfCellsForSpawning = worldMap.getQuontityOfCells()*10/100;
	}

	@Override
	protected Entity getSpawningEntity() {
		return new Herbivore();
	}	

}
