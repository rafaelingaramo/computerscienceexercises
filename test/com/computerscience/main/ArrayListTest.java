package com.computerscience.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {
    @Test
    void testAppend() {
        //given I have an empty array list
        ArrayList<String> arrayList = new ArrayList<>();
        //when I insert a new element
        arrayList.append("Banana");
        //Then I need to make sure of the array size and which elements are inside
        assert arrayList.size() == 1;
        assert arrayList.get(0) == "Banana";
    }

    @Test
    void testAppendDynamicArray() {
        //given I have an empty array list
        ArrayList<String> arrayList = new ArrayList<>();
        //when I insert 100 new elements
        for (int i=0;i<100;i++) {
            arrayList.append("Banana " + i);
        }
        //then I need to verify the 100 elements
        assert arrayList.size() == 100;
    }

    @Test
    void testAppendOnIndex() {
        //given I have a non-empty array list
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.append("Banana");
        //when I insert on index 0, then Banana should be right shifted
        arrayList.append("Grape", 0);
        assert arrayList.size() == 2;
        assert arrayList.get(0) == "Grape";
        assert arrayList.get(1) == "Banana";
        //and I insert on pos 2, then Orange should be on the end
        arrayList.append("Orange", 2);
        assert arrayList.size() == 3;
        assert arrayList.get(2) == "Orange";
        //and I insert on pos 1, Strawberry should be 1 and everything else should be right shifted
        arrayList.append("Strawberry", 1);
        assert arrayList.size() == 4;
        assert arrayList.get(0) == "Grape";
        assert arrayList.get(1) == "Strawberry";
        assert arrayList.get(2) == "Banana";
        assert arrayList.get(3) == "Orange";
    }

    @Test
    void testAppendOnInvalidIndex() {
        //given I have an empty array list
        ArrayList<String> arrayList = new ArrayList<>();
        //when I try to append on index -1 I should receive an error
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayList.append("Banana", -1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayList.append("Banana", 2));
    }

    @Test
    void testRemoval() {
        //given I have a populated array list
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.append("Banana");
        assert arrayList.size() == 1;
        //when I try to remove the element, it should not exist
        arrayList.remove(0);
        assert arrayList.size() == 0;
    }

    @Test
    void testRemovalWithInvalidIndex() {
        //given I have a populated array list
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.append("Banana");
        //when I try to remove an invalid index it throws an exception
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayList.remove(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayList.remove(2));
    }

    @Test
    void testRemovalWithObject() {
        //given I have a populated array list with two positions
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.append("Banana");
        arrayList.append("Grape");
        //when I try to remove the banana object I need to verify that only grape exists
        arrayList.remove("Banana");
        assert arrayList.get(0) == "Grape";
        assert arrayList.size() == 1;
    }

    @Test
    void testRemovalNonExistentObject() {
        //given I have a populated array list with two positions
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.append("Banana");
        arrayList.append("Grape");
        //when I try to remove the non-existing object, nothing happens
        arrayList.remove("Strawberry");
        assert arrayList.get(0) == "Banana";
        assert arrayList.get(1) == "Grape";
        assert arrayList.size() == 2;
    }

    @Test
    void testRemovalOfNullObject() {
        //given I have an empty array list
        ArrayList<String> arrayList = new ArrayList<>();
        //when I try to remove null then I get an exception
        assertThrows(IllegalArgumentException.class, () -> arrayList.remove(null));
    }

    @Test
    void testGenerics() {
        //given I have an empty array list that allows duplicates
        ArrayList<Integer> arrayList = new ArrayList<>();
        //when I insert duplicated data
        arrayList.append(1);
        arrayList.append(1);
        //I need to verify that the size == 2
        assert arrayList.size() == 2;
    }
}
