package SugarscapeSimulation;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import society.ColorFactory;
import society.State;
import society.StateManager;
import society.XMLObject;

public class SugarscapeStateManager extends StateManager {

	private int myGenerations;
	private int myIntervalCount;
	
	private List<String[]> innerList;

	private SugarscapeTypeState myAgent;
	private SugarscapeTypeState myPatch;
	
	private double maximumSugarLevel;

	public SugarscapeStateManager(int x, int y, XMLObject object, String[] params, List<String[]> innerParams, int generations, 
			int intervalCount){
		super(x, y, object, params);
		innerList = innerParams;
		myGenerations = generations;
		myIntervalCount = intervalCount;
		maximumSugarLevel = Double.parseDouble(object.getPatchMaxCapacity());
		initializeStates();
		updateVisual();
	}

	public SugarscapeStateManager(int x, int y, int xLoc, int yLoc, XMLObject object){
		this(xLoc, yLoc, object, object.getValues().get(y * object.getCellsWide() + x),
				object.getInnerValues().get(y * object.getCellsWide() + x) , 1, 1);
	}

	public SugarscapeStateManager(SugarscapeStateManager oldManager, String[] params, List<String[]> innerParams, int generations,
			int intervalCount){
		this(oldManager.getLocation().getMyX(), oldManager.getLocation().getMyY(), 
				oldManager.getMyObject(), params, innerParams, generations, intervalCount);
	}

	@Override
	public void initializeStates() {
		myPatch = new Patch(innerList.get(0), (int) Double.parseDouble(getMyParams()[0]), this);
		addToMyStates(myPatch);
		for(int i = 1; i < getMyParams().length; i++){
			System.out.println("o hai");
			myAgent = new Agent(innerList.get(i), (int) Double.parseDouble(getMyParams()[i]), i, this);
			addToMyStates(myAgent);
		}
	}

	@Override
	public void updateVisual(){
		ColorFactory myFactory = new ColorFactory();
		((Shape) getVisual().getChildren().get(0)).setFill(myFactory.getColor(getMyColorParams()[0]));
		((Shape) getVisual().getChildren().get(0)).setOpacity((getMyStates().get(0).getValue()/maximumSugarLevel));
		if(getMyShapeEdges().equals("outline")){ 
			((Shape) getVisual().getChildren().get(0)).setStroke(myFactory.getColor(getMyShapeEdgesColor()));
		}	
		for (int i = 1; i < getMyStates().size(); i++){
			Image smallImage = new Image(getMyImageParams()[2]);
			ImageView picture = new ImageView(smallImage);
			picture.setOpacity(.5);
			picture.setFitWidth((getVisual().getChildren().get(0).getBoundsInParent().getMaxX() - getVisual().getChildren().get(0).getBoundsInParent().getMinX())/(3/2));
			picture.setFitHeight((getVisual().getChildren().get(0).getBoundsInParent().getMaxY() - getVisual().getChildren().get(0).getBoundsInParent().getMinY())/(3/2));
			picture.setX(getVisual().getChildren().get(0).getBoundsInParent().getMinX() + 
					(getVisual().getChildren().get(0).getBoundsInParent().getMaxX() - getVisual().getChildren().get(0).getBoundsInParent().getMinX())/6);
			picture.setY(getVisual().getChildren().get(0).getBoundsInParent().getMinY() + 
					(getVisual().getChildren().get(0).getBoundsInParent().getMaxY() - getVisual().getChildren().get(0).getBoundsInParent().getMinY())/6);
			getVisual().getChildren().add(picture);
		}
	}
	
	public int getMyGenerations() {
		return myGenerations;
	}

	public void setMyGenerations(int myGenerations) {
		this.myGenerations = myGenerations;
	}

	public SugarscapeTypeState getMyAgent() {
		return myAgent;
	}

	public void setMyAgent(SugarscapeTypeState myAgent) {
		this.myAgent = myAgent;
	}

	public SugarscapeTypeState getMyPatch() {
		return myPatch;
	}

	public void setMyPatch(SugarscapeTypeState myPatch) {
		this.myPatch = myPatch;
	}
	
	@Override
	public String[] toggleState() {
		int currentIndex = Integer.parseInt(getMyParams()[0]);
		currentIndex++;
		System.out.println("State param is now: " + currentIndex);
		String[] param = {currentIndex+""};
		return param;
	}
	
	@Override 
	public int getMyVision(){
		if(innerList.size() > 1){
			return (int) Double.parseDouble(innerList.get(1)[1]);
		}
		else{
			return 1;
		}
	}

	@Override
	public State getMyState() {
		return myPatch;
	}

	public List<String[]> getInnerList() {
		return innerList;
	}

	public void setInnerList(List<String[]> innerList) {
		this.innerList = innerList;
	}

	public int getMyIntervalCount() {
		return myIntervalCount;
	}

	public void setMyIntervalCount(int myIntervalCount) {
		this.myIntervalCount = myIntervalCount;
	}

}
