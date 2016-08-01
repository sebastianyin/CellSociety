package LifeSimulation;

import society.Cell;

public class Dead extends LifeState {

	@Override
	public int getMyLife() {
		return 0;
	}

	@Override
	public void updateStates(LifeRule rule, Cell cell, int liveNeighbors) {
		rule.updateDeadCell(cell, liveNeighbors);
	}

	@Override
	public String getLabel() {
		return "Dead";
	}

}
