
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridMonitor implements GridMonitorInterface {

	// declaring a variables in order to use in the class
	Scanner scan;
	double[][] baseGrid;
	double[][] sumGrid;
	double[][] averageGrid;
	double[][] deltaGrid;
	boolean[][] dangerGrid;

	/*
	 * **************************************************
	 *  Constructor for GridMonitor Class 
	 *  This Constructor attempts to open the file by the file name
	 *  passed when the GridMonitor class is instantiated and throws an exception if
	 *  there is any exceptions.
	 */

	public GridMonitor(String fileName) throws FileNotFoundException {

		Scanner scan = new Scanner(new File(fileName));

		int gridRow = scan.nextInt(); // getting first integer as a row from a file
		int gridCol = scan.nextInt(); // getting second integer as a column from a file

		// setting rows and columns of all grids as read from the file
		baseGrid = new double[gridRow][gridCol];
		sumGrid = new double[gridRow][gridCol];
		averageGrid = new double[gridRow][gridCol];
		deltaGrid = new double[gridRow][gridCol];
		dangerGrid = new boolean[gridRow][gridCol];

		// for loop to fill the remaining elements of the file to the grid
		for (int row = 0; row < baseGrid.length; row++) {
			for (int col = 0; col < baseGrid[0].length; col++) {

				baseGrid[row][col] = scan.nextDouble(); // next Double

			}
		}
		scan.close();
	}

	/*
	 * ***************************************************** 
	 * Implementing getBaseGrid method from the interface 
	 * This method returns sums of four adjacent cells
	 ******************************************************
	 */

	public double[][] getBaseGrid() {

		// making a copy of baseGrid and returning the copied grid
		double[][] returningBaseArray = new double[baseGrid.length][baseGrid[0].length];
		returningBaseArray = makeCopy(baseGrid);
		return returningBaseArray;
	}

	/*
	 * Implementing getSurroundingSumGrid() method of interface. This method
	 * calculates the averages of the sum of surrounding cells and returns the
	 * calculated averages as the same dimensions of base grid.
	 * 
	 * @see GridMonitorInterface#getSurroundingSumGrid()
	 */
	public double[][] getSurroundingSumGrid() {

		// summing up the value of adjacent cells

		for (int row = 0; row < baseGrid.length; row++) {

			for (int col = 0; col < baseGrid[0].length; col++) {

				sumGrid[row][col] = getCellsSum(row, col);

			}
		}

		// copying the sumGrid and returning the copied array for encapsulation
		double[][] returningSumGrid = new double[sumGrid.length][sumGrid[0].length];
		returningSumGrid = makeCopy(sumGrid);
		return returningSumGrid;
	}

	/*
	 * This additional method checks the adjacent cells and sums up the value on the
	 * basis of current position passed
	 */

	public double getCellsSum(int row, int col) {

		double sum = 0.0;

		// checks a step above of array from the current location of array
		if (isValid(row - 1, col) == true) {
			sum = sum + baseGrid[row - 1][col];

		} else
			sum = sum + baseGrid[row][col];

		// checks the left array from the current location of array
		if (isValid(row, col - 1) == true) {
			sum = sum + baseGrid[row][col - 1];

		} else
			sum = sum + baseGrid[row][col];

		// checks the right array from the current location of array
		if (isValid(row, col + 1) == true) {
			sum = sum + baseGrid[row][col + 1];

		} else
			sum = sum + baseGrid[row][col];

		// checks just down of array from the current location of array
		if (isValid(row + 1, col) == true) {
			sum = sum + baseGrid[row + 1][col];

		} else
			sum = sum + baseGrid[row][col];

		return sum;

	}

	/*
	 * This additional method checks whether the passed row and column is within the
	 * matrix bounds.
	 */

	private boolean isValid(int row, int col) {

		// checks if the passed row and column is within the array bound
		// returns true if row and column are within array bound otherwise reutrns false
		if (row >= 0 && row < baseGrid.length && col >= 0 && col < baseGrid[0].length)
			return true;
		else
			return false;
	}

	/**
	 * This additional method makes copies of an array passed as a parameter and
	 * returns the copy of an array.
	 */

	private double[][] makeCopy(double[][] array) {

		// new variable to store the passed grid
		double[][] copiedArray = new double[array.length][array[0].length];

		// loop to go through elements of every row and column
		for (int row = 0; row < array.length; row++) {

			int col = 0;
			for (double el : array[row]) { // el is the current element in the cell

				copiedArray[row][col] = el;
				col++;
			}
		}

		return copiedArray;
	}

	/**
	 * This additional method makes copies of an boolean array passed as a parameter
	 * and returns the copy of an array.
	 */

	private boolean[][] makeCopy(boolean[][] array) {
		// new array to store passed boolean grid
		boolean[][] copiedArray = new boolean[array.length][array[0].length];

		// for loop to go thorough every elements in the array
		for (int row = 0; row < array.length; row++) {

			int col = 0;
			for (boolean el : array[row]) { // el is the current element in the cell

				copiedArray[row][col] = el;
				col++;
			}
		}

		return copiedArray;
	}

	/**
	 * This additional method checks if the required grid for calculations is empty
	 * or not. For example, to calculate averages, sumGrid must contain the sums;
	 */

	private boolean isEmpty(double[][] array) {

		int count = 0;

		// loop to go through every element in the array
		for (int row = 0; row < array.length; row++) {

			for (double el : array[row]) {

				if (el == 0) { // el is the current element in the cell
					count++;
				}
			}
		}

		// count value will be more than 0 if there no stored elements in the grid,
		// otherwise
		// count remains 0
		if (count > 0)
			return true;
		else
			return false;
	}

	/*
	 * Implementing getSurroundingAvgGrid() method of interface. This method
	 * calculates the average of four adjacent cells
	 * 
	 * @see GridMonitorInterface#getSurroundingAvgGrid()
	 */
	public double[][] getSurroundingAvgGrid() {

		// checking if sumGrid is empty or not
		if (isEmpty(sumGrid) == true)
			getSurroundingSumGrid();

		// loop to go through every element in the grid
		for (int row = 0; row < sumGrid.length; row++) {

			int col = 0;
			for (double el : sumGrid[row]) { // el is the current element in of the grid

				double average = el / 4;
				averageGrid[row][col] = average;
				col++;
			}
		}

		// copying the averageGrid and returning the copied Grid for encapsulation
		double[][] returningAverageGrid = new double[averageGrid.length][averageGrid[0].length];
		returningAverageGrid = makeCopy(averageGrid);
		return returningAverageGrid; // returning the grid with calculated averages

	}

	/**
	 * Implementing getDeltaGrid() method of interface. This method calculates the
	 * maximum delta form the four surrounding cells and returns the calculated
	 * maximum delta value in same format as baseGrid
	 * 
	 * @see GridMonitorInterface#getDeltaGrid()
	 */

	public double[][] getDeltaGrid() {

		// checking if averageGrid is empty or not
		if (isEmpty(averageGrid) == true)
			getSurroundingAvgGrid();

		// loop to go through every elements in the grid
		for (int row = 0; row < averageGrid.length; row++) {

			int col = 0;
			for (double el : averageGrid[row]) { // el is the current element in of the grid

				double deltaValue = Math.abs(el / 2);
				deltaGrid[row][col] = deltaValue;
				col++;
			}
		}

		// copying the deltaGrid and returning the copied Grid for encapsulation
		double[][] returningDeltaGrid = new double[deltaGrid.length][deltaGrid[0].length];
		returningDeltaGrid = makeCopy(deltaGrid);
		return returningDeltaGrid; // returning the grid with calculated averages

	}

	/**
	 * Implementing getDangerGrid() method of interface. This method checks if the
	 * cell is in danger of explosion or not.
	 * 
	 * @see GridMonitorInterface#getDangerGrid()
	 */
	public boolean[][] getDangerGrid() {

		// checking if deltaGrid is empty or not
		if (isEmpty(deltaGrid) == true)
			getDeltaGrid();

		// loop to go through every element in the grid
		for (int row = 0; row < baseGrid.length; row++) {
			for (int col = 0; col < baseGrid[0].length; col++) {

				// this condition checks if the grids are in danger or not
				if ((baseGrid[row][col] < (averageGrid[row][col] - deltaGrid[row][col]))
						|| (baseGrid[row][col] > (averageGrid[row][col] + deltaGrid[row][col]))) {

					dangerGrid[row][col] = true;
				} else
					dangerGrid[row][col] = false;
			}
		}

		// copying the averageGrid and returning the copied Grid for encapsulation
		boolean[][] returningDangerGrid = new boolean[averageGrid.length][averageGrid[0].length];
		returningDangerGrid = makeCopy(dangerGrid);
		return returningDangerGrid;
	}
	
	/*
	 * Overriding toString method and returning what elements are in baseGrid 
	 */
	
	public String toString() {
		
		String str = "";

		// loop to go through every element in the array
		for (int row = 0; row < baseGrid.length; row++) {

			for (double el : baseGrid[row]) {

				str += el + "\t" ;
			}
			
			str += "\n";
		}
		
		String returningString  = "You have following values in your baseGrid:\n" + str;
		return returningString;
		
	}
}
// End of Code
