package SugarscapeSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Agent extends SugarscapeTypeState {
	
	private int mySugarLevel;
	private SugarscapeStateManager myManager;
	private int mySugarMetabolism;
	private int myVision;
	
	private int myAgentId;
	
	public Agent(String[] myValues, int sugarLevel, int id, SugarscapeStateManager manager){
		setMySugarMetabolism((int) Double.parseDouble(myValues[0]));
		setMyVision((int) Double.parseDouble(myValues[1]));
		setMySugarLevel(sugarLevel);
		setMyAgentId(id);
		myManager = manager;
	}

	@Override
	public int getValue() {
		return -17;
	}
	
	public void updateState(SugarscapeRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations){
		if(myManager.getMyGenerations() > generations){
			return;
		}
		rule.updateAgent(myAgentId, cell, myCells);
	}

	public int getMySugarLevel() {
		return mySugarLevel;
	}

	public void setMySugarLevel(int mySugarLevel) {
		this.mySugarLevel = mySugarLevel;
	}

	public int getMyAgentId() {
		return myAgentId;
	}

	public void setMyAgentId(int myAgentId) {
		this.myAgentId = myAgentId;
	}

	@Override
	public String getLabel() {
		return "Agent";
	}

	public int getMySugarMetabolism() {
		return mySugarMetabolism;
	}

	public void setMySugarMetabolism(int mySugarMetabolism) {
		this.mySugarMetabolism = mySugarMetabolism;
	}

	public int getMyVision() {
		return myVision;
	}

	public void setMyVision(int myVision) {
		this.myVision = myVision;
	}

}
