package action.turn;

import java.util.List;

import action.Action;
import entity.Creature;
import entity.Entity;
import main.EntityNotFoundException;
import main.WorldMap;

public class CleanDeadCreaturesAction extends Action {

	public CleanDeadCreaturesAction(WorldMap worldMap) {
		super(worldMap);
	}

	@Override
	public void execute() {
		List<Entity> entities = worldMap.getListOfEntities();
		
		for (Entity entity : entities) {			
			if (entity instanceof Creature) {
				Creature creature = (Creature) entity;
				if (creature.isDead()) {
					try {
						worldMap.removeEntity(creature);
					} catch (EntityNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
