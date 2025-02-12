package com.computerscience.main;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T> implements Iterable<T> {

    private int buffer = 3;
    private int internalSize = 0;
    private Object[] internalArray = new Object[buffer];


    // [] -> [] -> [] -> [] -> []
    // pushes to specific index
    // if index > array.length return false
    // if index < 0 return false
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

    public int size() {
        return internalSize;
    }

    public T get(int index) {
        if (index < 0 || index > internalSize) {
            throw new ArrayIndexOutOfBoundsException("asked index is out of bounds");
        }
        return (T) internalArray[index];
    }

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
