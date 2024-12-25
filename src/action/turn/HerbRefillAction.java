package action.turn;

import action.init.EntitySpawnAction;
import entity.Herb;
import main.WorldMap;

public class HerbRefillAction extends EntityRefillAction {

	public HerbRefillAction(WorldMap worldMap) {
		super(worldMap);
		this.percentPerTurn = 0.5;
		this.maxPercentOnMap = 5;
		this.refillingEntity = Herb.class;
		this.entitySpawnAction = new EntitySpawnAction(worldMap, () -> new Herb());
	}

}
