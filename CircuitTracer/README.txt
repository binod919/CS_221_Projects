********************
* Circuit Tracer
* CS-221
* April 27, 2018
* Binod Karki
********************

OVERVIEW:
	Circuit Tracer program finds the shortest paths for connecting pair of 
	components on a crowded circuit board as read from an input file using
	stack or queue as a user specified data storage. 
	
INCLUDED FILES:

	CircuitBoard.java					 	- Source File
	CircuitTracer.java						- Source File
	InvalidFileFormatException.java			- Source File
	OccupiedPositionException.java			- Source File
	Storage.java							- Source File
	TraceState.java							- Source File
	valid1.dat								- Test File
	README.txt								- This File
	
COMPILING AND RUNNING:

	From the directory containing all the source file and the test file, 
	run the following command:
	
	$ javac *.java  -- To compile all source files
	
	$ java CircuitTracer -s|-q -c|-g FileName
	
		Note:
		
				-s  		-- Use Stack for storage
				-q 			-- use Queue for storage
		
				-c 			-- run program in console mode
				-g  		-- run program in GUI mode
					
				FileName	-- name of the input file
		
ANALYSIS:
	
	*	The choice of storage does not noticeably affects the performance of 
		a search algorithm. Because stack lacks flexibility in adding and removing 
		elements than queue, searching paths could take more time than 
		compared to queue. The stack only allows the top most element to be
		removed at a time, hence searching an element or searching for open 
		spaces in a stack could be really time consuming. On the other hand, 
		queue can remove element from the first of the queue, and adds element
		to the last in the queue. However, searching element in the middle of the 
		queue will also be time consuming. 
		
	*	No, the possible number of paths will  not be affected by the choice 
		of stack or queue although, the program running time might be affected.
		
	*	Them memory usage will not be affected since, stack and queue will both be 
		able to store multiple data at a time. 
		
	*	The Big-Oh notation for the search algorithm is (4^n). Since, the shortest
		path between starting point and ending point is calculated by trying every 
		possible paths between them, the complexity grows exponentially.
		Since this algorithm explores every possible paths between two points,
		it could reflect the number of paths explored and among those paths, 
		our goal is to find minimum path. 
		
	*	It is not true that using one of the storage structures always finds a
		solution in fewer steps.
	
	*	No, There is no guarantee that using either of the storage structure finds
		the shortest path in first solution. The search algorithm looks for every 
		possible path between starting point and ending point. The first solution
		will be a valid solution, but not shortest path.    
		
PROGRAM DESIGN AND IMPORTANT CONCEPTS:
	
	Circuit Tracer is designed to find a shortest path between any two points
	in a circuit board, reading input from a input file and choosing a data
	structure i.e. stack or queue as a storage.
	This program is made up of multiple classes listed above. All classes
	depend on each other in order to find the shortest path. Each class functions
	in a following way:
	
		CircuitBoard.java: This class contains a char array in order to read the 
		inputs from a file, and also holds a x and y coordinates for starting 
		point and ending point respectively. It also contains additional public
		methods. This class is overall representation of 2D Circuit board as read
		from a file.
		
		CircuitTracer.java: This Class contains 2D circuit board, best paths between
		two starting and ending point. It contains algorithm to find the shortest path
		between starting and ending point. The user must execute this class in order
		to produce an output. 
		
		CircuitTracerTester.java: This class contains multiple tests case scenarios 
		in order to verify correct implementation of search algorithm using stacks
		and queues.
		
		InvalidFileFormatException.java: It indicates an InvalidFileFormatException, 
		thrown whenever there is invalid format from the input file.
		
		OccupiedPositionException.java: It indicates an OccupiedPositionException thrown 
		a trace state is attempted to place in an occupied place in a circuit board.
		
		Storage.java: It is a container for storing elements of type T. It contains two 
		data structures Stack and Queue, which will be instantiated according to the 
		need of users. It also contains other public methods to help adjusting or 
		making changes to data structures.
		
		TraceState.java: This class represents a possible valid states between two points.
		It also contains additional public methods in order to search valid trace states. 
		
TESTING:
	
	CircuitTracerTester.java class is the primary source for the testing of Circuit Tracer.
	Different test scenarios has been added in this class in order to ensure the correct
	functionality of the program. The test scenarios includes:
	
		CircuitBoard Constructor Tests
		
		CircuitTracer Valid Input Tests
		
		CircuitTracer Invalid Input Tests
		
		CircuitTracer Invalid Command Line Tests
		
		CircuitTracer GUI Option Tests
	
	These tests confirms the correct functionality of this program.
	
DISCUSSION:
	
	The challenge in doing this program was to first understand the pre-written code.
	The functionality of each classes and its public method was necessary to understand.
	I struggled on how to let user choose between console output and GUI output. At first
	I thought of using switch case, but then I thought of using enum to select Console or 
	GUI mode. And, I struggled on the searching algorithm, which took me a lot of time and
	thinking to make it work. The Eclipse debugger was very helpful to keep track of every
	variables and see what is happening in them. 