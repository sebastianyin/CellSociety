package WatorSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Water extends WatorTypeState {
	
	public static final int myWatorValue = 0;

	@Override
	public int getWatorType() {
		return myWatorValue;
	}

	@Override
	public void updateState(WatorRule watorRule, int generations, Cell cell, Map<Coordinate, Cell> myCells) {
		return;
	}

	@Override
	public String getLabel() {
		return "Water";
	}

}
