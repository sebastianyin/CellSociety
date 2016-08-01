package SugarscapeSimulation;

import java.util.Map;

import society.Cell;
import society.Coordinate;

public class Patch extends SugarscapeTypeState { 
	
	private double mySugarCapacity;
	private double mySugarLevel;
	
	public Patch(String[] sugarCapacity, int sugarLevel, SugarscapeStateManager manager){
		setMySugarCapacity(Double.parseDouble(sugarCapacity[0]));
		setMySugarLevel(sugarLevel);
	}

	@Override
	public int getValue() {
		return (int) mySugarLevel;
	}

	public double getMySugarLevel() {
		return mySugarLevel;
	}

	public void setMySugarLevel(int mySugarLevel) {
		this.mySugarLevel = mySugarLevel;
	}

	@Override
	public void updateState(SugarscapeRule rule, Cell cell, Map<Coordinate, Cell> myCells, int generations) {
		if(((SugarscapeStateManager) cell.getStates()).getMyIntervalCount() >= 
				(int) (((SugarscapeRule) rule).getMySugarGrowBackInterval())){
			int patchSugarLevel = (int) ((Patch) cell.getStates().getMyStates().get(0)).getMySugarLevel();
			if(patchSugarLevel < mySugarCapacity){
				if(((SugarscapeRule) rule).getMySugarGrowBackRate() + patchSugarLevel < mySugarCapacity){
				patchSugarLevel += (int) (((SugarscapeRule) rule).getMySugarGrowBackRate());
				}
				else{
					patchSugarLevel = (int) mySugarCapacity;
				}
			}
			String[] beforeValue = cell.getStates().getMyParams();
			String[] afterValue = new String[beforeValue.length];
			afterValue[0] = patchSugarLevel + "";
			for(int i = 1; i < beforeValue.length; i++){
				afterValue[i] = beforeValue[i];
			}
			cell.setMyStates(new SugarscapeStateManager((SugarscapeStateManager) cell.getStates(), afterValue, 
					((SugarscapeStateManager) cell.getStates()).getInnerList(), 
					((SugarscapeStateManager) cell.getStates()).getMyGenerations(), 
					0)); 
		}
		else{
			cell.setMyStates(new SugarscapeStateManager((SugarscapeStateManager) cell.getStates(), 
					((SugarscapeStateManager) cell.getStates()).getMyParams(),
					((SugarscapeStateManager) cell.getStates()).getInnerList(), 
					((SugarscapeStateManager) cell.getStates()).getMyGenerations(), 
					((SugarscapeStateManager) cell.getStates()).getMyIntervalCount() + 1)); 
		}
	}

	@Override
	public String getLabel() {
		return "Patch";
	}

	public double getMySugarCapacity() {
		return mySugarCapacity;
	}

	public void setMySugarCapacity(double mySugarCapacity) {
		this.mySugarCapacity = mySugarCapacity;
	}

}
