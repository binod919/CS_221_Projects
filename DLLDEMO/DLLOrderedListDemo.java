/**
 * Demo class showing the implementations of ordered doubly linked list
 * @author karki
 *
 */
public class DLLOrderedListDemo {
   public static void main(String[] args) {
	   
	   /**
	    * Instantiating new ordered doubly linked list
	    */
      DLLOrderedList<Integer> DLLOr = new DLLOrderedList<Integer>();

      // use add() method to add elements in the list
      DLLOr.add(15);
      DLLOr.add(50);
      DLLOr.add(30);
      DLLOr.add(40);
      
      DLLOr.add(25);
      DLLOr.add(45);
      DLLOr.add(34);

      // adding element 25 at third position
      //DLLOr.add(2,25);
        
      // let us print all the elements available in list
      for (Integer number : DLLOr) {
         System.out.println("Number = " + number);
      }  
   }
}
