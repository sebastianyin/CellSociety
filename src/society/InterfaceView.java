package society;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class InterfaceView {

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myTextStrings;

	private BorderPane myWindow;
	private Map<String, Object> myGUIElements = new HashMap<String, Object>();

	private VBox interactToolbar;

	private InterfaceController myController;

	public InterfaceView(InterfaceController controller){
		myController = controller;
		getInterfaceText();
		makeBorderPane();
		displayFPSLabel(myController.getFPS());
	}

	private void getInterfaceText(){
		myTextStrings = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "myStrings");
	}

	// incomplete
	private void makeBorderPane(){
		myWindow = new BorderPane();
		makeInterfaceFrame();
		makeSideFrame();
	}

	public BorderPane makeInterfaceFrame(){
		HBox toolbar = new HBox();
		toolbar.getChildren().addAll(
				makeButton(myTextStrings.getString("playButton")), 
				makeButton(myTextStrings.getString("stepButton")),
				makeButton(myTextStrings.getString("resetButton")),
				makeButton(myTextStrings.getString("plot")),
				makeDropdown(myTextStrings.getString("typeDropdown"), myController.getAllXMLs()));
		//System.out.println(myController.getAllXMLs().toString());
		for(int i = 0; i < toolbar.getChildren().size(); i ++){
			HBox.setMargin(toolbar.getChildren().get(i), new Insets(10, 10, 10, 10));
		}
		myWindow.setTop(toolbar);
		return myWindow;
	}

	public BorderPane makeSideFrame(){
		interactToolbar = new VBox();
		interactToolbar.setStyle("-fx-background-color: #336699;");
		myWindow.setRight(interactToolbar);
		return myWindow;
	}

	public void setSideFrame(){
		interactToolbar.getChildren().clear();
		Matrix thisMatrix = myController.getGrid();

		SideBarFactory myFactory = new SideBarFactory();
		myFactory.addSliders(this, interactToolbar, thisMatrix);

	}
	
	public int getFPS(){
		return myController.getFPS();
	}
	
	public void setFPS(int newValue){
		myController.setFPS(newValue);
	}
	
	public void updateFPS(){
		myController.getSimulation().resetFrameAndPlay(myController.getSimulation().isPlaying());
	}

	private Button makeButton(String text){
		Button newButton = new Button();
		newButton.setText(text);
		myGUIElements.put(text, newButton);
		return newButton;
	}

	public ComboBox<String> makeDropdown(String label, Collection<String> options){
		ComboBox<String> dropdown = new ComboBox<String>();
		dropdown.setPromptText(label);
		dropdown.getItems().addAll(options);
		myGUIElements.put("dropdown", dropdown);
		return dropdown;
	}

	public void setButtonText(String text, Button button){
		button.setText(text);
	}

	public void setDropdownText(String text, ComboBox<String> dropdown){
		dropdown.setPromptText(text);
	}

	public void addGrid(Group gridRoot){
		myWindow.setCenter(gridRoot);
	}

	public void displayFPSLabel(int quantity) {
		HBox container = new HBox();
		container.getChildren().add(makeGenericLabel("FPS", quantity));
		myWindow.setBottom(container);
	}

	private Label makeGenericLabel(String label, int quantity) {
		Label genericLabel = new Label();
		genericLabel.setTextFill(Color.RED);
		StringProperty genericProperty = new SimpleStringProperty(label + ": " + quantity);
		genericLabel.textProperty().bindBidirectional(genericProperty);
		return genericLabel;
	}

	public BorderPane getWindow(){
		return myWindow;
	}

	public Map<String, Object> getGUIElements(){
		return myGUIElements;
	}

}
