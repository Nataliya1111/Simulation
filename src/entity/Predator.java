package entity;

import main.Coordinates;
import main.EntityNotFoundException;
import main.WorldMap;
import pathfinder.BfsPathFinder;
import pathfinder.Pathway;

public class Predator extends Creature {
	
	private final static int MAX_HP = 10;
	private final static int DEFAULT_SPEED = 2;
	private final static Class<? extends Entity> DEFAULT_FOOD = Herbivore.class;

	private  Class<? extends Entity> entityForFood;


	public Predator(){
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
			// проверить, есть ли на карте вообще таргетЭнтити. 
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
		
		//making targetEntity dead by setting zero HP in case it would try to move being eaten
		try {
			Entity targetEntity = worldMap.getEntityByCoordinates(targetCoordinates);
			((Creature)targetEntity).setHp(0);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		//
		
		this.makeMovement(worldMap, targetCoordinates);
		this.hp = MAX_HP;
	}



}
