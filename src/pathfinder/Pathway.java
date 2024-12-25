package pathfinder;

import java.util.ArrayList;
import java.util.List;

import main.Coordinates;

public class Pathway {
	
	private List<Coordinates> coordinatesOfPath = new ArrayList<>();
	
	public Pathway(List<Coordinates> coordinatesOfPath) {
		this.coordinatesOfPath = coordinatesOfPath;
	}

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
	
	public boolean isAvailableTargetExist() {
        return !coordinatesOfPath.isEmpty();
    }
	
	public boolean isTargetCloseEnoughToEat(int speed){
        return coordinatesOfPath.size() <= speed;
    }
	
	public int quantityOfStepsToTarget() {
		return coordinatesOfPath.size();
	}
	
	

}
