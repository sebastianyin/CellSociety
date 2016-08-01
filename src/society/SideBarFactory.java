package society;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SideBarFactory {

	public SideBarFactory() {
	}
	
	public void addSliders(InterfaceView view, VBox interactToolbar, Matrix thisMatrix){
		
		Text speedSliderText = new Text("Speed Slider");
		interactToolbar.getChildren().add(speedSliderText);
		
		Slider speedSlider = new Slider();
		
		speedSlider.setMin(1);
		speedSlider.setMax(10);
		speedSlider.setValue(view.getFPS());
		
		speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				view.setFPS(((Double) new_val).intValue()); 
				view.updateFPS();
				view.displayFPSLabel(view.getFPS());
			}
		});
		
		interactToolbar.getChildren().add(speedSlider);
		
		Text runtimeSliders = new Text("Runtime Sliders");
		interactToolbar.getChildren().add(runtimeSliders);

		for(int i = 0; i < thisMatrix.getRule().getMySliderValues().length; i++){
			Slider slider = null;
			Text sliderLabel = new Text();

			if(thisMatrix.getRule().getMySliderValues()[i].equals("burnProbability")){
				slider = new Slider();
				
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getBurnProbability()) * 10);

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setBurnProbability(((Double) new_val/10) + ""); 
					}
				});
				
				sliderLabel.setText("Burn Probability");
			}
			else if(thisMatrix.getRule().getMySliderValues()[i].equals("satisfactionRate")){
				slider = new Slider();
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getSatisfactionRate()) * 10);

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setSatisfactionRate(((Double) new_val/10) + ""); 
					}
				});
				
				sliderLabel.setText("Satisfaction Rate");
			}
			else if(thisMatrix.getRule().getMySliderValues()[i].equals("sharkBirthRate")){
				slider = new Slider();
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getSharkBirthRate()));

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setSharkBirthRate(((Double) new_val) + ""); 
					}
				});
				
				sliderLabel.setText("Cycles for Shark Birth");
			}
			else if(thisMatrix.getRule().getMySliderValues()[i].equals("sharkDeathRate")){
				slider = new Slider();
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getSharkDeathRate()));

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setSharkDeathRate(((Double) new_val) + ""); 
					}
				});
				
				sliderLabel.setText("Cycles for Shark Death");
			}
			else if(thisMatrix.getRule().getMySliderValues()[i].equals("fishBirthRate")){
				slider = new Slider();
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getFishBirthRate()));

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setFishBirthRate(((Double) new_val) + ""); 
					}
				});
				sliderLabel.setText("Cycles for Fish Birth");
			}
			else if(thisMatrix.getRule().getMySliderValues()[i].equals("maximumAntsPerCell")){
				slider = new Slider();
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getMaximumAntsPerCell()));

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setMaximumAntsPerCell(((Double) new_val) + ""); 
					}
				});
				
				sliderLabel.setText("Ants Allowed Per Cell");
			}
			else if(thisMatrix.getRule().getMySliderValues()[i].equals("foodPheromones")){
				slider = new Slider();
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getFoodPheromone()));

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setFoodPheromone(((Double) new_val) + ""); 
					}
				});
				
				sliderLabel.setText("Pheromone in Food Cell");
			}
			else if(thisMatrix.getRule().getMySliderValues()[i].equals("homePheromones")){
				slider = new Slider();
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getHomePheromone()));

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setHomePheromone(((Double) new_val) + ""); 
					}
				});
				
				sliderLabel.setText("Pheromone in Home Cell");
			}
			else if(thisMatrix.getRule().getMySliderValues()[i].equals("sugarGrowthRate")){
				slider = new Slider();
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getSugarGrowthRate()));

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setSugarGrowthRate(((Double) new_val) + ""); 
					}
				});
				
				sliderLabel.setText("Sugar Growth Rate");
			}
			else if(thisMatrix.getRule().getMySliderValues()[i].equals("sugarGrowthInterval")){
				slider = new Slider();
				slider.setMin(0);
				slider.setMax(10);
				slider.setValue(Double.parseDouble(thisMatrix.getMyParsed().getSugarGrowthInterval()));

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						thisMatrix.getMyParsed().setSugarGrowthInterval(((Double) new_val) + ""); 
					}
				});
				
				sliderLabel.setText("Cycles for Sugar to Grow");
			}

			if(slider != null){
				interactToolbar.getChildren().add(slider);
				interactToolbar.getChildren().add(sliderLabel);
			}
		}
	}

}
