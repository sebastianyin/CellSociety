package society;

import java.util.Map;

public class FiniteEdges extends Edges{

	public FiniteEdges() {
		
	}

	@Override
	public Map<Coordinate, Cell> getCoordinates(Map<Coordinate, Cell> myCells, int width, int height) {
		return myCells;
	}

}
