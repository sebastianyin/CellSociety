package society;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CellSociety {

	public static final int SIZE = 700;

	private Stage myStage;
	private Scene myScene;
	private Timeline myAnimation = new Timeline();
	private Matrix myGrid;
	private KeyFrame myFrame;
	private InterfaceController interfaceController;
	private ResourceBundle myTextStrings;
	
	private boolean firstTime = true;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public CellSociety(Stage primaryStage){
		myStage = primaryStage;
	}

	public void initialize() throws CloneNotSupportedException{	
		myTextStrings = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+ "myStrings");
		interfaceController = new InterfaceController(this);
		myScene = new Scene(interfaceController.getView().getWindow(), SIZE, SIZE);
		myStage.setScene(myScene);
		myStage.setResizable(false);
		myStage.setTitle(myTextStrings.getString("projectName"));
		myStage.show();
	}
	
	public void createMatrix(XMLObject parseObject){
		try{
			myGrid = new Matrix(parseObject);
		} catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	
	public void loadSystemFromXML() {
		firstTime = true;
		createMatrix(interfaceController.getXML());
	}

	public void displaySystem() {
		interfaceController.getView().addGrid(myGrid.getRoot());
		myStage.setScene(myScene);
		myStage.show();
		interfaceController.getView().setSideFrame();
		resetFrameAndPlay(false);
	}
	
	
	public void playSimulation(){
		myAnimation.play();
	}
	
	public void pauseSimulation(){
		myAnimation.pause();
	}
	
	public void resetFrameAndPlay(boolean playing){
		myAnimation.stop();
		myAnimation.getKeyFrames().clear();
		myFrame = new KeyFrame(Duration.millis(interfaceController.getSimulationDelay()),
				e -> {myGrid.play();
					//frameCounter++;
					if (interfaceController.getPopulationGrapher()!=null){
						interfaceController.getPopulationGrapher().makeGraphData(myGrid.getMyGenerations(), myGrid);
					}
					System.out.println("FRAMES PLAYED:" + myGrid.getMyGenerations());
				});
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(myFrame);
		if (playing) myAnimation.play();
	}
	
	public void resetMatrix(Matrix grid){
		grid.resetMatrix();
	}
	
	public boolean isPlaying(){
		return (myAnimation.getCurrentRate() != 0);
	}
	
	public boolean isFirstTimeRunning(){
		return firstTime;
	}
	
	public void setFirstTimeBoolean(boolean firsttime){
		firstTime = firsttime;
	}
	
	public Timeline getTimeline(){
		return myAnimation;
	}
	
	public Matrix getMatrix(){
		return myGrid;
	}
	
	public KeyFrame getFrame(){
		return myFrame;
	}
}