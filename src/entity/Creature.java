package entity;

import java.util.Random;

import main.Coordinates;
import main.EntityNotFoundException;
import main.WorldMap;

public abstract class Creature extends Entity {	
	
	protected int hp;
	protected int speed;
	
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
	
	public abstract void makeMove(WorldMap worldMap);
	
	protected void makeOneCellMovement(WorldMap worldMap) {
		try {
			Coordinates startingCoordinates = worldMap.getCoordinatesByEntity(this);
			Coordinates newCoordinates;
			int x = startingCoordinates.getX();
			int y = startingCoordinates.getY();
			Random random = new Random();
			
			while(true) {
				int randomInt = random.nextInt(4);
				switch(randomInt) {
					case 0:
						newCoordinates = new Coordinates(x-1, y);
						break;
					case 1:
						newCoordinates = new Coordinates(x+1, y);
						break;
					case 2:
						newCoordinates = new Coordinates(x, y-1);
						break;
					case 3:
						newCoordinates = new Coordinates(x, y+1);
						break;
					default:
						newCoordinates = startingCoordinates;
				}
				if (worldMap.areCoordinatesAvailableForMove(newCoordinates)) {
					break;
				}
			}
			worldMap.setEntity(newCoordinates, this);
			worldMap.removeEntity(startingCoordinates);
			System.out.println("" +newCoordinates.getX()+ newCoordinates.getY());
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
