package LifeSimulation;

import society.Cell;

public class Alive extends LifeState {

	@Override
	public int getMyLife() {
		return 1;
	}

	@Override
	public void updateStates(LifeRule rule, Cell cell, int liveNeighbors) {
		rule.updateLiveCell(cell, liveNeighbors);
	}

	@Override
	public String getLabel() {
		return "Alive";
	}

}
