/**
 * Double Node for doubly linked list
 * @author karki
 *
 * @param <T> 
 */
public class DoubleNode<T> {
	
	private DoubleNode<T> next, previous; // Reference to next node and previous node
	private T element;                    // Reference to a element in a node
	
	/**
	 * Creates a new Double Node
	 */
	public DoubleNode() {
		next = null;
		previous =null;
		element = null;
	}
	
	/**
	 * Creates a new node with an element
	 * @param elem Element to be set in a new node
	 */
	public DoubleNode(T elem) {
		next = null;
		previous = null;
		element = elem;
	}
	
	/**
	 * Method to get the next node 
	 * @return Returns the next node in the list
	 */
	public DoubleNode<T> getNext() {
		return next;
	}
	
	/**
	 * Method to get the previous node
	 * @return Returns the previous node in the list
	 */
	public DoubleNode<T> getPrevious(){
		return previous;
	}
	
	/**
	 * sets the next node in the list
	 * @param node Node to be set next
	 */
	public void setNext(DoubleNode<T> node) {
		next = node;
	}
	
	/**
	 * Sets the previous node in the list
	 * @param node Node to be set previous
	 */
	public void setPrevious(DoubleNode<T> node) {
		previous = node;
	}
	
	/**
	 * Method to get the element from a node
	 * @return Returns the element in a node
	 */
	public T getElement() {
		return element;
	}
	
	/**
	 * Method to set the element in a node
	 * @param elem Element to be set in a node
	 */
	public void setElement(T elem) {
		element = elem;
	}

}
