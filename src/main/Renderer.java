package main;

import entity.*;

import java.util.Random;

public class Renderer {	
	
	public static final String SpriteOfEmptySell = "🏻";   //1F3FB = \uD83C\uDFFB
	
	private final WorldMap worldMap;
	
	public Renderer(WorldMap worldMap){
		this.worldMap = worldMap;
	}

	
	public void render() {
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
					Entity entity = this.worldMap.getEntity(coordinates);
					mapLine += getSpriteOfEntity(entity);
				}				
			}
			System.out.println(mapLine);
		}		
	}
	//возможно сделать внутренний класс SpriteOfAntity
	private String getSpriteOfEntity(Entity entity) { 
		Random random = new Random();
		switch(entity.getClass().getSimpleName()) {
		    case "Herbivore":
		    	return "🐰";
		    case "Predator":
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
		return "";
	}
	
//	private String getSpriteOfEmptySell() {
//		return "🏻";  //1F3FB = \uD83C\uDFFB
//	}

}
