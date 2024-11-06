package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import entity.Entity;

public class BfsPathFinder implements Pathfinder {
	
	private final WorldMap worldMap;
	private Deque<Coordinates> nextCoordinates;
	private Map<Coordinates, Coordinates> seenCoordinates;
	
	BfsPathFinder(WorldMap worldMap){
		this.worldMap = worldMap;
	}
	
	public <T> List<Coordinates> getCoordinatesOfPath(Coordinates startCoordinates, Class<T> goalEntity) {
		List<Coordinates> coordinatesOfPath = new ArrayList<>();
		Optional<Coordinates> goalCoordinatesOptional = getCoordinatesOfGoal(startCoordinates, goalEntity); 
		if (goalCoordinatesOptional.isPresent()){
			Coordinates currentCoordinates = goalCoordinatesOptional.get();
			while(!currentCoordinates.equals(startCoordinates)) {
				coordinatesOfPath.add(currentCoordinates);
				currentCoordinates = seenCoordinates.get(currentCoordinates);
			}
			Collections.reverse(coordinatesOfPath);	
		}
			
		
		return coordinatesOfPath;		
	}
	
	private <T> Optional<Coordinates> getCoordinatesOfGoal(Coordinates startCoordinates, Class<T> goalEntity) {
		nextCoordinates = new ArrayDeque<>();
		seenCoordinates = new HashMap<>();
		nextCoordinates.addLast(startCoordinates);
		seenCoordinates.put(startCoordinates, new Coordinates(-1, -1));
		while(!nextCoordinates.isEmpty()) {
			Coordinates currentCoordinates = nextCoordinates.pollFirst();
			if (worldMap.isCellEmty(currentCoordinates) || (currentCoordinates == startCoordinates)) {
				for (Coordinates coordinates : this.getConnectedCoordinates(currentCoordinates)) {
					if(!seenCoordinates.containsKey(coordinates)) {
						nextCoordinates.addLast(coordinates);
						seenCoordinates.put(coordinates,currentCoordinates);
					}				
				}				
			}
			else {
				Entity currentEntity = null;
				try {
					currentEntity = worldMap.getEntityByCoordinates(currentCoordinates);
				} catch (EntityNotFoundException e) {
					e.printStackTrace();
				}
				if(currentEntity.getClass()==goalEntity) { 
					return Optional.of(currentCoordinates);
				}
			}						
		}
		return Optional.empty();  //CHANGE вариант с кончились цели и с застрял
	}
	
	private List<Coordinates> getConnectedCoordinates(Coordinates coordinates) {
		List<Coordinates> listOfCoordinates = new ArrayList<>();
		List<Coordinates> connectedCoordinates = new ArrayList<>();
		int x = coordinates.getX();
		int y = coordinates.getY();
		listOfCoordinates.add(new Coordinates(x-1,y));
		listOfCoordinates.add(new Coordinates(x+1,y));
		listOfCoordinates.add(new Coordinates(x,y-1));
		listOfCoordinates.add(new Coordinates(x,y+1));
		for (Coordinates coordinate : listOfCoordinates) {
			if (worldMap.areCoordinatesValid(coordinate)) {
				connectedCoordinates.add(coordinate);
			}
		}
		return connectedCoordinates;
	}

}
