package entity;

import main.Coordinates;

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
	
	public abstract void makeMove(Coordinates startingCoordinates);

}
