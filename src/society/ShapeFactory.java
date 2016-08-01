package society;

public class ShapeFactory {

	public ShapeFactory() {
		
	}

	public CellShape getShape(String name, int x, int y, int width, int height, String neighborsType){
		Neighbors neighborAlgorithm = createNeighbors(neighborsType);
		if(name.equals("square")){
			return new SquareCell(name, x, y, width, height, neighborAlgorithm);
		}
		else if (name.equals("triangle")){
			return new TriangleCell(name, x, y, width, height, neighborAlgorithm);
		}
		else if(name.equals("hexagon")){
			return new HexagonCell(name, x, y, width, height);
		}
		else{
			return null;
		}
	}
	
	public Neighbors createNeighbors(String type){
		if(type.equals("cardinal")){
			return new CardinalNeighbors();
		}
		else if(type.equals("all")){
			return new AllNeighbors();
		}
		else if(type.equals("diagonal")){
			return new DiagonalNeighbors();
		}
		else{
			return null;
		}
	}

}
