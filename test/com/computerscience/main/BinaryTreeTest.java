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
        binaryTree.printTraversalOrder();
    }

    @Test
    public void testBinaryTreeAddAndSearch() {
        //given: I have an empty binary tree with root value of 10
        BinaryTree binaryTree = new BinaryTree(10);
        binaryTree.add(5);
        binaryTree.add(15);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(11);
        //when: I search an existing item
        BinaryTree.BinaryNode node = binaryTree.search(3);
        //then: The node exists and returns not null
        assert node.getValue() == 3;
    }


    @Test
    public void testBinaryTreeAddAndSearchUnExistentNode() {
        //given: I have an empty binary tree with root value of 10
        BinaryTree binaryTree = new BinaryTree(10);
        binaryTree.add(5);
        binaryTree.add(15);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(11);
        //when: I search an existing item
        BinaryTree.BinaryNode node = binaryTree.search(99);
        //then: The node do not exist and returns null
        assert node == null;
    }

    @Test
    public void printBinaryTree() {
        //given: I have an empty binary tree with root value of 10
        BinaryTree binaryTree = new BinaryTree(10);
        binaryTree.add(5);
        binaryTree.add(15);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(11);
        binaryTree.add(17);
        binaryTree.printTree();
    }

    //AI Generated
    @Test
    public void testBinaryTreeDeleteNode() {
        // given: I have a binary tree with multiple nodes
        BinaryTree binaryTree = new BinaryTree(10);
        binaryTree.add(5);
        binaryTree.add(15);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(11);
        binaryTree.add(17);

        // when: I delete a node with value 7
        binaryTree.delete(7);

        // then: The node should no longer exist in the tree
        BinaryTree.BinaryNode deletedNode = binaryTree.search(7);
        assert deletedNode == null;

        // when: I delete the root node (10)
        binaryTree.delete(10);

        // then: The root should be replaced by another in-order successor
        BinaryTree.BinaryNode newRoot = binaryTree.search(10);
        assert newRoot == null;
        assert binaryTree.getTraversalArrayList().size() == 5; // Tree should now have 5 nodes
    }

    //AI generated
    @Test
    public void testBinaryTreeAddWithRepeatedValues() {
        // given: I have an empty binary tree with a root value of 10
        BinaryTree binaryTree = new BinaryTree(10);

        // when: I add values, including repeated values
        binaryTree.add(5);
        binaryTree.add(15);
        binaryTree.add(5); // repeated value
        binaryTree.add(15); // repeated value
        binaryTree.add(3);
        binaryTree.add(7);

        // then: The repeated values should be ignored
        ArrayList<Integer> traversalArrayList = binaryTree.getTraversalArrayList();
        assert traversalArrayList.get(0) == 3;
        assert traversalArrayList.get(1) == 5;
        assert traversalArrayList.get(2) == 7;
        assert traversalArrayList.get(3) == 10;
        assert traversalArrayList.get(4) == 15;
    }
}
