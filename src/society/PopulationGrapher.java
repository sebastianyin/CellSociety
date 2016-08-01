package society;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.chart.XYChart.Series;

public class PopulationGrapher {
		
	private Map<String, Series<Number, Number>> seriesMap = new HashMap<String, Series<Number, Number>>();

	private Plotter myPlotter;
	
	public PopulationGrapher(Plotter plotter, Matrix matrix){

		if (matrix.isEmpty()) return;
		
		myPlotter = plotter;
		String[] possibleStates = matrix.getMyCells().
				get(matrix.getMyCells().keySet().toArray()[0]).
				getStates().getMyState().getPossibleStates();
		
		for (String state: possibleStates){
			seriesMap.put(state, new Series<Number, Number>());
		}
	}
	
	public Map<String, Series<Number, Number>> getSeriesMap(){
		return seriesMap;
	}
	
	public Map<String, Integer> makeDataPoint(Matrix grid) {
		
		Map<String, Integer> cellFrequencies = new HashMap<String, Integer>();
		
		for (Coordinate c: grid.getMyCells().keySet()){
			
			String currentCellParam = grid.getMyCells().get(c).getStates().getMyState().getLabel();
			if (cellFrequencies.get(currentCellParam) == null){
				cellFrequencies.put(currentCellParam, 1);
			}
			else {
				int currentFrequency = cellFrequencies.get(currentCellParam);
				cellFrequencies.put(currentCellParam, currentFrequency+1);
			}
		}
		
		for (String s : seriesMap.keySet()) {
			if(cellFrequencies.get(s) == null){
				cellFrequencies.put(s, 0);
			}
		}
	
		return cellFrequencies;
	}
	

	public void makeGraphData(int currentFrame, Matrix grid) {
		
		if (myPlotter != null && myPlotter.getStageIsOpen()) {
			
			Map<String, Integer> frequencyMap = makeDataPoint(grid);
			System.out.println("Frequency map = " + frequencyMap.keySet().toString());
						
			for (String s : seriesMap.keySet()) {
				System.out.println(s);
				//seriesMap.get(s).setName(s);
				myPlotter.addDataPointToPlot(currentFrame, frequencyMap.get(s), seriesMap.get(s));
			}	
			
		}
	}
	

}
