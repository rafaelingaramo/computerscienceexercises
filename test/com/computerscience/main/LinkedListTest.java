package com.computerscience.main;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class LinkedListTest {
    @Test
    void testAppend() {
        //given I have an empty linked list
        LinkedList<String> linkedList = new LinkedList<>();
        //when I insert data
        linkedList.append("Banana");
        linkedList.append("Orange");
        linkedList.append("Grape");
        linkedList.append("Apple");
        linkedList.append("Strawberry");
        //then I need to verify the elements and the size of the collection
        assert linkedList.size() == 5;
        assert Objects.equals(linkedList.get(0), "Banana");
        assert Objects.equals(linkedList.get(1), "Orange");
        assert Objects.equals(linkedList.get(2), "Grape");
        assert Objects.equals(linkedList.get(3), "Apple");
        assert Objects.equals(linkedList.get(4), "Strawberry");
    }

    @Test
    void testAppendOnHeadPos() {
        //given I have a populated linked list
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.append("Banana");
        linkedList.append("Orange");
        linkedList.append("Grape");
        linkedList.append("Apple");
        linkedList.append("Strawberry");
        //when I insert data to specific pos
        linkedList.append("Lime", 0);
        linkedList.append("Pineapple", 3);
        //then I need to verify the elements and the size of the collection
        assert linkedList.size() == 7;
        assert Objects.equals(linkedList.get(0), "Lime");
        assert Objects.equals(linkedList.get(1), "Banana");
        assert Objects.equals(linkedList.get(2), "Orange");
        assert Objects.equals(linkedList.get(3), "Pineapple");
        assert Objects.equals(linkedList.get(4), "Grape");
        assert Objects.equals(linkedList.get(5), "Apple");
        assert Objects.equals(linkedList.get(6), "Strawberry");
    }

    @Test
    void testAppendOnHeadAndTail() {
        //given I have a populated linked list
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.append("Banana");
        linkedList.append("Orange");
        linkedList.append("Grape");
        linkedList.append("Apple");
        linkedList.append("Strawberry");
        //when I insert data to specific pos
        linkedList.appendFirst("Lime");
        linkedList.appendLast("Pineapple");
        //then I need to verify the elements and the size of the collection
        assert linkedList.size() == 7;
        assert Objects.equals(linkedList.get(0), "Lime");
        assert Objects.equals(linkedList.get(1), "Banana");
        assert Objects.equals(linkedList.get(2), "Orange");
        assert Objects.equals(linkedList.get(3), "Grape");
        assert Objects.equals(linkedList.get(4), "Apple");
        assert Objects.equals(linkedList.get(5), "Strawberry");
        assert Objects.equals(linkedList.get(6), "Pineapple");
    }

    @Test
    void testClear() {
        //given I have a populated linked list
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.append("Banana");
        linkedList.append("Orange");
        linkedList.append("Grape");
        linkedList.append("Apple");
        linkedList.append("Strawberry");
        //when I insert data to specific pos
        linkedList.appendFirst("Lime");
        linkedList.appendLast("Pineapple");
        //then I need to verify the elements and the size of the collection
        assert linkedList.size() == 7;
        //and I call the clear function
        linkedList.clear();
        //then I need to assert the total size
        assert linkedList.size() == 0;
    }

    @Test
    void testRemoveByIndexPosition() {
        //given I have a populated linked list
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.append("Banana");
        linkedList.append("Orange");
        linkedList.append("Grape");
        linkedList.append("Apple");
        linkedList.append("Strawberry");
        //when I insert data to specific pos
        linkedList.remove(0);
        linkedList.remove(4);
        //then I need to verify the elements and the size of the collection
        assert linkedList.size() == 3;
        assert Objects.equals(linkedList.get(0), "Orange");
        assert Objects.equals(linkedList.get(1), "Grape");
        assert Objects.equals(linkedList.get(2), "Apple");
        //And now I'll remove a mid-object
        linkedList.remove(2);
        assert linkedList.size() == 2;
        assert Objects.equals(linkedList.get(0), "Orange");
        assert Objects.equals(linkedList.get(1), "Grape");
    }
}
