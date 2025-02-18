package com.computerscience.main;

import java.util.Objects;

public class BinarySearchTree {
    private BinaryNode root;

    /**
     * Constructs a binary tree with a specified root value.
     *
     * @param rootValue The value of the root node.
     *                  Must not be null.
     *                  <p>
     *                  Time complexity: O(1)
     */
    public BinarySearchTree(Integer rootValue) {
        this.root = new BinaryNode(rootValue);
    }

    public static class BinaryNode {
        private final Integer value;
        private BinaryNode parent;
        private BinaryNode left;
        private BinaryNode right;

        static BinaryNode withParent(Integer value, BinaryNode parent) {
            return new BinaryNode(value, parent);
        }

        private BinaryNode(Integer value, BinaryNode parent) {
            this.value = value;
            this.parent = parent;
        }

        private BinaryNode(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }

        public boolean isRoot() {
            return parent == null;
        }

        public boolean isLeaf() { return right == null && left == null; }

        public Integer getValue() { return this.value; }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof BinaryNode other)) {
                return false;
            }
            return Objects.equals(other.getValue(), this.getValue());
        }
    }

    /**
     * Adds a value to the binary tree following binary search tree rules.
     *
     * @param value The value to add to the tree.
     *              Must not be null.
     *              <p>
     *              Time complexity: O(h) where h is the height of the tree.
     */
    public void add(Integer value) {
        if (this.root == null) {
            this.root = new BinaryNode(value);
            return;
        }

        int rootCompare = value.compareTo(this.root.value);
        if (rootCompare > 0) {
            subtree_addAfter(this.root, value);
        } else if (rootCompare < 0) {
            subtree_addBefore(this.root, value);
        }
    }

    /**
     * Prints the binary tree structure to the console.
     * <p>
     * Time complexity: O(n) where n is the number of nodes in the tree.
     */
    public void printTree() {
        if (this.root == null) {
            System.out.println("The tree is empty.");
        } else {
            printTree(this.root, "", true);
        }
    }

    private void printTree(BinaryNode node, String prefix, boolean isLeft) {
        if (node != null) {
            printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.getValue());
            printTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    /**
     * Deletes a node from the binary tree by its value if it exists.
     *
     * @param value Value of the node to delete.
     *              Must not be null.
     *              <p>
     *              Time complexity: O(h) where h is the height of the tree.
     */
    public void delete(Integer value) {
        BinaryNode node = search(value);

        if (node == null) {
            return;
        }

        //if it's a leaf, then just de-assign parent left or right references
        if (node.isLeaf()) {
            if (Objects.equals(node.parent.left, node)) {
                node.parent.left = null;
            } else if (Objects.equals(node.parent.right, node)) {
                node.parent.right = null;
            }
            return;
        }

        if (node.left == null && node.right == null) {
            if (node.isRoot()) {
                this.root = null;
            } else {
                BinaryNode parent = node.parent;
                if (Objects.equals(parent.left, node)) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } else if (node.left != null && node.right != null) {
            //replace the far-most left node from the right side, with the root node, and delete the far most left leaf
            //this is done because we need to keep traversal ordering
            BinaryNode farMostLeft = locateFarLeftLeafNode(node.right);
            //remove farMostLeft.parent left reference
            farMostLeft.parent.left = null;

            if (node.isRoot()) {
                farMostLeft.left = this.root.left;
                farMostLeft.right = this.root.right;
                farMostLeft.parent = null;
                this.root = farMostLeft;
            } else {
                BinaryNode nodeParent = node.parent;
                nodeParent.left = farMostLeft;
                farMostLeft.parent = nodeParent;
            }
        } else {
            this.root = Objects.requireNonNullElseGet(node.left, () -> node.right); //re-assigns root to be node.right or node.left pointer
        }

    }

    /**
     * Searches the binary tree for a node with the specified value.
     *
     * @param value The value to search for.
     *              Must not be null.
     * @return The {@link BinaryNode} containing the value, or null if not found.
     * <p>
     * Time complexity: O(h) where h is the height of the tree.
     */
    public BinaryNode search(Integer value) {
        return search(value, this.root);
    }

    private BinaryNode search(Integer value, BinaryNode node) {
        if (node == null) {
            return null;
        }

        int compareTo = value.compareTo(node.value);
        return switch (compareTo) {
            case 0 -> node;
            case -1 -> search(value, node.left);
            case 1 -> search(value, node.right);
            default -> null;
        };
    }

    /**
     * Prints the binary tree's in-order traversal to the console.
     * <p>
     * Time complexity: O(n) where n is the number of nodes in the tree.
     */
    public void printTraversalOrder() {
        ArrayList<Integer> traversalArrayList = getTraversalArrayList();
        for (Integer item : traversalArrayList) {
            System.out.print(item + " ");
        }
    }

    /**
     * Retrieves the binary tree's in-order traversal as a list of values.
     *
     * @return An {@link ArrayList} containing the tree's in-order traversal.
     * <p>
     * Time complexity: O(n) where n is the number of nodes in the tree.
     */
    public ArrayList<Integer> getTraversalArrayList() {
        ArrayList<Integer> traversalNodes = new ArrayList<>();
        ArrayList<Integer> rightTraversalNodes = new ArrayList<>();
        if (root.left != null) {
            BinaryNode farLeftLeafNode = locateFarLeftLeafNode(this.root.left);
            printTraversalNodes(farLeftLeafNode, traversalNodes);
        }
        if (root.right != null) {
            BinaryNode farLeftLeafNodeFromRight = locateFarLeftLeafNode(this.root.right);
            printTraversalNodes(farLeftLeafNodeFromRight, rightTraversalNodes);
        }
        traversalNodes.append(this.root.value);
        traversalNodes.appendAll(rightTraversalNodes);
        return traversalNodes;
    }


    private void printTraversalNodes(BinaryNode node, ArrayList<Integer> arrayList) {
        //don't print root node
        if (node.isRoot()) {
            return;
        }
        arrayList.append(node.value);
        if (node.right != null) {
            arrayList.append(node.right.value);
        }
        if (node.parent != null) {
            printTraversalNodes(node.parent, arrayList);
        }
    }

    private BinaryNode locateFarLeftLeafNode(BinaryNode node) {
        if (node.left != null) {
            return locateFarLeftLeafNode(node.left);
        }
        return node;
    }


    private void subtree_addBefore(BinaryNode node, Integer value) {
        if (node.left == null) {
            node.left = BinaryNode.withParent(value, node);
            return;
        }

        //node.left is not null, therefore can't be added to the left side, is the value smaller or greater than current node?
        int compareTo = value.compareTo(node.left.value);
        if (compareTo > 0) {
            subtree_addAfter(node.left, value);
        } else if (compareTo < 0) {
            subtree_addBefore(node.left, value);
        }
    }

    private void subtree_addAfter(BinaryNode node, Integer value) {
        if (node.right == null) {
            node.right = BinaryNode.withParent(value, node);
            return;
        }

        //node.right is not null, therefore can't be added to the right side, is the value smaller or greater than the current node?
        int compareTo = value.compareTo(node.right.value);
        if (compareTo > 0) {
            subtree_addAfter(node.right, value);
        } else if (compareTo < 0) {
            subtree_addBefore(node.right, value);
        }
    }
}
