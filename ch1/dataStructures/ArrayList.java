public class ArrayList<T> {
	
	private final int defaultSize = 1;
	private T[] array = createArray(defaultSize);
	private int nextIndex = 0;

	public boolean add(T value) {
		if (nextIndex == array.length) {
			doubleArray();
		}
		array[nextIndex] = value;
		nextIndex ++;
		return true;
	}

	public T get(int index) {
		return array[index];
	}

	public int size() {
		return nextIndex;
	}

	// Private methods

	private void doubleArray() {
		int currLength = array.length;
		T[] newArray = createArray(currLength * 2);
		for (int i = 0; i < currLength; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	private T[] createArray(int size) {
		T[] newArray = (T[]) new Object[size];
		return newArray;
	}
}