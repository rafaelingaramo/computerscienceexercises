package com.computerscience.main;


/**
 * A generic implementation of a stack (LIFO collection), where the last element added is the first to be removed.
 * This class internally uses a {@link LinkedList} to manage the elements.
 *
 * @param <T> The type of elements stored in the stack.
 */
public class Stack<T> {
    private final LinkedList<T> internalLinkedList = new LinkedList<>();

    /**
     * Checks if the stack is empty.
     *
     * @return {@code true} if the stack contains no elements; {@code false} otherwise.
     * <p>Time complexity: O(1)</p>
     */
    public boolean empty() {
        return internalLinkedList.size() == 0;
    }

    /**
     * Looks at but does not remove the top element of the stack.
     *
     * @return The top element of the stack, or {@code null} if the stack is empty.
     * <p>Time complexity: O(1)</p>
     */
    public T peek() {
        return internalLinkedList.get(internalLinkedList.size()-1);
    }

    /**
     * Retrieves and removes the top element of the stack.
     *
     * @return The top element of the stack, or {@code null} if the stack is empty.
     * <p>Time complexity: O(1)</p>
     */
    public T pop() {
        T pop = internalLinkedList.get(internalLinkedList.size()-1);
        if (pop != null) {
            internalLinkedList.remove(internalLinkedList.size()-1);
        }
        return pop;
    }

    /**
     * Pushes the specified element onto the top of the stack.
     *
     * @param e The element to be added. Must not be null.
     *          <p>Time complexity: O(1)</p>
     */
    public void push(T e) {
        internalLinkedList.append(e);
    }
}
