package main;

import java.util.List;
import java.util.ArrayList;

import action.*;
import action.init.EntitySpawnAction;
import action.init.HerbSpawnAction;
import action.init.HerbivoreSpawnAction;
import action.init.PredatorSpawnAction;
import action.init.RockSpawnAction;
import action.init.TreeSpawnAction;
import action.turn.CheckCreaturesPresenceAction;
import action.turn.CleanDeadCreaturesAction;
import action.turn.HerbRefillAction;
import action.turn.HerbivoreRefillAction;
import action.turn.MoveAllCreaturesAction;
import action.turn.PredatorRefillAction;
import ui.Renderer;
import ui.UserCommandsExecutor;

public class Simulation {	

	private final WorldMap worldMap = new WorldMap(15, 10);
	private final Renderer renderer = new Renderer(worldMap);
	private List<EntitySpawnAction> initActions = new ArrayList<>();
	private List<Action> turnActions = new ArrayList<>();
	
	private int movesCounter = 0;
	private boolean isPaused = false;
	private boolean isFinished = false;	
	
	public void start() {
		
		UserCommandsExecutor executor = new UserCommandsExecutor(this);
		executor.launch();
		
		initActions = getInitSpawnActionsList();
		for(EntitySpawnAction action : initActions) {
			action.executeOnStart();
		}

		renderer.render();
		makeThreadSleep(1000);	
		
		turnActions = this.getTurnActionsList();
		
		while(!isFinished) {
			
			if (!isPaused) {				
				for(Action action : turnActions) {
					action.execute();
					if(isFinished) {
						break;
					}
				}
				movesCounter = getMovesCounter() + 1;
				renderer.render();	
				System.out.println(movesCounter);
				
				makeThreadSleep(10);				
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
	
	public void finish() {
		isFinished = true;
	}
	
	private List<EntitySpawnAction> getInitSpawnActionsList() {
		initActions.add(new PredatorSpawnAction(worldMap));
		initActions.add(new HerbivoreSpawnAction(worldMap));
		initActions.add(new HerbSpawnAction(worldMap));
		initActions.add(new TreeSpawnAction(worldMap));
		initActions.add(new RockSpawnAction(worldMap));

		return initActions;
	}
	
	private List<Action> getTurnActionsList() {
		turnActions.add(new CleanDeadCreaturesAction(worldMap));
		turnActions.add(new MoveAllCreaturesAction(worldMap));
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
