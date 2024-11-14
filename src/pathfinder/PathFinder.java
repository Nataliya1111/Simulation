package pathfinder;

import java.util.List;

import entity.Entity;
import main.Coordinates;

public interface PathFinder {	

	 <T extends Entity> Pathway getPathway(Coordinates startCoordinates, Class<T> targetEntity);

}
