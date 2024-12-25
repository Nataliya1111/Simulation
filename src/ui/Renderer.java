package ui;

import entity.*;
import main.Coordinates;
import main.EntityNotFoundException;
import main.WorldMap;
import ui.ConsoleCleaner.CleaningMode;

public class Renderer {	
	
	private static final String SPRITE_OF_EMPTY_SELL = "🟫";   
	private static final String SPRITE_OF_HERBIVORE = "🐰";
	private static final String SPRITE_OF_PREDATOR = "🐺";
	private static final String SPRITE_OF_DEAD_CREATURE = "💀"; 
	private static final String SPRITE_OF_HERB = "🥕";
	private static final String SPRITE_OF_ROCK = "🪨";
	private static final String SPRITE_OF_TREE = "🌲"; 
	
	
	private static final String ANSI_RESET = "\033[0m"; 
	private static final String ANSI_RED_BACKGROUND = "\033[41m"; 
	private static final String ANSI_GREEN_BACKGROUND = "\033[42m";
	
	CleaningMode cleaningMode = CleaningMode.ZERO;
	
	private final WorldMap worldMap;
	
	public Renderer(WorldMap worldMap){
		this.worldMap = worldMap;
	}

	
	public void render() {		
		
		ConsoleCleaner.clean();
		System.out.println();
		
		DisplayInfo.printTurnMessage();
		
		final int heightOfMap = worldMap.getHeight();
		final int widthOfMap = worldMap.getWidth();		
		
		for(int y = 0; y < heightOfMap; y++) {
			String mapLine = "";
			for (int x = 0; x < widthOfMap; x ++) {
				Coordinates coordinates = new Coordinates(x, y);
				if (worldMap.isCellEmpty(coordinates)){
					mapLine += SPRITE_OF_EMPTY_SELL;
				}	
				else {
					Entity entity;
					try {
						entity = this.worldMap.getEntity(coordinates);
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
		    		return ANSI_GREEN_BACKGROUND + SPRITE_OF_HERBIVORE + ANSI_RESET;
		    	}
		    	if (herbivore.isDead()){
		    		return SPRITE_OF_DEAD_CREATURE; 
		    	}
		    	if (herbivore.getHp() < 3) {
		    		return ANSI_RED_BACKGROUND + SPRITE_OF_HERBIVORE + ANSI_RESET;
		    	}
		    	return SPRITE_OF_HERBIVORE;
		    case "Predator":
		    	Creature predator = (Creature) entity;
		    	if (predator.isNewBorn()) {
		    		return ANSI_GREEN_BACKGROUND + SPRITE_OF_PREDATOR + ANSI_RESET;
		    	}
		    	if (predator.isDead()){
		    		return SPRITE_OF_DEAD_CREATURE;  
		    	}
		    	if (predator.getHp() < 3) {
		    		return ANSI_RED_BACKGROUND + SPRITE_OF_PREDATOR + ANSI_RESET;
		    	}
		    	return SPRITE_OF_PREDATOR;
		    case "Herb":
		    	return SPRITE_OF_HERB;
		    case "Rock":
		    	return SPRITE_OF_ROCK;
		    case "Tree":
		    	return SPRITE_OF_TREE; 
		}
		return "??";
	}

}
