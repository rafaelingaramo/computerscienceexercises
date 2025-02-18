package com.computerscience.main;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {


    @Test
    public void testBinaryTreeAdd() {
        //given: I have an empty binary tree with root value of 10
        BinarySearchTree binarySearchTree = new BinarySearchTree(10);
        //when: I add items to it
        binarySearchTree.add(5);
        binarySearchTree.add(15);
        binarySearchTree.add(3);
        binarySearchTree.add(7);
        binarySearchTree.add(11);
        //then: I need to assert the positions
        ArrayList<Integer> traversalArrayList = binarySearchTree.getTraversalArrayList();
        assert traversalArrayList.size() == 6;
        assert traversalArrayList.get(0) == 3;
        assert traversalArrayList.get(1) == 5;
        assert traversalArrayList.get(2) == 7;
        assert traversalArrayList.get(3) == 10;
        assert traversalArrayList.get(4) == 11;
        assert traversalArrayList.get(5) == 15;
        binarySearchTree.printTraversalOrder();
    }

    @Test
    public void testBinaryTreeAddAndSearch() {
        //given: I have an empty binary tree with root value of 10
        BinarySearchTree binarySearchTree = new BinarySearchTree(10);
        binarySearchTree.add(5);
        binarySearchTree.add(15);
        binarySearchTree.add(3);
        binarySearchTree.add(7);
        binarySearchTree.add(11);
        //when: I search an existing item
        BinarySearchTree.BinaryNode node = binarySearchTree.search(3);
        //then: The node exists and returns not null
        assert node.getValue() == 3;
    }


    @Test
    public void testBinaryTreeAddAndSearchUnExistentNode() {
        //given: I have an empty binary tree with root value of 10
        BinarySearchTree binarySearchTree = new BinarySearchTree(10);
        binarySearchTree.add(5);
        binarySearchTree.add(15);
        binarySearchTree.add(3);
        binarySearchTree.add(7);
        binarySearchTree.add(11);
        //when: I search an existing item
        BinarySearchTree.BinaryNode node = binarySearchTree.search(99);
        //then: The node do not exist and returns null
        assert node == null;
    }

    @Test
    public void printBinaryTree() {
        //given: I have an empty binary tree with root value of 10
        BinarySearchTree binarySearchTree = new BinarySearchTree(10);
        binarySearchTree.add(5);
        binarySearchTree.add(15);
        binarySearchTree.add(3);
        binarySearchTree.add(7);
        binarySearchTree.add(11);
        binarySearchTree.add(17);
        binarySearchTree.printTree();
    }

    //AI Generated
    @Test
    public void testBinaryTreeDeleteNode() {
        // given: I have a binary tree with multiple nodes
        BinarySearchTree binarySearchTree = new BinarySearchTree(10);
        binarySearchTree.add(5);
        binarySearchTree.add(15);
        binarySearchTree.add(3);
        binarySearchTree.add(7);
        binarySearchTree.add(11);
        binarySearchTree.add(17);

        // when: I delete a node with value 7
        binarySearchTree.delete(7);

        // then: The node should no longer exist in the tree
        BinarySearchTree.BinaryNode deletedNode = binarySearchTree.search(7);
        assert deletedNode == null;

        // when: I delete the root node (10)
        binarySearchTree.delete(10);

        // then: The root should be replaced by another in-order successor
        BinarySearchTree.BinaryNode newRoot = binarySearchTree.search(10);
        assert newRoot == null;
        assert binarySearchTree.getTraversalArrayList().size() == 5; // Tree should now have 5 nodes
    }

    //AI generated
    @Test
    public void testBinaryTreeAddWithRepeatedValues() {
        // given: I have an empty binary tree with a root value of 10
        BinarySearchTree binarySearchTree = new BinarySearchTree(10);

        // when: I add values, including repeated values
        binarySearchTree.add(5);
        binarySearchTree.add(15);
        binarySearchTree.add(5); // repeated value
        binarySearchTree.add(15); // repeated value
        binarySearchTree.add(3);
        binarySearchTree.add(7);

        // then: The repeated values should be ignored
        ArrayList<Integer> traversalArrayList = binarySearchTree.getTraversalArrayList();
        assert traversalArrayList.get(0) == 3;
        assert traversalArrayList.get(1) == 5;
        assert traversalArrayList.get(2) == 7;
        assert traversalArrayList.get(3) == 10;
        assert traversalArrayList.get(4) == 15;
    }
}
