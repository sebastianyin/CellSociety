package WatorSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Fish extends WatorTypeState {
	
	public static final int myWatorValue = 1;

	@Override
	public int getWatorType() {
		return myWatorValue;
	}

	@Override
	public void updateState(WatorRule watorRule, int generations, Cell cell, Map<Coordinate, Cell> myCells) {
		if(((WatorStateManager) cell.getStates()).getMyGenerations() <= generations){
			watorRule.calculateFishState(cell, myCells);
		}
		else{
			return;
		}
		
	}

	@Override
	public String getLabel() {
		return "Fish";
	}

}
