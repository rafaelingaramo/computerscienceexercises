package com.computerscience.main;


//LIFO collection that works as last in first out, internally uses a linked list to control, the elements
public class Stack<T> {
    private final LinkedList<T> internalLinkedList = new LinkedList<>();

    public boolean empty() {
        return internalLinkedList.size() == 0;
    }

    //looks but not retrieves the top object of the collection
    public T peek() {
        return internalLinkedList.get(internalLinkedList.size()-1);
    }

    //retrieves the top object of the collection, returns null if there's none
    public T pop() {
        T pop = internalLinkedList.get(internalLinkedList.size()-1);
        if (pop != null) {
            internalLinkedList.remove(internalLinkedList.size()-1);
        }
        return pop;
    }

    //pushes the element onto the top of the stack
    public void push(T e) {
        internalLinkedList.append(e);
    }
}
