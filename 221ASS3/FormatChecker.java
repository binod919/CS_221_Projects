import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Binod karki
 *
 */

public class FormatChecker {

	/**
	 * main method to create instance according to the number of files passed
	 * 
	 * @param args.
	 *  args could be the name of the file(s) to be checked
	 */
	public static void main(String[] args) {

		int argLenth = args.length;
		
		if (argLenth != 0) {
			for (int i = 0; i < argLenth; i++) {
				new FormatChecker(args[i]);
			}
		} else {
			System.out.println("\n" + "File(s) must be passed in order to check for valid or invalid format." + "\n");
		}
	}

	/**
	 * constructor of the FormatChecker class
	 * 
	 * @param fileName
	 */
	public FormatChecker(String fileName) {
		Scanner scan = null;
		
		/*
		 * Every cheching happens in this try catch block.
		 * @throw ecxception if the given file is not found or if there is someting wrong with the given data
		 */

		try {

			scan = new Scanner(new File(fileName));
			String[] rowAndCol = scan.nextLine().split("\\s+");

			/**
			 * Checking if first line of the file contains only two integers for row and
			 * column
			 */
			if (rowAndCol.length != 2) {
				throw new InputMismatchException(
						"First line should only contains two integers, one for row and other for column.");
			}

			try {

				/**
				 * Parsing the integers for row and column from the first line. If the first
				 * line contains other elements than Integers then the exception is thrown.
				 */
				int row = Integer.parseInt(rowAndCol[0]);
				int col = Integer.parseInt(rowAndCol[1]);

				// checking if there are correct number of rows and columns as provided in the
				// dimensions
				int rowCount = 0;
				int colCount = 0;
				double element = 0.0;
				while (scan.hasNextLine()) {

					// currentColElements contains the contents of a current line as read by the
					// scanner
					String[] currentColElements = scan.nextLine().split(" ");

					// checking if the current line is empty or not.
					if (!currentColElements[0].isEmpty()) {

						// counting the number of rows, as the scanner moves to the next line
						rowCount++;

						// checking if the number of columns matches the defined number of columns every
						// time the scanner moves
						// to the next line
						if (col > currentColElements.length || col < currentColElements.length) {
							colCount = currentColElements.length;
						} else
							colCount = currentColElements.length;

						// checking if there is same number of columns as defined column
						if (col > colCount) {
							throw new InputMismatchException(
									"The file has less column than provided column definition.");
						} else if (col < colCount) {
							throw new InputMismatchException(
									"The file has more column than provided column definition.");
						}

						/*
						 * checking if the given values in the file is valid data type or not
						 */
						try {
							for (int i = 0; i < currentColElements.length; i++) {
								element = Double.parseDouble(currentColElements[i]);
							}
						} catch (NumberFormatException e) {
							throw new InputMismatchException(
									"The given values in the files shoule be either in integer or double format.");
						}

					}

				}

				/*
				 * checking if our rowCount matches the row provided in the row definition
				*/
				if (row > rowCount) {
					throw new InputMismatchException("The file has less rows than provided row definition.");
				} else if (row < rowCount) {
					throw new InputMismatchException("The file has more rows than provided row definition.");
				}

				// if everything untill this line executes without an exception, then these line
				// will be executed.
				System.out.println( "\n" + fileName);
				System.out.println("VALID" + "\n");
				scan.close();

			} catch (NumberFormatException e) {
				throw new InputMismatchException("The dimentions should not be other than integers.");
			}

		} catch (FileNotFoundException e) {
			System.out.println("\n" + fileName);
			System.out.println("Given file is not found.");
			System.out.println("INVALID" + "\n");

		} catch (InputMismatchException e) {
			System.out.println("\n" + fileName);
			System.out.println(e.getMessage());
			System.out.println("INVALID" + "\n");
		} finally {
			if (scan != null)
			scan.close();
		}
	}

}
