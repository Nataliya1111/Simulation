package entity;

import main.Coordinates;
import main.EntityNotFoundException;
import main.WorldMap;
import pathfinder.Pathway;

public class Predator extends Creature {
	
	private final static int MAX_HP = 14;
	private final static int DEFAULT_SPEED = 1;
	private final static Class<? extends Entity> DEFAULT_FOOD = Herbivore.class;

	public Predator(){
		super(MAX_HP, DEFAULT_SPEED, DEFAULT_FOOD);
		this.isNewBorn = true;
	}
	
	@Override
	protected void eat(Pathway path, WorldMap worldMap) {	
		
		Coordinates targetCoordinates = path.getCoordinatesOfTarget();		
		
		try {
			Entity targetEntity = worldMap.getEntityByCoordinates(targetCoordinates);
			((Creature)targetEntity).setHp(0);      //making targetEntity dead by setting zero HP in case it would try to move being eaten
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		this.makeMovement(worldMap, targetCoordinates);
		this.hp = MAX_HP;
	}

}
