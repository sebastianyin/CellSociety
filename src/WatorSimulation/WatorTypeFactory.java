package WatorSimulation;

public class WatorTypeFactory {

	public WatorTypeFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public WatorTypeState getType(int type, WatorStateManager manager){
		if(type == 0){
			return new Water();
		}
		else if(type == 1){
			return new Fish();
		}
		else if(type == 2){
			return new Shark(manager);
		}
		else{
			return null;
		}
	}

}
