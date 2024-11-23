package pathfinder;

import entity.Entity;
import main.Coordinates;

public interface PathFinder {	

	 <T extends Entity> Pathway getPathway(Coordinates startCoordinates, Class<T> targetEntity);

}
