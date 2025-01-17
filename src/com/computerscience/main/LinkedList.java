package com.computerscience.main;

/**
 * Represents a double linked list collection
 * [] -> [] -> [] -> []
 * Each node has a link to the next
 * @param <T>
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private Integer internalSize = 0;
    private T[] internalCacheArray; //this is used to avoid re-computation of the array everytime the get method is called

    //appends into the collection, each node is linked pointing to the next one by reference
    public void append(T e) {

        internalSize++;
        //if head is null then it's the first insertion
        if (head == null) {
            Node<T> newNode = new Node<>(e, null, null);
            this.head = newNode;
            this.tail = newNode;
            return;
        }
        Node<T> newNode = new Node<>(e, this.tail, null);
        //updates the before last node to point to the new node
        this.tail.next = newNode;
        //updates the tail to the new node
        this.tail = newNode;
        //it invalidates the internal cache array
        this.internalCacheArray = null;
    }

    //appends into specified index, it shifts all entries to the right
    //if the index is invalid then an IllegalArgumentException is thrown
    //it needs to iterate through all nodes to find the specific index, it makes O(n)
    //[] -> [] -> [] -> X -> [] - []
    public void append(T e, int index) {
        if (index < 0 || index > internalSize) {
            throw new IllegalArgumentException("Invalid requested index");
        }
        if (index == 0) {
            this.head = new Node<>(e, null, this.head); //re-assigns head
            internalSize++;
            return;
        }

        //I'm adding to the last pos
        if (index == internalSize) {
            Node<T> newTail = new Node<>(e, this.tail, null);
            Node<T> oldTail = this.tail;
            oldTail.next = newTail;
            this.tail = newTail; //re-assigns tail
            internalSize++;
            return;
        }

        Node<T> node = findSpecificNodeByIndex(this.head, index, 0);
        if (node == null) {
            throw new IllegalArgumentException("Non-existing pos");
        }
        internalSize++;
        Node<T> newNode = new Node<>(e, node.previous, node);
        //re-assigns previous node, next property to the new pos
        if (node.previous != null) {
            node.previous.next = newNode;
        }
        //it invalidates the internal cache array
        this.internalCacheArray = null;
    }

    public void appendFirst(T e) {
        append(e, 0);
    }

    public void appendLast(T e) {
        append(e, internalSize);
    }

    public void clear() {
        clearRecursively(this.tail);
        this.head = null;
        this.tail = null;
        this.internalSize = 0;
        this.internalCacheArray = null;
    }

    public void remove(int index) {
        Node<T> node = findSpecificNodeByIndex(this.head, index, 0);
        if (node == null) {
            throw new IllegalArgumentException("Non-existing pos");
        }

        //I'm removing the head and there's a next pos to shift back
        if (node.previous == null && node.next != null) {
            this.head = this.head.next;
        }

        //I'm removing a mid-position object and I need to shift everything to the right to left
        if (node.previous != null && node.next != null) {
            Node<T> prevNode = node.previous;
            prevNode.next = node.next; //I'm updating the prevNode.next attribute to be the next pos
        }

        //I'm removing the tail
        if (node.previous != null && node.next == null) {
            this.tail.previous.next = null;
            this.tail = this.tail.previous;
        }

        this.internalSize--;
        this.internalCacheArray = null;
    }

    //it uses the index to fetch the element
    //it uses the toArray therefore it's O(n)
    public T get(int index) {
        return toArray()[index];
    }

    //iterate though all elements to form a new array, O(n)
    //this class has a internal cache that prevents this method from re-computing all times
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        if (internalCacheArray != null) {
            return internalCacheArray;
        }

        Object[] resultArray = new Object[internalSize];
        fillArrayPosWithNodes(this.head, resultArray, 0);
        return (T[]) resultArray;
    }

    public Integer size() {
        return this.internalSize;
    }

    private void clearRecursively(Node<T> e) {
        e.next = null;
        if (e.previous != null) {
            clearRecursively(e.previous);
            e.previous = null;
        }
    }

    private Node<T> findSpecificNodeByIndex(Node<T> node, int searchIndex, int pos) {
        //i'm looking the head
        if (searchIndex == 0) {
            return this.head;
        }
        //i'm looking the tail
        if (searchIndex == internalSize) {
            return this.tail;
        }
        //i'm looking for a mid-position searchIndex object
        if (searchIndex == pos) {
            return node;
        }
        return findSpecificNodeByIndex(node.next, searchIndex, ++pos);
    }

    private void fillArrayPosWithNodes(Node<T> node, Object[] resultArray, Integer nextPos) {
        resultArray[nextPos] = node.value;
        if (node.next != null) {
            fillArrayPosWithNodes(node.next, resultArray, ++nextPos);
        }
    }

    private static class Node<T> {
        private final T value;
        private Node<T> previous;
        private Node<T> next;

        private Node(T value, Node<T> previous, Node<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}

