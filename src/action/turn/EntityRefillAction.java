package action.turn;

import java.util.List;

import action.Action;
import action.init.EntitySpawnAction;
import entity.Entity;
import main.WorldMap;

public abstract class EntityRefillAction extends Action {
	
	protected double percentPerTurn; 
	protected int maxPercentOnMap;
	protected Class<? extends Entity> refillingEntity;
	protected EntitySpawnAction entitySpawnAction;
	
	private int currentCellsCount = 0;
	
	private final int mapCellsQuantity;

	public EntityRefillAction(WorldMap worldMap) {		
		super(worldMap);
		this.mapCellsQuantity = worldMap.getQuantityOfCells();
	}

	@Override
	public void execute() {
		
		int maxEntityQuantity = this.mapCellsQuantity * maxPercentOnMap / 100;
		int EntityQuantityOnMap = this.countOfEntity();
		if (EntityQuantityOnMap >= maxEntityQuantity) {
			return;
		}
		
		int cellsForEntityPerTurn = (int) (100 / percentPerTurn);
		while(currentCellsCount >= cellsForEntityPerTurn) {			
			entitySpawnAction.execute();			
			currentCellsCount -= cellsForEntityPerTurn;
		}
		currentCellsCount += mapCellsQuantity;
	}
	
	private int countOfEntity() {
		List<Entity> entities = worldMap.getListOfEntities();
		int countOfEntity = 0;
		for (Entity entity : entities) {
			if (entity.getClass() == refillingEntity) {
				countOfEntity++;
			}
		}
		return countOfEntity;
	}

}
