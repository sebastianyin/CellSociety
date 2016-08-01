package FireSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class NotBurned extends FireState{
	
	@Override
	public int getMyBurnStat() {
		return 1;
	}
	
	@Override
	public String[] updateStates(FireRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		
		int newValue = rule.checkCatchFire(cell);
		String[] params = {newValue + ""};
		return params;
		
	}

	@Override
	public String getLabel() {
		return "Not Burned";
	}

}
