import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a 2D circuit board as read from an input file.
 *  
 * @author mvail
 */
public class CircuitBoard {
	private char[][] board;
	private Point startingPoint;
	private Point endingPoint;

	//constants you may find useful
	private final int ROWS; //initialized in constructor
	private final int COLS; //initialized in constructor
	private final char OPEN = 'O'; //capital 'o'
	private final char CLOSED = 'X';
	private final char TRACE = 'T';
	private final char START = '1';
	private final char END = '2';
	private final String ALLOWED_CHARS = "OXT12";

	/** Construct a CircuitBoard from a given board input file, where the first
	 * line contains the number of rows and columns as ints and each subsequent
	 * line is one row of characters representing the contents of that position.
	 * Valid characters are as follows:
	 *  'O' an open position
	 *  'X' an occupied, unavailable position
	 *  '1' first of two components needing to be connected
	 *  '2' second of two components needing to be connected
	 *  'T' is not expected in input files - represents part of the trace
	 *   connecting components 1 and 2 in the solution
	 * 
	 * @param filename
	 * 		file containing a grid of characters
	 * @throws FileNotFoundException if Scanner cannot read the file
	 * @throws InvalidFileFormatException for any other format or content issue that prevents reading a valid input file
	 */
	public CircuitBoard(String filename) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner fileScan = new Scanner(new File(filename));
		
		//TODO: parse the given file to populate the char[][]
		// throw FileNotFoundException if Scanner cannot read the file
		// throw InvalidFileFormatException if any formatting or parsing issues are encountered
				
		int row = 0; 
		int col = 0;
		
		try {
			if(fileScan.hasNextLine()) {
				String[] gridDimentions = fileScan.nextLine().split(" ");
				
				if(gridDimentions.length != 2) {
					throw new InvalidFileFormatException("The provided dimentions are invalid");
				}else {
					row = Integer.parseInt(gridDimentions[0]);
					col = Integer.parseInt(gridDimentions[1]);
				}
				
				board = new char [row] [col];
				int startCount = 0;
				int endCount = 0;
				int countRow = 0;
				while(fileScan.hasNextLine()) {
					String[] tempContents = fileScan.nextLine().split(" ");
					if(tempContents.length != col)
						throw new InvalidFileFormatException("Number of columns does not match with the number of column provided");
					else {
						
						for (int i = 0; i< tempContents.length;i++) {
							board[countRow][i] = tempContents[i].charAt(0);
							if(tempContents[i].charAt(0) == START) {
								startingPoint = new Point(countRow, i);
								startCount++;
							}else if(tempContents[i].charAt(0) == END) {
								endingPoint = new Point(countRow, i);
								endCount++;
							}
						}
						countRow++;
					}
				}
					
				if (startCount > 1 || startCount < 1) {
					throw new InvalidFileFormatException("Invalid Starting Point");
				}

				if (endCount > 1 || endCount < 1) {
					throw new InvalidFileFormatException("Invalid Ending Point");
				}

				if (countRow != row) {
					throw new InvalidFileFormatException("Number of Rows does not match with the number of Rows provided");
				}				
			}
		}catch (InvalidFileFormatException e) {			
			System.out.println(e);
			System.exit(1);
		}
		
	
		ROWS = row; //replace with initialization statements using values from file
		COLS = col;
		
		fileScan.close();
		
	}
	
	/** Copy constructor - duplicates original board
	 * 
	 * @param original board to copy
	 */
	public CircuitBoard(CircuitBoard original) {
		board = original.getBoard();
		startingPoint = new Point(original.startingPoint);
		endingPoint = new Point(original.endingPoint);
		ROWS = original.numRows();
		COLS = original.numCols();
	}

	/** utility method for copy constructor
	 * @return copy of board array */
	private char[][] getBoard() {
		char[][] copy = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				copy[row][col] = board[row][col];
			}
		}
		return copy;
	}
	
	/** Return the char at board position x,y
	 * @param row row coordinate
	 * @param col col coordinate
	 * @return char at row, col
	 */
	public char charAt(int row, int col) {
		return board[row][col];
	}
	
	/** Return whether given board position is open
	 * @param row
	 * @param col
	 * @return true if position at (row, col) is open 
	 */
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
			return false;
		}
		return board[row][col] == OPEN;
	}
	
	/** Set given position to be a 'T'
	 * @param row
	 * @param col
	 * @throws OccupiedPositionException if given position is not open
	 */
	public void makeTrace(int row, int col) {
		if (isOpen(row, col)) {
			board[row][col] = TRACE;
		} else {
			throw new OccupiedPositionException("row " + row + ", col " + col + "contains '" + board[row][col] + "'");
		}
	}
	
	/** @return starting Point */
	public Point getStartingPoint() {
		return new Point(startingPoint);
	}
	
	/** @return ending Point */
	public Point getEndingPoint() {
		return new Point(endingPoint);
	}
	
	/** @return number of rows in this CircuitBoard */
	public int numRows() {
		return ROWS;
	}
	
	/** @return number of columns in this CircuitBoard */
	public int numCols() {
		return COLS;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				str.append(board[row][col] + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	
}// class CircuitBoard
