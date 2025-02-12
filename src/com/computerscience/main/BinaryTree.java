package com.computerscience.main;

public class BinaryTree {
    private BinaryNode root;

    public BinaryTree(Integer rootValue) {
        this.root = new BinaryNode(rootValue);
    }

    public static class BinaryNode {
        private Integer value;
        private BinaryNode parent;
        private BinaryNode left;
        private BinaryNode right;


        public BinaryNode(Integer value, BinaryNode parent) {
            this.value = value;
            this.parent = parent;
        }

        private BinaryNode(Integer value) {
            this.value = value;
        }
    }

    public void add(Integer value) {
        if (this.root == null) {
            this.root = new BinaryNode(value);
            return;
        }

        int rootCompare = value.compareTo(this.root.value);
        if (rootCompare > 0) {
            subtree_addAfter(this.root, value);
        } else {
            subtree_addBefore(this.root, value);
        }
    }


    public void printBinaryTree() {
//        int leftHeight = this.root.left != null ? this.calculateHeight(this.root.left, 0) : 0;
//        int rightHeight = this.root.right != null ? this.calculateHeight(this.root.right, 0) : 0;
        //calculate mid side of pyramid
//        int tabSpaces = Math.max(leftHeight, rightHeight)/2;
//        String tabulation = new String(new char[tabSpaces]).replace("\0", "\t");
        if (root.left != null) {
            printNodes(this.root.left, 1);
        }
        System.out.print(this.root.value);
        if (root.right != null) {
            printNodes(this.root.right, 1);
        }
    }

    private void printNodes(BinaryNode node, int level) {
        System.out.print(node.value + " ");
        int nextLevel = ++level;
        if (node.left != null) {
            printNodes(node.left, nextLevel);
        }
        if (node.right != null) {
            printNodes(node.right, nextLevel);
        }
    }

//    private int calculateHeight(BinaryNode node, int height) {
//        int levelLeftHeight = height;
//        int levelRightHeight = height;
//        int leftHeight = node.left != null ? calculateHeight(node.left, ++levelLeftHeight) : 0;
//        int rightHeight = node.right != null ? calculateHeight(node.right, ++levelRightHeight) : 0;
//
//        return Math.max(leftHeight, rightHeight) + 1;
//    }


    private void subtree_addBefore(BinaryNode node, Integer value) {
        if (node.left == null) {
            node.left = new BinaryNode(value, node);
            return;
        }

        //node.left is not null, therefore can't be added to the left side, is the value smaller or greater than current node?
        int compareTo = value.compareTo(node.left.value);
        if (compareTo > 0) {
            subtree_addAfter(node.left, value);
        } else {
            if (node.left.right == null) {
                node.left.right = new BinaryNode(value);
            } else {
                subtree_addBefore(node.left, value);
            }
        }
    }

    private void subtree_addAfter(BinaryNode node, Integer value) {
        if (node.right == null) {
            node.right = new BinaryNode(value, node);
            return;
        }

        //node.right is not null, therefore can't be added to the right side, is the value smaller or greater than the current node?
        int compareTo = value.compareTo(node.value);
        if (compareTo > 0) {
            subtree_addAfter(node.right, value);
        } else {
            subtree_addBefore(node.left, value);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(11);

        tree.printBinaryTree();
    }
}
