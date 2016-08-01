package society;

import java.util.List;
import java.util.Map;

public abstract class Neighbors {

	public Neighbors() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract List<Cell> returnNeighbors(Cell cell, Map<Coordinate, Cell> myCells, int width, int height);

}
