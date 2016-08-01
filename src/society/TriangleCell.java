package society;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class TriangleCell extends CellShape {
	
	Neighbors myAlgorithm;
	Group myPane;

	public TriangleCell(String name, int x, int y, int width, int height, Neighbors neighborAlgorithm) {
		super(name, x, y, width, height);
		myAlgorithm = neighborAlgorithm;
		myPane = new Group();
	}

	@Override
	public Group createShape() {
		Polygon polygon = new Polygon();
		if((getY() % (2 * getHeight())) == 0){
			if(((getX() - (getWidth()/2)) % (2 * getWidth())) != 0){
				polygon.getPoints().addAll((createCoordinates(0, getX(), getY(), getWidth(), getHeight())));
			}
			else{
				polygon.getPoints().addAll((createCoordinates(1, getX(), getY(), getWidth(), getHeight())));
			}
		}
		else{
			if(((getX() - (getWidth()/2)) % (2 * getWidth())) != 0){
				polygon.getPoints().addAll((createCoordinates(1, getX(), getY(), getWidth(), getHeight())));
			}
			else{
				polygon.getPoints().addAll((createCoordinates(0, getX(), getY(), getWidth(), getHeight())));
			}
		}
		
		myPane.getChildren().add(polygon);
		
		return myPane;
	}
	
	public Double[] createCoordinates(int direction, int x, int y, int width, int height){

		int minusOne;
		if(direction == 0){
			minusOne = -1;
		}
		else{
			minusOne = 1;
		}
		
			return new Double[]{Double.parseDouble(x + "") + minusOne*(width/2),
					Double.parseDouble(y + ""), 
					Double.parseDouble(x + "") + width + (width/2), 
					Double.parseDouble(y + "") + (height * direction),
					Double.parseDouble(x + "") - minusOne*(width/2), 
					Double.parseDouble(y + "") + height};

	}

	@Override
	public void findNeighbors(Cell cell, Map<Coordinate, Cell> myCells, Edges gridEdges, int vision) {
		
		Map<Coordinate, Cell> myCoords = gridEdges.getCoordinates(myCells, getWidth(), getHeight());
		
		cell.setNeighbors(myAlgorithm.returnNeighbors(cell, myCoords, getWidth(), getHeight()));

	}

}
