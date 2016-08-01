package ForagingAntsSimulation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import society.ColorFactory;
import society.State;
import society.StateManager;
import society.XMLObject;

public class ForagingAntsStateManager extends StateManager {
	
	private int homePheromones;
	private int foodPheromones;

	private int myGenerations;
	
	public ForagingAntsStateManager(int x, int y, XMLObject object, String[] params, int generations, int foodPheromone, int homePheromone){
		super(x, y, object, params);
		myGenerations = generations;
		if(params[0].equals("1")){
			homePheromones = (int) Double.parseDouble(getMyObject().getHomePheromone());
			foodPheromones = 0;
		}
		else if(params[0].equals("2")){
			homePheromones = 0;
			foodPheromones = (int) Double.parseDouble(getMyObject().getFoodPheromone());;
		}
		else{
			homePheromones = homePheromone;
			foodPheromones = foodPheromone;
		}
		initializeStates();
		updateVisual();
	}
	
	public ForagingAntsStateManager(int x, int y, int xLoc, int yLoc, XMLObject object){
		this(xLoc, yLoc, object, object.getValues().get(y * object.getCellsWide() + x), 1, 0, 0);
	}
	
	public ForagingAntsStateManager(ForagingAntsStateManager oldManager, String[] params, int generation, int foodPheromone, int homePheromone){
		this(oldManager.getLocation().getMyX(), oldManager.getLocation().getMyY(), 
				oldManager.getMyObject(), params, generation, foodPheromone, homePheromone);
	}
	
	public void initializeStates(){
		ForagingAntsTypeFactory myFactory = new ForagingAntsTypeFactory();
		ForagingAntsTypeState myType = myFactory.getType(Integer.parseInt(getMyParams()[0]), this);
		addToMyStates(myType);
		for(int i = 1; i < getMyParams().length; i++){
			Ant myAnt = new Ant(Integer.parseInt(getMyParams()[i]), i);
			addToMyStates(myAnt);
		}
	}
	
	public ForagingAntsStateManager(){
		super();
	}

	public int getMyGenerations() {
		return myGenerations;
	}

	public void setMyGenerations(int myGenerations) {
		this.myGenerations = myGenerations;
	}
	
	@Override
	public String[] toggleState() {
		int currentIndex = this.getMyStates().get(0).getValue();
		if (currentIndex == 2){
			currentIndex = 0;
		}
		else if(currentIndex == 0){
			currentIndex = 2;
		}
		System.out.println("State param is now: " + currentIndex);
		String[] param = {currentIndex+""};
		return param;
	}

	@Override
	public State getMyState() {
		return getMyStates().get(0);
	}
	
	@Override
	public void updateVisual(){
		ColorFactory myFactory = new ColorFactory();
		int useThisColor = 0;
		if(homePheromones > useThisColor){
			useThisColor = 1;
		}
		if(foodPheromones > 0){
			useThisColor = 2;
		}
		((Shape) getVisual().getChildren().get(0)).setFill(myFactory.getColor(getMyColorParams()[useThisColor]));
		if(useThisColor == 2){
			((Shape) getVisual().getChildren().get(0)).setOpacity(((foodPheromones)/Double.parseDouble(getMyObject().getFoodPheromone())));
		}
		else{
			((Shape) getVisual().getChildren().get(0)).setOpacity(((homePheromones)/Double.parseDouble(getMyObject().getHomePheromone())));
		}
		if(getMyShapeEdges().equals("outline")){ 
			((Shape) getVisual().getChildren().get(0)).setStroke(myFactory.getColor(getMyShapeEdgesColor()));
		}	
		for (int i = 1; i < getMyStates().size(); i++){
			Image smallImage = new Image(getMyImageParams()[2]);
			ImageView picture = new ImageView(smallImage);
			picture.setOpacity(.5);
			picture.setFitWidth((getVisual().getChildren().get(0).getBoundsInParent().getMaxX() - getVisual().getChildren().get(0).getBoundsInParent().getMinX())/(3/2));
			picture.setFitHeight((getVisual().getChildren().get(0).getBoundsInParent().getMaxY() - getVisual().getChildren().get(0).getBoundsInParent().getMinY())/(3/2));
			picture.setX(getVisual().getChildren().get(0).getBoundsInParent().getMinX() + 
					(getVisual().getChildren().get(0).getBoundsInParent().getMaxX() - getVisual().getChildren().get(0).getBoundsInParent().getMinX())/6);
			picture.setY(getVisual().getChildren().get(0).getBoundsInParent().getMinY() + 
					(getVisual().getChildren().get(0).getBoundsInParent().getMaxY() - getVisual().getChildren().get(0).getBoundsInParent().getMinY())/6);
			getVisual().getChildren().add(picture);
		}
	}

	public int getHomePheromones() {
		return homePheromones;
	}

	public void setHomePheromones(int homePheromones) {
		this.homePheromones = homePheromones;
	}

	public int getFoodPheromones() {
		return foodPheromones;
	}

	public void setFoodPheromones(int foodPheromones) {
		this.foodPheromones = foodPheromones;
	}
	
}
