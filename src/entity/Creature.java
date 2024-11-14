package entity;

import main.Coordinates;
import main.EntityNotFoundException;
import main.WorldMap;
import pathfinder.Pathway;

public abstract class Creature extends Entity {	
	
	protected int hp;
	protected int speed;
	//protected  Class<? extends Entity> entityForFood;
	
	protected Creature(int creatureHp, int creatureSpeed) {
		  this.hp = creatureHp;
		  this.speed = creatureSpeed;
	}

	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
//	protected Coordinates getCoordinates(WorldMap worldMap) {
//		try {
//			return worldMap.getCoordinatesByEntity(this);
//		} catch (EntityNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	protected void makeMovement(WorldMap worldMap, Coordinates finishCoordinates) {

		try {
			worldMap.removeEntity(this);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		worldMap.setEntity(finishCoordinates, this);
	}
	
//	public void makeMove1(WorldMap worldMap) throws EntityNotFoundException {
//		if (isDead()) {
//			return;
//		}
//		BfsPathFinder pathFinder = new BfsPathFinder(worldMap);
//		Pathway path = pathFinder.getPathway(startCoordinates, this.entityForFood);
//		
//		if(isTargetNear) {
//			eat
//		}
//	}
	
	public boolean isDead(){
		if (this.hp <= 0) {
			return true;
		}
		return false;
	}
	
	public abstract void makeMove(WorldMap worldMap) throws EntityNotFoundException;
	
	protected abstract void eat(Pathway path, WorldMap worldMap);
	
	


//	protected abstract <T extends Entity> Class<T> goalEntity();

}
