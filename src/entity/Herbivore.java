package entity;


import main.Coordinates;
import main.EntityNotFoundException;
import main.WorldMap;
import pathfinder.BfsPathFinder;
import pathfinder.Pathway;

public class Herbivore extends Creature {
	
	private final static int MAX_HP = 3;
	private final static int DEFAULT_SPEED = 2;
	private final static Class<? extends Entity> DEFAULT_FOOD = Herb.class;
	
	private  Class<? extends Entity> entityForFood;

	
	public Herbivore(){
		super(MAX_HP, DEFAULT_SPEED);
		this.entityForFood = DEFAULT_FOOD;
	}
	

	@Override  //м.б. вынести в Creature
	public void makeMove(WorldMap worldMap) throws EntityNotFoundException {
		this.hp -= 1;
		
		if (isDead()) {
			return;
		}
		BfsPathFinder pathFinder = new BfsPathFinder(worldMap);
		Pathway path = pathFinder.getPathway(worldMap.getCoordinatesByEntity(this), this.entityForFood);
		
		
		if (!path.isTargetOnMap()) {
			// проверить, есть ли на карте вообще таргетЭнтити, если нет закончить симуляцию
			return;
			 
		}
		if (path.isTargetNearestCell()) {			
			this.eat(path, worldMap);
			return;
		}
		
		Coordinates movementCoordinates;
		if (this.speed > 1 && path.quontityOfStepsToTarget() == this.speed) {
			movementCoordinates = path.getCoordinates(this.speed - 2);
		}
		else {
			movementCoordinates = path.getCoordinates(this.speed - 1);
		}		
		this.makeMovement(worldMap, movementCoordinates);
		
	}
	
	@Override
	protected void eat(Pathway path, WorldMap worldMap) {	
		Coordinates targetCoordinates = path.getCoordinatesOfTarget();
		this.makeMovement(worldMap, targetCoordinates);
		this.hp += Math.round(MAX_HP / 2);
	}
	
//	private void runAway(Entity entity, WorldMap worldMap) {
//		int x = 0;
//		int y = 0;
//				
//		try {
//			Coordinates entityCoordinates = worldMap.getCoordinatesByEntity(entity);
//			x = entityCoordinates.getX();
//			y = entityCoordinates.getY();
//		} catch (EntityNotFoundException e) {
//			e.printStackTrace();
//			return;
//		}
//		Coordinates runAwayCoordinates = new Coordinates(-x, -y);
//		
//	}
	

//	@Override
//	protected <T extends Entity> Class<T>  goalEntity() {
//
//		return (Class<T>) entityForFood;
//		//return this.getClass();
//	}
	
	
	

}
