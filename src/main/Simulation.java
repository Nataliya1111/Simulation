package main;

import java.util.List;
import java.util.ArrayList;

//import entity.*;
import action.*;

public class Simulation {
	
	public static final String ANSI_RESET = "\033[0m"; 
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
