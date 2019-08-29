import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {
	private CircuitBoard board;
	private Storage<TraceState> stateStore;
	private ArrayList<TraceState> bestPaths;
	private GameMode gameMode;
	
	private static long startTime = 0;
	private static long endTime = 0;
	
	/**
	 * Enum to select GUI or Console Output
	 * @author karki
	 */
	private enum GameMode {
		GUI, Console
		};

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		
		
		
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {	
			startTime = System.nanoTime();
			new CircuitTracer(args); //create this with args
			
			endTime = System.nanoTime();
			System.out.println("Total Time Taken: " +((endTime - startTime)/ 10e9) + " Seconds");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
		
		System.err.print("Usage: java CircuitTracer -s|-q -c|-g Filename");
		System.out.println("\rHINT: \r -s: Stack \r -q: Queue \r -c: Use Console Output \r -g: GUI Output \r Filename: input file name");		
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 * @throws FileNotFoundException 
	 */
	private CircuitTracer(String[] args) throws FileNotFoundException {
		
		if(args[0].equals("-s")) {
			stateStore = new Storage<>(Storage.DataStructure.stack);
			System.out.println("Data Storage type selected: Stack");
		} else if(args[0].equals("-q")) {
			stateStore = new Storage<>(Storage.DataStructure.queue);
			System.out.println("Data Storage type selected: Queue");
		}else {
			// printing usage message
			printUsage();
			System.exit(1);
		}
		
		
		if(args[1].equals("-c")) {
			gameMode = GameMode.Console;
		}else if(args[1].equals("-g"))
			gameMode = GameMode.GUI;
		else {
			// printing usage message
			printUsage();
			System.exit(1);
		}
		
		board = new CircuitBoard(args[2]);
		bestPaths = new ArrayList<>();
		
		searchForBestPaths(); // run best path search algorithm
		
		printBestPaths(); // print best paths to console or GUI
		
		//TODO: parse command line args
		//TODO: initialize the Storage to use either a stack or queue
		//TODO: read in the CircuitBoard from the given file
		//TODO: run the search for best paths
		//TODO: output results to console or GUI, according to specified choice
	}

	/**
	 * Prints All the best paths 
	 */
	private void printBestPaths() {
		if(gameMode == GameMode.Console) {
			
			if(bestPaths.size() == 0)
				System.out.println("Could not Found Best Paths");
			
			for(int i = 0; i < bestPaths.size(); i++) {
				System.out.println("Best Paths " + (i + 1) + ":");
				System.out.println(bestPaths.get(i).getBoard().toString());
			}
			
		} else if(gameMode == GameMode.GUI) {
			System.out.println("GUI is currently NOT supproted. \rPlease run this program again in console mode.");
		}
		
	}

	/**
	 * Runs a Brute Force algorithm and searches for
	 * best path. 
	 * 
	 */
	private void searchForBestPaths() {
		
		int x = (int) board.getStartingPoint().getX();
		int y = (int) board.getStartingPoint().getY();
		
		getInitialTraceStates(x, y); // get initial trace state
		
		while(!stateStore.isEmpty()) {
			TraceState retrievedState = stateStore.retrieve();
			
			if(retrievedState.isComplete()) {
				if(bestPaths.size() == 0) {
					bestPaths.add(retrievedState);
				}else {
					if(retrievedState.pathLength() == bestPaths.get(bestPaths.size() - 1).pathLength()) {
						bestPaths.add(retrievedState);
					}else if(retrievedState.pathLength() < bestPaths.get(bestPaths.size() -1).pathLength()) {
						bestPaths = new ArrayList<TraceState>();
						bestPaths.add(retrievedState);
					}
				}
			} else {
				generateNewTraceStates(retrievedState);
			}
		}	
	}

	/**
	 * Generates New trace state from current tracestate and adds it
	 * to the state store.
	 * @param Current trace state
	 */
	private void generateNewTraceStates(TraceState retrievedState) {
		Point[] adjacentPoints = getAdjacentsPoints((int) retrievedState.getPath().get(retrievedState.getPath().size() - 1).getX(),
				(int) retrievedState.getPath().get(retrievedState.getPath().size() - 1).getY());
		
		for (Point p : adjacentPoints) {
			if(retrievedState.getBoard().isOpen(p.x, p.y)) {
				stateStore.store(new TraceState(retrievedState, p.x, p.y));
			}
		}	
	}

	/**
	 * Generates new trace state for each position to 
	 * starting components
	 * @param x Point x
	 * @param y Point y
	 */
	private void getInitialTraceStates(int x, int y) {
		
		Point[] adjacentPoints = getAdjacentsPoints(x, y);
		
		for(Point p : adjacentPoints) {
			if(board.isOpen(p.x, p.y)) {
				stateStore.store(new TraceState(board, p.x, p.y));
			}
		}
		
	}
	
	/**
	 * Calculates adjacents points from the given coordinate
	 * @param x X coordinate
	 * @param y y coordinate
	 * @return Returns adjacents points
	 */
	private Point[] getAdjacentsPoints(int x, int y) {
		
		Point [] adjacentPoints = new Point[4];
		adjacentPoints[0] = new Point(x + 1, y);
		adjacentPoints[1] = new Point(x - 1, y);
		adjacentPoints[2] = new Point(x, y - 1);
		adjacentPoints[3] = new Point(x, y + 1);
			
		return adjacentPoints;
	}
	
} // class CircuitTracer
