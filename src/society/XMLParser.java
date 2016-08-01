package society;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {

	private String myFileName;

	public XMLParser(String fileName){
		myFileName = fileName;
	}

	public XMLObject doParse() {

		XMLObject parseResult = null;

		try {

			File xmlFile = new File(myFileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			parseResult = new XMLObject(doc);

			Element gridSettingsElement = (Element) doc.getElementsByTagName("gridSettings").item(0);

			int height = Integer.parseInt(getTagValue("height", gridSettingsElement));
			int width = Integer.parseInt(getTagValue("width", gridSettingsElement));
			int cellsHigh = Integer.parseInt(getTagValue("cellsHigh", gridSettingsElement));
			int cellsWide = Integer.parseInt(getTagValue("cellsWide", gridSettingsElement));

			parseResult.setHeight(height);
			parseResult.setWidth(width);
			parseResult.setCellsHigh(cellsHigh);
			parseResult.setCellsWide(cellsWide);

			String cellShape = getTagValue("cellShape", gridSettingsElement);
			String cellEdges = getTagValue("cellEdges", gridSettingsElement);
			String cellEdgesColor = getTagValue("cellEdgesColor", gridSettingsElement);
			String gridEdges = getTagValue("gridEdges", gridSettingsElement);
			String neighborsType = getTagValue("neighborsType", gridSettingsElement);

			parseResult.setCellShape(cellShape);
			parseResult.setCellEdges(cellEdges);
			parseResult.setCellEdgesColor(cellEdgesColor);
			parseResult.setGridEdges(gridEdges);
			parseResult.setNeighborsType(neighborsType);

			Element simulationElement = (Element) doc.getElementsByTagName("simulationSettings").item(0);

			String simulation = getTagValue("simulation", simulationElement);
			String simulationName = getTagValue("simulationName", simulationElement);
			String simulationAuthor = getTagValue("simulationAuthor", simulationElement);

			parseResult.setMatrixType(simulation);
			parseResult.setTitle(simulationName);
			parseResult.setAuthor(simulationAuthor);
			
			String burnProbability = getTagValue("burnProbability", simulationElement);
			String satisfactionRate = getTagValue("satisfactionRate", simulationElement);
			String sharkBirthRate = getTagValue("sharkBirthRate", simulationElement);
			String sharkDeathRate = getTagValue("sharkDeathRate", simulationElement);
			String fishBirthRate = getTagValue("fishBirthRate", simulationElement);
			String maximumAntsPerCell = getTagValue("maximumAntsPerCell", simulationElement);
			String foodPheromone = getTagValue("foodPheromone", simulationElement);
			String homePheromone = getTagValue("homePheromone", simulationElement);
			String sugarGrowthRate = getTagValue("sugarGrowthRate", simulationElement);
			String sugarGrowthInterval = getTagValue("sugarGrowthInterval", simulationElement);
			String patchMaxCapacity = getTagValue("patchMaxCapacity", simulationElement);
			String agentMetabolism = getTagValue("agentMetabolism", simulationElement);
			String agentVision = getTagValue("agentVision", simulationElement);
			
			parseResult.setBurnProbability(burnProbability);
			parseResult.setSatisfactionRate(satisfactionRate);
			parseResult.setSharkBirthRate(sharkBirthRate);
			parseResult.setSharkDeathRate(sharkDeathRate);
			parseResult.setFishBirthRate(fishBirthRate);
			parseResult.setMaximumAntsPerCell(maximumAntsPerCell);
			parseResult.setFoodPheromone(foodPheromone);
			parseResult.setHomePheromone(homePheromone);
			parseResult.setSugarGrowthRate(sugarGrowthRate);
			parseResult.setSugarGrowthInterval(sugarGrowthInterval);
			parseResult.setPatchMaxCapacity(patchMaxCapacity);
			parseResult.setAgentMetabolism(agentMetabolism);
			parseResult.setAgentVision(agentVision);

			parseResult.createParams(this);

		} 
		catch (Exception e) {
			e.printStackTrace();
		}


		return parseResult;

	}

	public String getTagValue(String sTag, Element eElement) {
		try{
			
			if(eElement.getElementsByTagName(sTag).item(0) == null){
				throw new XMLFormatException(sTag);
			}
			
			NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

			Node nValue = (Node) nlList.item(0);

			if(nValue == null){
				throw new XMLFormatException(sTag);
			}

			return nValue.getNodeValue();
		}
		catch(XMLFormatException e){
			return e.insertDefault();
		}

	}


}
