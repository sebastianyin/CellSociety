#Design

Bailey Wall, Michael Daou, and Jiadong Yin

##Introduction:

This project will deal with the problem of understanding complex, large-scale systems using simulations based on small-scale mechanics. Our goal is to make this simulation an automatic process by writing concise and clear code that allows flexibility with changing design parameters. It is most important to be flexible with the number of the states that a cell can have, the rules that govern the changes of the system, and numerous other parameters like changing speed of simulation, size of grid, and initial conditions of cells.

##Overview:

The first thing created will be the Main class, which extends Application and contains the main method. This class will receive the stage and create an instance of the CellSociety class. This class will set the initial scene to the stage, then take the XML file information from the Reader class (from a file that the reader selects) and create one of three instances of the abstract Matrix class: FireMatrix, WaTorMatrix, or SegregationMatrix. These Matrices contain the root Group and return a scene, which the stage will be set to whenever the scene is active. The specific instances of the Matrix class can also be changed in the interface if the user desires to change the values of some of the customizable input parameters such as simulation speed. 

A Rules class will contain the general framework for determining the next state of a Cell in question. A Rules class can be extended and its determineNextState() method overridden to implement simulation-specific behavior. This method returns a generic State object, and when overridden, returns a State subclass also specific to the type of simulation being run.

A Matrix object creates different instances of the superclass StateManager according to the type of simulation being played: FireStateManager, WaTorStateManager, or SegregationStateManager. These StateManagers take in initial parameters, as well as the Rules that govern the process of updating the States, and create the appropriate instances of particular States accordingly.

The Matrix is then used to create new Cells that are passed the State information from StateManager. The ‘Matrix’ stores cells in a HashMap that maps from a Coordinate key to a Cell value. It also adds each Cell’s visual component, a square, to the Group for viewing. The Cell class knows its position, its other States which are contained in an ArrayList, and its neighboring Cells. It also contains a copy of its previous self which retains its old neighbors and information.

The CellSociety class starts the loop for each Matrix class which runs as long as the simulation is active. The user can interact with this loop by stopping, starting, or stepping through it using buttons on the CellSociety class. A complete step is a single complete iteration over all the Cells in the Matrix.

The loop calls on the specific instance of Matrix to update itself, which calls on the Cells to update themselves, which tell their StateManagers to update themselves, passing them the Cell’s neighbors as a parameter. Neighbors are retrieved by a getNeighbors() method that takes a depth parameter, which determines the radius of the Neighborhood, ie: how deep to look into the surrounding Cells. This is done to account for rules that would require considering Cells whose coordinates are more than 1 Cell away from the current Cell in question.

The FireMatrix class creates Cells that have FireStateManagers. These StateManagers contain a Coordinate and a Fire subclass of State. It will also hold a value that corresponds to the probability of a tree beside a burning tree to catch on fire.

When the FireStateManager calls the updateStates() method of the Matrix class, it first calls the Fire state to update itself and passes it the Cell neighbors to compare to itself. The Fire state calculates how many of its neighbors are on fire and then passes that information to its ‘Rule’ subclass, which uses the getNextState() method to determine whether the Cell should be on Fire during the next generation. The newly calculated States will determine how the Rectangle is updated in updateVisual().

The WaTorMatrix class creates Cells that have WaTorStateManagers. These StateManagers contain a Coordinate and a Type subclass of State.

When the WaTorStateManager calls updateStates(), it first calls the Type state to update itself and passes it the Cell neighbors to compare to itself. It checks what it is; a shark or water or a fish. If it is a fish it checks how many turns it has made, and if it is the threshold it lets the WaTorMatrix class know and resets the counter. It then checks its neighbors, and it tries to find an adjacent empty space and randomly selects one to move to, as long as it is not going to be occupied by another fish or shark. It checks its surrounding Cells and if one or more than one of them are not occupied by a fish, it randomly chooses one to move to as long as one fish or shark hasn’t already decided to move there.

The SegregationMatrix class creates Cells that have SegregationStateManagers. These StateManagers contain a Coordinate and a Type subclass of State. It will also hold a value that corresponds to the ‘SatisfactionRate’ of the Cells. When the SegregationStateManager calls updateStates(), it first calls the Type state to update itself and passes it the Cell neighbors to compare to itself. 

If the Type is empty, it doesn’t do anything. If the Type is Red or Blue, it checks its neighbors and calculates how many of them are its same type, and how many aren’t. It then passes these numbers, along with its satisfaction rate, to the Rules subclass for it to figure out what its next State should be. If the result is unsatisfied, the SegregationMatrix then calls to update the Coordinate class which calculates the coordinates of an empty cell the current Cell can switch places with.

![](https://github.com/duke-compsci308-fall2015/cellsociety_team02/blob/master/CellSocietyDesign.jpg?raw=true)

##User interface:

The user will interact with the program through buttons with listeners implemented in the CellSociety class. They will be able to select which file they want to load the simulation from (hopefully from a dropdown menu) as well as stop the loop from executing, make it start executing again, as well as step through the generations one at a time.

##Design details:

XML file information will be read in and will determine CellSociety column height and CellSociety row width, as well as the height and width of each individual cell. CellSociety also determines the user interface, which will take any parameters the user can set during runtime and update them in the running grid.

The Matrix superclass will contain the loop constructor, as well as the HashMap of Cells used in each Matrix. The Matrix subclasses will determine which StateManager is created and applied to the cells. The StateManager superclass will contain both the Coordinate State and the visual representation of the state, in this case a Rectangle object. This is because each cell will have these components. The StateManager superclasses will determine which other states are created, and the parameters they take in from the Matrix subclasses will determine how these specific states are initialized, expect for the x and y coordinates which will be passed to the StateManager superclass for the creation of the Cell’s Coordinate State.
Each subclass of State must implement an update() method it will call upon being updated.

To keep track of Cells that are empty and Cells that are going to be filled in the next pass, each Cell will receive a reference to a list of empty cells in the Matrix in its updateStates() method. As each Cell decides to move to an empty Cell or give birth to another Cell, it either gets its empty Cell from this list or checks that the empty Cell will be available. If it is found on this list, it removes the Coordinate and switches places with or gives birth into the empty Cell.

###Use cases:

####To construct a grid, which is the first thing that will happen every time the simulation is created:

The user will run the program. This first will run the Main class, creating an instance of the CellSociety class which will wait for the user to select which type of Matrix they want to see in action (most likely from a dropdown menu). Once this happens, the Matrix will be created, the loop will be set, and the Cells will be created based on the size of the scene.

####To apply the rules to a middle cell:

The Matrix calls the findNeighbors method on each of the Cells in the grid. This takes in the Map object holding all of the Cells and creates an ArrayList of all of the cells to the right, left, up, down, right-up, right-down, left-down, and left-up of the current cell. The Matrix then calls the updateStates() method on each of the Cells in the grid. Each Cell in turn calls the updateStates() method on its specific StateManager, passing it the ArrayList of neighboring Cells. Each respective StateManager then calls on the necessary States to update themselves, passing them the ArrayList of neighboring Cells. The individual state, which would in this case be the Life state, then counts the number of neighboring Cells which have a state of getLife() == true. It will then pass this number to the rules class and receive the return value that determines what the next state will be. The updateStates() method in StateManager then calls the Shape to update itself accordingly, passing it the result of the State’s update.

####To apply the rules to an edge cell:

The same findNeighbors call is made followed by the same updateStates() method receiving the same ArrayList of neighbors. The Life state again calculates the number of neighboring Cells which have a state of getLife() == 0 and compares that to the Rule based on the number of neighbors it has.

####For one generation to update itself to the next:

The findNeighbors call is made, and the neighbors Cells are stored in ArrayLists in the Cells which it matters for. The updateStates() method is then called on each cell, and each Cell’s state is then calculated based on the status of its neighbors when it looked at them, and based on the current state of the Map if necessary. The Group is then refreshed in the specific Matrix class.

####Set a simulation parameter:

The XML file reader will read in the probability of the tree to catch on fire and pass it to the FireStateManager to remember and pass to the FireState when it is calculating whether the current cell should be on fire in the next generation or not.

####Use the GUI to change the current simulation from Game of Life to Wator:

The user will stop the Game of Life simulation from running and choose a different file to upload. This will cause the CellSociety to construct a new Matrix object and begin that specific simulation.

##Design Considerations: 

One of the first design considerations that we had to account involved storing the previous Cell states to account for the fact that the Cells in the Matrix are updated sequentially in a for-loop. Upon realizing this, we contemplated whether to store an entire Matrix containing the previous generation of Cells, or to store a Cell’s previous states within the Cell object as an ArrayList. Ultimately, we decided to store an instance of the old version of the Cell inside the current Cell object. This old Cell would contain the current Cell’s previous States, including its previous location, and all other instance variables known to a Cell object. The reason we decided to do this was to account for a wider possibility of rules that might be contingent on a Cell’s instance variables other than its state. Thus to have this information available, if it was ever needed, for the Rule class to run its getNextState() method, we would need all the attributes of a Cell.

Another design consideration that we debated at lengths about was where to put hold our Rules class, and what kind of methods it would contain. There was a suggestion to make the Rules class a Utils class with global, static methods, but this suggestion was quickly forgotten as we agreed that Utils classes should generally be avoided. Not long after, we decided that the Matrix class would hold the Rules as an instance variable. From the standpoint of wanting to have an intuitive design, this made sense; a Matrix object is specific to the type of simulation that is being run, and thus, it should have a corresponding Rules object that would also be specific to the type of simulation being run. The primary method of interest within a Rules object would be the determineNextState() method, which is simulation-specific and would have to be coded differently for different kinds of implementations.

One alternative that we considered for the Rules class was to have the determineNextState() method inside the State object as a virtual method, which the Rules class would then instantiate with the proper method definition. We did not see any merits to this alternative as compared to the implementation mentioned in the previous paragraph (and vice versa), so we decided to choose the simpler design, which is the one discussed in the previous paragraph.

Another design consideration was whether to create a State class or to include states in a data structure within a Cell. The benefits of having a State superclass would be extendibility, or the ability to create more States that all share a common inheritance but may implement different behaviors or have that behavior passed down from the Rules class. The disadvantage of having a Rules class would be the increased complexity of having to deal with an extra class in our hierarchy. However, it was agreed that the benefits of extendibility outweighed the drawbacks. It thus became that the Cell class holds several State instances in a States ArrayList.

The Rules class and the State class were kept separate, and this was done in order to be able to modify the rules associated with a state without changing the State object. In the alternative implementation, a State would contain the simulation-specific code that would need to be modified whenever the rules of the simulation needed to be changed. The benefits of the alternative approach would be the encapsulation of rules and states within one object. The major factor that ultimately helped us make the decision of sticking to split Rules and State classes was the compartmentalization afforded by this approach in situations where the calculation of a new state would require looking at different kinds of states of the neighboring cells. If the determineNewState() method was found within a State, then a state of specific type, say isBurning, would have to deal with states of other types in its “determine” method. We thought that a Rules class with an overarching “determine” method would be more appropriate to deal with such a situation.

In the case of WaTor World, one issue that we ran into was the need to have all the sharks and fish update at the same time. One solution involved running a preliminary for-loop (before the main for-loop), that would run over the Matrix and determine and store all the possible states of the fish and sharks, and then run the rules of the game (shark eating random fish), after which the main for-loop is run. However, we decided to table this solution until further notice. 

###Code dependencies:

####Cell Society class:
Holds the specific Matrix subclass implementation

####Matrix class:
* Matrix class holds map of Coordinates to Cells.
* Matrix class creates Cells, passes them StateManager.
* Matrix class calls updateStates() on each Cell in the Map

####Cell class:
* Calls updateStates() on StateManager

####StateManager class:
* StateManager calls update() on specific State implementations

####subclass of State:
* implements the update() method, gathers data from neighboring cells to pass to Rules class
* updates itself based on the Rules class return
* updates the list of empty Cells in the Matrix class

####Rules subclass:
* Returns the result of the input it was given to the State so it can update itself

##Team responsibilities:

Bailey will work on parsing the xml data into a format ready to be implemented by the matrices, and creating xml test files. Michael will work on the user interface. Jiadong will work on implementing the FireMatrix in the existing code skeleton.