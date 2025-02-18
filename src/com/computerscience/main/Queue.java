package com.computerscience.main;

/**
 * A FIFO (First-In-First-Out) queue implementation using a linked list for storage.
 *
 * <p>The `Queue` class provides methods to perform typical queue operations:
 * enqueue (append), dequeue (poll), peek, and check if the queue is empty.</p>
 *
 * <p>All operations are optimized for constant time complexity O(1),
 * making this queue implementation efficient for generic data storage and retrieval.</p>
 *
 * @param <T> The type of elements held in this queue.
 */
public class Queue<T> {
    private final LinkedList<T> internalLinkedList = new LinkedList<>();

    /**
     * Inserts the element onto the queue.
     *
     * @param e The element to insert. Must not be null.
     *          Time complexity: O(1)
     */
    public void append(T e) {
        internalLinkedList.append(e);
    }

    /**
     * Retrieves but does not remove the next element of the queue.
     *
     * @return The next element of the queue, or null if the queue is empty.
     * Time complexity: O(1)
     */
    public T peek() {
        return internalLinkedList.get(0);
    }

    /**
     * Retrieves and removes the next element of the queue.
     *
     * @return The element that was removed, or null if the queue is empty.
     * Time complexity: O(1)
     */
    public T poll() {
        T t = internalLinkedList.get(0);
        if (t != null) {
            internalLinkedList.remove(0);
        }
        return t;
    }

    /**
     * Checks if the collection is empty.
     *
     * @return true if the queue is empty, false otherwise.
     * Time complexity: O(1)
     */
    public boolean empty() {
        return internalLinkedList.size() == 0;
    }
}
