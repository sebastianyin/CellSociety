package society;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public abstract class StateManager {

	private Coordinate myLocation;
	private Group myVisual;
	private List<State> myStates = new ArrayList<State>();
	private String myShapeName;
	private String myShapeEdges;
	private String myShapeEdgesColor;

	private CellShape myShape;

	private String[] myParams;
	private String[] myColorParams;
	private String[] myImageParams;

	private int myCellWidth;
	private int myCellHeight;

	private XMLObject myObject;

	public StateManager(){

	}

	public StateManager(StateManager instance){
		this(instance.getLocation().getMyX(), 
				instance.getLocation().getMyY(), 
				instance.getMyObject(), instance.getMyParams());
	}

	public StateManager(int x, int y, XMLObject object, String[] params){
		setMyObject(object);
		myShapeName = object.getCellShape();
		setMyCellWidth(object.getWidth()/object.getCellsWide());
		setMyCellHeight(object.getHeight()/object.getCellsHigh());
		ShapeFactory myFactory = new ShapeFactory();
		myShape = myFactory.getShape(myShapeName, x, y, getMyCellWidth(), getMyCellHeight(), object.getNeighborsType());
		setVisual(myShape.createShape());
		myLocation = new Coordinate(x, y);
		myShapeEdges = object.getCellEdges();
		myShapeEdgesColor = object.getCellEdgesColor();
		myParams = params;
		myColorParams = object.getColorParams();
		myImageParams = object.getImageParams();
	}

	public abstract void initializeStates();

	public void updateVisual(){
		ColorFactory myFactory = new ColorFactory();
		((Shape) getVisual().getChildren().get(0)).setFill(myFactory.getColor(getMyColorParams()[getMyStates().get(0).getValue()]));
		if(getMyShapeEdges().equals("outline")){ 
			((Shape) getVisual().getChildren().get(0)).setStroke(myFactory.getColor(getMyShapeEdgesColor()));
		}	
		if(getMyImageParams()[getMyStates().get(0).getValue()] != null){
			Image sharkImage = new Image(getMyImageParams()[getMyStates().get(0).getValue()]);
			ImageView picture = new ImageView(sharkImage);
			picture.setFitWidth((getVisual().getChildren().get(0).getBoundsInParent().getMaxX() - getVisual().getChildren().get(0).getBoundsInParent().getMinX())/(3/2));
			picture.setFitHeight((getVisual().getChildren().get(0).getBoundsInParent().getMaxY() - getVisual().getChildren().get(0).getBoundsInParent().getMinY())/(3/2));
			picture.setX(getVisual().getChildren().get(0).getBoundsInParent().getMinX() + 
					(getVisual().getChildren().get(0).getBoundsInParent().getMaxX() - getVisual().getChildren().get(0).getBoundsInParent().getMinX())/6);
			picture.setY(getVisual().getChildren().get(0).getBoundsInParent().getMinY() + 
					(getVisual().getChildren().get(0).getBoundsInParent().getMaxY() - getVisual().getChildren().get(0).getBoundsInParent().getMinY())/6);
			getVisual().getChildren().add(picture);
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

	public void printStuff(){
		System.out.println("stuff");
	}

	public Coordinate getLocation(){
		return myLocation;
	}

	public Group getVisual(){
		return myVisual;
	}

	public void setVisual(Group visual){
		myVisual = visual;
	}

	public List<State> getMyStates() {
		return myStates;
	}

	public abstract State getMyState();

	public void setMyStates(List<State> myStates) {
		this.myStates = myStates;
	}

	public void addToMyStates(State state){
		myStates.add(state);
	}

	public void setMyShapeName(String name){
		myShapeName = name;
	}

	public String getMyShapeName(){
		return myShapeName;
	}

	public String[] toggleState() {
		int currentIndex = myStates.get(0).getValue();
		if (currentIndex == 2){
			currentIndex=0;
		}
		else currentIndex++;
		System.out.println("State param is now: " + currentIndex);
		String[] param = {currentIndex+""};
		return param;
	}

	public int getMyCellWidth() {
		return myCellWidth;
	}

	public void setMyCellWidth(int myCellsWidth) {
		this.myCellWidth = myCellsWidth;
	}

	public int getMyCellHeight() {
		return myCellHeight;
	}

	public void setMyCellHeight(int myCellsHeight) {
		this.myCellHeight = myCellsHeight;
	}

	public String getMyShapeEdges() {
		return myShapeEdges;
	}

	public void setMyShapeEdges(String myShapeEdges) {
		this.myShapeEdges = myShapeEdges;
	}

	public String getMyShapeEdgesColor() {
		return myShapeEdgesColor;
	}

	public void setMyShapeEdgesColor(String myShapeEdgesColor) {
		this.myShapeEdgesColor = myShapeEdgesColor;
	}

	public String[] getMyColorParams() {
		return myColorParams;
	}

	public void setMyColorParams(String[] myColorParams) {
		this.myColorParams = myColorParams;
	}

	public String[] getMyParams() {
		return myParams;
	}

	public void setMyParams(String[] myParams) {
		this.myParams = myParams;
	}

	public CellShape getMyShape() {
		return myShape;
	}

	public void setMyShape(CellShape myShape) {
		this.myShape = myShape;
	}

	public XMLObject getMyObject() {
		return myObject;
	}

	public void setMyObject(XMLObject myObject) {
		this.myObject = myObject;
	}

	public String[] getMyImageParams() {
		return myImageParams;
	}

	public void setMyImageParams(String[] myImageParams) {
		this.myImageParams = myImageParams;
	}

	public int getMyVision(){
		return 1;
	}

}
