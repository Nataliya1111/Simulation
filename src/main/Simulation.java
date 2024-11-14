package main;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.AbstractList;
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
	
	private final WorldMap worldMap = new WorldMap(10, 3);
	private final Renderer renderer = new Renderer(worldMap);
	private  List<EntitySpawnAction> initActions = new ArrayList<>();
	private  List<Action> turnActions = new ArrayList<>();
	
	
	
	public void start() {
		
		initActions.add(new PredatorSpawnAction(worldMap));
		initActions.add(new HerbivoreSpawnAction(worldMap));
		initActions.add(new HerbSpawnAction(worldMap));
		for(EntitySpawnAction action : initActions) {
			action.executeOnStart();
		}
		
//		worldMap.setEntity(new Coordinates(5,5),ANSI_GREEN_BACKGROUND + "🐰" +  ANSI_RESET);
//		worldMap.setEntity(new Coordinates(3,2),ANSI_YELLOW_BACKGROUND + "🐺" +  ANSI_RESET);
//		worldMap.setEntity(new Coordinates(4,5),ANSI_RED_BACKGROUND + "🐰" +  ANSI_RESET);

		renderer.render();
		
	

		
		System.out.println(worldMap.getListOfHerbivores());
		System.out.println(worldMap.getListOfPredators());
		

		turnActions.add(new MoveAllCreaturesAction(worldMap));
		
		
		for(int i = 0; i<3; i++) {
			for(Action action : turnActions) {
				action.execute();
			}
			renderer.render();
			System.out.println();
		}
		

		
	}
	
//	private List getInitSpawnActionsList() {
//		initActions.add(new PredatorSpawnAction(worldMap));
//		initActions.add(new HerbivoreSpawnAction(worldMap));
//		initActions.add(new HerbSpawnAction(worldMap));
//
//		for(EntitySpawnAction action : initActions) {
//			action.executeOnStart();
//		}
//	}
	
//	private void executeTurnActions() {
//		turnActions.add(new MoveAllCreaturesAction(worldMap));
//		
//		for(Action action : turnActions) {
//			action.execute();
//		}
//	}
	

}
