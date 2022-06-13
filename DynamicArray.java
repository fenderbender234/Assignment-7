import java.util.Arrays;

/**
 * 
 * @author Josh Freeman
 *
 */
public class DynamicArray {

	// Instantiate private int array
	private int array[];
	
	// Instantiate private int to hold size of the array
	private int size;
	
	// Instantiate private int capacity for the array
	private int capacity;
	
	/**
	 * Constructor
	 * Sets value for array, size and capacity
	 * @param capacity
	 * @throws IllegalArgumentException if capacity is less than 0
	 */
	public DynamicArray(int capacity) throws IllegalArgumentException {
		
		if(capacity < 0) {
			
			throw new IllegalArgumentException("Size cannot have a value less than 0");
		}
		
		array = new int[capacity];
		size = 0;
		this.capacity = capacity;
	}
	
	/**
	 * No Argument Constructor initializes array and sets default values
	 */
	public DynamicArray() {
		
		array = new int [3];
		size = 0;
		this.capacity = 3;
	}
	
	/**
	 * Copy Constructor
	 * takes object of DynamicArray as a parameter and copes into created object
	 * @param copy (array)
	 * @throws IllegalArgumentException if object that was passed is null
	 */
	public DynamicArray(DynamicArray copy) throws IllegalArgumentException {
		
		if(copy == null) {
			
			throw new IllegalArgumentException("Copy array cannot be null");
		}
		
		this.array =  Arrays.copyOf(copy.array, copy.array.length);
		this.size = copy.size;
		this.capacity = copy.capacity;
	}
	
	/**
	 * Getter
	 * Returns the number of "occupied" elements in the array
	 * @return size
	 */
	public int getSize() {
		
		return size;
	}
	
	/**
	 * Getter
	 * Returns actual length of the partially-filled array
	 * @return capacity
	 */
	public int getCapacity() {
		
		return capacity;
	}
	
	/**
	 * Accessor returns the entire partially-filled array.
	 * @return
	 */
	public int[] getArray() {
		
		return Arrays.copyOf(array, array.length);
	}
	
	/**
	 * Accessor returns an "occupied" part of the partially-filled array
	 * @return
	 */
	public int[] toArray() {
		
		return Arrays.copyOf(array, size);
	}
	
	/**
	 * Adds new element to the end of the array and increments the size field
	 * If the array is full, call makeRoom method to increase capacity for the array
	 * @param num
	 */
	public void push(int num) {
		
		// double capacity if all space is being used
		if(size == capacity) {
			
			makeRoom(2);
		}
		
		array[size] = num;
		size++;
	}
	
	/**
	 * Method makes more room for the push method if needed
	 * @param minCapacity
	 */
	public void makeRoom(int minCapacity) {
		
		int temp[] = new int[capacity * minCapacity];
		
		for(int i = 0; i < capacity; i++) {
			
			temp[i] = array[i];
		}
		
		array = temp;
		capacity = capacity * minCapacity;
	}
	
	/**
	 * Method removes the last element of the array and returns it
	 * Decrements the size field
	 * Checks capacity of the array, if the capacity is 5 times larger than the size, shrink the array
	 * @return result
	 * @throws RuntimeException if the array is empty
	 */
	public int pop() throws RuntimeException{
		
		if(size == 0) {
			
			throw new RuntimeException("Array is empty");
		}
		
		int result = array[--size];
		
		if(capacity > 4 * size) {
			
			int temp[] = new int[capacity / 2];
			
			for(int i = 0; i < capacity / 2; i++) {
				
				temp[i] = array[i];
			}
			
			
			array = temp;
			capacity = capacity / 2;
		}
		
		return result;
	}
	
	/**
	 * Returns element of the array with the requested index
	 * @param index
	 * @return array[index]
	 * @throws IndexOutOfBoundsException if index is >= size or negative
	 */
	public int get(int index) throws IndexOutOfBoundsException {
		
		if(index >= size || index < 0) {
			
			throw new IndexOutOfBoundsException("Illegal index");
		}
		
		return array[index];
	}
	
	/**
	 * Returns the index of the first occurence of the given number
	 * Returns -1 when number is not found
	 * @param key
	 * @return
	 */
	public int indexOf(int key) {
		
		for(int i = 0; i < size; i++) {
			
			if(array[i] == key) {
				
				return i;
				
			}
		}
		
		return -1;
	}
	
	/**
	 * Method adds a new element (num) to the location of the array specified by (index)
	 * Shifts all elements to the right to make room for the new element
	 * If there is no room for the new element, calls makeRoom() method to double size
	 * @param int index
	 * @param int num
	 * @throws IndexOutOfBoundsException if index > size or if index is negative
	 */
	public void add(int index, int num) throws IndexOutOfBoundsException {
		
		if(index > size || index < 0) {
			
			throw new IndexOutOfBoundsException();
		}
		
		// double capacity if all space is being used
		if(size == capacity) {
			
			makeRoom(2);
		}
		
		// shift elements from the index to right
		for(int i = size - 1; i >= index; i--) {
			
			array[i + 1] = array[i];
		}
		
		// insert element at specified index
		array[index] = num;
		size++;
	}
	
	/**
	 * Method removes the element at the specified position in the array
	 * When element is removed from the middle, elements are shifted to close the gap created by the removed element
	 * Check capacity of the array, if capacity is 4* larger than size, shrink array
	 * @param index (int)
	 * @throws IndexOutOfBoundsException if index >= size or is negative
	 */
	public void remove(int index) throws IndexOutOfBoundsException {
		
		if(index >= size || index < 0) {
			
			throw new IndexOutOfBoundsException();
		}
		
		else {
			
			for(int i = index; i < size - 1; i++) {
				
				array[i] = array[i + 1];
			}
			
			array[size-1] = 0;
			size--;
		}
		
		if(capacity > 4 * size) {
			
			int temp[] = new int[capacity / 2];
			
			for(int i = 0; i < capacity / 2; i++) {
				
				temp[i] = array[i];
			}
			
			array = temp;
			capacity = capacity / 2;
		}
	}
	
	/**
	 * Method returns true if the size of the array is 0
	 * Method returns false if the size of the array is greater than 0
	 * @return
	 */
	public boolean isEmpty() {
		
		if(size > 0 ) {
			
			return false;
		}
		
		else {
			
			return true;
		}
	}
	
	/**
	 * Method returns an array as a string of comma-separated values
	 * Sample: [1, 2, 3, 4]
	 */
	public String toString() {
		
		if(size > 0) {
			
			int[] num = Arrays.copyOf(array, size);
			
			System.out.println(Arrays.toString(num));
			
			return Arrays.toString(num);
		}
		
		else {
			
			return "[ ]";
		}
	}
	
	/**
	 * Method compares 2 objects (this one and one passed as a parameter) element by element to see if they are the same
	 * Capacity of the 2 objects not being compared can be different
	 * @param obj
	 * @return
	 */
	public boolean equals(DynamicArray obj) {
		
		int[] num1 = Arrays.copyOf(array, size);
		int[] num2 = Arrays.copyOf(obj.array, obj.size);
		
		return Arrays.equals(num1, num2);
	}
}
