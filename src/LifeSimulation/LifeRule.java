package LifeSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;
import society.Rule;
import society.XMLObject;

public class LifeRule extends Rule {
	
	private double[] myProportions = new double[2];
	String[] defaultProportions;

	public LifeRule(XMLObject object){
		
		defaultProportions = new String[2];
		defaultProportions[0] = ".5";
		defaultProportions[1] = ".5";
		object.setProportionParams(defaultProportions);
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
	}

	@Override
	public void determineNextState(Cell cell, Map<Coordinate, Cell> myCells, int generations) {

		int liveNeighbors = countLiveNeighbors(cell, myCells);
		((LifeState) cell.getPreviousStates().getMyStates().get(0)).updateStates(this, cell, liveNeighbors);

	}

	public int countLiveNeighbors(Cell cell, Map<Coordinate, Cell> myCells){

		int liveNeighbors = 0;
		for(int i = 0; i < cell.getNeighbors().size(); i++){
			liveNeighbors += ((LifeStateManager) cell.getNeighbors().get(i).getPreviousStates()).getLife().getMyLife();
		}
		if(liveNeighbors > 0){
			System.out.println("me " + cell.getPreviousStates().getLocation().getMyX() + " " + 
					cell.getPreviousStates().getLocation().getMyY() + " and my neighbors " + liveNeighbors);
			for(int i = 0; i < cell.getNeighbors().size(); i++){
				if(((LifeStateManager) cell.getNeighbors().get(i).getPreviousStates()).getLife().getMyLife() == 1){
					System.out.println(cell.getNeighbors().get(i).getPreviousStates().getLocation().getMyX() + " "
							+ cell.getNeighbors().get(i).getPreviousStates().getLocation().getMyY());
				}
			}
		}
		return liveNeighbors;
	}

	public void updateLiveCell(Cell cell, int liveNeighbors){
		int returnThis;
		if ((liveNeighbors < 2) || (liveNeighbors > 3)){
			returnThis = 0;
		}
		else{
			returnThis = 1;
		}

		String[] returnValues = {returnThis + ""};
		createConstructor(cell, returnValues);
	}

	public void updateDeadCell(Cell cell, int liveNeighbors){
		int returnThis = 0;
		if(liveNeighbors == 3){
			returnThis = 1;
		}
		String[] returnValues = {returnThis + ""};
		createConstructor( cell, returnValues);
	}

	public void createConstructor(Cell cell, String[] params){
		cell.setMyStates(new LifeStateManager
				((LifeStateManager) cell.getPreviousStates(), params));
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
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
	}

	@Override
	public String[] getMySliderValues() {
		String[] mySliderValues = {"myProportions[0]", "myProportions[1]"};
		return mySliderValues;
	}

	@Override
	public String[] getDefaultProportions() {
		return defaultProportions;
	}

}
