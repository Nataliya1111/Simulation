package action;

import main.WorldMap;

public abstract class Action {
	
	protected WorldMap worldMap;
	
	public Action(WorldMap worldMap) {
		this.worldMap = worldMap;	
	}
	
	public abstract void execute();

}
