
import java.util.Iterator;

/**
 * 
 * ListADT defines the interface to a general list collection. Specific
 * types of lists will extend this interface to complete the
 * set of necessary operations
 */

public interface ListADT<T> extends Iterable<T>{

	/**
	 * Removes the first element from the list
	 * @return returns the removed element 
	 */
	public T removeFirst();

	/**
	 * Removes the last element from the list
	 * @return Returns the removed element
	 */
	public T removeLast();
	
	/**
	 * Removes the specified element from the list
	 * @param element Element to be removed
	 * @return Returns the removed element
	 */

	public T remove(T element);

	/**
	 * Returns the first element from the list
	 * @return First element from the list
	 */
	public T first();
	
	/**
	 * Returns the last element from the list
	 * @return Returns last element from the list
	 */

	public T last();
	
	/**
	 * Checks if the list contains specified element
	 * @param target Element to be checked
	 * @return true if element is in the list, otherwise returns false
	 */

	public boolean contains(T target);
	
	/**
	 * Checks if the list is empty or not
	 * @return true if list is empty, otherwise returns false
	 */

	public boolean isEmpty();
	
	/**
	 * Checks the size of the list
	 * @return Returns the size of the list
	 */

	public int size();
	
	/**  
	 * Returns an iterator for the elements in this list. 
	 *
	 * @return an iterator over the elements in this list
	 */

	public Iterator<T> iterator();
	
	/**  
	 * Returns a string representation of this list. 
	 *
	 * @return a string representation of this list
	 */

	public String toString();
}
