package SegregationSimulation;

public class SegregationTypeFactory {

	public SegregationTypeFactory() {
	}
	
	public SegregationTypeState getType(int type, SegregationStateManager manager){
		if(type == 0){
			return new Empty(manager);
		}
		else if(type == 1){
			return new Blue(manager);
		}
		else if(type == 2){
			return new Red(manager);
		}
		else{
			return null;
		}
	}

}
