package society;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Params {
	
	public static final String ZERO_COLOR = "zerocolor";
	public static final String ONE_COLOR = "onecolor";
	public static final String TWO_COLOR = "twocolor";
	
	public static final String ZERO_PROPORTION = "zeroproportion";
	public static final String ONE_PROPORTION = "oneproportion";
	public static final String TWO_PROPORTION = "twoproportion";
	
	public static final String ZERO_IMAGE = "zeroimage";
	public static final String ONE_IMAGE = "oneimage";
	public static final String TWO_IMAGE = "twoimage";
	
	public static final String BURN_PROBABILITY = "probabilityToBurn";
	public static final String SATISFACTION_RATE = "satisfactionRate";
	public static final String SUGAR_GROW_BACK_RATE = "sugarGrowBackRate";
	public static final String SUGAR_GROW_BACK_INTERVAL = "sugarGrowBackInterval";
	
	private XMLParser myParser;
	private String myProbabilityToBurn;
	private String mySatisfactionRate;
	private String mySugarGrowBackRate;
	private String mySugarGrowBackInterval;
	private String[] myProportionParams;
	private String[] myColorParams;
	private String[] myImageParams;
	
	private List<String[]> myValues = new ArrayList<String[]>();
	private List<ArrayList<String[]>> myInnerValues = new ArrayList<ArrayList<String[]>>();

	public Params(XMLParser parser) {
		myParser = parser;
	}
	
	public Params create(Document doc){
		
		Element simulationSpecificElement = (Element) ((Element) doc.getElementsByTagName("simulationSettings").item(0)).getElementsByTagName("simulationSpecific").item(0);
	
		setMyProbabilityToBurn(myParser.getTagValue(BURN_PROBABILITY, simulationSpecificElement));
		
		setMySatisfactionRate(myParser.getTagValue(SATISFACTION_RATE, simulationSpecificElement));
		
		setMySugarGrowBackRate(myParser.getTagValue(SUGAR_GROW_BACK_RATE, simulationSpecificElement));
		
		setMySugarGrowBackInterval(myParser.getTagValue(SUGAR_GROW_BACK_INTERVAL, simulationSpecificElement));
		
		String[] colorParams = {myParser.getTagValue(ZERO_COLOR, simulationSpecificElement),
								myParser.getTagValue(ONE_COLOR, simulationSpecificElement),
								myParser.getTagValue(TWO_COLOR, simulationSpecificElement)};
		
		myColorParams = colorParams;
		
		String[] proportionParams = {myParser.getTagValue(ZERO_PROPORTION, simulationSpecificElement),
				 					 myParser.getTagValue(ONE_PROPORTION, simulationSpecificElement),
				 					 myParser.getTagValue(TWO_PROPORTION, simulationSpecificElement)};
		
		myProportionParams = proportionParams;
		
		String[] imageParams = {myParser.getTagValue(ZERO_IMAGE, simulationSpecificElement),
				 myParser.getTagValue(ONE_IMAGE, simulationSpecificElement),
				 myParser.getTagValue(TWO_IMAGE, simulationSpecificElement)};

		myImageParams = imageParams;
		
		NodeList cellsList = doc.getElementsByTagName("cell");

		for (int temp = 0; temp < cellsList.getLength(); temp++) {

			Node squareNode = cellsList.item(temp);
			if (squareNode.getNodeType() == Node.ELEMENT_NODE) {

				Element squareElement = (Element) squareNode;
				Node characteristicNode = squareElement.getElementsByTagName("characteristic").item(0);
				Element characteristicElement = (Element) characteristicNode;
				String characteristicValue = myParser.getTagValue("value", characteristicElement);
				String capacity = myParser.getTagValue("value", characteristicElement);
				
				ArrayList<String> values = new ArrayList<String>();
				ArrayList<String[]> individCell = new ArrayList<String[]>();
				
				String[] patchParams = {capacity};
				individCell.add(patchParams);
				
				values.add(characteristicValue);
				
				for(int i = 1; i < squareElement.getElementsByTagName("characteristic").getLength(); i++){
					Element e = (Element) squareElement.getElementsByTagName("characteristic").item(i);
					String val = myParser.getTagValue("value", e);
					String met = myParser.getTagValue("sugarMetabolism", e);
					String vis = myParser.getTagValue("vision", e);
					String[] agentParams = {met, vis};
					individCell.add(agentParams);
					values.add(val);
				}
				
				String[] params = new String[values.size()];
				for(int i = 0; i < values.size(); i++){
					params[i] = values.get(i);
				}
				myValues.add(params);
				myInnerValues.add(individCell);
				
			}
			
		}
		
		return this;
	
	}
	
	public XMLParser getMyParser() {
		return myParser;
	}

	public void setMyParser(XMLParser myParser) {
		this.myParser = myParser;
	}

	public String[] getMyProportionParams() {
		return myProportionParams;
	}

	public void setMyProportionParams(String[] proportionParams) {
		this.myProportionParams = proportionParams;
	}

	public List<String[]> getMyValues() {
		return myValues;
	}

	public void setMyValues(List<String[]> myValues) {
		this.myValues = myValues;
	}
	
	public List<ArrayList<String[]>> getMyInnerValues() {
		return myInnerValues;
	}

	public void setMyInnerValues(List<ArrayList<String[]>> myValues) {
		this.myInnerValues = myValues;
	}

	public String[] getMyColorParams() {
		return myColorParams;
	}

	public void setMyColorParams(String[] myColorParams) {
	}

	public String getMyProbabilityToBurn() {
		return myProbabilityToBurn;
	}

	public void setMyProbabilityToBurn(String myProbabilityToBurn) {
		this.myProbabilityToBurn = myProbabilityToBurn;
	}

	public String getMySatisfactionRate() {
		return mySatisfactionRate;
	}

	public void setMySatisfactionRate(String mySatisfactionRate) {
		this.mySatisfactionRate = mySatisfactionRate;
	}

	public String[] getMyImageParams() {
		return myImageParams;
	}

	public void setMyImageParams(String[] myImageParams) {
		this.myImageParams = myImageParams;
	}

	public String getMySugarGrowBackRate() {
		return mySugarGrowBackRate;
	}

	public void setMySugarGrowBackRate(String mySugarGrowBackRate) {
		this.mySugarGrowBackRate = mySugarGrowBackRate;
	}

	public String getMySugarGrowBackInterval() {
		return mySugarGrowBackInterval;
	}

	public void setMySugarGrowBackInterval(String mySugarGrowBackInterval) {
		this.mySugarGrowBackInterval = mySugarGrowBackInterval;
	}

}
