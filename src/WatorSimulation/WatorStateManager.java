package WatorSimulation;

import society.StateManager;
import society.XMLObject;

public class WatorStateManager extends StateManager {
	
	private WatorTypeState myType;
	private int myGenerations;
	private int myCount;
	private int myLastEatenCount;
	
	public WatorTypeState getMyState(){
		return myType;
	}
	
	public WatorStateManager(int x, int y, XMLObject object, String[] params, int generations, int startCount, int lastEatenCount){
		super(x, y, object, params);
		myGenerations = generations;
		myCount = startCount;
		myLastEatenCount = lastEatenCount;
		initializeStates();
		updateVisual();
	}
	
	public WatorStateManager(int x, int y, int xLoc, int yLoc, XMLObject object){
		this(xLoc, yLoc, object, object.getValues().get(y * object.getCellsWide() + x), 1, 0, 0);
	}
	
	public WatorStateManager(WatorStateManager oldManager, String[] params, int generations, int startCount, int lastEatenCount){
		this(oldManager.getLocation().getMyX(), oldManager.getLocation().getMyY(), 
				oldManager.getMyObject(), 
				params, generations, startCount, lastEatenCount);
	}
	
	public void initializeStates(){
		WatorTypeFactory myWatorFactory = new WatorTypeFactory();
		myType = myWatorFactory.getType(Integer.parseInt(getMyParams()[0]), this);
		addToMyStates(myType);
	}
	
	public WatorStateManager(){
		super();
	}
	
	public int getMyGenerations() {
		return myGenerations;
	}

	public void setMyGenerations(int myGenerations) {
		this.myGenerations = myGenerations;
	}

	public int getMyCount() {
		return myCount;
	}

	public void setMyCount(int myCount) {
		this.myCount = myCount;
	}
	
	public int getMyLastEatenCount(){
		return myLastEatenCount;
	}
	
	public void setMyLastEatenCount(int count){
		myLastEatenCount = count;
	}
	
}
