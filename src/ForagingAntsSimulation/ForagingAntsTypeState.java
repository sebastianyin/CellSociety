package ForagingAntsSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;
import society.State;

public abstract class ForagingAntsTypeState extends State {
	
	public static final String[] POSSIBLE_STATES = {"Ant", "Food", "Nest", "Road"};

	@Override
	public String[] getPossibleStates(){
		return POSSIBLE_STATES;
	}
	
	@Override
	public int getValue() {
		return getMyType();
	}
	
	public abstract int getMyType();
	public abstract void updateState(ForagingAntsRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations);

}
