package society;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Rule {
	
	public abstract void determineNextState(Cell cell, Map<Coordinate, Cell> myCells, int generations);
	public abstract void determineNextStateAgain(Cell cell, Map<Coordinate, Cell> myCells, int generations);
	public abstract void update(XMLObject object);
	
	public abstract double[] getMyProportions();
	public abstract String[] getDefaultProportions();
	public abstract String[] getMySliderValues();
	public abstract String[] setMyProportionAndGetNew(int i, double newValue);
	
	public List<String[]> getRandomGrid(XMLObject object) {
		
		double[] myProportions = getMyProportions();
		
		List<String[]> myRandomArray = new ArrayList<String[]>();

		for(int i = 0; i < ((object.getCellsHigh()) * (object.getCellsWide())); i++){
			double choice = Math.random();
			int selection = 0;
			if(choice < myProportions[0]){
				selection = 0;
			}
			else if(choice < myProportions[1] + myProportions[0]){
				selection = 1;
			}
			else{
				selection = 2;
			}
			String[] value = {selection + ""};
			myRandomArray.add(value);
		}
		
		for(int i = 0; i < myRandomArray.size(); i++){
			System.out.println(i + " " + myRandomArray.get(i)[0]);
		}
		
		return myRandomArray;
	}
	
	public List<ArrayList<String[]>> getRandomInnerParams(XMLObject object, List<String[]> grid){
		return null;
	}
	
}
