package society;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllNeighbors extends Neighbors {

	public AllNeighbors() {
	}

	@Override
	public List<Cell> returnNeighbors(Cell cell, Map<Coordinate, Cell> myCells, int width, int height) {
		
		List<Cell> myNeighbors = new ArrayList<Cell>();
		Coordinate checkCoordinate = cell.getStates().getLocation();
		
		for(Coordinate c : myCells.keySet()){
			if((c.getMyX() == (checkCoordinate.getMyX() - width) 
					&& ((checkCoordinate.getMyY() == c.getMyY()) 
							|| ((checkCoordinate.getMyY() - height == c.getMyY())) 
							|| ((checkCoordinate.getMyY() + height == c.getMyY())))) ||
					(c.getMyX() == (checkCoordinate.getMyX() + width) 
					&& ((checkCoordinate.getMyY() == c.getMyY()) 
							|| ((checkCoordinate.getMyY() - height == c.getMyY())) 
							|| ((checkCoordinate.getMyY() + height == c.getMyY())))) ||
					(c.getMyY() == (checkCoordinate.getMyY() - height) 
					&& (checkCoordinate.getMyX() == c.getMyX()))||
					(c.getMyY() == (checkCoordinate.getMyY() + height) 
					&& (checkCoordinate.getMyX() == c.getMyX()))){
				myNeighbors.add(myCells.get(c));
			}
		}
		
		return myNeighbors;
		
	}

}
