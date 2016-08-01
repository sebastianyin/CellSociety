package LifeSimulation;

import society.StateManager;
import society.XMLObject;

public class LifeStateManager extends StateManager {
	
	private LifeState life;
	
	public LifeState getMyState(){
		return life;
	}
	
	public LifeStateManager(int x, int y, XMLObject object, String[] params){
		super(x, y, object, params);
		initializeStates();
		updateVisual();
	}
	
	public LifeStateManager(int x, int y, int xLoc, int yLoc, XMLObject object){
		this(xLoc, yLoc, object, object.getValues().get(y * object.getCellsWide() + x));
	}
	
	public LifeStateManager(LifeStateManager oldManager, String[] params){
		this(oldManager.getLocation().getMyX(), oldManager.getLocation().getMyY(), 
				oldManager.getMyObject(), params);
	}
	
	public void initializeStates(){
		LifeStateFactory myFactory = new LifeStateFactory();
		life = myFactory.getType(Integer.parseInt(getMyParams()[0]), this);
		addToMyStates(life);
	}
	
	public LifeStateManager(){
		super();
    	life = null;
    }
	
	public LifeStateManager(LifeStateManager lifeManager) {
		super((StateManager) lifeManager);
		life = lifeManager.getLife();
		addToMyStates(life);
	}
	
	public LifeState getLife(){
		return life;
	}

	@Override
	public String[] toggleState() {
		int currentIndex = life.getMyLife();
		if (currentIndex == 1){
			currentIndex=0;
		}
		else currentIndex++;
		System.out.println("State param is now: " + currentIndex);
		String[] param = {currentIndex+""};
		return param;
	}

}
