package society;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class HexagonCell extends CellShape {
	
	Group myPane;

	public HexagonCell(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		myPane = new Group();
	}

	@Override
	public Group createShape() {
		Polygon polygon = new Polygon();
		double sideWidth = (((double) getWidth()*4)/6);
		if(((getX() - (getWidth()/2)) % (2 * getWidth())) != 0){
			polygon.getPoints().addAll((createHexagon(0, getX(), getY(), sideWidth, getHeight())));
		}
		else{
			polygon.getPoints().addAll((createHexagon(1, getX(), getY(), sideWidth, getHeight())));
		}
		myPane.getChildren().add(polygon);
		return myPane;
	}
	
	public Double[] createHexagon(int direction, int x, int y, double sideWidth, int height){
			return new Double[]{Double.parseDouble(x + "") - (sideWidth/2), 
					Double.parseDouble(y + "") - ((height/2) * direction), 
					Double.parseDouble(x + "") - (sideWidth), 
					Double.parseDouble(y + "") - (height/2) - ((height/2) * direction), 
					Double.parseDouble(x + "") - (sideWidth/2), 
					Double.parseDouble(y + "") - height - ((height/2) * direction),
					Double.parseDouble(x + "") + (sideWidth/2), 
					Double.parseDouble(y + "") - height - ((height/2) * direction), 
					Double.parseDouble(x + "") + (sideWidth), 
					Double.parseDouble(y + "") - (height/2) - ((height/2) * direction),
					Double.parseDouble(x + "") + (sideWidth/2), 
					Double.parseDouble(y + "") - ((height/2) * direction)};
	}

	@Override
	public void findNeighbors(Cell cell, Map<Coordinate, Cell> myCells, Edges gridEdges, int vision) {
		
		List<Cell> myNeighbors = new ArrayList<Cell>();
		Coordinate checkCoordinate = cell.getStates().getLocation();
		
		int checkCoordinateX = checkCoordinate.getMyX();
		int checkCoordinateY = checkCoordinate.getMyY();
		
		Map<Coordinate, Cell> myCoords = gridEdges.getCoordinates(myCells, getWidth(), getHeight());
		
		for(Coordinate c : myCoords.keySet()){
			if((cell.getStates().getLocation().getMyX()- 10) % (2 * getWidth()) != 0){
				if(((c.getMyX() == (checkCoordinateX - getWidth())) && ((checkCoordinateY == c.getMyY()) || (checkCoordinateY + getHeight() == c.getMyY())))  ||
						((c.getMyX() == (checkCoordinateX + getWidth())) && ((checkCoordinateY == c.getMyY()) || ((checkCoordinateY + getHeight() == c.getMyY())))) ||
						(c.getMyY() == (checkCoordinate.getMyY() - getHeight()) && (checkCoordinateX == c.getMyX()))||
						(c.getMyY() == (checkCoordinate.getMyY() + getHeight()) && (checkCoordinateX == c.getMyX()))){
					myNeighbors.add(myCoords.get(c));
				}
			}
			else{
				if(((c.getMyX() == (checkCoordinateX - getWidth())) && ((checkCoordinateY == c.getMyY()) || (checkCoordinateY - getHeight() == c.getMyY())))  ||
						((c.getMyX() == (checkCoordinateX + getWidth())) && ((checkCoordinateY == c.getMyY()) || ((checkCoordinateY - getHeight() == c.getMyY())))) ||
						(c.getMyY() == (checkCoordinateY - getHeight()) && (checkCoordinateX == c.getMyX()))||
						(c.getMyY() == (checkCoordinateY + getHeight()) && (checkCoordinateX == c.getMyX()))){
					myNeighbors.add(myCoords.get(c));
				}
			}
		}
		
		cell.setNeighbors(myNeighbors);
	}

}
