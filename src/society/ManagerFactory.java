package society;

import FireSimulation.FireRule;
import FireSimulation.FireStateManager;
import ForagingAntsSimulation.ForagingAntsRule;
import ForagingAntsSimulation.ForagingAntsStateManager;
import LifeSimulation.LifeRule;
import LifeSimulation.LifeStateManager;
import SegregationSimulation.SegregationRule;
import SegregationSimulation.SegregationStateManager;
import SugarscapeSimulation.SugarscapeRule;
import SugarscapeSimulation.SugarscapeStateManager;
import WatorSimulation.WatorRule;
import WatorSimulation.WatorStateManager;

public class ManagerFactory {

	public ManagerFactory() {

	}
	
	public StateManager getManager(int x, int y, int xLoc, int yLoc, XMLObject object){
		String type = object.getMatrixType();
		if(type.equals("LifeMatrix")){
			return new LifeStateManager(x, y, xLoc, yLoc, object);
		}
		else if(type.equals("FireMatrix")){
			return new FireStateManager(x, y, xLoc, yLoc, object);
		}
		else if(type.equals("SegregationMatrix")){
			return new SegregationStateManager(x, y, xLoc, yLoc, object);
		}
		else if(type.equals("WatorMatrix")){
			return new WatorStateManager(x, y, xLoc, yLoc, object);
		}
		else if(type.equals("SugarscapeMatrix")){
			return new SugarscapeStateManager(x, y, xLoc, yLoc, object);
		}
		else if(type.equals("ForagingAntsMatrix")){
			return new ForagingAntsStateManager(x, y, xLoc, yLoc, object);
		}
		else{
			return null;
		}
	}
	
	public StateManager getClickedManager(StateManager oldManager, String[] newParam, String type){
		if(type.equals("LifeMatrix")){
			return new LifeStateManager((LifeStateManager) oldManager, newParam);
		}
		else if(type.equals("FireMatrix")){
			return new FireStateManager((FireStateManager) oldManager, newParam);
		}
		else if(type.equals("SegregationMatrix")){
			return new SegregationStateManager((SegregationStateManager) oldManager, newParam, ((SegregationStateManager) oldManager).getMyGenerations());
		}
		else if(type.equals("WatorMatrix")){
			return new WatorStateManager((WatorStateManager) oldManager, newParam, ((WatorStateManager) oldManager).getMyGenerations(), 0, 0);
		}
		else if(type.equals("SugarscapeMatrix")){
			return new SugarscapeStateManager((SugarscapeStateManager) oldManager, newParam, 
					((SugarscapeStateManager) oldManager).getInnerList(), 
					((SugarscapeStateManager) oldManager).getMyGenerations(),
					((SugarscapeStateManager) oldManager).getMyIntervalCount());
		}
		else if(type.equals("ForagingAntsMatrix")){
			return new ForagingAntsStateManager((ForagingAntsStateManager) oldManager, newParam, 
					((ForagingAntsStateManager) oldManager).getMyGenerations(), 0, 0);
		}
		else{
			return null;
		}
	}
	
	public Rule getRule(XMLObject object){
		String type = object.getMatrixType();
		if(type.equals("LifeMatrix")){
			return new LifeRule(object);
		}
		else if(type.equals("FireMatrix")){
			return new FireRule(object);
		}
		else if(type.equals("SegregationMatrix")){
			return new SegregationRule(object);
		}
		else if(type.equals("WatorMatrix")){
			return new WatorRule(object);
		}
		else if(type.equals("SugarscapeMatrix")){
			return new SugarscapeRule(object);
		}
		else if(type.equals("ForagingAntsMatrix")){
			return new ForagingAntsRule(object);
		}
		else{
			return null;
		}
	}

}
