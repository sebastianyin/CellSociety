package LifeSimulation;

import society.Cell;
import society.State;

public abstract class LifeState extends State {
	
	public static final String[] POSSIBLE_STATES = {"Alive", "Dead"};
	
	public String[] getPossibleStates(){
		return POSSIBLE_STATES;
	}
	
	public int getValue(){
		return getMyLife();
	}

	public abstract int getMyLife();
	public abstract void updateStates(LifeRule rule, Cell cell, int liveNeighbors);
	
	

}
