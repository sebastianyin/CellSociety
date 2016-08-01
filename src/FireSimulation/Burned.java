package FireSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Burned extends FireState {
	
	@Override
	public int getMyBurnStat() {
		return 0;
	}
	
	@Override
	public String[] updateStates(FireRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		
		String[] params = {"0"};
		return params;
		
	}
	
	@Override
	public String getLabel(){
		return "Burned";
	}

}
