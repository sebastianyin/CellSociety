package society;

import java.util.List;
import java.util.Map;

import javafx.scene.Group;

public class Cell {
	
	private StateManager myStates;
	private StateManager myPreviousStates;
	private List<Cell> myNeighbors;
	
	private Rule myRule;
	
	private XMLObject myObject;

	public Cell(){

	}

	public Cell(int y, int x, int xLoc, int yLoc, XMLObject object, Rule rule){
		myRule = rule;
		ManagerFactory myFactory = new ManagerFactory();
		myStates = myFactory.getManager(x, y, xLoc, yLoc, object);
		myObject = object;
	}

	public void findNeighbors(Map<Coordinate, Cell> myCells, Edges gridEdges) {
		myStates.getMyShape().findNeighbors(this, myCells, gridEdges, myStates.getMyVision());
	}

	public void updatePreviousStates() {
		setPreviousStates(myStates);
	}

	public void updateStates(Rule rule, Map<Coordinate, Cell> grid, int generations) {
		rule.determineNextState(this, grid, generations);
	}
	
	public void updateStatesAgain(Rule rule, Map<Coordinate, Cell> grid, int generations){
		rule.determineNextStateAgain(this, grid, generations);
	}

	public StateManager getStates(){
		return myStates;
	}

	public StateManager getPreviousStates() {
		return myPreviousStates;
	}


	public void setPreviousStates(StateManager prevStates) {
		this.myPreviousStates = prevStates;
	}

	public void setMyStates(StateManager manager){
		myStates = manager;
	}

	public Group cellClicked() {
		ManagerFactory myFactory = new ManagerFactory();
		setMyStates(myFactory.getClickedManager(getStates(), myStates.toggleState(), myObject.getMatrixType()));
		return myStates.getVisual();
	}

	public List<Cell> getNeighbors() {
		return myNeighbors;
	}

	public void setNeighbors(List<Cell> neighbors) {
		this.myNeighbors = neighbors;
	}

	public Rule getMyRule() {
		return myRule;
	}

	public void setMyRule(Rule myRule) {
		this.myRule = myRule;
	}
}
