package ForagingAntsSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Ant extends ForagingAntsTypeState {
	
	private int hasFood;	// 1-hasFood, 0-no Food
	private int myId;
	
	public Ant(int food, int id){
		setHasFood(food);
		myId = id;
	}
	
	@Override
	public int getMyType() {
		return 3;
	}

	@Override
	public void updateState(ForagingAntsRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		if(generations < ((ForagingAntsStateManager) cell.getStates()).getMyGenerations()){
			return;
		}
		else{
			if(hasFood < 10){
				rule.updateAntFoodward(myId, cell, myCells);
			}
			else{
				rule.updateAntHomeward(myId, cell, myCells);
			}
		}
	}

	@Override
	public String getLabel() {
		return "ant";
	}

	public int getHasFood() {
		return hasFood;
	}

	public void setHasFood(int hasFood) {
		this.hasFood = hasFood;
	}

	public int getOrientation() {
		if(hasFood >= 10){
			return hasFood - 10;
		}
		else{
			return hasFood;
		}
	}

}
