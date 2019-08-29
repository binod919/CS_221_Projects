import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Double linked list implementation 
 * @author karki
 *
 * @param <T> type to be stored in the list
 */
public class DoubleList<T> implements ListADT<T>{

   protected DoubleNode<T> front,rear;
   protected int count, modCount;

   /** creates an empty double list*/
   public DoubleList(){
      rear = null;
      front = null;
      count = 0;
      modCount = 0;
   }

   /**
    * Removes the last element from the list, and returns the removed element
    */
   public T removeLast () throws EmptyCollectionException{
      T result;

      if (isEmpty())
         throw new EmptyCollectionException ("DoubleList");
      
      result = rear.getElement();
      rear = rear.getPrevious();
      
      if (rear==null)
        front = null;
      else
        rear.setNext(null);

      count--;
      modCount++;

      return result;
   }

   /***
	 * Removes and returns first element of the list
	 */
   public T removeFirst() throws EmptyCollectionException{
      
	   if (isEmpty())
		   throw new EmptyCollectionException ("DouleList");
	   
	   T result = front.getElement();
	   front = front.getNext();
	   
	   if(front == null)
		   rear = null;
	   
	   count--;
	   modCount++;
      return result;
   }

   /**
    * removes an element from the list
    * @param element Element to be removed from the list
    */
   public T remove (T element){
      T result;
      DoubleNode<T> nodeptr = find (element);

      if (nodeptr == null)
         throw new ElementNotFoundException ("DoubleList");

      result = nodeptr.getElement();

      // check to see if front or rear
      if (nodeptr == front)
          result = this.removeFirst();
          
      else if (nodeptr == rear)
           result = this.removeLast();

      else
			{
         nodeptr.getNext().setPrevious(nodeptr.getPrevious());
         nodeptr.getPrevious().setNext(nodeptr.getNext());
			}

      count--;
      modCount++;

      return result;
   }

   /**
    * Returns the first element from the list
    */
   public T first() throws EmptyCollectionException{
      if (isEmpty())
         throw new EmptyCollectionException ("DoubleList"); 

      return front.getElement();
   }

   /**
    * Returns the last element from the list
    */
   public T last() throws EmptyCollectionException{
	   if (isEmpty())
	         throw new EmptyCollectionException ("DoubleList"); 

	      return rear.getElement();
   }
   
   /**
    * Checks if the list contains an element
    * @param target Element to be checked
    */
   public boolean contains (T target){
	   if (isEmpty())
	         throw new EmptyCollectionException ("DoubleList");
	   
	   if(find(target).getElement() == target)
		   return true;
	   else return false;
   }

   /**
    * Checks if the element is in the list
    * @return returns the node on which target is found
    */
   private DoubleNode<T> find (T target){
      boolean found = false;
      DoubleNode<T> traverse = front;
      DoubleNode<T> result = null;

      if (isEmpty())
	         throw new EmptyCollectionException ("DoubleList");
      
      while(traverse != null && !found) {
    	  if(traverse.getElement().equals(target)) {
    		  result = traverse;
    		  found = true;
    	  } else {
    		  traverse = traverse.getNext();
    	  }
      }

      return result;
   }

   /**
    * Checks if the list if it is empty
    * @return true if the list is empty otherwise returns false
    */
   public boolean isEmpty(){
      return (count == 0);
   }
 
   /**
    * returns the size of a list
    * @return The size of a list
    */
   public int size(){
      return count;
   }

   /**
    * Overriding to string method to print meaningful output
    * @return returns the meaningful string 
    */
   public String toString(){
      String result = "";
      DoubleNode<T> traverse = front;

      while (traverse != null)
         { 
         result = result + (traverse.getElement()).toString() + "\n";
         traverse = traverse.getNext();
         }
      return result;
   }

   /**
    * New list iterator
    * @return returns new list iterator for doubly linked list
    */
   public Iterator<T> iterator(){
      return new DoubleIterator ();
   }


   /**
    * Iterator for Double linked list
    * @author karki
    *
    */
   private class DoubleIterator implements Iterator<T>
    {
        private int iteratorModCount;  // the number of elements in the collection
        private DoubleNode<T> current;  // the current position

        /**
         * creates new Double iterator
         */
        public DoubleIterator()
        {
            current = front;
            iteratorModCount = modCount;
        }

        /**
		 * returns true if iterator has next node otherwise returns false
		 * 
		 * @throws ConcurrentModificationException
		 *             in case of concurrency
		 */
        public boolean hasNext() throws ConcurrentModificationException
        {
            
        	if(iteratorModCount != modCount)
        		throw new ConcurrentModificationException();
        	return (current!= null);
        	
        }

        /**
		 * returns the next element in the iteration
		 * 
		 * @throws NoSuchElementException
		 *             if list contains no elements
		 */
        public T next() throws ConcurrentModificationException
        {

        	if(iteratorModCount != modCount)
        		throw new ConcurrentModificationException();
        	if(!hasNext())
        		throw new NoSuchElementException();
        	
        	T result = current.getElement();
        	current = current.getNext();
        	return result;
        }

        /**
         * @throws UnsupportedOperationException when called
         */
        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }
    }


}
