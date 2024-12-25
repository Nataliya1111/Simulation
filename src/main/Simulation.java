package main;

import java.util.List;
import java.util.ArrayList;

import action.Action;
import action.init.*;
import action.turn.*;
import entity.*;
import ui.DisplayInfo;
import ui.Renderer;

public class Simulation {	

	private WorldMap worldMap = new WorldMap();
	private Renderer renderer;
	private List<EntitySpawnAction> initActions = new ArrayList<>();
	private List<Action> turnActions = new ArrayList<>();
	private CountStatisticsAction countStatistics;
	
	private int movesCounter = 0;
	private boolean isPaused = false;
	private boolean isFinished = false;	
	private int sleepDuration = 1000;	
	
	public void setWorldMap(int width, int height) {
		this.worldMap = new WorldMap(width, height);
	}
	
	public void setSleepDuration(int sleepDuration) {
		this.sleepDuration = sleepDuration;
	}

	public void start() {		
		renderer = new Renderer(worldMap);
		
		initActions = getInitSpawnActionsList();
		for(EntitySpawnAction action : initActions) {
			action.executeOnStart();
		}

		renderer.render();		
		makeThreadSleep(sleepDuration);	
		
		countStatistics = new CountStatisticsAction(worldMap, this);
		turnActions = this.getTurnActionsList();
		
		while(!isFinished) {
			
			if (!isPaused) {	
				movesCounter++;
				for(Action action : turnActions) {
					action.execute();
					if(isFinished) {
						break;
					}
				}
				renderer.render();	
				
				DisplayInfo.printStatistics(countStatistics);
				
				makeThreadSleep(sleepDuration);				
			}
			makeThreadSleep(10);			
		}		
	}
	
	public int getMovesCounter() {
		return movesCounter;
	}
	
	public void setPaused() {
		isPaused = true;
	}
	
	public void setContinued() {
		isPaused = false;
	}
	
	public void setFinished() {
		isFinished = true;
	}
	
	private List<EntitySpawnAction> getInitSpawnActionsList() {
		initActions.add(new EntitySpawnAction(worldMap, () -> new Predator(), 3));
		initActions.add(new EntitySpawnAction(worldMap, () -> new Herbivore(), 5));
		initActions.add(new EntitySpawnAction(worldMap, () -> new Herb(), 5));
		initActions.add(new EntitySpawnAction(worldMap, () -> new Tree(), 10));
		initActions.add(new EntitySpawnAction(worldMap, () -> new Rock(), 10));

//		initActions.add(new PredatorSpawnAction(worldMap));
//		initActions.add(new HerbivoreSpawnAction(worldMap));
//		initActions.add(new HerbSpawnAction(worldMap));
//		initActions.add(new TreeSpawnAction(worldMap));
//		initActions.add(new RockSpawnAction(worldMap));

		return initActions;
	}
	
	private List<Action> getTurnActionsList() {
		turnActions.add(new CleanDeadCreaturesAction(worldMap));
		turnActions.add(new MoveAllCreaturesAction(worldMap));
		turnActions.add(countStatistics);
		turnActions.add(new CheckCreaturesPresenceAction(worldMap, this));	
		turnActions.add(new PredatorRefillAction(worldMap));
		turnActions.add(new HerbivoreRefillAction(worldMap));
		turnActions.add(new HerbRefillAction(worldMap));		

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
