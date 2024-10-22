package action;

import entity.Herb;
import main.WorldMap;

public class HerbSpawnAction  extends EntitySpawnAction {	

	public HerbSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.entity = new Herb();	
		this.quantityOfCellsForSpawning = worldMap.getQuontityOfCells()*5/100;
	}	
}