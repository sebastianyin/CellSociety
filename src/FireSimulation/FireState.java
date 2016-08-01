package FireSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;
import society.State;

public abstract class FireState extends State {
	
	public static final String[] POSSIBLE_STATES = {"Burned", "Burning", "Not Burned"};
	public String[] getPossibleStates(){
		return POSSIBLE_STATES;
	}
	
	public int getValue(){
		return getMyBurnStat();
	}
	
	public abstract int getMyBurnStat();
	
	public abstract String[] updateStates(FireRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations);

}
