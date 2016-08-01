package ForagingAntsSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Nest extends ForagingAntsTypeState {

	@Override
	public int getMyType() {
		return 1;
	}

	@Override
	public String getLabel() {
		return "Nest";
	}
	
	public void updateState(ForagingAntsRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		int foodAttract = 0;
		Cell moveToHere = null;
		for(Cell c : cell.getNeighbors()){
			if (((ForagingAntsStateManager) c.getStates()).getFoodPheromones() > foodAttract){
				foodAttract = ((ForagingAntsStateManager) c.getStates()).getFoodPheromones();
				moveToHere = c;
			}
		}
		if(moveToHere != null){
			rule.createAnt(moveToHere);
		}
		else{
			double random = Math.random();
			int choice = (int) (random * cell.getNeighbors().size());
			rule.createAnt(cell.getNeighbors().get(choice));
		}
		
	}

}
