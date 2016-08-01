package SegregationSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import society.Cell;
import society.Coordinate;
import society.Rule;
import society.XMLObject;

public class SegregationRule extends Rule {

	private double[] myProportions = new double[3];
	String[] defaultProportions;
	private double satisfyRatio;
	private Random rndGenerator = new Random();
	
	public SegregationRule(XMLObject object){
		satisfyRatio = Double.parseDouble(object.getSatisfactionRate());
		defaultProportions = new String[3];
		defaultProportions[0] = ".1";
		defaultProportions[1] = ".45";
		defaultProportions[2] = ".45";
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
		myProportions[2] = Double.parseDouble(object.getProportionParams()[2]);
	}

	@Override
	public void determineNextState(Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		((SegregationStateManager) cell.getStates()).getMyState().updateState(this, cell, myCells, satisfyRatio, generations);
	}

	public void move(Cell cell, Map<Coordinate, Cell> myCells) {
		changeEmptySpotStates(cell, myCells);
		changeCellStates(cell);
	}

	private void changeCellStates(Cell cell) {
		int returnThis = 0;
		String[] currCellReturnValue = {returnThis + ""};

		cell.setMyStates(new SegregationStateManager
				(((SegregationStateManager)cell.getPreviousStates()), currCellReturnValue, 
						((SegregationStateManager)cell.getPreviousStates()).getMyGenerations() + 1));
	}

	private void changeEmptySpotStates(Cell cell, Map<Coordinate, Cell> myCells) {
		int returnThis = 0;

		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		findEmptyCells(myCells, emptyCells);

		rndGenerator = new Random();
		Cell pickEmpty = emptyCells.get(rndGenerator.nextInt(emptyCells.size()));

		returnThis = ((SegregationTypeState) cell.getStates().getMyStates().get(0)).getMyType();
		String[] emptyReturnValue = {returnThis + ""};

		pickEmpty.setMyStates(new SegregationStateManager
				(((SegregationStateManager)pickEmpty.getPreviousStates()),
						emptyReturnValue, 
						((SegregationStateManager)pickEmpty.getPreviousStates()).getMyGenerations() + 1));
	}

	private void findEmptyCells(Map<Coordinate, Cell> myCells, List<Cell> emptyCells) {
		for (Cell n: myCells.values()) {
			if(((SegregationStateManager) n.getStates()).getMyState().getMyType() == 0){
				emptyCells.add(n);
			}
		}
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
		satisfyRatio = Double.parseDouble(object.getSatisfactionRate());
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
		myProportions[2] = Double.parseDouble(object.getProportionParams()[2]);
	}

	@Override
	public String[] getMySliderValues() {
		String[] mySliderValues = {"satisfactionRate", "myProportions[0]", "myProportions[1]", "myProportions[2]"};
		return mySliderValues;
	}

	@Override
	public String[] getDefaultProportions() {
		return defaultProportions;
	}

}
