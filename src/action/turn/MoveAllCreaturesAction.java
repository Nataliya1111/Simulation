package action.turn;

import java.util.ArrayList;
import java.util.List;

import action.Action;
import entity.Creature;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import main.EntityNotFoundException;
import main.WorldMap;

public class MoveAllCreaturesAction extends Action {	

	public MoveAllCreaturesAction(WorldMap worldMap) {
		super(worldMap);
	}

	@Override
	public void execute() {	
		
		List<Entity> entities = worldMap.getListOfEntities();
////
//		List<Creature> creatures = new ArrayList<>();
//		for (Entity entity : entities) {			
//			if (entity instanceof Creature) {
//				creatures.add((Creature)entity);
//			}
//		}
//	
//		for (Creature creature : creatures) {
//			try {
//				creature.makeMove(worldMap);
//			} catch (EntityNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
////		
		List<Creature> predators = new ArrayList<>();
		for (Entity entity : entities) {			
			if (entity instanceof Predator) {
				predators.add((Predator)entity);
			}
		}
		
		List<Creature> herbivores = new ArrayList<>();
		for (Entity entity : entities) {			
			if (entity instanceof Herbivore) {
				herbivores.add((Herbivore)entity);
			}
		}
	
		for (Creature creature : predators) {
			try {
				creature.makeMove(worldMap);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
		}	
		for (Creature creature : herbivores) {
			try {
				creature.makeMove(worldMap);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
		}
		

		


		
	}

}
