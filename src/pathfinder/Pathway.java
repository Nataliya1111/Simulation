package pathfinder;

import java.util.ArrayList;
import java.util.List;

import main.Coordinates;

public class Pathway {
	
	private List<Coordinates> coordinatesOfPath = new ArrayList<>();
	
	public Pathway(List<Coordinates> coordinatesOfPath) {
		this.coordinatesOfPath = coordinatesOfPath;
	}
	
//	public <T extends Entity> Pathway (WorldMap worldMap, Coordinates startCoordinates, Class<T> targetEntity) {
//		this.worldMap = worldMap;
//		this.list = (new BfsPathFinder(worldMap)).getPathCoordinates(startCoordinates, targetEntity); 
//	}

	public List<Coordinates> getCoordinatesOfPath() {
		return coordinatesOfPath;
	}

	public void setList(List<Coordinates> coordinatesOfPath) {
		this.coordinatesOfPath = coordinatesOfPath;
	}
	
	public Coordinates getCoordinates(int index) {
		return coordinatesOfPath.get(index);
	}
	
	public Coordinates getCoordinatesOfTarget() {
		int targetIndexInList = coordinatesOfPath.size()-1;
		return getCoordinates(targetIndexInList);
	}
	
	public boolean isTargetOnMap() {
		if (coordinatesOfPath.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean isTargetNearestCell(){
		if (coordinatesOfPath.size() == 1) {
			return true;
		}
		return false;
	}
	
	public int quontityOfStepsToTarget() {
		return coordinatesOfPath.size();
	}
	
	

}