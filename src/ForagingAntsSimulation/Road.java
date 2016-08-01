package ForagingAntsSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Road extends ForagingAntsTypeState {

	@Override
	public int getMyType() {
		return 0;
	}

	@Override
	public String getLabel() {
		return "road";
	}

	@Override
	public void updateState(ForagingAntsRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		
	}

}
