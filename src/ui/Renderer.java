package ui;

import java.util.Random;

import entity.*;
import main.Coordinates;
import main.EntityNotFoundException;
import main.WorldMap;

public class Renderer {	
	
	public static final String SpriteOfEmptySell = "🏻";   //1F3FB = \uD83C\uDFFB
	
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
//		System.out.println();
	}
	//возможно сделать внутренний класс SpriteOfAntity
	// if 
	private String getSpriteOfEntity(Entity entity) { 
		Random random = new Random();
		switch(entity.getClass().getSimpleName()) {
		    case "Herbivore":
		    	Creature herbivore = (Creature) entity;
		    	if (herbivore.isDead()){
		    		return "💀"; //1F480 
		    	}
		    	return "🐰";
		    case "Predator":
		    	Creature predator = (Creature) entity;
		    	if (predator.isDead()){
		    		return "💀"; //1F480 
		    	}
		    	return "🐺";
		    case "Herb":
		    	String[] herbs = {"🥕", "🥬"};
		    	return herbs[random.nextInt(herbs.length)];
		    case "Rock":
		    	return "🪨";
		    case "Tree":
		    	String[] trees = {"🌲", "🌳"};
		    	return trees[random.nextInt(trees.length)];
		}
		return "  ";
	}
	
//	private String getSpriteOfEmptySell() {
//		return "🏻";  //1F3FB = \uD83C\uDFFB
//	}

}
