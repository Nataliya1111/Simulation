package main;

import java.util.List;

public interface Pathfinder {
	
	public static final WorldMap worldMap = new WorldMap();
	
	 <T> List<Coordinates> getCoordinatesOfPath(Coordinates startCoordinates, Class<T> goalEntity);

}
