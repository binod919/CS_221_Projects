import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList. An Iterator with working
 * remove() method is implemented, but ListIterator is unsupported.
 * 
 * @author
 *
 * @param <T>
 *            type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;

	private T[] array;
	private int rear;
	private int modCount;

	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates an empty list with the given initial capacity
	 * 
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[]) (new Object[initialCapacity]);
		rear = 0;
		modCount = 0;
	}

	/** Double the capacity of array */
	private void expandCapacity() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public void addToFront(T element) {
		// TODO

		if (rear == array.length)
			expandCapacity();
		// shift elements
		for (int i = rear; i > 0; i--) {
			array[i] = array[i - 1];
		}
		array[0] = element;
		rear++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		// TODO

		if (rear == array.length)
			expandCapacity();

		array[rear] = element;
		rear++;
		modCount++;
	}

	@Override
	public void add(T element) {
		// TODO
		if (rear == array.length)
			expandCapacity();

		array[rear] = element;
		rear++;
		modCount++;
	}

	@Override
	public void addAfter(T element, T target) {
		// TODO
		if (rear == array.length)
			expandCapacity();
		int index = indexOf(target);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		// shift elements
		for (int i = rear; i > index; i--) {
			array[i] = array[i - 1];
		}
		rear++;
		array[index + 1] = element;
		modCount++;
	}

	@Override
	public void add(int index, T element) {
		// TODO

		if (rear == array.length)
			expandCapacity();
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		// shifting elements
		for (int i = rear; i > index; i--) {
			array[i + 1] = array[i];
		}

		rear++;
		array[index] = element;
		modCount++;
	}

	@Override
	public T removeFirst() {
		// TODO

		if (isEmpty())
			throw new NoSuchElementException();

		T retValue = array[0];
		rear--;
		array[0] = null;
		// shifting elements
		for (int i = 0; i < rear; i++) {
			array[i] = array[i + 1];
		}
		array[rear] = null;
		modCount++;
		return retValue;
	}

	@Override
	public T removeLast() {
		// TODO

		if (isEmpty())
			throw new NoSuchElementException();

		rear--;
		T retValue = array[rear];

		array[rear] = null;
		modCount++;
		return retValue;
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}

		T retVal = array[index];

		rear--;
		// shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i + 1];
		}
		array[rear] = null;
		modCount++;

		return retVal;
	}

	@Override
	public T remove(int index) {
		// TODO
		if (index < 0 || index > size()-1)
			throw new IndexOutOfBoundsException();

		T retValue = array[index];
		array[index] = null;
		rear--;
		// shifting elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i + 1];
		}
		array[rear] = null;
		modCount++;
		return retValue;
	}

	@Override
	public void set(int index, T element) {
		// TODO
		if (index < 0 || index > size()-1)
			throw new IndexOutOfBoundsException();

		array[index] = element;
		modCount++;
	}

	@Override
	public T get(int index) {
		// TODO
		if (index < 0 || index > size()-1)
			throw new IndexOutOfBoundsException();

		return array[index];
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;

		if (!isEmpty()) {
			int i = 0;
			while (index == NOT_FOUND && i < rear) {
				if (element.equals(array[i])) {
					index = i;
				} else {
					i++;
				}
			}
		}

		return index;
	}

	@Override
	public T first() {
		// TODO
		if (isEmpty())
			throw new NoSuchElementException();

		return array[0];
	}

	@Override
	public T last() {
		// TODO
		if (isEmpty())
			throw new NoSuchElementException();
		return array[rear - 1];
	}

	@Override
	public boolean contains(T target) {
		return (indexOf(target) != NOT_FOUND);
	}

	@Override
	public boolean isEmpty() {
		// TODO
		int count = 0;
		for (T el : array) { // el holds elemens of the array
			if (el != null)
				count++;
		}
		return (count == 0);
	}

	@Override
	public int size() {
		// TODO
		return rear;
	}

	@Override
	public Iterator<T> iterator() {
		return new ALIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	// overriding toString Method
	public String toString() {

		String str = "";
		if (isEmpty())
			return str = "[]";
		str = "[";
		
		for(int i = 0; i < rear; i++) {
			
			if(i == rear - 1) {
				str += array[i];
				break;
			}
						
			if(array[i] != null) 
				str += array[i] + ", ";
		}
		str += "]";				
		return str;
	}

	/** Iterator for IUArrayList */
	private class ALIterator implements Iterator<T> {
		private int nextIndex;
		private int iterModCount;
		private boolean canRemove;

		public ALIterator() {
			nextIndex = 0;
			iterModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			// TODO
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			return (nextIndex < rear);
		}

		@Override
		public T next() {
			// TODO
			
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();
			
			if (!hasNext())
				throw new NoSuchElementException();		

			nextIndex++;
			canRemove = true;
			return array[nextIndex - 1];
		}

		@Override
		public void remove() {
			// TODO
			
			if (iterModCount != modCount)
				throw new ConcurrentModificationException();

			
			if (!canRemove) {
				throw new IllegalStateException();
			}			
			
			array[nextIndex - 1] = null;
			for (int i = nextIndex - 1; i < rear - 1; i++) {
				array[i] = array[i + 1];
			}
			array[rear - 1] = null;
			rear--;
			modCount++;
			iterModCount = modCount;
			nextIndex--;
			canRemove = false;

		}

	}
}
