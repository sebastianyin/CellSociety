package ForagingAntsSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import society.Cell;
import society.Coordinate;
import society.Rule;
import society.XMLObject;

public class ForagingAntsRule extends Rule {

	private double[] myProportions = new double[2];
	String[] defaultProportions;
	
	private double maximumAntsPerCell;

	private int generations;
	
	private XMLObject myObject;
	
	private int foodPheromones;
	private int homePheromones;

	public ForagingAntsRule(XMLObject object){
		myObject = object;
		maximumAntsPerCell = Double.parseDouble(object.getMaximumAntsPerCell());
		foodPheromones = (int) Double.parseDouble(object.getFoodPheromone());
		homePheromones = (int) Double.parseDouble(object.getHomePheromone());
		defaultProportions = new String[2];
		defaultProportions[0] = ".5";
		defaultProportions[1] = "1";
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
	}

	@Override
	public void determineNextState(Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		this.generations = generations;
		if(cell.getStates().getMyStates().size() > 1){
			System.out.println("mysize: " + cell.getStates().getMyStates().size());
			for(int i = 1; i < cell.getStates().getMyStates().size(); i++){
				((ForagingAntsTypeState) cell.getStates().getMyStates().get(i)).updateState(this, cell, myCells, generations);
			}
		}
	}
	
	public boolean isWithin(Cell cell, int id, Cell neighborCell){
		
		List<int[]> myDirectionalNeighbors = new ArrayList<int[]>();
		
		int x = (myObject.getWidth()/myObject.getCellsWide());
		int y = (myObject.getHeight()/myObject.getCellsHigh());
		
		int[] north = {-x , -y, 0, -y, +x, -y};
		int[] northEast = {0, -y, +x, -y, +x, 0};
		int[] east = {+x, -y, +x, 0, +x, +y};
		int[] southEast = {+x, 0, +x, +y, 0, +y};
		int[] south = {+x, +y, 0, +y, -x, +y};
		int[] southWest = {0, +y, -x, +y, -x, 0};
		int[] west = {-x, +y, -x, 0, -x, -y};
		int[] northWest = {-x, 0, -x, -y, 0, -y};
		
		myDirectionalNeighbors.add(north);
		myDirectionalNeighbors.add(northEast);
		myDirectionalNeighbors.add(east);
		myDirectionalNeighbors.add(southEast);
		myDirectionalNeighbors.add(south);
		myDirectionalNeighbors.add(southWest);
		myDirectionalNeighbors.add(west);
		myDirectionalNeighbors.add(northWest);
		
		//0-N, 1-NE, 2-E, 3-SE, 4-S, 5-SW, 6-W, 7-NW
		
		int neighborX = neighborCell.getStates().getLocation().getMyX();
		int neighborY = neighborCell.getStates().getLocation().getMyY();
		int cellX = cell.getStates().getLocation().getMyX();
		int cellY = cell.getStates().getLocation().getMyY();
		
		for(int i = 0; i < 8; i++){
			if(((Ant) cell.getStates().getMyStates().get(id)).getHasFood() == i 
					|| ((Ant) cell.getStates().getMyStates().get(id)).getHasFood() ==  (10 + i)){
				if(((((neighborX  + myDirectionalNeighbors.get(i)[0]) == cellX) && 
						(neighborY + myDirectionalNeighbors.get(i)[1]) == cellY)) || 
				((((neighborX + myDirectionalNeighbors.get(i)[2]) == cellX) && 
						(neighborY + myDirectionalNeighbors.get(i)[3]) == cellY)) || 
				((((neighborX + myDirectionalNeighbors.get(i)[4]) == cellX) && 
						(neighborY + myDirectionalNeighbors.get(0)[5]) == cellY)))  {
									return true;
				}
				else{
					return false;
				}
			}
		}
		return false;
	}

	public void updateAntFoodward(int id, Cell cell, Map<Coordinate, Cell> myCells){

		List<Cell> availableForwardNeighbors = new ArrayList<Cell>();
		for(int i = 0; i < cell.getNeighbors().size(); i++){
			if(cell.getNeighbors().get(i).getStates().getMyStates().get(0).getValue() != 1 && 
					cell.getNeighbors().get(i).getStates().getMyParams().length < maximumAntsPerCell + 1 &&
					isWithin(cell, id, cell.getNeighbors().get(i))){
				availableForwardNeighbors.add(cell.getNeighbors().get(i));
				System.out.println("yes!");
			}
		}
		
		double foodAttract = .2;
		Cell moveForwardToHere = null;
		for(Cell c : availableForwardNeighbors){
			if (((ForagingAntsStateManager) c.getStates()).getFoodPheromones() > foodAttract){
				foodAttract = ((ForagingAntsStateManager) c.getStates()).getFoodPheromones();
				moveForwardToHere = c;
			}
		}
		if(moveForwardToHere != null){
			moveAnt(id, cell, moveForwardToHere);
			return;
		}
		
		List<Cell> availableNeighbors = new ArrayList<Cell>();
		for(int i = 0; i < cell.getNeighbors().size(); i++){
			if(cell.getNeighbors().get(i).getStates().getMyStates().get(0).getValue() != 1 && 
					cell.getNeighbors().get(i).getStates().getMyParams().length < maximumAntsPerCell + 1){
				availableNeighbors.add(cell.getNeighbors().get(i));
			}
		}

		foodAttract = 0;
		Cell moveToHere = null;
		for(Cell c : availableNeighbors){
			if (((ForagingAntsStateManager) c.getStates()).getFoodPheromones() > foodAttract){
				foodAttract = ((ForagingAntsStateManager) c.getStates()).getFoodPheromones();
				moveToHere = c;
			}
		}
		if(moveToHere != null){
			moveAnt(id, cell, moveToHere);
		}
		else if(availableForwardNeighbors.size() > 0){
			double random = Math.random();
			int choice = (int) (random * availableForwardNeighbors.size());
			moveAnt(id, cell, availableForwardNeighbors.get(choice));
		}
		else if(availableNeighbors.size() > 0){
			double random = Math.random();
			int choice = (int) (random * availableNeighbors.size());
			moveAnt(id, cell, availableNeighbors.get(choice));
		}
		else{
			return;
		}
	}

	public void updateAntHomeward(int id, Cell cell, Map<Coordinate, Cell> myCells){
		
		List<Cell> availableForwardNeighbors = new ArrayList<Cell>();
		for(int i = 0; i < cell.getNeighbors().size(); i++){
			if(cell.getNeighbors().get(i).getStates().getMyStates().get(0).getValue() != 1 && 
					cell.getNeighbors().get(i).getStates().getMyParams().length < maximumAntsPerCell + 1 &&
					isWithin(cell, id, cell.getNeighbors().get(i))){
				availableForwardNeighbors.add(cell.getNeighbors().get(i));
				System.out.println("yes!");
			}
		}
		double homeAttract = .2;
		Cell moveForwardToHere = null;
		for(Cell c : availableForwardNeighbors){
			if (((ForagingAntsStateManager) c.getStates()).getFoodPheromones() > homeAttract){
				homeAttract = ((ForagingAntsStateManager) c.getStates()).getFoodPheromones();
				moveForwardToHere = c;
			}
		}
		if(moveForwardToHere != null){
			moveAnt(id, cell, moveForwardToHere);
			return;
		}

		List<Cell> availableNeighbors = new ArrayList<Cell>();
		for(int i = 0; i < cell.getNeighbors().size(); i++){
			if(cell.getNeighbors().get(i).getStates().getMyParams().length < maximumAntsPerCell + 1){
				availableNeighbors.add(cell.getNeighbors().get(i));
			}
		}

		homeAttract = 0;
		Cell moveToHere = null;
		for(Cell c : availableNeighbors){
			if (((ForagingAntsStateManager) c.getStates()).getHomePheromones() > homeAttract){
				homeAttract = ((ForagingAntsStateManager) c.getStates()).getHomePheromones();
				moveToHere = c;
			}
		}
		if(moveToHere != null){
			moveAnt(id, cell, moveToHere);
		}
		else if(availableForwardNeighbors.size() > 0){
			double random = Math.random();
			int choice = (int) (random * availableForwardNeighbors.size());
			moveAnt(id, cell, availableForwardNeighbors.get(choice));
		}
		else if(availableNeighbors.size() > 0){
			double random = Math.random();
			int choice = (int) (random * availableNeighbors.size());
			moveAnt(id, cell, availableNeighbors.get(choice));
		}
		else{
			return;
		}
	}

	public void createAnt(Cell cell){

		String [] newParams = new String[cell.getStates().getMyParams().length + 1];

		for(int i = 0; i < cell.getStates().getMyParams().length; i++){
			newParams[i] = cell.getStates().getMyParams()[i];
		}
		
		double choice = Math.random();
		int antValue = (int) (choice * 8);

		newParams[newParams.length - 1] = antValue + "";

		int nextFoodPheromone = ((ForagingAntsStateManager) cell.getStates()).getFoodPheromones();

		int nextHomePheromone = updateHomePheromoneValue(cell);

		cell.setMyStates(new ForagingAntsStateManager(
				(ForagingAntsStateManager) cell.getStates(),  newParams, 
				generations, nextFoodPheromone, nextHomePheromone));
	}

	public void moveAnt(int id, Cell cell, Cell moveToHere){

		String[] moveToParams = new String[moveToHere.getStates().getMyParams().length + 1];

		for(int i = 0; i < moveToHere.getStates().getMyParams().length; i++){
			moveToParams[i] = moveToHere.getStates().getMyParams()[i];
		}

		moveToParams[moveToParams.length - 1] = cell.getStates().getMyParams()[id];	

		String[] movedFromParams = new String[cell.getStates().getMyParams().length - 1];

		int j = 0;
		for(int i = 0; i < cell.getStates().getMyParams().length - 1; i++){
			if(j == id){
				j++;
			}
			movedFromParams[i] = cell.getStates().getMyParams()[j];
			j++;
		}

		if(moveToHere.getStates().getMyStates().get(0).getValue() == 2){
			moveToParams[moveToParams.length - 1] = (((Integer.parseInt(cell.getStates().getMyParams()[id])) + 10) + "");
		}

		if(moveToHere.getStates().getMyStates().get(0).getValue() == 1){
			String[] tempMoveToParams = moveToParams;
			moveToParams = new String[tempMoveToParams.length - 1];
			for(int i = 0; i < moveToParams.length; i++){
				moveToParams[i] = tempMoveToParams[i];
			}
		}

		cell.setMyStates(new ForagingAntsStateManager(
				(ForagingAntsStateManager) cell.getStates(), movedFromParams, generations, 
				((ForagingAntsStateManager) cell.getStates()).getFoodPheromones(), 
				((ForagingAntsStateManager) cell.getStates()).getHomePheromones()));

		int nextFoodPheromone = ((ForagingAntsStateManager) moveToHere.getStates()).getFoodPheromones();
		if (Integer.parseInt(moveToParams[moveToParams.length - 1]) >= 10){
			nextFoodPheromone = updateFoodPheromoneValue(moveToHere);
		}

		int nextHomePheromone = ((ForagingAntsStateManager) moveToHere.getStates()).getHomePheromones();
		if (Integer.parseInt(moveToParams[moveToParams.length - 1]) < 10){
			nextHomePheromone = updateHomePheromoneValue(moveToHere);
		}

		moveToHere.setMyStates(new ForagingAntsStateManager(
				(ForagingAntsStateManager) moveToHere.getStates(), moveToParams, 
				((ForagingAntsStateManager) cell.getStates()).getMyGenerations(), 
				nextFoodPheromone, nextHomePheromone));
	}

	@Override
	public List<String[]> getRandomGrid(XMLObject object) {

		List<String[]> myRandomArray = new ArrayList<String[]>();

		for(int i = 0; i < (object.getCellsHigh() * object.getCellsWide()); i++){
			int selection = 0;
			String[] value = {selection + ""};
			myRandomArray.add(value);
		}

		double choice = Math.random();

		int nestIndex = (int) (choice * ((object.getCellsHigh() * object.getCellsWide())));
		String[] nestValue = {"1"};
		myRandomArray.add(nestIndex, nestValue);

		double anotherChoice = Math.random();
		int foodIndex = (int) (anotherChoice * ((object.getCellsHigh() * object.getCellsWide())));
		String[] foodValue = {"2"};
		myRandomArray.add(foodIndex, foodValue);

		for(int i = 0; i < myRandomArray.size(); i++){
			System.out.println(i + " " + myRandomArray.get(i)[0]);
		}

		return myRandomArray;
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
	public void determineNextStateAgain(Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		if(cell.getStates().getMyStates().get(0).getValue() == 1){
			((ForagingAntsTypeState) cell.getStates().getMyState()).updateState(this, cell, myCells, generations);
		}
	}

	public int updateFoodPheromoneValue(Cell cell){
		int foodHigh = 0;
		for(Cell c : cell.getNeighbors()){
			if(((ForagingAntsStateManager) c.getStates()).getFoodPheromones() > foodHigh){
				foodHigh = ((ForagingAntsStateManager) c.getStates()).getFoodPheromones();
			}
		}
		return foodHigh - 1;
	}

	public int updateHomePheromoneValue(Cell cell){
		int homeHigh = 0;
		for(Cell c : cell.getNeighbors()){
			if(((ForagingAntsStateManager) c.getStates()).getHomePheromones() > homeHigh){
				homeHigh = ((ForagingAntsStateManager) c.getStates()).getHomePheromones();
			}
		}

		return homeHigh - 1;
	}

	@Override
	public void update(XMLObject object) {
		maximumAntsPerCell = Double.parseDouble(object.getMaximumAntsPerCell());
		foodPheromones = (int) Double.parseDouble(object.getFoodPheromone());
		homePheromones = (int) Double.parseDouble(object.getHomePheromone());
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
	}

	@Override
	public String[] getMySliderValues() {
		String[] mySliderValues = {"maximumAntsPerCell", "foodPheromones", "homePheromones"};
		return mySliderValues;
	}

	public int getFoodPheromones() {
		return foodPheromones;
	}

	public void setFoodPheromones(int foodPheromones) {
		this.foodPheromones = foodPheromones;
	}

	public int getHomePheromones() {
		return homePheromones;
	}

	public void setHomePheromones(int homePheromones) {
		this.homePheromones = homePheromones;
	}

	@Override
	public String[] getDefaultProportions() {
		return defaultProportions;
	}

}
