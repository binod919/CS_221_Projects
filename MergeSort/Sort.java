import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator. As
 * written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort {
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. As
	 * configured, uses WrappedDLL. Must be changed if using your own
	 * IUDoubleLinkedList class.
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() {
		return new IUDoubleLinkedList<T>(); // TODO: replace with your IUDoubleLinkedList for extra-credit
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface using
	 * compareTo() method defined by class of objects in list. DO NOT MODIFY THIS
	 * METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface
	 * @see IndexedUnsortedList
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) {
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface using given
	 * Comparator. DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList
	 */
	public static <T> void sort(IndexedUnsortedList<T> list, Comparator<T> c) {
		mergesort(list, c);
	}

	/**
	 * Mergesort algorithm to sort objects in a list that implements the
	 * IndexedUnsortedList interface, using compareTo() method defined by class of
	 * objects in list. DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list) {
		
		if (list.size() < 2)
			return;

		int middle = list.size() / 2;
		int last = list.size();

		IUDoubleLinkedList<T> Left = new IUDoubleLinkedList<T>();
		IUDoubleLinkedList<T> Right = new IUDoubleLinkedList<T>();

		// Dividing a list into two halves, ie. left and right
		for (int i = 0; i < middle; i++)
			Left.add(list.removeFirst());
		for (int i = middle; i < last; i++)
			Right.add(list.removeFirst());

		// calling mergesort method for recursion
		mergesort(Left);
		mergesort(Right);
		
		// Comparing and swapping elements according to increasing order
		while (Left.size() != 0 && Right.size() != 0) {
			T leftElement = Left.removeFirst();
			T rightElement = Right.removeFirst();

			// comparing the left single element to right single element 
			// and putting them in order
			if (leftElement.compareTo(rightElement) > 0) {
				list.add(rightElement);
				Left.addToFront(leftElement);
			} else {
				list.add(leftElement);
				Right.addToFront(rightElement);
			}
		}

		/*
		 * Removing elements from the left and right divided list after 
		 * comparing and accurately putting elements in the sorted list
		 * */
		while (Left.size() != 0)
			list.add(Left.removeFirst());
		while (Right.size() != 0)
			list.add(Right.removeFirst());
	}

	/**
	 * Mergesort algorithm to sort objects in a list that implements the
	 * IndexedUnsortedList interface, using the given Comparator. DO NOT MODIFY THIS
	 * METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c) {
		if (list.size() < 2)
			return;

		int middle = list.size() / 2;
		int last = list.size();

		IUDoubleLinkedList<T> Left = new IUDoubleLinkedList<T>();
		IUDoubleLinkedList<T> Right = new IUDoubleLinkedList<T>();

		// Dividing a list into two halves, ie. left and right
		for (int i = 0; i < middle; i++)
			Left.add(list.removeFirst());
		for (int i = middle; i < last; i++)
			Right.add(list.removeFirst());

		// calling mergesort method for recursion
		mergesort(Left, c);
		mergesort(Right, c);

		while (Left.size() != 0 && Right.size() != 0) {
			T leftElement = Left.removeFirst();
			T rightElement = Right.removeFirst();

			// comparing the left single element to right single element 
			// and putting them in order
			if (c.compare(leftElement, rightElement) > 0) {
				list.add(rightElement);
				Left.addToFront(leftElement);
			} else {
				list.add(leftElement);
				Right.addToFront(rightElement);
			}
		}

		/*
		 * Removing elements from the left and right divided list after 
		 * comparing and accurately putting elements in the sorted list
		 * */
		while (Left.size() != 0)
			list.add(Left.removeFirst());
		while (Right.size() != 0)
			list.add(Right.removeFirst());

	}

}
