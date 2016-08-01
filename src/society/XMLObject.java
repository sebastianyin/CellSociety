package society;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

public class XMLObject {
	
	private Document myDocument;
	
	private String matrixType;
	private String author;
	private String title;
	
	private int height;
	private int width;
	private int cellsHigh;
	private int cellsWide;
	
	private String cellShape;
	private String cellEdges;
	private String cellEdgesColor;
	private String gridEdges;
	private String neighborsType;
	
	private String[] colorParams;
	private String[] proportionParams;
	private String[] imageParams;
	
	private String burnProbability;
	private String satisfactionRate;
	private String sharkBirthRate;
	private String sharkDeathRate;
	private String fishBirthRate;
	private String maximumAntsPerCell;
	private String foodPheromone;
	private String homePheromone;
	private String sugarGrowthRate;
	private String sugarGrowthInterval;
	private String patchMaxCapacity;
	private String agentMetabolism;
	private String agentVision;
	
	private List<String[]> values = new ArrayList<String[]>();
	private List<ArrayList<String[]>> innerValues = new ArrayList<ArrayList<String[]>>();
	
	private Params params;

	public Document getMyDocument() {
		return myDocument;
	}

	public void setMyDocument(Document myDocument) {
		this.myDocument = myDocument;
	}

	public XMLObject(Document doc) {
		setMyDocument(doc);
	}
	
	public String getMatrixType() {
		return matrixType;
	}
	public void setMatrixType(String matrixType) {
		this.matrixType = matrixType;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Params getParams() {
		return params;
	}
	public void setParams(Params params) {
		this.params = params;
	}
	public void createParams(XMLParser parser){
		Params param = new Params(parser);
		this.params = param.create(myDocument);
		setColorParams(params.getMyColorParams());
		setProportionParams(params.getMyProportionParams());
		setImageParams(params.getMyImageParams());
		setValues(params.getMyValues());
		setInnerValues(params.getMyInnerValues());
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getCellsHigh() {
		return cellsHigh;
	}
	public void setCellsHigh(int cellsHigh) {
		this.cellsHigh = cellsHigh;
	}
	public int getCellsWide() {
		return cellsWide;
	}
	public void setCellsWide(int cellsWide) {
		this.cellsWide = cellsWide;
	}

	public String getCellShape() {
		return cellShape;
	}

	public void setCellShape(String cellShape) {
		this.cellShape = cellShape;
	}

	public String getCellEdges() {
		return cellEdges;
	}

	public void setCellEdges(String cellEdges) {
		this.cellEdges = cellEdges;
	}

	public String getGridEdges() {
		return gridEdges;
	}

	public void setGridEdges(String gridEdges) {
		this.gridEdges = gridEdges;
	}

	public String getCellEdgesColor() {
		return cellEdgesColor;
	}

	public void setCellEdgesColor(String cellEdgesColor) {
		this.cellEdgesColor = cellEdgesColor;
	}

	public String[] getColorParams() {
		return colorParams;
	}

	public void setColorParams(String[] colorParams) {
		this.colorParams = colorParams;
	}

	public String[] getProportionParams() {
		return proportionParams;
	}

	public void setProportionParams(String[] proportionParams) {
		this.proportionParams = proportionParams;
	}

	public List<String[]> getValues() {
		return values;
	}

	public void setValues(List<String[]> values) {
		this.values = values;
	}

	public String getNeighborsType() {
		return neighborsType;
	}

	public void setNeighborsType(String neighborsType) {
		this.neighborsType = neighborsType;
	}

	public String[] getImageParams() {
		return imageParams;
	}

	public void setImageParams(String[] imageParams) {
		this.imageParams = imageParams;
	}

	public List<ArrayList<String[]>> getInnerValues() {
		return innerValues;
	}

	public void setInnerValues(List<ArrayList<String[]>> innerValues) {
		this.innerValues = innerValues;
	}

	public String getBurnProbability() {
		return burnProbability;
	}

	public void setBurnProbability(String burnProbability) {
		this.burnProbability = burnProbability;
	}

	public String getSatisfactionRate() {
		return satisfactionRate;
	}

	public void setSatisfactionRate(String satisfactionRate) {
		this.satisfactionRate = satisfactionRate;
	}

	public String getSharkBirthRate() {
		return sharkBirthRate;
	}

	public void setSharkBirthRate(String sharkBirthRate) {
		this.sharkBirthRate = sharkBirthRate;
	}

	public String getSharkDeathRate() {
		return sharkDeathRate;
	}

	public void setSharkDeathRate(String sharkDeathRate) {
		this.sharkDeathRate = sharkDeathRate;
	}

	public String getFishBirthRate() {
		return fishBirthRate;
	}

	public void setFishBirthRate(String fishBirthRate) {
		this.fishBirthRate = fishBirthRate;
	}

	public String getMaximumAntsPerCell() {
		return maximumAntsPerCell;
	}

	public void setMaximumAntsPerCell(String maximumAntsPerCell) {
		this.maximumAntsPerCell = maximumAntsPerCell;
	}

	public String getSugarGrowthRate() {
		return sugarGrowthRate;
	}

	public void setSugarGrowthRate(String sugarGrowthRate) {
		this.sugarGrowthRate = sugarGrowthRate;
	}

	public String getSugarGrowthInterval() {
		return sugarGrowthInterval;
	}

	public void setSugarGrowthInterval(String sugarGrowthInterval) {
		this.sugarGrowthInterval = sugarGrowthInterval;
	}

	public String getPatchMaxCapacity() {
		return patchMaxCapacity;
	}

	public void setPatchMaxCapacity(String patchMaxCapacity) {
		this.patchMaxCapacity = patchMaxCapacity;
	}

	public String getAgentMetabolism() {
		return agentMetabolism;
	}

	public void setAgentMetabolism(String agentMetabolism) {
		this.agentMetabolism = agentMetabolism;
	}

	public String getAgentVision() {
		return agentVision;
	}

	public void setAgentVision(String agentVision) {
		this.agentVision = agentVision;
	}

	public String getFoodPheromone() {
		return foodPheromone;
	}

	public void setFoodPheromone(String foodPheromone) {
		this.foodPheromone = foodPheromone;
	}

	public String getHomePheromone() {
		return homePheromone;
	}

	public void setHomePheromone(String homePheromone) {
		this.homePheromone = homePheromone;
	}

}
