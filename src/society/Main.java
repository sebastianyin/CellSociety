package society;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private CellSociety cellSociety;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		cellSociety = new CellSociety(primaryStage);
		cellSociety.initialize();
		
	}

}