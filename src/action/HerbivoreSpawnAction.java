package action;

import entity.Herbivore;
import main.WorldMap;

public class HerbivoreSpawnAction extends EntitySpawnAction {	

	public HerbivoreSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.entity = new Herbivore();	
		this.quantityOfCellsForSpawning = worldMap.getQuontityOfCells()*10/100;
	}
}
