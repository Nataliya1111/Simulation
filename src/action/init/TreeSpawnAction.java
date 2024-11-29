package action.init;

import entity.Entity;
import entity.Tree;
import main.WorldMap;

public class TreeSpawnAction extends EntitySpawnAction {

	public TreeSpawnAction(WorldMap worldMap) {
		super(worldMap);
		this.percentOfCellsForSpawning = 10;
	}	
	
	@Override
	protected Entity getSpawningEntity() {
		return new Tree();
	}
	
}