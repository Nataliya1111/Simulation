package main;

import java.util.List;
import java.util.ArrayList;

import action.*;
import ui.Renderer;
import ui.UserCommandsExecutor;

public class Simulation {
	
//	public static final String ANSI_RESET = "\033[0m"; 
//	public static final String ANSI_RED_BACKGROUND = "\033[41m";   
//	public static final String ANSI_GREEN_BACKGROUND = "\033[42m"; 
//	public static final String ANSI_YELLOW_BACKGROUND = "\033[43m";
	
	private final WorldMap worldMap = new WorldMap(10, 3);
	private final Renderer renderer = new Renderer(worldMap);
	private List<EntitySpawnAction> initActions = new ArrayList<>();
	private List<Action> turnActions = new ArrayList<>();
	
	boolean isPaused = false;
	boolean isFinished = false;	
	
	public void start() {
		
		UserCommandsExecutor executor = new UserCommandsExecutor(this);
		executor.launch();
		
		initActions = getInitSpawnActionsList();
		for(EntitySpawnAction action : initActions) {
			action.executeOnStart();
		}
		
//		worldMap.setEntity(new Coordinates(5,5),ANSI_GREEN_BACKGROUND + "🐰" +  ANSI_RESET);
//		worldMap.setEntity(new Coordinates(3,2),ANSI_YELLOW_BACKGROUND + "🐺" +  ANSI_RESET);
//		worldMap.setEntity(new Coordinates(4,5),ANSI_RED_BACKGROUND + "🐰" +  ANSI_RESET);

		renderer.render();
		makeThreadSleep(1000);
		
	

		
		System.out.println(worldMap.getListOfHerbivores());
		System.out.println(worldMap.getListOfPredators());		
		
		
		
		turnActions = this.getTurnActionsList();
		
		while(!isFinished) {
			
			if (!isPaused) {				
				
				for(Action action : turnActions) {
					action.execute();
				}				
				renderer.render();				
				
				makeThreadSleep(1000);				
			}
			makeThreadSleep(10);			
		}		
	}
	
	public void setPaused() {
		isPaused = true;
	}
	
	public void setContinued() {
		isPaused = false;
	}
	
	public void finish() {
		isFinished = true;
	}
	
	private List<EntitySpawnAction> getInitSpawnActionsList() {
		initActions.add(new PredatorSpawnAction(worldMap));
		initActions.add(new HerbivoreSpawnAction(worldMap));
		initActions.add(new HerbSpawnAction(worldMap));

		return initActions;
	}
	
	private List<Action> getTurnActionsList() {
		turnActions.add(new MoveAllCreaturesAction(worldMap));		

		return turnActions;
	}
	
	private void makeThreadSleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
