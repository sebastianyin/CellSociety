package LifeSimulation;

public class LifeStateFactory {

	public LifeStateFactory() {
		
	}

	public LifeState getType(int type, LifeStateManager manager){
		if(type == 0){
			return new Dead();
		}
		else if(type == 1){
			return new Alive();
		}
		else{
			return null;
		}
	}
	
}
