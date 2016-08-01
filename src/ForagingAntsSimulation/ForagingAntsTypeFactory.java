package ForagingAntsSimulation;

public class ForagingAntsTypeFactory {

	public ForagingAntsTypeFactory() {
	}
	
	public ForagingAntsTypeState getType(int type, ForagingAntsStateManager manager){
		if(type == 0){
			return new Road();
		}
		else if(type == 1){
			return new Nest();
		}
		else if(type == 2){
			return new Food();
		}
		else{
			return null;
		}
	}

}
