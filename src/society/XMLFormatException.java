package society;

public class XMLFormatException extends Exception {
	
	private static final long serialVersionUID = 5730340504071130706L;
	private String myTag;

	public XMLFormatException(String tag) {
		myTag = tag;
	}
	
	public String insertDefault(){
		if(myTag.equals("height")){
			return "400";
		}
		else if(myTag.equals("width")){
			return "400";
		}
		else if(myTag.equals("cellsHigh")){
			return "20";
		}
		else if(myTag.equals("cellsWide")){
			return "20";
		}
		else if(myTag.equals("cellShape")){
			return "square";
		}
		else if(myTag.equals("cellEdges")){
			return "outline";
		}
		else if(myTag.equals("cellEdgesColor")){
			return "black";
		}
		else if(myTag.equals("gridEdges")){
			return "finite";
		}
		else if(myTag.equals("neighborsType")){
			return "all";
		}
		else if(myTag.equals("simulation")){
			return "FireMatrix";
		}
		else if(myTag.equals("simulationName")){
			return "On Fire!";
		}
		else if(myTag.equals("simulationAuthor")){
			return "Bailey";
		}
		else if(myTag.equals("burnProbability")){
			return ".5";
		}
		else if(myTag.equals("satisfactionRate")){
			return ".5";
		}
		else if(myTag.equals("sharkBirthRate")){
			return "4";
		}
		else if(myTag.equals("sharkDeathRate")){
			return "3";
		}
		else if(myTag.equals("fishBirthRate")){
			return "4";
		}
		else if(myTag.equals("maximumAntsPerCell")){
			return "10";
		}
		else if(myTag.equals("foodPheromone")){
			return "10";
		}
		else if(myTag.equals("homePheromone")){
			return "10";
		}
		else if(myTag.equals("sugarGrowthRate")){
			return "2";
		}
		else if(myTag.equals("sugarGrowthInterval")){
			return "1";
		}
		else if(myTag.equals("patchMaxCapacity")){
			return "6";
		}
		else if(myTag.equals("agentMetabolism")){
			return "3";
		}
		else if(myTag.equals("agentVision")){
			return "1";
		}
		//for specifically created sugarscapes
		else if(myTag.equals("value")){
			return "10";
		}
		else if(myTag.equals("sugarMetabolism")){
			return "2";
		}
		else if(myTag.equals("vision")){
			return "1";
		}
		else if(myTag.equals("zerocolor")){
			return "blue";
		}
		else if(myTag.equals("onecolor")){
			return "red";
		}
		else if(myTag.equals("twocolor")){
			return "green";
		}
		else if(myTag.equals("zeroproportion")){
			return null;
		}
		else if(myTag.equals("oneproportion")){
			return null;
		}
		else if(myTag.equals("twoproportion")){
			return null;
		}
		else if(myTag.equals("zeroimage")){
			return null;
		}
		else if(myTag.equals("oneimage")){
			return null;
		}
		else if(myTag.equals("twoimage")){
			return null;
		}
		else{
			return null;
		}
	}

}
