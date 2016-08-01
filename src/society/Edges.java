package society;

import java.util.Map;

public abstract class Edges {

	public Edges() {
		
	}
	
	public abstract Map<Coordinate, Cell> getCoordinates(Map<Coordinate, Cell> myCells, int width, int height);

}
