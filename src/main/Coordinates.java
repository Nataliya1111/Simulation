package main;

public class Coordinates {
	private final Integer x;
	private final Integer y;
	
	public Coordinates(Integer x, Integer y) {
		this.x = x;
		this.y = y;		
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	@Override
	public int hashCode() {
		int result = 31 + x.hashCode();		
		result = 31 * result + y.hashCode();
        return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		return x == other.x && y == other.y;
	}

}
