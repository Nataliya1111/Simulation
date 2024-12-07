package action.turn;

import java.util.List;

import action.Action;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import main.Simulation;
import main.WorldMap;

public class CheckCreaturesPresenceAction extends Action {
	Simulation simulation;

	public CheckCreaturesPresenceAction(WorldMap worldMap, Simulation simulation) {
		super(worldMap);
		this.simulation = simulation; 
	}

	@Override
	public void execute() {
		List<Entity> entities = worldMap.getListOfEntities();
		boolean isHerbivoreOnMap = false;
		boolean isPredatorOnMap = false;
		
		for (Entity entity : entities) {			
			if (entity instanceof Herbivore) {
				isHerbivoreOnMap = true;
				break;				
			}
		}
		
		for (Entity entity : entities) {			
			if (entity instanceof Predator) {
				isPredatorOnMap = true;
				break;				
			}
		}
		
		if(!isHerbivoreOnMap || !isPredatorOnMap) {
			this.simulation.setFinished();			
		}
	}

}
