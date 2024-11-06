package entity;

import main.WorldMap;

public class Predator extends Creature {
	
	private final static int MAX_HP = 10;
	private final static int DEFAULT_SPEED = 2;

	public Predator(){
		this.hp = MAX_HP;
		this.speed = DEFAULT_SPEED;
	}

	@Override
	public void makeMove(WorldMap worldMap) {		//м.б. вынести в Creature
		
		for (int i = 0; i < this.speed; i++) {
			makeOneCellMovement(worldMap);
		}		
	}
	

}
