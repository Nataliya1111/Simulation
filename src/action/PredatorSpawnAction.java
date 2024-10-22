package action;

import entity.Predator;
import main.WorldMap;

public class PredatorSpawnAction extends EntitySpawnAction {	

	public PredatorSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.entity = new Predator();	
		this.quantityOfCellsForSpawning = worldMap.getQuontityOfCells()*5/100;
	}	
}
