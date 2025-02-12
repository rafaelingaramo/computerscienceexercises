package com.computerscience.main;

public class BinaryTree {
    private BinaryNode root;

    public BinaryTree(Integer rootValue) {
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

    public void printTraversalOrder() {
        ArrayList<Integer> traversalArrayList = getTraversalArrayList();
        for (Integer item : traversalArrayList) {
            System.out.print(item + " ");
        }
    }

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
        } else {
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
        } else {
            subtree_addBefore(node.right, value);
        }
    }
}
