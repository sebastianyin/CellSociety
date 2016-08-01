package SugarscapeSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;
import society.State;

public abstract class SugarscapeTypeState extends State {
	
	public static final String[] POSSIBLE_STATES = {"Agent", "Patch"};

	@Override
	public String[] getPossibleStates(){
		return POSSIBLE_STATES;
	}
	
	public abstract void updateState(SugarscapeRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations);

}
