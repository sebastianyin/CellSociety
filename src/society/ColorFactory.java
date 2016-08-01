package society;

import javafx.scene.paint.Color;

public class ColorFactory {

	public ColorFactory() {
	}
	
	public Color getColor(String color){
		if(color.equals("black")){
			return Color.BLACK;
		}
		else if(color.equals("red")){
			return Color.RED;
		}
		else if(color.equals("blue")){
			return Color.BLUE;
		}
		else if(color.equals("purple")){
			return Color.LAVENDER;
		}
		else if(color.equals("green")){
			return Color.GREENYELLOW;
		}
		else if(color.equals("gray")){
			return Color.GRAY;
		}
		else if(color.equals("white")){
			return Color.WHITE;
		}
		else if(color.equals("blue")){
			return Color.BLUE;
		}
		else{
			return Color.WHITE;
		}
	}

}
