package action.turn;

import java.util.List;

import action.Action;
import entity.Creature;
import entity.Entity;
import entity.Herb;
import entity.Herbivore;
import entity.Predator;
import main.Simulation;
import main.WorldMap;

public class CountStatisticsAction extends Action {
	
	private int eatenHerbCounter = 0;
	private int eatenHerbivoresCounter = 0;
	private int hungerDeadHerbivoresCounter = 0;
	private int hungerDeadPredatorsCounter = 0;
	private int movesCounter = 0;
	
	List<Entity> entities;
	
	Simulation simulation;	

	public CountStatisticsAction(WorldMap worldMap, Simulation simulation) {
		super(worldMap);
		this.simulation = simulation; 
		this.entities = worldMap.getListOfEntities();
	}

	@Override
	public void execute() {
		
		List<Entity> currentEntities = worldMap.getListOfEntities();
		
		for (Entity entity : entities) {
			
			if(!currentEntities.contains(entity)) {
				if (entity instanceof Herb) {
					eatenHerbCounter++;
				}
				else if (entity instanceof Herbivore) {
					eatenHerbivoresCounter++;
				}
			}
			
			else if ((entity instanceof Creature) && ((Creature) entity).isDead()) {
				if (entity instanceof Herbivore) {
					hungerDeadHerbivoresCounter++;
					currentEntities.remove(entity);
					
				}
				else if (entity instanceof Predator) {
					hungerDeadPredatorsCounter++;
				}
			}			
		}
		
		entities = currentEntities;
		
		movesCounter = simulation.getMovesCounter();
	}

	public int getEatenHerbCounter() {
		return eatenHerbCounter;
	}
	
	public int getMovesCounter() {
		return movesCounter;
	}

	public int getEatenHerbivoresCounter() {
		return eatenHerbivoresCounter;
	}

	public int getHungerDeadHerbivoresCounter() {
		return hungerDeadHerbivoresCounter;
	}

	public int getHungerDeadPredatorsCounter() {
		return hungerDeadPredatorsCounter;
	}

}
