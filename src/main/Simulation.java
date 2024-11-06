package main;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

//import entity.*;
import action.*;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import entity.Creature;

public class Simulation {
	
//	public static final String ANSI_RESET = "\033[0m"; 
//	public static final String ANSI_RED_BACKGROUND = "\033[41m";   
//	public static final String ANSI_GREEN_BACKGROUND = "\033[42m"; 
//	public static final String ANSI_YELLOW_BACKGROUND = "\033[43m";
	
	private final WorldMap worldMap = new WorldMap(5, 5);
	private final Renderer renderer = new Renderer(worldMap);
	private final List<EntitySpawnAction> initActions = new ArrayList<>();
	
	
	public void start() {
		performInitSpawnActions();
		
//		worldMap.setEntity(new Coordinates(5,5),ANSI_GREEN_BACKGROUND + "🐰" +  ANSI_RESET);
//		worldMap.setEntity(new Coordinates(3,2),ANSI_YELLOW_BACKGROUND + "🐺" +  ANSI_RESET);
//		worldMap.setEntity(new Coordinates(4,5),ANSI_RED_BACKGROUND + "🐰" +  ANSI_RESET);

		renderer.render();
		
	

		
		System.out.println(worldMap.getListOfHerbivores());
		System.out.println(worldMap.getListOfPredators());
		
//		MoveAllCreaturesAction action = new MoveAllCreaturesAction(worldMap);
//		action.perform();
//		renderer.render();
		
		Coordinates prCoord = null;
		Predator pr1 = null;
		Coordinates herbCoord = null;
		Herbivore herb1 = null;
		try {
			prCoord = worldMap.getCoordinatesByEntity(worldMap.getListOfPredators().get(0));
			//pr1 =  worldMap.getListOfPredators().get(0);
			//herbCoord = worldMap.getCoordinatesByEntity(worldMap.getListOfHerbivores().get(0));
			//herb1 =  worldMap.getListOfHerbivores().get(0);
			//System.out.println(herb1.getClass());

		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BfsPathFinder finder = new BfsPathFinder(worldMap);
//		Coordinates foundCoord = finder.getCoordinatesOfGoal(prCoord, Herbivore.class);
		List<Coordinates> getCoordinatesOfPath = finder.getCoordinatesOfPath(prCoord, Herbivore.class);
		System.out.println(getCoordinatesOfPath);
	//	System.out.println("" + foundCoord.getX() + foundCoord.getY());

		for(Coordinates coordinatesOfPath : getCoordinatesOfPath) {
			System.out.println("" + coordinatesOfPath.getX() + coordinatesOfPath.getY());
		}

		
	}
	
	private void performInitSpawnActions() {
		initActions.add(new PredatorSpawnAction(worldMap));
		initActions.add(new HerbivoreSpawnAction(worldMap));
		initActions.add(new HerbSpawnAction(worldMap));

		for(EntitySpawnAction action : initActions) {
			action.performOnStart();
		}
	}
	

}
