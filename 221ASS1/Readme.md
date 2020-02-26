**************************
* Warm Up Exercise
* CS 221
* January 25, 2018
* Binod Karki
**************************

Overview:
	GridMonitor class is a class that implements the methods from the GridMonitorInterface interface, and makes sure that the requirements of this
	assignment is fulfilled.
	

Included Files:
 	1. GridMonitor.java - This class contains all the codes for calculation of sums, averages, delta values and checking if the cells are in danger or not.		
 	2. GridMonitorInterface.java - A ready-made interface provided to implements its method to calculate the sums, averages, delta values and checking if 
 								   the cells are in danger or not.
 	2. GridMonitorTest.java -  A Ready-made class provided to check the working of GridMonitor class.
 	3. negatives.txt - A ready made file containing negatives values for the grid.
 	4. oneByOne.txt -  A ready made file containing only one element for the grid.
 	5. Readme.txt - This file
 	6. Sample.txt -  A ready made file containing the sample elements for the grid.
 	7. sample4x5.txt -  A ready made file containing the elements for 4x5 grid
 	8. sampleDoubles.txt -  A ready made file containing the double values. 
 	10. WideRange.txt -  A ready made file containing wide range values.
 	
BUILDING AND RUNNING:

	First of all, all the files listed above should be in a same directory and compile the GridMonitorTest class with the following command:
	$ javac GridMonitorTest.java
	
	Run the compiled GridMonitorTest class with the folowing command:
	$ java GridMonitorTest.java
	
	A console will show the output with the name of test performed, number of tests, number of passed tests, and number of failed tests.

PROGRAM DESIGN:
	
	GridMonitor implements the provided GridMonitorInterface interface. This interface defined methods for performing sum of adjacent cells, calculating
	averages of adjacent cells, calculating maximum delta value, and checking if the cells are in danger of explosion.
	
	GridMonitor class contains all the codes, so that all the required calculations are done without any error. getBaseGrid method makes a base grid as
	read from the file and returns the filled grid whenever called. 
	
	getSurroundingSumGrid calculates the sum of four adjacent cells by summing the elements of right, left, up, and down cells from the current cells.
	It is made sure that the method will not crash while checking every element in the grid, and if the checking requires to go out of the grid bounds, 
	then the current cell element is taken and summed. For example, program could crash if it goes beyond 3x3 grid and while checking for [2,2] cell, 
	checking element just down would cause program to crash. In this case, the element of [2,2] is taken to to perform the complete sum. 
	after finishing summing values for all the gird, this method returns the grid with the calculated value
	
	getSurroundingSumGrid method uses elements from sumGrid to calculate the average of four adjacent cells. It returns a grid with calculated average 
	value whenever called.
	
	getDeltaGrid uses the values from averageGrid to calculate maximum delta. Maximum delta is calculated by dividing the average value by two. After
	performing the calculations, it returns grid with calculated delta values. 
	
	getDangerGrid uses the values from deltaGrid to check whether the cells are in danger of explosion. This is checked by whether the cells element
	is between the average value and delta value. It returns boolean grid containing information about which cells are in more danger. 

TESTING:

	All the java files, excluding GridMonitor.java, was ready-made and was provided to us to ensure that our GridMonitor class worked as expected. Although
	the GridMonitorTest class was provided to us to ensure that everything that is required in this assignment works, it would not let check the working of 
	the codes I have written until and unless all the methods form the GridMonitorInterface.java is implemented. So in order to check whether my codes and 
	methods works fine, I copied piece of code in different project, which are not included in this submission, and made sure that my codes and methods 
	works well.  
	
	The GridMonitorTest class, which was already provided to us, was able to check following things:
		
		*Checking if the file is loaded correctly. 
		*Checking if the base grid contains elements provided in the file.
		*Checking if the summing of four adjacent cells were performed correctly.
		*Checking if the average of four adjacent cells were performed correctly.
		*Checking if the maximum delta value is correctly calculated.
		*Checking if the cells are in danger and provide information if the cells are in danger.
		*Checking if the toString method has been overridden 

DISCUSSION:

	The main issue for me in this first assignment was that I am completely new in Java. Reading through couple of guides for java and with the help of google,
	I was able to get myself back on track. 
	
	In this assignment, checking four adjacent cells was a bit challenging. It would work easily for the middle cell, for example cell [1,1] in 3x3 grid, but
	checking other cells were challenging. But after making changes in the logic and practicing couple of times, I was able to figure it out. 
