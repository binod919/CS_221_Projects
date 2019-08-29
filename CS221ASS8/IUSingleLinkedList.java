import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Single-linked node implementation of IndexedUnsortedList. An Iterator with
 * working remove() method is implemented, but ListIterator is unsupported.
 * 
 * @author
 * 
 * @param <T> type to store
 */
public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	private LinearNode<T> head, tail;
	private int size;
	private int modCount;

	/** Creates an empty list */
	public IUSingleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	/**
	 * Adds element to the front of the list
	 * 
	 * @param element to be added in the list
	 */
	@Override
	public void addToFront(T element) {

		LinearNode<T> newNode = new LinearNode<T>(element);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}
		size++;
		modCount++;
	}

	/**
	 * Adds element to the rear of the list
	 * 
	 * @param Element to be added in the list
	 */
	@Override
	public void addToRear(T element) {

		LinearNode<T> newNode = new LinearNode<T>(element);
		LinearNode<T> current = head;

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {

			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(newNode);
			tail = newNode;
		}

		size++;
		modCount++;
	}

	/**
	 * Adds element to the rear of the list
	 * 
	 * @param Element to be added to the rear of the list
	 */
	@Override
	public void add(T element) {

		addToRear(element);

	}

	/**
	 * Adds elements after specified target
	 * 
	 * @param element to be added in the list
	 * @param target the target is the item that the element will be added after
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public void addAfter(T element, T target) {

		boolean found = false;
		LinearNode<T> current = head;
		LinearNode<T> newNode = new LinearNode<T>(element);

		while (current != null && !found) {

			if (current.getElement().equals(target))
				found = true;
			else
				current = current.getNext();
		}

		if (!found)
			throw new NoSuchElementException("Linked List");

		newNode.setNext(current.getNext());
		current.setNext(newNode);

		if (current == tail)
			tail = newNode;

		size++;
		modCount++;
	}

	/**
	 * Adds element to the index
	 * 
	 * @param index index in which element to be added
	 * @param element element to be added in the index
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	@Override
	public void add(int index, T element) {

		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();

		LinearNode<T> newNode = new LinearNode<T>(element);
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		int idx = 0;

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else if (size() == 1) {
			newNode.setNext(head);
			head = newNode;
		} else if (current == head) {
			newNode.setNext(current);
		} else {

			while (idx != index) {
				previous = current;
				current = current.getNext();
				idx++;
			}

			newNode.setNext(previous.getNext());
			previous.setNext(newNode);
		}

		if (current == head)
			head = newNode;
		if (current == tail)
			tail = newNode;

		size++;
		modCount++;
	}

	/**
	 * Removes and returns the first element from the list
	 */
	@Override
	public T removeFirst() {

		if (isEmpty())
			throw new NoSuchElementException();

		LinearNode<T> retValue = head;
		head = head.getNext();

		if (head == null)
			tail = null;

		size--;
		modCount++;
		return retValue.getElement();
	}

	/***
	 * Removes and returns last element of the list
	 */
	@Override
	public T removeLast() {

		if (isEmpty())
			throw new NoSuchElementException();

		LinearNode<T> previous = null;
		LinearNode<T> current = head;
		LinearNode<T> retValue = tail;

		if (size() == 1) {
			head = null;
			tail = null;
		}

		while (current != null) {
			previous = current;
			current = current.getNext();
		}

		tail = previous;

		if (tail == null)
			head = null;

		size--;
		modCount++;

		return retValue.getElement();
	}

	/***
	 * Removes the element from the list
	 * 
	 * @param Element to be removed
	 */
	@Override
	public T remove(T element) {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		boolean found = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		while (current != null && !found) {
			if (element.equals(current.getElement())) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}

		if (!found) {
			throw new NoSuchElementException();
		}

		if (size() == 1) { // only node
			head = tail = null;
		} else if (current == head) { // first node
			head = current.getNext();
		} else if (current == tail) { // last node
			tail = previous;
			tail.setNext(null);
		} else { // somewhere in the middle
			previous.setNext(current.getNext());
		}

		size--;
		modCount++;

		return current.getElement();
	}

	/**
	 * Removes and returns the element at the specified index.
	 *
	 * @param index the index of the element to be retrieved
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size)
	 */
	@Override
	public T remove(int index) {

		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();

		LinearNode<T> previous = null;
		LinearNode<T> current = head;
		LinearNode<T> retValue = null;

		for (int i = 0; i < index; i++) {
			previous = current;
			current = current.getNext();
		}

		retValue = current;

		if (size() == 1) {
			head = null;
			tail = null;
		} else if (current == head) {
			head = current.getNext();
		} else if (current == tail) {
			tail = previous;
			tail.setNext(null);
		} else
			previous.setNext(current.getNext());

		size--;
		modCount++;
		return retValue.getElement();
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

		if (index < 0 || index > size() - 1)
			throw new IndexOutOfBoundsException();

		LinearNode<T> current = head;
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

		LinearNode<T> current = head;

		for (int i = 0; i < index; i++) {
			current = current.getNext();
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

		if (!isEmpty()) {

			LinearNode<T> current = head;
			boolean found = false;
			int idx = 0;

			while (current != null && !found) {

				if (element.equals(current.getElement())) {
					found = true;
					return idx;
				} else {
					current = current.getNext();
					idx++;
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
			throw new NoSuchElementException();

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
			throw new NoSuchElementException();

		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {

		boolean found = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		if (size() == 1) {
			if (target.equals(current.getElement()))
				return true;
		}

		while (current != null && !found) {

			if (target.equals(current.getElement())) {
				found = true;
				break;
			}

			previous = current;
			current = current.getNext();

		}

		return found;
	}

	/**
	 * Returns true of the list is empty otherwise it returns false
	 */
	@Override
	public boolean isEmpty() {

		return (size == 0);
	}

	/**
	 * returns the size of the list
	 */
	@Override
	public int size() {

		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	/**
	 * Overriding toString method to print meaningful output
	 */
	public String toString() {

		String str = "";
		LinearNode<T> current = head;

		if (isEmpty())
			return str = "[]";

		str = "[";

		do {
			if (size() == 0) {
				str += current.getElement().toString() + ",";
			} else if (current != tail) {
				str += current.getElement().toString() + ",";
			}

			if (current == tail)
				str += current.getElement().toString() + "]";

			current = current.getNext();
		} while (current != null);

		return str;

	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private LinearNode<T> nextNode;
		private int iterModCount;

		private LinearNode<T> previous;
		private boolean canRemove;

		/** Creates a new iterator for the list */
		public SLLIterator() {
			nextNode = head;
			iterModCount = modCount;
			previous = null;
			canRemove = false;
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

			T result = nextNode.getElement();
			previous = nextNode;
			nextNode = nextNode.getNext();

			canRemove = true;
			return result;
		}

		/**
		 * removes the element in the iterator
		 * 
		 * @throws ConcurrentModificationException
		 *             in case of concurrency
		 * @throws IllegalStateException
		 *             if the next has not been called
		 */
		@Override
		public void remove() {

			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			if (!canRemove)
				throw new IllegalStateException();

			LinearNode<T> current = head;
			LinearNode<T> temp = null;

			while (current != previous) {
				temp = current;
				current = current.getNext();
			}

			if (size() == 1) {
				head = null;
				tail = null;
			} else if (previous == head) {
				head = current.getNext();
			} else if (previous == tail) {
				tail = temp;
				tail.setNext(null);
			} else
				temp.setNext(current.getNext());

			canRemove = false;
			size--;
			modCount++;
			iterModCount = modCount;
		}
	}
}
