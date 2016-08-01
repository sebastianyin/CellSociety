package FireSimulation;

import java.util.Map;
import java.util.Random;

import society.Cell;
import society.Coordinate;
import society.Rule;
import society.XMLObject;

public class FireRule extends Rule {

	private double[] myProportions = new double[3];
	String[] defaultProportions;
	private double myProbability;
	private Random RdnGenerator = new Random();

	public FireRule(XMLObject object){
		myProbability = Double.parseDouble(object.getBurnProbability());
		defaultProportions = new String[3];
		defaultProportions[0] = "0";
		defaultProportions[1] = ".99";
		defaultProportions[2] = ".01";
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
		myProportions[2] = Double.parseDouble(object.getProportionParams()[2]);
	}

	@Override
	public void determineNextState(Cell cell, Map<Coordinate, Cell> myCells, int generations) {

		constructStates
		(cell, ((FireState) ((FireStateManager) cell.getStates()).getMyState()).updateStates(this, cell, myCells, generations));

	}

	public void constructStates(Cell cell, String[] params){
		cell.setMyStates(new FireStateManager (((FireStateManager) cell.getPreviousStates()), params));
	}

	public int checkCatchFire(Cell cell) {
		int fireValue = 1;
		int burningNeighbors = isNeighborBurning(cell);
		if(burningNeighbors > 0) {
			double myNewProbability = calculateProbability(burningNeighbors);
			if (RdnGenerator.nextDouble() < myNewProbability) {
				fireValue = 2;
			}
		}
		return fireValue;
	}

	private int isNeighborBurning(Cell cell) {
		int burningNeighbors = 0;
		for (Cell n : cell.getNeighbors()) {
			if(n.getPreviousStates().getMyStates().size() > 0){
				if (n.getPreviousStates().getMyStates().get(0).getValue() == 2) {
					burningNeighbors++;
				}
			}
		}
		return burningNeighbors;
	}

	/* 
	 * 1 - myProbability is the probability to not burn
	 * 1 - (1 -myProbability)^burningCells is the probability of the cell to burn
	 * depending on how many burning neighbors it has
	 */
	private double calculateProbability(int burningCells){
		double myNewProbability = 1 - (Math.pow((1 - myProbability), burningCells));
		return myNewProbability;
	}

	public double[] getMyProportions(){
		return myProportions;
	}
	
	public String[] setMyProportionAndGetNew(int i, double newValue){
		myProportions[i] = newValue;
		String[] myReturn = new String[myProportions.length];
		for(int j = 0; j < myReturn.length; j++){
			myReturn[j] = myProportions[j] + "";
		}
		return myReturn;
	}

	@Override
	public void determineNextStateAgain(Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		return;
	}

	@Override
	public void update(XMLObject object) {
		myProbability = Double.parseDouble(object.getBurnProbability());
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
		myProportions[2] = Double.parseDouble(object.getProportionParams()[2]);
	}

	@Override
	public String[] getMySliderValues() {
		String[] mySliderValues = {"burnProbability"};
		return mySliderValues;
	}

	@Override
	public String[] getDefaultProportions() {
		return defaultProportions;
	}

}
