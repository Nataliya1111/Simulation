package action.turn;

import action.init.HerbivoreSpawnAction;
import entity.Herbivore;
import main.WorldMap;

public class HerbivoreRefillAction extends EntityRefillAction {

	public HerbivoreRefillAction(WorldMap worldMap) {
		super(worldMap);
		this.percentPerTurn = 0.15;
		this.maxPercentOnMap = 5;
		this.refillingEntity = Herbivore.class;
		this.entitySpawnAction = new HerbivoreSpawnAction(worldMap);
	}

}
