package society;

import java.util.HashMap;
import java.util.Map;

public class ToroidalEdges extends Edges {

	public ToroidalEdges() {
		
	}

	@Override
	public Map<Coordinate, Cell> getCoordinates(Map<Coordinate, Cell> myCells, int width, int height) {
		Map<Coordinate, Cell> myCoordinates = new HashMap<Coordinate, Cell>();
		
		int smallestX = 100;
		int largestX = 1;
		int smallestY = 100;
		int largestY = 1;
		
		for(Coordinate c: myCells.keySet()){
			myCoordinates.put(c, myCells.get(c));
			
			if(c.getMyX() < smallestX){
				smallestX = c.getMyX();
			}
			if(c.getMyX() > largestX){
				largestX = c.getMyX();
			}
			if(c.getMyY() < smallestY){
				smallestY = c.getMyY();
			}
			if(c.getMyY() > largestY){
				largestY = c.getMyY();
			}
			
		}
		
		for(int i = smallestX; i <= largestX; i += width){
			myCoordinates.put(new Coordinate(i, (smallestY - height)), myCells.get(new Coordinate(i, largestY)));
			myCoordinates.put(new Coordinate(i, (largestY + height)), myCells.get(new Coordinate(i, smallestY)));
		}
		
		for(int i = smallestY; i <= largestY; i += height){
			myCoordinates.put(new Coordinate((smallestX - width), i), myCells.get(new Coordinate(largestX, i)));
			myCoordinates.put(new Coordinate((largestX + width), i), myCells.get(new Coordinate(smallestX, i)));
		}
		
		myCoordinates.put(new Coordinate((smallestX - width), (smallestY - height)), myCells.get(new Coordinate(largestX, largestY)));
		myCoordinates.put(new Coordinate((smallestX - width), (largestY + height)), myCells.get(new Coordinate(largestX, smallestY)));
		myCoordinates.put(new Coordinate((largestX + width), (largestY + height)), myCells.get(new Coordinate(smallestX, smallestY)));
		myCoordinates.put(new Coordinate((largestX + width), (smallestY - height)), myCells.get(new Coordinate(smallestX, largestY)));
		
		return myCoordinates;
	}

}
