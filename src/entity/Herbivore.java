package entity;

import main.Coordinates;
import main.WorldMap;
import pathfinder.Pathway;

public class Herbivore extends Creature {
	
	private final static int MAX_HP = 12;
	private final static int DEFAULT_SPEED = 1;
	private final static Class<? extends Entity> DEFAULT_FOOD = Herb.class;	
	
	public Herbivore(){
		super(MAX_HP, DEFAULT_SPEED, DEFAULT_FOOD);
		this.isNewBorn = true;
	}
	
	@Override
	protected void eat(Pathway path, WorldMap worldMap) {	
		Coordinates targetCoordinates = path.getCoordinatesOfTarget();
		this.makeMovement(worldMap, targetCoordinates);
		this.hp += Math.round(MAX_HP / 2);
	}	

}
