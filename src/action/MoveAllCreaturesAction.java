package action;

import java.util.List;

import entity.Creature;
import entity.Herbivore;
import entity.Predator;
import main.WorldMap;

public class MoveAllCreaturesAction extends Action {
	
	private final List<Herbivore> listOfHerbivores = worldMap.getListOfHerbivores();
	private final List<Predator> listOfPredators = worldMap.getListOfPredators();

	public MoveAllCreaturesAction(WorldMap worldMap) {
		super(worldMap);
	}

	@Override
	public void perform() {	
		
		for (Creature creature : listOfPredators) {
			creature.makeMove(worldMap);
		}
		
		for (Creature creature : listOfHerbivores) {
			creature.makeMove(worldMap);
		}
		


		
	}

}
