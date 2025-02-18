package com.computerscience.main;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A dynamic array-based generic list implementation.
 *
 * <p>The ArrayList class allows adding, removing, retrieving elements,
 * and supports iteration. Internally, it uses a dynamic array that grows
 * or shrinks as required. Various utility methods are provided to manage
 * and manipulate the list efficiently.</p>
 *
 * @param <T> The type of elements the list can hold.
 */
public class ArrayList<T> implements Iterable<T> {

    private int buffer = 3;
    private int internalSize = 0;
    private Object[] internalArray = new Object[buffer];


    // [] -> [] -> [] -> [] -> []
    // pushes to specific index
    // if index > array.length return false
    // if index < 0 return false

    /**
     * Adds an element to the specified index in the array list.
     *
     * @param e     The element to add. Cannot be null.
     * @param index The index at which to insert the element. Must be between 0 and size inclusive.
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds.
     *                                        <p>Time complexity: O(n) in the worst case due to shifting elements.</p>
     */
    public void append(T e, int index) {
        if (index > internalSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("asked index is out of bounds");
        }

        //if new array size gets bigger than total array length, we need a new copy
        //O(n) times the present elements on the list
        if (internalSize == internalArray.length) {
            internalArray = Arrays.copyOf(internalArray, internalArray.length + 1);
        }

        //if index is occupied then it needs to shift right all others
        //O(n) times the present elements on the list
        if (internalArray[index] != null) {
            for (int i=internalArray.length-1;i>index;i--) {
                internalArray[i] = internalArray[i-1];
            }
        }
        internalArray[index] = e;
        internalSize++;
    }
    // [] -> [] -> [] -> [] -> []
    // pushes to last index
    //O(1) if there's no need to resize the array

    /**
     * Adds an element to the end of the array list.
     *
     * @param e The element to add. Cannot be null.
     *          <p>Time complexity: O(1) if resizing is not required, or O(n) if resizing occurs.</p>
     */
    public void append(T e) {
        //O(n) if there's need to resize the array
        if (internalSize == internalArray.length) {
            internalArray = Arrays.copyOf(internalArray, internalArray.length + buffer);
            buffer+=3;
        }

        internalArray[internalSize++] = e;
    }

    // [] -> [] -> [] -> [] -x []
    // removes specific index, shift left all remaining objects
    // if index > array.length throw exception
    // if index < 0 throw exception
    // de-size the array when needed

    /**
     * Removes the element at the specified index in the array list.
     *
     * @param index The index of the element to remove. Must be within bounds (0 to size-1).
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds.
     *                                        <p>Time complexity: O(n) in the worst case for shifting elements.</p>
     */
    public void remove(int index) {
        if (index < 0 || index > internalSize) {
            throw new ArrayIndexOutOfBoundsException("asked index is out of bounds");
        }

        //if it wants to remove the last pos then it will fail?
        if  (index == internalSize-1) {
            internalArray[index] = null;
            internalSize--;
            return;
        }

        for (int i=index;i<internalArray.length-1;i++) {
            internalArray[i] = internalArray[i+1];
        }

        //de-size the array when needed
        if ((internalArray.length - internalSize) >= buffer) {
            internalArray = Arrays.copyOf(internalArray, internalArray.length - buffer);
            internalSize -= buffer;
            return;
        }

        internalSize--;
    }

    //uses equals to verify the first entry of the object, will return nothing if the entry does not exist
    //it throws an exception if the entry is empty
    //it iterates through the whole array to find the first occurrence
    //O(n)

    /**
     * Removes the first occurrence of the specified element from the array list.
     *
     * @param e The element to remove. Cannot be null.
     * @throws IllegalArgumentException if the element is null.
     *                                  <p>Time complexity: O(n) in the worst case, as it iterates through the list to find the element.</p>
     */
    public void remove(T e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        for (int i=0;i<internalArray.length;i++) {
            Object internalObject = internalArray[i];
            if (internalObject != null && internalObject.equals(e)) {
                remove(i);
                break;
            }
        }
    }

    /**
     * Returns the current number of elements in the array list.
     *
     * @return The size of the array list.
     * <p>Time complexity: O(1).</p>
     */
    public int size() {
        return internalSize;
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve. Must be within bounds (0 to size-1).
     * @return The element at the specified index.
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds.
     *                                        <p>Time complexity: O(1).</p>
     */
    public T get(int index) {
        if (index < 0 || index > internalSize) {
            throw new ArrayIndexOutOfBoundsException("asked index is out of bounds");
        }
        return (T) internalArray[index];
    }

    /**
     * Adds all elements from the specified {@link ArrayList} to this list.
     *
     * @param collection The {@link ArrayList} whose elements are to be added. Cannot be null.
     *                   <p>Time complexity: O(m + n), where m is the size of this list, and n is the size of the collection.</p>
     */
    public void appendAll(ArrayList<T> collection) {
        for(T item: collection) {
            this.append(item);
        }
    }

    private static class ArrayListIterator<T> implements Iterator<T> {
        private final ArrayList<T> list;
        private int currentIndex = 0;

        public ArrayListIterator(ArrayList<T> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < list.internalSize;
        }

        @Override
        public T next() {
            return (T) list.get(currentIndex++);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<T>(this);
    }

}
