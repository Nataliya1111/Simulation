package action.turn;

import action.init.PredatorSpawnAction;
import entity.Predator;
import main.WorldMap;

public class PredatorRefillAction extends EntityRefillAction {

	public PredatorRefillAction(WorldMap worldMap) {
		super(worldMap);
		this.percentPerTurn = 0.1;
		this.maxPercentOnMap = 3;
		this.refillingEntity = Predator.class;
		this.entitySpawnAction = new PredatorSpawnAction(worldMap);
	}

}
