package com.computerscience.main;

import org.junit.jupiter.api.Test;

public class BinaryTreeTest {
    @Test
    public void testBinaryTreeAdd() {
        //given: I have an empty binary tree with root value of 10
        BinaryTree binaryTree = new BinaryTree(10);
        //when: I add items to it
        binaryTree.add(5);
        binaryTree.add(15);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(11);
        //then: I need to assert the positions
        ArrayList<Integer> traversalArrayList = binaryTree.getTraversalArrayList();
        assert traversalArrayList.size() == 6;
        assert traversalArrayList.get(0) == 3;
        assert traversalArrayList.get(1) == 5;
        assert traversalArrayList.get(2) == 7;
        assert traversalArrayList.get(3) == 10;
        assert traversalArrayList.get(4) == 11;
        assert traversalArrayList.get(5) == 15;
    }
}
