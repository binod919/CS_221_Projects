/**
 * Double Node for doubly linked list
 * @author karki
 *
 * @param <T> 
 */

public class DoubleNode<T>{

    private DoubleNode<T> next;      // A reference to next node
    private T element;               // A reference to element in node
    private DoubleNode<T> previous;  // A reference to previous node

    /**
     * Creates an empty Double node
     */
    public DoubleNode(){
       next = null;
       previous = null;
       element = null;
    }

    /**
     * creates a node storing a specific element
     * @param elem element to be stored in the new node.
     */
    public DoubleNode (T elem){
       next = null;
       previous = null;
       element = elem;
    }

    /**
     * Returns the next node in the list
     * @return node that follows current one
     */
    public DoubleNode<T> getNext(){
      return next;
    }

    /**
     * Returns the previous node in the list
     * @return node previous from the current one
     */
    public DoubleNode<T> getPrevious(){
      return previous;
    }
    
    /**
     * Sets the next node in the list
     * @param dnode node to be set next
     */
    public void setNext (DoubleNode<T> dnode){
      next = dnode;  
    }

    /**
     * Sets the previous node in the list
     * @param dnode node to be set in the previous
     */
    public void setPrevious (DoubleNode<T> dnode){
    	previous = dnode;
    }

    /**
     * Returns the element in the node
     * @return current element in the node
     */
    public T getElement(){
      return element;   
    }

    /**
     * Sets the element stored in this node
     * @param elem element to be stored in this node
     */
    public void setElement (T elem){
       element = elem;
    }
}



