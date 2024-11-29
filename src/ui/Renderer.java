package ui;

import entity.*;
import main.Coordinates;
import main.EntityNotFoundException;
import main.WorldMap;

public class Renderer {	
	
	public static final String SpriteOfEmptySell = "🏻";   //1F3FB = \uD83C\uDFFB
	public static final String SpriteOfHerbivore = "🐰";
	public static final String SpriteOfPredator = "🐺";
	public static final String SpriteOfDeadCreature = "💀"; //1F480
	public static final String SpriteOfHerb = "🥕";
	public static final String SpriteOfRock = "🪨";
	public static final String SpriteOfTree = "🌲";  //1F332	= \uD83C\uDF32
	
	
	public static final String ANSI_RESET = "\033[0m"; 
	public static final String ANSI_RED_BACKGROUND = "\033[41m"; 
	public static final String ANSI_GREEN_BACKGROUND = "\033[42m"; 
	
	private final WorldMap worldMap;
	
	public Renderer(WorldMap worldMap){
		this.worldMap = worldMap;
	}

	
	public void render() {		
		
		ConsoleCleaner.setCleaningMode(1);
		ConsoleCleaner.clean();
		System.out.println();
		
		final int heightOfMap = worldMap.getHeight();
		final int widthOfMap = worldMap.getWidth();		
		
		for(int y = 0; y < heightOfMap; y++) {
			String mapLine = "";
			for (int x = 0; x < widthOfMap; x ++) {
				Coordinates coordinates = new Coordinates(x, y);
				if (worldMap.isCellEmty(coordinates)){ 
					mapLine += SpriteOfEmptySell;
				}	
				else {
					Entity entity;
					try {
						entity = this.worldMap.getEntityByCoordinates(coordinates);
						mapLine += getSpriteOfEntity(entity);
					} catch (EntityNotFoundException e) {
						e.printStackTrace();
					}					
				}				
			}
			System.out.println(mapLine);			
		}
	}

	private String getSpriteOfEntity(Entity entity) { 
		switch(entity.getClass().getSimpleName()) {
		    case "Herbivore":
		    	Creature herbivore = (Creature) entity;
		    	if (herbivore.isNewBorn()) {
		    		return ANSI_GREEN_BACKGROUND + SpriteOfHerbivore + ANSI_RESET;
		    	}
		    	if (herbivore.isDead()){
		    		return SpriteOfDeadCreature; 
		    	}
		    	if (herbivore.getHp() < 3) {
		    		return ANSI_RED_BACKGROUND + SpriteOfHerbivore + ANSI_RESET;
		    	}
		    	return SpriteOfHerbivore;
		    case "Predator":
		    	Creature predator = (Creature) entity;
		    	if (predator.isNewBorn()) {
		    		return ANSI_GREEN_BACKGROUND + SpriteOfPredator + ANSI_RESET;
		    	}
		    	if (predator.isDead()){
		    		return SpriteOfDeadCreature;  
		    	}
		    	if (predator.getHp() < 3) {
		    		return ANSI_RED_BACKGROUND + SpriteOfPredator + ANSI_RESET;
		    	}
		    	return SpriteOfPredator;
		    case "Herb":
		    	return SpriteOfHerb;
		    case "Rock":
		    	return SpriteOfRock;
		    case "Tree":
		    	return SpriteOfTree; 
		}
		return "??";
	}

}
