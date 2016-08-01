#Cell Society API

##Visualization:

getXML() is the method that tells the configuration to run by creating an XMLObject and then tells the simulation to load itself from the controller’s XMLObject. It is also updated from each StateManager through the updateVisual() method each time the frame is changed. This calls the getVisual() object from each cell in the grid and modified it. The runtime sliders, when changed, overwrite information in the XMLObject being used to run the simulation. The speed slider overwrites the FPS variable in the InterfaceController, which creates a new KeyFrame in the CellSociety gameLoop, resetFrameAndPlay(). 
It also displayed the populations of each simulation by creating a new Plotter and PopulationGrapher, which read the labels of the active States from the active simulation and counted them.

The button methods should have been private: pausePlayAnimation(), resetSimulation(), displayPlots(), and more.
GetXML() could have been private, updateXML() could have been deleted, and in the InterfaceView makeSideFrame() and setSideFrame() could have been private.

##Configuration:

The XMLObject is what is returned to the visualization from the XMLParser, so the doParse() method needs to be public. Additionally the getTagValue() is called from the Params class, so it needs to be public so that different classes reading in values from XML can call it. In doing this the XMLParser calls createParams(), which gets the colorParams(), proportionParams(), imageParams(), values(), and innerValues() from the Parser it creates; everything else in this could have been private. The get and set methods in the XMLObject are called throughout the program when runtime values change, so most of these need to be public.

##Simulation:

In the Matrix class, the play() method should be public because it is being called for every frame in the timeline.
Other methods in this class should have been private, such as the createInstance() method since it is called inside Matrix to create the initial cells and puts them in the Cell map, which is a public variable. Since the map is public, then createInstance() doesn’t need to be public.

In Cell, getNeighbors() should be public because it is called inside Matrix for every cell for every frame in the game.

In each StateManager, initializeStates() should be private, but many of the variables need to be accessed for Rules so they should be public. The StateFactories all need to have public methods because they are returning a specific object to an external class. 

In each Rules class, determineNextState, and determineNextStateAgain(), needs to be public because it is called from each Cell every time the play() loop is called in Matrix. getMySliderValues() needs to be public so that the correct sliders can be made for each simulation, as does getRandomGrid() so that the Matrix can access that grid and make Cells from it. So do the getProportions() and setProportions() methods so that the Matrix can check that appropriate values have been assigned to the Rule. Additionally, the methods called from the individual states into the Rules class need to be public, but those sub methods do not. This is a laundry list of methods in each specific Rules class.
