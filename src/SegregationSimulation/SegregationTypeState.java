package SegregationSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;
import society.State;

public abstract class SegregationTypeState extends State {
	
	public static final String[] POSSIBLE_STATES = {"Blue", "Empty", "Red"};
	
	public String[] getPossibleStates(){
		return POSSIBLE_STATES;
	}
	
	public int getValue(){
		return getMyType();
	}
	
	public abstract int getMyType();
	public abstract void updateState(SegregationRule rule, Cell cell, Map<Coordinate, Cell> myCells, double satisfyRatio, int generations);

	public boolean isSatisfied(Cell cell, double satisfyRatio) {
		int cellState = getMyType();

		int sameAgent = 0;
		int nonEmptyAgent = 0;

		for (Cell n: cell.getNeighbors()) {
			int neighborState = ((SegregationTypeState) n.getPreviousStates().getMyStates().get(0)).getMyType();
			if (neighborState == 0) {
				continue;
			}
			if (neighborState == cellState) {
				sameAgent++;
			}
			nonEmptyAgent++;
		}

		double sRatio = (double) sameAgent / nonEmptyAgent;
		return sRatio >= satisfyRatio;
	}
	
}
