package society;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;

public class Matrix implements MatrixInterface {

	private Map<Coordinate, Cell> myCells;
	private Rule rule;
	private Group root = new Group();

	private String[] colorParams;

	private String myType;

	private int width;
	private int height;
	private int cellsWide;
	private int cellsHigh;

	private int cellWidthMult;
	private int cellHeightMult;

	private String cellShape;
	private String cellEdges;
	private String cellEdgesColor;
	private String gridEdges;

	private XMLObject myParsed;
	
	private Edges myGridEdges;

	private int myGenerations = 0;

	public Matrix(XMLObject parsed){
		myType = parsed.getMatrixType();
		width = parsed.getWidth();
		height = parsed.getHeight();
		cellsWide = parsed.getCellsWide();
		cellsHigh = parsed.getCellsHigh();
		cellWidthMult = width/cellsWide;
		cellHeightMult = height/cellsHigh;
		myParsed = parsed;
		cellShape = parsed.getCellShape();
		cellEdges = parsed.getCellEdges();
		setCellEdgesColor(parsed.getCellEdgesColor());
		ManagerFactory myFactory = new ManagerFactory();
		setRule(myFactory.getRule(myParsed));
		gridEdges = parsed.getGridEdges();
		checkXML();
		setMyGridEdges();
		createCells();
	}
	
	public void checkXML(){
		if(myParsed.getParams().getMyValues().size() == 0 || 
				myParsed.getParams().getMyValues().size() < myParsed.getCellsWide() * myParsed.getCellsHigh()){
			myParsed.setValues(rule.getRandomGrid(myParsed));
			myParsed.setInnerValues(rule.getRandomInnerParams(myParsed, myParsed.getValues()));
		}
		
		String[] probabilities = new String[rule.getDefaultProportions().length];
		for(int i = 0; i < probabilities.length; i++){
			if(myParsed.getProportionParams()[i] == null){
				probabilities[i] = rule.getDefaultProportions()[i];
			}
			else{
				probabilities[i] = myParsed.getProportionParams()[i];
			}
		}
		myParsed.setProportionParams(probabilities);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCellsWide() {
		return cellsWide;
	}

	public void setCellsWide(int cellsWide) {
		this.cellsWide = cellsWide;
	}

	public int getCellsHigh() {
		return cellsHigh;
	}

	public void setCellsHigh(int cellsHigh) {
		this.cellsHigh = cellsHigh;
	}

	public int getCellWidthMult() {
		return cellWidthMult;
	}

	public void setCellWidthMult(int cellWidthMult) {
		this.cellWidthMult = cellWidthMult;
	}

	public int getCellHeightMult() {
		return cellHeightMult;
	}

	public void setCellHeightMult(int cellHeightMult) {
		this.cellHeightMult = cellHeightMult;
	}

	public XMLObject getMyParsed() {
		return myParsed;
	}

	public void setMyParsed(XMLObject myParsed) {
		this.myParsed = myParsed;
	}

	public Map<Coordinate, Cell> getMyCells() {
		return myCells;
	}

	public Map<Coordinate, Cell> getGrid() {
		return myCells;
	}

	public void setMyCells(Map<Coordinate, Cell> myCells){
		this.myCells = myCells;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule){
		this.rule = rule;
	}

	public Group getRoot(){
		return root;
	}

	public void setRoot(Group root){
		this.root = root;
	}

	public Cell createInstance(int i, int j, String myType){
		return new Cell(i, j, (j * getCellWidthMult() + (getCellWidthMult()/2)), (i * getCellHeightMult()), myParsed, rule);
	}

	public void createCells(){
		Map<Coordinate, Cell> myCells = new HashMap<Coordinate, Cell>();
		for(int i = 0; i < getCellsHigh(); i++){
			for(int j = 0; j < getCellsWide(); j++){
				Cell newCell = createInstance(i, j, myType);
				myCells.put(newCell.getStates().getLocation(), newCell);
				getRoot().getChildren().add(newCell.getStates().getVisual());
				makeClickable(newCell.getStates().getVisual(), newCell);
			}
		}
		setMyCells(myCells);
	}


	public void resetMatrix(){
		myCells.clear();
		root.getChildren().clear();
		myGenerations = 0;
	}
	
	public void updateRule(){
		rule.update(myParsed);
	}

	public void play() {
		updateRule();
		if(myParsed.getMatrixType().equals("SugarscapeMatrix") || myParsed.getMatrixType().equals("ForagingAntsMatrix")
				||myParsed.getMatrixType().equals("WatorMatrix")){
			myGenerations++;
			for (Coordinate c : getMyCells().keySet()){
				getMyCells().get(c).updatePreviousStates();
			}
			for (Coordinate c : getMyCells().keySet()){
				getMyCells().get(c).findNeighbors(myCells, myGridEdges);
			}
			for (Coordinate c : getMyCells().keySet()){
				getMyCells().get(c).updateStates(myCells.get(c).getMyRule(), myCells, myGenerations);
			}
			for (Coordinate c : getMyCells().keySet()){
				getMyCells().get(c).updateStatesAgain(myCells.get(c).getMyRule(), myCells, myGenerations);
			}
			getRoot().getChildren().clear();
			for (Coordinate c : getMyCells().keySet()){
				getRoot().getChildren().add(myCells.get(c).getStates().getVisual());
				makeClickable(myCells.get(c).getStates().getVisual(), myCells.get(c));
			}
		}
		else{
			myGenerations++;
			for (Coordinate c : myCells.keySet()){
				myCells.get(c).updatePreviousStates();
			}
			for (Coordinate c : myCells.keySet()){
				myCells.get(c).findNeighbors(myCells, myGridEdges);
			}
			for (Coordinate c : myCells.keySet()){
				myCells.get(c).updateStates(myCells.get(c).getMyRule(), myCells, myGenerations);
			}
			root.getChildren().clear();
			for (Coordinate c : myCells.keySet()){
				root.getChildren().add(myCells.get(c).getStates().getVisual());
				makeClickable(myCells.get(c).getStates().getVisual(), myCells.get(c));
			}
		}
	}

	public void makeClickable(Node visual, Cell cell){
		visual.setOnMouseClicked(e->shapeClicked(cell));
	}

	public void shapeClicked(Cell cell){
		root.getChildren().remove(cell.getStates().getVisual());
		root.getChildren().add(cell.cellClicked());
		makeClickable(cell.getStates().getVisual(), cell);
	}

	public void setMyGenerations(int generations){
		myGenerations = generations;
	}

	public int getMyGenerations(){
		return myGenerations;
	}

	public String getCellShape() {
		return cellShape;
	}

	public void setCellShape(String cellShape) {
		this.cellShape = cellShape;
	}

	public String getCellEdges() {
		return cellEdges;
	}

	public void setCellEdges(String cellEdges) {
		this.cellEdges = cellEdges;
	}

	public String getGridEdges() {
		return gridEdges;
	}

	public void setGridEdges(String gridEdges) {
		this.gridEdges = gridEdges;
	}

	public String getCellEdgesColor() {
		return cellEdgesColor;
	}

	public void setCellEdgesColor(String cellEdgesColor) {
		this.cellEdgesColor = cellEdgesColor;
	}

	public String[] getColorParams() {
		return colorParams;
	}

	public void setColorParams(String[] colorParams) {
		this.colorParams = colorParams;
	}

	public Edges getMyGridEdges() {
		return myGridEdges;
	}

	public void setMyGridEdges() {
		if(gridEdges.equals("finite")){
			myGridEdges = new FiniteEdges();
		}
		else{
			myGridEdges = new ToroidalEdges();
		}
	}
	
	public String getType(){
		String type = "Simulation";
		if (myType!=null){
			type = myType;
		}
		return type;
	}
	
	public boolean isEmpty(){
		return (myCells.keySet().size() ==0);
	}

}
