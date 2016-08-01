package SegregationSimulation;

import society.StateManager;
import society.XMLObject;

public class SegregationStateManager extends StateManager {
	
	private SegregationTypeState myTypeState;
	private int myGenerations;
	
	public SegregationTypeState getMyState(){
		return myTypeState;
	}
	
	public SegregationStateManager(int x, int y, XMLObject object, String[] params, int generations){
		super(x, y, object, params);
		myGenerations = generations;
		initializeStates();
		updateVisual();
	}
	
	public SegregationStateManager(int x, int y, int xLoc, int yLoc, XMLObject object){
		this(xLoc, yLoc, object, object.getValues().get(y * object.getCellsWide() + x), 1);
	}
	
	public SegregationStateManager(SegregationStateManager oldManager, String[] params, int generation){
		this(oldManager.getLocation().getMyX(), oldManager.getLocation().getMyY(), 
				oldManager.getMyObject(), params, generation);
	}
	
	public void initializeStates(){
		SegregationTypeFactory myFactory = new SegregationTypeFactory();
		myTypeState = myFactory.getType(Integer.parseInt(getMyParams()[0]), this);
		addToMyStates(myTypeState);
	}
	
	public SegregationStateManager(){
		super();
	}

	public int getMyGenerations() {
		return myGenerations;
	}

	public void setMyGenerations(int myGenerations) {
		this.myGenerations = myGenerations;
	}
	
}
