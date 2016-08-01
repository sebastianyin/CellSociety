package society;

import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


public class InterfaceController {

	private XMLObject myXML;
	private int myFPS = 1;
	
	private CellSociety mySimulation;
	private InterfaceView myView;
	private PopulationGrapher myPopulationGrapher;
	private Plotter myPlotter;
	
	public InterfaceController(CellSociety simulation){
		mySimulation = simulation;
		myView = new InterfaceView(this);
		makeDropdownFunctionality();
	}

	private void setXML(String xmlFile){
		XMLParser parser = new XMLParser(xmlFile);
		myXML = parser.doParse();
		makeButtonFunctionality();
	}
	
	
	private void makeDropdownFunctionality(){
		@SuppressWarnings("unchecked")
		ComboBox<String> dropdown = (ComboBox<String>) myView.getGUIElements().get("dropdown");
	    dropdown.valueProperty().addListener(new ChangeListener<String>() {
	        @Override 
	        public void changed(ObservableValue<? extends String> ov, String t, String t1) {
	        		setXML(dropdown.getValue());
	        		Button playButton = (Button) myView.getGUIElements().get("Play");
	        		playButton.setText("Play");
	        		if (!mySimulation.isFirstTimeRunning()) mySimulation.resetMatrix(mySimulation.getMatrix());
	        		mySimulation.loadSystemFromXML();
	        		mySimulation.displaySystem();
	        }    
	    });
	}
	
	public void pausePlayAnimation(){
		Button playButton = (Button) myView.getGUIElements().get("Play");
		if (mySimulation.isPlaying()){
			mySimulation.pauseSimulation();
			playButton.setText("Play");
		}
		else {
			mySimulation.playSimulation();
			playButton.setText("Pause");
		}
		if (mySimulation.isFirstTimeRunning()){
			mySimulation.displaySystem();
			mySimulation.playSimulation();
			mySimulation.setFirstTimeBoolean(false);
		};
	}
	
	private void makeButtonFunctionality(){
		((Button) myView.getGUIElements().get("Play")).setOnMouseClicked(e->pausePlayAnimation());
		((Button) myView.getGUIElements().get("Step")).setOnMouseClicked(e->doSingleStep());
		((Button) myView.getGUIElements().get("Reset")).setOnMouseClicked(e->resetSimulation());
		((Button) myView.getGUIElements().get("Plots")).setOnMouseClicked(e->displayPlots());
	}
	
	public List<String> getAllXMLs(){
		List<String> XMLs = new ArrayList<String>();
		File directory = new File(System.getProperty("user.dir"));
		for (File file : directory.listFiles())
		{
		   if (file.getName().endsWith("txt"))
		   {
			   //System.out.println(file.getName());
			   XMLs.add(file.getName());
		   }
		}
		return XMLs;
	}
	
	private void doSingleStep(){
		if (!mySimulation.isPlaying()) 
			mySimulation.getMatrix().play();
	}
	
	public double getSimulationDelay(){
		double animationDelay = 1000 / myFPS;
		return animationDelay;
	}
	
	public void resetSimulation(){
		mySimulation.getTimeline().stop();
		mySimulation.resetMatrix(mySimulation.getMatrix());
		mySimulation.loadSystemFromXML();
		mySimulation.displaySystem();
		Button playButton = ((Button) myView.getGUIElements().get("Play"));
		playButton.setText("Play");
		myFPS = 1;
		myView.displayFPSLabel(getFPS());
	}

	@SuppressWarnings("unchecked")
	public XMLObject getXML(){
		try {
		setXML(((ComboBox<String>) myView.getGUIElements().get("dropdown")).getValue());
		return myXML;
		} catch(NullPointerException e){
			System.out.println("Haven't chosen the XML file yet!");
		}
		return myXML;
	}
	
	public InterfaceView getView(){
		return myView;
	}
	
	public int getFPS(){
		return myFPS;
	}
	
	public void setFPS(int newValue){
		myFPS = newValue;
	}
	
	public CellSociety getSimulation(){
		return mySimulation;
	}
	
	public Matrix getGrid(){
		return mySimulation.getMatrix();
	}
	
	public void displayPlots(){
		myPlotter = new Plotter(mySimulation.getMatrix());
		myPopulationGrapher = myPlotter.getPopulationGrapher();
	}
	
	public PopulationGrapher getPopulationGrapher(){
		return myPopulationGrapher;
	}

}
