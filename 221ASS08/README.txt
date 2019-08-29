********************
* Doubly Linked List
* CS-221
* April 13, 2018
* Binod Karki
********************

OVERVIEW:
	Doubly Linked List in an linked based implementation of IndexedUnsortedList ADT, 
	which allows users to store set of collections in double linear order. ListTester 
	makes sure that this program is fully valid.
	
INCLUDED FILES:
	DoubleNode.java 			 - Source File
	IndexedUnsortedList.java 	 - Source File
	IUDoubleLinkedList.java 	 - SourceFile
	ListTester.java				 - SourceFile
	README.txt					 - This File
	
COMPILING AND RUNNING:
	From the directory containing all the source files, run the command
	  
	   $ javac DoubleNode.java
	   $ javac IndexedUnsortedList.java
	   $ javac IUDoubleLinkedList.java
	   $ javac ListTester.java
	   
	   or,
	   
	   $ javac *.java  
	   
	This will compile all the source file needed to run the program. Then, run the command
	
		$ java ListTester 
		
	This command will run the compiled ListTester class.
	
	The console screen will output the results whether the implementation of doubly linked list
	passed or failed.
	
PROGRAM DESIGN AND IMPORTANT CONCEPTS:
  	Doubly Linked List implements IndexedUnsortedList ADT, which contains defined methods for the
  	linked list. Users can Add, remove, and element however they want. 
  	As the name implies, user's data are stored in a series of list connected by a node. This node
  	is capable to store an element, store reference to next node, and store reference to previous 
  	node, hence forming a linked chain like structure. Since it is an unordered list, the elements 
  	are not in any kind of order. Position of an element depends upon how user adds an element into 
  	the list. For example, calling method add(A) will add A to the rear of the list, if the list is 
  	empty, then new list will be created with an element A in it. Calling method add(4, A) will add 
  	A to the fourth index of a list. Therefore, Contents of this list is not ordered.
  	
  	Since Doubly linked lists are not indexed, it is not possible to iterate into list via for loops.
  	A list iterator is used to iterate through each and every element in the list. Not only list iterator
  	is used to iterate through elements in the list, it also has functionality to add, set, and remove
  	elements in the list. List iterator also enables users to iterate through elements reversely through 
  	the list. 
  	
  	ListTester class makes sure that every implementation of IndexedUnsortedList is functional. It also 
  	has tests for change scenarios for empty list, one element list, and three element list to show the correct
  	functionality of IndexedUnsortedList methods.  
  	
 TESTING:
 	ListTester class is the primary source for the testing of IUDoubleLindedList class, which implements 
 	IndexedUnsortedList ADT. ListTester contains different tests to test every possible change scenarios
 	that could take place in empty list, one element list, two element list, and three element list. Upon
 	executing this program, the console output shows the detailed information about the change scenarios,
 	number of tests, number of passed tests, and percentage of total passes. There are currently 65 change
 	scenarios being tested by ListTester. Such as,
 	
 	a new empty list
 	adding first element in a empty list
 	removing first element from a one element list
 	removing first element from two element list 
 	removing second element from two element list
 	adding an element to specified indexed in one element list
 	adding specified element into two element list
 	
 	a new list iterator
 	adding an element via list iterator 
 	removing an element via list iterator
 	setting an element via list iterator ... etc.
 	
 	More tests are necessary to check the validity of the program. 
 	Currently every tests are passing without any errors, or exceptions, which confirms the correct functionality of
 	doubly linked list.
 	
 DISCUSSION:
 	It was challenging to implement IndexedUnsortedList's method. Because every node has reference to previous and 
 	next node, I always missed setting some of the nodes. This caused many NullPointerExceptions. The debugger in Eclipse IDE
 	was especially helpful in solving these kind of problem. It allowed me to keep track of every variables and every nodes,
 	which made debugging easier. 
 	
  	 
  	