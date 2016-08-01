package WatorSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;
import society.State;

public abstract class WatorTypeState extends State {
	
	public static final String[] POSSIBLE_STATES = {"Fish", "Water", "Shark"};
	
	public int getValue(){
		return getWatorType();
	}
	
	public String[] getPossibleStates(){
		return POSSIBLE_STATES;
	}
	
	public abstract int getWatorType();
	public abstract void updateState(WatorRule watorRule, int generations, Cell cell, Map<Coordinate, Cell> myCells);

}
