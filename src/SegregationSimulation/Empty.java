package SegregationSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Empty extends SegregationTypeState {
	
	SegregationStateManager myManager;

	public Empty(SegregationStateManager manager) {
		myManager = manager;
	}

	@Override
	public int getMyType() {
		return 0;
	}

	@Override
	public void updateState(SegregationRule rule, Cell cell, Map<Coordinate, Cell> myCells, double satisfyRatio, int generations) {
		return;
	}

	@Override
	public String getLabel() {
		return "Empty";
	}

}
