package FireSimulation;

public class FireStateFactory {
	
	public FireStateFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public FireState getType(int type, FireStateManager manager){
		if(type == 0){
			return new Burned();
		}
		else if(type == 1){
			return new NotBurned();
		}
		else if(type == 2){
			return new Burning();
		}
		else{
			return null;
		}
	}

}
