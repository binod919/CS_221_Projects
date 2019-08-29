/**
 * Doubly linked list extends DoubleList and implements OrderedListADT
 * Elements added in the list will be added in ordered.
 * @author karki
 *
 * @param <T> type of element to be added in the list
 */

public class DLLOrderedList<T> extends DoubleList<T> implements OrderedListADT<T> {

	/**
	 * create new doubly linked list
	 */
	public DLLOrderedList() {
		super();
	}

	/**
	 * adds element to the list by comparing elements in the list in order
	 * @param element Element to be added in the list
	 */
	public void add(T element) {
		// make sure the element is comparable in Ordered List
		Comparable temp;
		if (element instanceof Comparable)
			temp = (Comparable) element;
		else
			throw new NonComparableElementException("double ordered list");

		DoubleNode<T> traverse = front;
		DoubleNode<T> newNode = new DoubleNode<T>(element);
		boolean found = false;

		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else if (temp.compareTo(rear.getElement()) >= 0) {
			rear.setNext(newNode);
			newNode.setPrevious(rear);
			rear = newNode;

		} else if (temp.compareTo(front.getElement()) <= 0) {
			front.setPrevious(newNode);
			newNode.setNext(front);
			front = newNode;

		}

		else {

			while (traverse != null && !found) {

				if (temp.compareTo(newNode.getElement()) >= 0 && temp.compareTo(newNode.getElement()) <= 0) {

					newNode.setNext(traverse.getNext());
					traverse.getNext().setPrevious(newNode);
					traverse.setNext(newNode);
					newNode.setPrevious(traverse);

					found = true;

				} else
					traverse = traverse.getNext();
			}
		}
		count++;
		modCount++;
	}
}
