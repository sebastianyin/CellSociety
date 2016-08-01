package FireSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Burning extends FireState {
	
	@Override
	public int getMyBurnStat() {
		return 2;
	}

	@Override
	public String[] updateStates(FireRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		
		String[] params = {"0"};
		return params;
		
	}
	
	@Override
	public String getLabel(){
		return "Burning";
	}


}
