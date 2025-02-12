package com.computerscience.main;

public class Queue<T> {
    private final LinkedList<T> internalLinkedList = new LinkedList<>();

    //inserts the element onto the queue
    public void append(T e) {
        internalLinkedList.append(e);
    }

    //retrieves but not removes the next element of the queue
    public T peek() {
        return internalLinkedList.get(0);
    }

    //retrieves and removes the next element of the queue
    public T poll() {
        T t = internalLinkedList.get(0);
        if (t != null) {
            internalLinkedList.remove(0);
        }
        return t;
    }

    //check if the collection is empty
    public boolean empty() {
        return internalLinkedList.size() == 0;
    }
}
