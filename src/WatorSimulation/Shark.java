package WatorSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Shark extends WatorTypeState {
	
	WatorStateManager myManager;
	public static final int myWatorValue = 2;

	public Shark(WatorStateManager manager) {
		myManager = manager;
	}

	@Override
	public int getWatorType() {
		return myWatorValue;
	}

	@Override
	public void updateState(WatorRule watorRule, int generations, Cell cell, Map<Coordinate, Cell> myCells) {
		if(myManager.getMyGenerations() <= generations){ 
			watorRule.calculateSharkState(cell, myCells);
		}
		else{
			return;
		}
	}

	@Override
	public String getLabel() {
		return "Shark";
	}

}
