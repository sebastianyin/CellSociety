package society;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class SquareCell extends CellShape {
	
	Neighbors myAlgorithm;
	Group myPane;

	public SquareCell(String name, int x, int y, int width, int height, Neighbors neighborAlgorithm) {
		super(name, x, y, width, height);
		myAlgorithm = neighborAlgorithm;
		myPane = new Group();
	}

	@Override
	public Group createShape(){
		Rectangle polygon = new Rectangle(getX() - (getWidth()/2), getY(), getWidth(), getHeight());
		myPane.getChildren().add(polygon);
		return myPane;
	}

	@Override
	public void findNeighbors(Cell cell, Map<Coordinate, Cell> myCells, Edges gridEdges, int vision) {

		Map<Coordinate, Cell> myCoords = gridEdges.getCoordinates(myCells, getWidth(), getHeight());
		
		cell.setNeighbors(myAlgorithm.returnNeighbors(cell, myCoords, getWidth(), getHeight()));

	}

}
