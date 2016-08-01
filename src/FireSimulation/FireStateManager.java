package FireSimulation;

import society.State;
import society.StateManager;
import society.XMLObject;

public class FireStateManager extends StateManager{
	
	private FireState myFireState;
	
	@Override
	public State getMyState(){
		return myFireState;
	}
	
	public FireStateManager(int x, int y, XMLObject object, String[] params){
		super(x, y, object, params);
		initializeStates();
		updateVisual();
	}
	
	public FireStateManager(int x, int y, int xLoc, int yLoc, XMLObject object){
		this(xLoc, yLoc, object, object.getValues().get(y * object.getCellsWide() + x));
	}
	
	public FireStateManager(FireStateManager oldManager, String[] params){ 
		this(oldManager.getLocation().getMyX(), oldManager.getLocation().getMyY(), 
				oldManager.getMyObject(), params);
	}
	
	public void initializeStates(){
		FireStateFactory myFactory = new FireStateFactory();
		myFireState = myFactory.getType(Integer.parseInt(getMyParams()[0]), this);
		addToMyStates(myFireState);
	}
    
    public FireStateManager(){
    	myFireState = null;
    }

}
