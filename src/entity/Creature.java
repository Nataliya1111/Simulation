package entity;

import java.util.Random;

import main.Coordinates;
import main.EntityNotFoundException;
import main.WorldMap;
import pathfinder.BfsPathFinder;
import pathfinder.Pathway;

public abstract class Creature extends Entity {	
	
	protected int hp;
	protected int speed;
	protected boolean isNewBorn;
	protected Class<? extends Entity> entityForFood;
	
	protected Creature(int creatureHp, int creatureSpeed, Class<? extends Entity> entityForFood) {
		  this.hp = creatureHp;
		  this.speed = creatureSpeed;
		  this.entityForFood = entityForFood;
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
	
	public boolean isNewBorn() {
		return isNewBorn;
	}
	
	public boolean isDead(){
		if (this.hp <= 0) {
			return true;
		}
		return false;
	}
	
	public void makeMove(WorldMap worldMap) throws EntityNotFoundException { 
		this.isNewBorn = false;

		this.hp -= 1;
		
		if (isDead()) {
			return;
		}
		
		BfsPathFinder pathFinder = new BfsPathFinder(worldMap);
		Pathway path = pathFinder.getPathway(worldMap.getCoordinatesByEntity(this), this.entityForFood);		
		
		if (!path.isAvailableTarget()) {
			makeRandomMove(worldMap);
			return;
			 
		}
		if (path.isTargetCloseEnoughToEat(this.speed)) {
			this.eat(path, worldMap);
			return;
		}
		
		Coordinates movementCoordinates = path.getCoordinates(this.speed - 1);		
		this.makeMovement(worldMap, movementCoordinates);		
	}
	
	protected void makeMovement(WorldMap worldMap, Coordinates finishCoordinates) {

		try {
			worldMap.removeEntity(this);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		worldMap.setEntity(finishCoordinates, this);
	}
	
	private void makeRandomMove(WorldMap worldMap) throws EntityNotFoundException {
		Coordinates startCoordinates = worldMap.getCoordinatesByEntity(this);
		Coordinates finishCoordinates = startCoordinates;
		int x = startCoordinates.getX();
		int y = startCoordinates.getY();
		Random random = new Random();
		int triesToMove = 4;
		for(triesToMove = 0; triesToMove < 4; triesToMove++) {
			int randomInt = random.nextInt(4);
			switch(randomInt) {
				case 0:
					finishCoordinates = new Coordinates(x-1, y);
					break;
				case 1:
					finishCoordinates = new Coordinates(x+1, y);
					break;
				case 2:
					finishCoordinates = new Coordinates(x, y-1);
					break;
				case 3:
					finishCoordinates = new Coordinates(x, y+1);
					break;
			}
			if (worldMap.areCoordinatesAvailableForMove(finishCoordinates)) {
				break;
			}
			else {
				finishCoordinates = startCoordinates;
			}
		}
		this.makeMovement(worldMap, finishCoordinates);
	}	
	
	protected abstract void eat(Pathway path, WorldMap worldMap);	

}
