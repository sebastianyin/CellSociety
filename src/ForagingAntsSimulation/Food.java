package ForagingAntsSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Food extends ForagingAntsTypeState {

	@Override
	public int getMyType() {
		return 2;
	}

	@Override
	public String getLabel() {
		return "Food";
	}

	@Override
	public void updateState(ForagingAntsRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		return;
	}

}
