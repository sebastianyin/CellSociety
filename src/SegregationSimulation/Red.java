package SegregationSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Red extends SegregationTypeState {
	
	SegregationStateManager myManager;

	public Red(SegregationStateManager manager) {
		myManager = manager;
	}

	@Override
	public int getMyType() {
		return 2;
	}

	@Override
	public void updateState(SegregationRule rule, Cell cell, Map<Coordinate, Cell> myCells, double satisfyRatio, int generations) {
		
		if(myManager.getMyGenerations() > generations){
			return;
		}
		
		if (isSatisfied(cell, satisfyRatio)) {
			return;
		}

		rule.move(cell, myCells);
		
	}

	@Override
	public String getLabel() {
		return "Red";
	}

}
