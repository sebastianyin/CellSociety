package SugarscapeSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import FireSimulation.FireStateManager;
import society.Cell;
import society.Coordinate;
import society.Rule;
import society.XMLObject;

public class SugarscapeRule extends Rule{

	private double[] myProportions = new double[3];
	String[] defaultProportions;
	
	private Random rdnGenerator = new Random();
	
	private double mySugarGrowBackRate;
	private double mySugarGrowBackInterval;
	private double myPatchMaxCapacity;
	private double myAgentMetabolism;
	private double myAgentVision;

	public SugarscapeRule(XMLObject object) {
		mySugarGrowBackRate = Double.parseDouble(object.getSugarGrowthRate());
		mySugarGrowBackInterval = Double.parseDouble(object.getSugarGrowthInterval());
		myPatchMaxCapacity = Integer.parseInt(object.getPatchMaxCapacity());
		myAgentMetabolism = Integer.parseInt(object.getAgentMetabolism());
		myAgentVision = Integer.parseInt(object.getAgentVision());
		defaultProportions = new String[3];
		defaultProportions[0] = ".5";
		defaultProportions[1] = "1";
		defaultProportions[2] = ".7";
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
		myProportions[2] = Double.parseDouble(object.getProportionParams()[2]);
	}

	@Override
	public void determineNextState(Cell cell, Map<Coordinate, Cell> myCells, int generations) {

		for(int i = 1; i < cell.getStates().getMyStates().size(); i++){
			System.out.println(i + " " + cell.getStates().getMyStates().get(i).getValue() + " " + generations);
			((Agent) cell.getStates().getMyStates().get(i)).updateState(this, cell, myCells, generations);
		}

	}

	@Override
	public void determineNextStateAgain(Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		((Patch) cell.getStates().getMyStates().get(0)).updateState(this, cell, myCells, generations);
	}

	public void updateAgent(int id, Cell cell, Map<Coordinate, Cell> myCells){
		System.out.println("called");
		int largestSugarValue = -1;
		for(Cell c : cell.getNeighbors()){
			if(((Patch) c.getStates().getMyStates().get(0)).getMySugarLevel() > largestSugarValue ){
				largestSugarValue = (int) ((Patch) c.getStates().getMyStates().get(0)).getMySugarLevel();
			}
		}
		List<Cell> movingOptions = new ArrayList<Cell>();
		for(Cell c : cell.getNeighbors()){
			if(((Patch) c.getStates().getMyStates().get(0)).getMySugarLevel() == largestSugarValue ){
				movingOptions.add(c);
			}
		}

		double randomNumber = rdnGenerator.nextDouble();
		Cell moveHere = movingOptions.get((int) (randomNumber * movingOptions.size()));
		addAgent(id, cell, moveHere);
	}

	public void addAgent(int id, Cell cell, Cell moveHere){

		String[] moveToParams = new String[moveHere.getStates().getMyParams().length + 1];
		ArrayList<String[]> moveToInnerParams = new ArrayList<String[]>();

		int moveToSugarLevel = Integer.parseInt(moveHere.getStates().getMyParams()[0]);

		moveToParams[0] = "0";
		moveToInnerParams.add(((SugarscapeStateManager) moveHere.getStates()).getInnerList().get(0)); 

		for(int i = 1; i < moveHere.getStates().getMyParams().length; i++){
			moveToParams[i] = moveHere.getStates().getMyParams()[i];
			moveToInnerParams.add(((SugarscapeStateManager) moveHere.getStates()).getInnerList().get(i)); 
		}

		int newSugarValue = Integer.parseInt(cell.getStates().getMyParams()[id]) + moveToSugarLevel - 
				((Agent) cell.getStates().getMyStates().get(id)).getMySugarMetabolism();
		if(newSugarValue > 0){
			moveToParams[moveToParams.length - 1] = newSugarValue + "";	
			moveToInnerParams.add(((SugarscapeStateManager) cell.getStates()).getInnerList().get(id)); 
		}
		else{
			String[] tempMoveToParams = moveToParams;
			moveToParams = new String[tempMoveToParams.length - 1];
			for(int i = 0; i < moveToParams.length; i++){
				moveToParams[i] = tempMoveToParams[i];
			}
		}

		String[] movedFromParams = new String[cell.getStates().getMyParams().length - 1];
		ArrayList<String[]> movedFromInnerParams = new ArrayList<String[]>();

		int j = 0;
		for(int i = 0; i < cell.getStates().getMyParams().length - 1; i++){
			if(j == id){
				j++;
			}
			movedFromParams[i] = cell.getStates().getMyParams()[j];
			movedFromInnerParams.add(((SugarscapeStateManager) cell.getStates()).getInnerList().get(j)); 
			j++;
		}

		cell.setMyStates(new SugarscapeStateManager((SugarscapeStateManager) cell.getStates(), movedFromParams, 
				movedFromInnerParams, ((SugarscapeStateManager) cell.getStates()).getMyGenerations(), 
				((SugarscapeStateManager) cell.getStates()).getMyIntervalCount()));
		moveHere.setMyStates(new SugarscapeStateManager((SugarscapeStateManager) moveHere.getStates(), moveToParams, 
				moveToInnerParams, ((SugarscapeStateManager) moveHere.getStates()).getMyGenerations() + 1,
				((SugarscapeStateManager) cell.getStates()).getMyIntervalCount()));
	}

	public void constructStates(Cell cell, String[] params){
		cell.setMyStates(new FireStateManager (((FireStateManager) cell.getPreviousStates()), params));
	}

	@Override
	public double[] getMyProportions() {
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
	public List<String[]> getRandomGrid(XMLObject object) {

		//double[] myProportions = getMyProportions();

		List<String[]> myRandomArray = new ArrayList<String[]>();

		for(int i = 0; i < (object.getCellsHigh() * object.getCellsWide()); i++){
			double choice = Math.random();
			int selectionOne = (int) (myPatchMaxCapacity * choice);
			double anotherChoice = Math.random();
			String[] value;
			if(anotherChoice > .7){
				int selectionTwo = (int) (5 * anotherChoice);
				value = new String[2];
				value[0] = selectionOne + "";
				value[1] = selectionTwo + "";
			}
			else{
				value = new String[1];
				value[0] = selectionOne + "";
			}
			myRandomArray.add(value);
		}
		return myRandomArray;
	}

	@Override
	public List<ArrayList<String[]>> getRandomInnerParams(XMLObject object, List<String[]> grid){

		List<ArrayList<String[]>> myRandomInnerParams = new ArrayList<ArrayList<String[]>>();
		for(int i = 0; i < grid.size(); i++){
			ArrayList<String[]> baseInnerParamList = new ArrayList<String[]>();
			if(grid.get(i).length == 0){
				String[] myCapacity = {object.getValues().get(i)[0] + ""};
				baseInnerParamList.add(myCapacity);
			}
			else if(grid.get(i).length > 0){
				String[] myCapacity = {object.getValues().get(i)[0] + ""};
				baseInnerParamList.add(myCapacity);
				for(int j = 0; j < grid.get(i).length; j++){
					double randomMet = Math.random();
					int metabolism = ((int) (randomMet * myAgentMetabolism) + 1);
					double randomVis = Math.random();
					int vision = ((int) (randomVis * myAgentVision) + 1); 
					String[] myAgentStuff = {metabolism + "", vision + ""};
					baseInnerParamList.add(myAgentStuff);
				}
			}
			myRandomInnerParams.add(baseInnerParamList);
		}
		return myRandomInnerParams;
	}

	@Override
	public void update(XMLObject object) {
		mySugarGrowBackRate = Double.parseDouble(object.getSugarGrowthRate());
		mySugarGrowBackInterval = Double.parseDouble(object.getSugarGrowthInterval());
		myPatchMaxCapacity = Integer.parseInt(object.getPatchMaxCapacity());
		myAgentMetabolism = Integer.parseInt(object.getAgentMetabolism());
		myAgentVision = Integer.parseInt(object.getAgentVision());
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
		myProportions[2] = Double.parseDouble(object.getProportionParams()[2]);
	}

	@Override
	public String[] getMySliderValues() {
		String[] mySliderValues = {"sugarGrowthRate", "sugarGrowthInterval", "patchMaxCapacity", "agentMetabolism",
				"agentVision", "myProportions[0]", "myProportions[1]", "myProportions[2]"};
		return mySliderValues;
	}

	public double getMySugarGrowBackInterval() {
		return mySugarGrowBackInterval;
	}

	public void setMySugarGrowBackInterval(double mySugarGrowBackInterval) {
		this.mySugarGrowBackInterval = mySugarGrowBackInterval;
	}

	public double getMySugarGrowBackRate() {
		return mySugarGrowBackRate;
	}

	public void setMySugarGrowBackRate(double mySugarGrowBackRate) {
		this.mySugarGrowBackRate = mySugarGrowBackRate;
	}

	@Override
	public String[] getDefaultProportions() {
		return defaultProportions;
	}

}
