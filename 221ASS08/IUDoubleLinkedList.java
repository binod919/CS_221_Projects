import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Double Linked List implementation of IndexedUnsortedList. Iterator with
 * working remove(), add() and set() with supported List Iterator.
 * 
 * @author karki
 * @version 1.0 CS221 Spring 2018
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

	private DoubleNode<T> head, tail;
	private int size;
	private int modCount;

	/**
	 * Creates a new Doubly linked list
	 */
	public IUDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
		modCount = 0;
	}

	/**
	 * Adds element to the front of the list
	 * 
	 * @param element
	 *            to be added in the list
	 */
	@Override
	public void addToFront(T element) {

		DoubleNode<T> newNode = new DoubleNode<T>(element);

		if (isEmpty()) {
			head = newNode;
			tail = head;
		} else {
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
		}
		size++;
		modCount++;
	}

	/**
	 * Adds element to the rear of the list
	 * 
	 * @param Element
	 *            to be added in the list
	 */
	@Override
	public void addToRear(T element) {

		DoubleNode<T> newNode = new DoubleNode<T>(element);

		if (isEmpty()) {
			tail = newNode;
			head = tail;
		} else {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
		modCount++;
	}

	/**
	 * Adds new element to the list
	 * 
	 * @param Element
	 *            to be added to the list
	 */
	@Override
	public void add(T element) {

		addToRear(element);

	}

	/**
	 * Adds elements after specified target
	 * 
	 * @param element
	 *            to be added in the list
	 * @param target
	 *            the target is the item that the element will be added after
	 * @throws NoSuchElementException
	 *             if the list does not contains the target element
	 */
	@Override
	public void addAfter(T element, T target) {

		if (isEmpty())
			throw new NoSuchElementException("Doubly Linked List");

		DoubleNode<T> newNode = new DoubleNode<T>(element);
		DoubleNode<T> current = head;
		boolean found = false;

		while (current != null && !found) {

			if (current.getElement().equals(target)) {
				found = true;
			} else
				current = current.getNext();
		}

		if (!found)
			throw new NoSuchElementException("Doubly Linked List");

		if (head == tail) {

			head.setNext(newNode);
			newNode.setPrevious(head);

			tail = newNode;
		} else if (current == head) {
			newNode.setNext(current.getNext());
			current.getNext().setPrevious(newNode);

			head.setNext(newNode);
			newNode.setPrevious(head);

		} else if (current == tail) {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail = newNode;
		} else {
			newNode.setNext(current.getNext());
			current.getNext().setPrevious(newNode);

			newNode.setPrevious(current);
			current.setNext(newNode);
		}

		size++;
		modCount++;
	}

	/**
	 * Adds element to the index
	 * 
	 * @param index
	 *            index in which element to be added
	 * @param element
	 *            element to be added in the index
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range
	 */
	@Override
	public void add(int index, T element) {

		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();

		DoubleNode<T> newNode = new DoubleNode<T>(element);
		DoubleNode<T> current = head;
		DoubleNode<T> temp = null;

		int idx = 0;

		while (idx != index) {
			temp = current;
			current = current.getNext();
			idx++;
		}

		if (isEmpty()) {
			head = newNode;
			tail = head;
		} else if (current == head) {
			newNode.setNext(current);
			current.setPrevious(newNode);

			head = newNode;
		} else if (index == size()) {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail = newNode;
		} else {
			temp.setNext(newNode);
			newNode.setPrevious(temp);

			newNode.setNext(current);
			current.setPrevious(newNode);
		}

		// if (isEmpty()) {
		// head = newNode;
		// tail = head;
		// } else if (index >= size()) {
		// current = newNode;
		// newNode.setPrevious(temp);
		// temp.setNext(current);
		// } else {
		// if (size() == 1) {
		// current = newNode;
		// head.setNext(newNode);
		// newNode.setPrevious(head);
		// tail = newNode;
		// } else if (current == tail) {
		// newNode.setNext(tail);
		// current.setPrevious(newNode);
		//
		// current.getPrevious().setNext(newNode);
		// newNode.setPrevious(current.getPrevious());
		// } else {
		//
		// current.setPrevious(newNode);
		// newNode.setNext(current);
		//
		// current.getPrevious().setNext(newNode);
		// newNode.setPrevious(current.getPrevious());
		// }
		// }
		size++;
		modCount++;
	}

	/**
	 * Removes the first element from the list
	 * 
	 * @return Returns the removed element
	 * @throws NoSuchElementException
	 *             if there is no elements in the list
	 */
	@Override
	public T removeFirst() {

		if (isEmpty())
			throw new NoSuchElementException();

		T retValue = head.getElement();
		DoubleNode<T> current = head;

		if (head == tail) {
			head = null;
			tail = head;
		} else {
			head = current.getNext();
			current = null;
		}
		size--;
		modCount++;
		return retValue;
	}

	/**
	 * Removes the lasr element from the list
	 * 
	 * @return Returns the removed element
	 * @throws NoSuchElementException
	 *             if there is no elements in the list
	 */
	@Override
	public T removeLast() {

		if (isEmpty())
			throw new NoSuchElementException();

		T retValue = tail.getElement();
		DoubleNode<T> current = tail;

		if (head == tail) {
			head = null;
			tail = head;
		} else {
			tail = current.getPrevious();
			tail.setNext(null);
			current = null;
		}
		size--;
		modCount++;
		return retValue;

	}

	/**
	 * Removes and returns the first element from the list matching the specified
	 * element.
	 *
	 * @param element
	 *            the element to be removed from the list
	 * @return removed element
	 * @throws NoSuchElementException
	 *             if element is not in this list
	 */
	@Override
	public T remove(T element) {

		DoubleNode<T> current = head;
		boolean found = false;
		T retValue = null;

		while (current != null && !found) {

			if (current.getElement().equals(element)) {
				retValue = current.getElement();
				found = true;
			} else
				current = current.getNext();
		}

		if (!found)
			throw new NoSuchElementException("Doubly Linked List");

		if (tail == head) {
			tail = null;
			head = tail;
		} else if (current == head) {
			head = current.getNext();
			current = null;
		} else if (current == tail) {
			tail = current.getPrevious();
			tail.setNext(null);
			current = null;
		} else {
			current.getNext().setPrevious(current.getPrevious());
			current.getPrevious().setNext(current.getNext());
		}
		size--;
		modCount++;
		return retValue;
	}

	/**
	 * Removes and returns the element at the specified index.
	 *
	 * @param index
	 *            the index of the element to be retrieved
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size)
	 */
	@Override
	public T remove(int index) {

		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();

		DoubleNode<T> current = head;
		T retValue = null;
		int idx = 0;

		while (idx != index) {
			current = current.getNext();
			idx++;
		}

		retValue = current.getElement();

		if (head == tail) {
			head = null;
			tail = head;
		} else if (current == head) {
			head = head.getNext();
			current = null;
		} else if (current == tail) {
			tail = current.getPrevious();
			tail.setNext(null);
			current = null;
		} else {
			current.getNext().setPrevious(current.getPrevious());
			current.getPrevious().setNext(current.getNext());
		}
		size--;
		modCount++;
		return retValue;
	}

	/**
	 * Sets the element at the specified index.
	 *
	 * @param index
	 *            the index into the array to which the element is to be set
	 * @param element
	 *            the element to be set into the list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size)
	 */
	@Override
	public void set(int index, T element) {

		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();

		DoubleNode<T> current = head;
		int idx = 0;

		while (idx != index) {
			current = current.getNext();
			idx++;
		}
		current.setElement(element);
		modCount++;
	}

	/**
	 * Returns a reference to the element at the specified index.
	 *
	 * @param index
	 *            the index to which the reference is to be retrieved from
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size)
	 */
	@Override
	public T get(int index) {

		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();

		int idx = 0;
		DoubleNode<T> current = head;

		while (idx != index) {
			current = current.getNext();
			idx++;
		}
		return current.getElement();
	}

	/**
	 * returns the index of element
	 * 
	 * @param element
	 *            the integer index for this element
	 * @throws NoSuchElementException
	 */
	@Override
	public int indexOf(T element) {

		int idx = -1;
		if (!isEmpty()) {
			DoubleNode<T> current = head;
			boolean found = false;

			while (current != null && !found) {
				idx++;
				if (current.getElement().equals(element)) {
					found = true;
					return idx;
				} else {
					current = current.getNext();
				}
			}
		}
		return -1;
	}

	/**
	 * Returns first element in the list
	 * 
	 * @throws NoSuchElementException
	 *             of the list is empty
	 */
	@Override
	public T first() {

		if (isEmpty())
			throw new NoSuchElementException("Doubly Linked List");
		return head.getElement();
	}

	/**
	 * Returns last element in the list
	 * 
	 * @throws NoSuchElementException
	 *             of the list is empty
	 */
	@Override
	public T last() {
		if (isEmpty())
			throw new NoSuchElementException("Doubly Linked List");
		return tail.getElement();
	}

	/**
	 * Returns true if this list contains the specified target element.
	 *
	 * @param target
	 *            the target that is being sought in the list
	 * @return true if the list contains this element, else false
	 */
	@Override
	public boolean contains(T target) {

		if (!isEmpty()) {

			DoubleNode<T> current = head;

			while (current != null) {
				if (current.getElement().equals(target))
					return true;
				else
					current = current.getNext();
			}
		}
		return false;
	}

	/**
	 * Returns true of the list is empty otherwise it returns false
	 */
	@Override
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * returns the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Overriding toString method to print meaningful output
	 */
	public String toString() {

		String str = "";
		DoubleNode<T> current = head;

		if (isEmpty())
			return str = "[]";

		str = "[";

		while (current != null) {
			if (current != tail)
				str += current.getElement() + ",";
			else
				str += current.getElement() + "]";
			current = current.getNext();
		}

		return str;

	}

	/**
	 * Returns an Iterator for the elements in this list.
	 *
	 * @return an Iterator over the elements in this list
	 */
	@Override
	public Iterator<T> iterator() {
		return new DLLIterator();
		// return null;
	}

	/**
	 * Returns a ListIterator for the elements in this list.
	 *
	 * @return a ListIterator over the elements in this list
	 *
	 * @throws UnsupportedOperationException
	 *             if not implemented
	 */
	@Override
	public ListIterator<T> listIterator() {

		return new DLLIterator();
	}

	/**
	 * Returns a ListIterator for the elements in this list, with the iterator
	 * positioned before the specified index.
	 *
	 * @return a ListIterator over the elements in this list
	 *
	 * @throws UnsupportedOperationException
	 *             if not implemented
	 */
	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLIterator(startingIndex);
	}

	/**
	 * Iterator for IUDoubleLinkedList
	 * 
	 * @author karki
	 */
	private class DLLIterator implements ListIterator<T> {
		private static final int defaultStartingIndex = 0;
		private int index;

		private DoubleNode<T> nextNode;
		private DoubleNode<T> previousNode;
		private int iterModCount;
		private boolean canRemove;
		private boolean calledNext;
		private boolean calledPrevious;

		public DLLIterator() {
			this(defaultStartingIndex);
		}

		/**
		 * Creates a new Iterator with specified starting position
		 * 
		 * @param startingIndex
		 *            starting position of list iterator
		 * @throws IndexOutOfBoundsException
		 */
		public DLLIterator(int startingIndex) {

			if (startingIndex < 0 || startingIndex > size())
				throw new IndexOutOfBoundsException();

			nextNode = head;
			previousNode = null;
			iterModCount = modCount;
			canRemove = false;
			calledNext = false;
			calledPrevious = false;
			index = 0;

			while (index != startingIndex) {
				previousNode = nextNode;
				nextNode = nextNode.getNext();
				index++;
			}
		}

		/**
		 * returns true if iterator has next node otherwise returns false
		 * 
		 * @throws ConcurrentModificationException
		 *             in case of concurrency
		 */
		@Override
		public boolean hasNext() {
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			return (nextNode != null);
		}

		/**
		 * returns the next element in the iteration
		 * 
		 * @throws NoSuchElementException
		 *             if list contains no elements
		 */
		@Override
		public T next() {

			if (!hasNext())
				throw new NoSuchElementException();

			T retValue = nextNode.getElement();
			previousNode = nextNode;
			nextNode = nextNode.getNext();

			calledPrevious = false;
			calledNext = true;
			canRemove = true;
			index++;
			return retValue;
		}

		/**
		 * removes the element in the iterator
		 * 
		 * @throws ConcurrentModificationException
		 *             in case of concurrency
		 * @throws IllegalStateException
		 *             if the next has not been called
		 */
		public void remove() {

			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			if (!canRemove)
				throw new IllegalStateException();

			if (calledNext) {
				DoubleNode<T> temp = previousNode;

				if (previousNode == null)
					head = tail = null;
				else if (previousNode == head) {
					head = nextNode;
					previousNode = null;
				} else if (previousNode == tail) {
					tail = temp.getPrevious();
					previousNode = temp.getPrevious();
					previousNode.setNext(nextNode);
					temp = null;
				} else {
					temp = previousNode.getPrevious();
					temp.setNext(nextNode);
					nextNode.setPrevious(temp);
					previousNode = temp;
				}

			}

			if (calledPrevious) {
				DoubleNode<T> temp = previousNode;

				if (head == tail) {
					head = tail = null;
				} else if (nextNode == head) {
					temp = nextNode;
					nextNode = nextNode.getNext();
					nextNode.setPrevious(null);
					head = nextNode;
				} else if (nextNode == tail) {
					tail = previousNode;
					previousNode.setNext(null);
					nextNode = null;

				} else {
					temp = nextNode.getNext();
					temp.setPrevious(previousNode);
					previousNode.setNext(temp);
					nextNode = temp;
				}
			}

			size--;
			modCount++;
			iterModCount = modCount;
			canRemove = calledPrevious = calledNext = false;
		}

		/**
		 * Inserts the specified element into the list
		 * 
		 * @param element
		 *            Element to be added in the list
		 * @throws ConcurrentModificationException
		 */
		@Override
		public void add(T element) {

			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			DoubleNode<T> newNode = new DoubleNode<T>(element);

			if (isEmpty())
				head = tail = newNode;
			else if (nextNode == head) {
				newNode.setNext(nextNode);
				nextNode.setPrevious(newNode);
				previousNode = head = newNode;
			} else if (previousNode == tail) {
				previousNode.setNext(newNode);
				newNode.setPrevious(previousNode);
				tail = previousNode = newNode;
			} else {
				nextNode.setPrevious(newNode);
				newNode.setNext(nextNode);
				newNode.setPrevious(previousNode);
				previousNode.setNext(newNode);
				previousNode = newNode;
			}

			index++;
			size++;
			modCount++;
			iterModCount = modCount;
			canRemove = false;
		}

		/**
		 * returns true if iterator has previous node otherwise returns false
		 * 
		 * @throws ConcurrentModificationException
		 *             in case of concurrency
		 */
		@Override
		public boolean hasPrevious() {
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			return (previousNode != null);
		}

		/**
		 * Returns the index of an element that would have been returned by subsequent
		 * call to next() or returns size of a list if list iterator is at the end of
		 * the list
		 * 
		 * @throws ConcurrentModificationException()
		 */
		@Override
		public int nextIndex() {

			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			if (nextNode != null)
				return (index);
			else
				return size();
		}

		/**
		 * returns the previous element in the iteration
		 * 
		 * @throws NoSuchElementException
		 *             if list contains no elements
		 * @throws ConcurrentModificationException
		 */
		@Override
		public T previous() {

			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			if (!hasPrevious())
				throw new NoSuchElementException();

			T retValue = previousNode.getElement();
			nextNode = previousNode;
			previousNode = previousNode.getPrevious();

			calledNext = false;
			calledPrevious = true;
			canRemove = true;
			index--;
			return retValue;
		}

		/**
		 * Returns the index of an element that would have been returned by subsequent
		 * call to previous() or returns -1 if list iterator is at the front of the list
		 * 
		 * @throws ConcurrentModificationException
		 */
		@Override
		public int previousIndex() {

			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			if (previousNode != null)
				return (index - 1);
			else
				return -1;
		}

		/**
		 * Replaces the last element returned by next() or previous with the specified
		 * element
		 * 
		 * @param element
		 *            Element to be set in the list
		 * @throws IllegalStateException
		 *             if neither next() or Previous() have been called
		 * @throws ConcurrentModificationException
		 */
		@Override
		public void set(T element) {

			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			if (calledNext)
				previousNode.setElement(element);
			else if (calledPrevious)
				nextNode.setElement(element);
			else
				throw new IllegalStateException();

			calledPrevious = calledNext = false;
			modCount++;
			iterModCount = modCount;
		}
	}
}
