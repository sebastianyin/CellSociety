package society;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiagonalNeighbors extends Neighbors {

	public DiagonalNeighbors(){
	}

	@Override
	public List<Cell> returnNeighbors(Cell cell, Map<Coordinate, Cell> myCells, int width, int height) {
		
		List<Cell> myNeighbors = new ArrayList<Cell>();
		Coordinate checkCoordinate = cell.getStates().getLocation();
		
		for(Coordinate c : myCells.keySet()){
			if((c.getMyX() == (checkCoordinate.getMyX() - width) 
					&& (((checkCoordinate.getMyY() - height == c.getMyY())) 
							|| ((checkCoordinate.getMyY() + height == c.getMyY())))) ||
					(c.getMyX() == (checkCoordinate.getMyX() + width) 
					&& ( ((checkCoordinate.getMyY() - height == c.getMyY())) 
							|| ((checkCoordinate.getMyY() + height == c.getMyY()))))){
				myNeighbors.add(myCells.get(c));
			}
		}
		
		return myNeighbors;
	}

}
