package society;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class Plotter {

	private LineChart<Number, Number> lineChart;
	private Stage myStage;
	private PopulationGrapher myPopulationGrapher;
	
	public Plotter(Matrix matrix){
		myPopulationGrapher = new PopulationGrapher(this, matrix);
		createChart();
		ObservableList<XYChart.Series<Number, Number>> myChartData = FXCollections.observableArrayList();
		for(String s : myPopulationGrapher.getSeriesMap().keySet()){
			System.out.println("adding something");
			Series<Number, Number> aSeries = myPopulationGrapher.getSeriesMap().get(s);
			aSeries.setName(s);
			myChartData.add(aSeries);
			System.out.println(myChartData.size());
		}
		lineChart.setData(myChartData);
		setStage(new Stage(), matrix.getType());
	}
	
	public void createChart(){
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Frame Number");
		
		// creating the chart
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Population vs. frame number");
	}
	
	public void setStage(Stage stage, String title) {
		stage.setTitle(title);

		Scene scene = new Scene(lineChart, 300, 300); // Need to get rid of these magic values.
		stage.setScene(scene);
		stage.show();
		myStage = stage;
	}
	
	public LineChart<Number, Number> getLineChart(){
		return lineChart;
	}
	
	public void addDataPointToPlot(int frameNumber, 
			int cellFrequency, Series<Number, Number> series) {
		series.getData().add(new XYChart.Data<Number, Number>(frameNumber - 1, cellFrequency));
	}

	
	public boolean getStageIsOpen(){
		return (myStage.isShowing());
	}
	
	public PopulationGrapher getPopulationGrapher(){
		return myPopulationGrapher;
	}
}
