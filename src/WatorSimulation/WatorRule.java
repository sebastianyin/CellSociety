package WatorSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import society.Cell;
import society.Coordinate;
import society.Rule;
import society.XMLObject;

public class WatorRule extends Rule {

	private int sharkBirthRate;
	private int sharkDeathRate;
	private int fishBirthRate;
	private double[] myProportions = new double[3];
	String[] defaultProportions;
	
	private Random rdnGenerator = new Random();

	public WatorRule(XMLObject object){
		sharkBirthRate = Integer.parseInt(object.getSharkBirthRate());
		sharkDeathRate = Integer.parseInt(object.getSharkDeathRate());
		fishBirthRate = Integer.parseInt(object.getFishBirthRate());
		defaultProportions = new String[3];
		defaultProportions[0] = ".5";
		defaultProportions[1] = ".45";
		defaultProportions[2] = ".05";
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
		myProportions[2] = Double.parseDouble(object.getProportionParams()[2]);
	}

	@Override
	public void determineNextState(Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		if(myCells == null){
			System.out.println("my cells");
		}
		if(cell == null){
			System.out.println("cell");
		}
		if(myCells.get(cell) == null){
			System.out.println("getting my cells");
		}
		if(cell.getStates() == null){
			System.out.println("getting states");
		}
		if(cell.getStates().getMyStates().get(0).getValue() == 2){
			((WatorStateManager) cell.getStates()).getMyState().updateState(this, generations, cell, myCells);
		}
	}

	public void calculateSharkState(Cell cell, Map<Coordinate, Cell> myCells){

		List<Cell> fishNeighbors = findFishNeighbors(cell, myCells);
		List<Cell> emptyNeighbors = findEmptyNeighbors(cell, myCells);
		
		System.out.println(((WatorStateManager) cell.getStates()).getMyLastEatenCount());

		Cell shark = cell;
		if(fishNeighbors.size() >= 1){

			int choice = ((int) (rdnGenerator.nextDouble() * fishNeighbors.size()));
			Cell fish = fishNeighbors.get(choice);
			eatFish(fish, shark);
			checkForSharkBabies(shark, myCells);

		}
		else{
			if(((WatorStateManager) shark.getStates()).getMyLastEatenCount() > sharkDeathRate){

				System.out.println("shark should be dead");
				turnToEmpty(shark);

			}
			else if(emptyNeighbors.size() > 0){

				int choice = ((int) (rdnGenerator.nextDouble() * emptyNeighbors.size()));
				Cell empty = emptyNeighbors.get(choice);
				swapCells(empty, shark);
				checkForSharkBabies(empty, myCells);

			}
			else{

				increaseEatenCount(shark);

			}
		}
	}

	public void calculateFishState(Cell cell, Map<Coordinate, Cell> myCells){

		List<Cell> emptyNeighbors = findEmptyNeighbors(cell, myCells);

		Cell fish = cell;

		if(emptyNeighbors.size() > 0){

			int choice = ((int) (rdnGenerator.nextDouble() * emptyNeighbors.size()));
			Cell empty = emptyNeighbors.get(choice);
			swapCells(empty, fish);
			checkForFishBabies(empty, myCells);

		}
		else{

			increaseFishAge(fish);

		}
	}

	public void checkForFishBabies(Cell cell, Map<Coordinate, Cell> myCells){
		if(((WatorStateManager) cell.getStates()).getMyCount() >= fishBirthRate){

			((WatorStateManager) cell.getStates()).setMyCount(0);
			List<Cell> emptyNeighbors = findEmptyNeighbors(cell, myCells);

			if(emptyNeighbors.size() > 0){
				int choice = ((int) (rdnGenerator.nextDouble() * emptyNeighbors.size()));
				Cell newFish = emptyNeighbors.get(choice);
				makeBaby(cell, newFish);
			}
		}
	}

	public void checkForSharkBabies(Cell cell, Map<Coordinate, Cell> myCells){

		if(((WatorStateManager) cell.getStates()).getMyCount() >= sharkBirthRate){

			((WatorStateManager) cell.getStates()).setMyCount(0);
			List<Cell> emptyNeighbors = findEmptyNeighbors(cell, myCells);

			if(emptyNeighbors.size() > 0){
				int choice = ((int) (rdnGenerator.nextDouble() * emptyNeighbors.size()));
				Cell newShark = emptyNeighbors.get(choice);
				makeBaby(cell, newShark);
			}
		}
	}

	public void eatFish(Cell fish, Cell shark){

		turnToEmpty(fish);
		resetEatenCount(shark);

	}

	public void turnToEmpty(Cell animal){

		String[] params = {"0"};
		constructStateManager(animal, 
				params, 
				((WatorStateManager) animal.getStates()).getMyGenerations() + 1, 
				0, 
				0);

	}

	public void resetEatenCount(Cell shark){

		constructStateManager(shark, 
				shark.getStates().getMyParams(), 
				((WatorStateManager) shark.getStates()).getMyGenerations() + 1, 
				((WatorStateManager) shark.getStates()).getMyCount() + 1, 
				0);
	}

	public void increaseEatenCount(Cell shark){

		constructStateManager(shark, 
				((WatorStateManager) shark.getStates()).getMyParams(), 
				((WatorStateManager) shark.getStates()).getMyGenerations() + 1, 
				((WatorStateManager) shark.getStates()).getMyCount() + 1, 
				((WatorStateManager) shark.getStates()).getMyLastEatenCount() + 1);
	}

	public void increaseFishAge(Cell fish){

		constructStateManager(fish, 
				((WatorStateManager) fish.getStates()).getMyParams(), 
				((WatorStateManager) fish.getStates()).getMyGenerations() + 1, 
				((WatorStateManager) fish.getStates()).getMyCount() + 1, 
				0);

	}

	public void swapCells(Cell empty, Cell animal){
		
		String[] param = ((WatorStateManager) empty.getStates()).getMyParams();

		constructStateManager(empty, 
				animal.getStates().getMyParams(), 
				((WatorStateManager) animal.getStates()).getMyGenerations() + 1,
				((WatorStateManager) animal.getStates()).getMyCount() + 1, 
				((WatorStateManager) animal.getStates()).getMyLastEatenCount() + 1);

		constructStateManager(animal, 
				param, 
				((WatorStateManager) empty.getPreviousStates()).getMyGenerations() + 1, 
				0, 
				0);

	}

	public void makeBaby(Cell parent, Cell baby){

		constructStateManager(baby, 
				parent.getStates().getMyParams(), 
				((WatorStateManager) parent.getStates()).getMyGenerations(), 
				0, 
				0);
	}

	public void constructStateManager(Cell cell, String[] params, int generations, int babyCount, int lastEaten){

		cell.setMyStates(new WatorStateManager
				(((WatorStateManager) cell.getStates()),
						params,
						generations,
						babyCount,
						lastEaten));
	}

	public List<Cell> findFishNeighbors(Cell cell, Map<Coordinate, Cell> myCells){

		ArrayList<Cell> fishNeighbors = new ArrayList<Cell>();
		for (Cell n : cell.getNeighbors()) {
			if(((WatorStateManager) n.getStates()).getMyState().getWatorType() == 1){
				fishNeighbors.add(n);
			}
		}
		return fishNeighbors;
	}

	public List<Cell> findEmptyNeighbors(Cell cell, Map<Coordinate, Cell> myCells){

		ArrayList<Cell> emptyNeighbors = new ArrayList<Cell>();
		for (Cell n : cell.getNeighbors()) {
			if(((WatorStateManager) n.getStates()).getMyState().getWatorType() == 0){
				emptyNeighbors.add(n);
			}
		}
		return emptyNeighbors;
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
		if(((WatorStateManager) cell.getStates()).getMyState().getWatorType() == 1){
			((WatorStateManager) cell.getStates()).getMyState().updateState(this, generations, cell, myCells);
		}
	}

	@Override
	public void update(XMLObject object) {
		sharkBirthRate = (int) Double.parseDouble(object.getSharkBirthRate());
		sharkDeathRate = (int) Double.parseDouble(object.getSharkDeathRate());
		fishBirthRate = (int) Double.parseDouble(object.getFishBirthRate());
		myProportions[0] = Double.parseDouble(object.getProportionParams()[0]);
		myProportions[1] = Double.parseDouble(object.getProportionParams()[1]);
		myProportions[2] = Double.parseDouble(object.getProportionParams()[2]);
	}

	@Override
	public String[] getMySliderValues() {
		String[] mySliderValues = {"sharkBirthRate", "sharkDeathRate", "fishBirthRate", "myProportions[0]", "myProportions[1]", "myProportions[2]"};
		return mySliderValues;
	}

	@Override
	public String[] getDefaultProportions() {
		return defaultProportions;
	}

}
