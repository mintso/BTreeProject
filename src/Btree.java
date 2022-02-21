/*
 * CS7280 Special Topics in Database Management
 * Project 1: B-tree implementation
 * Xinglu Jiang, 2022 - Feb 19
 *
 * You need to code for the following functions in this program
 *   1. Lookup(int value) -> nodeLookup(int value, int node)
 *   2. Insert(int value) -> nodeInsert(int value, int node)
 *   3. Display(int node)
 *
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Btree {
    private BTreeNode root;
    // maximum keys that a node could hold
    private int nodeSize;
    // t is minimum degree, which means minimum children that a node should have
    private int t;
    // except the root node, the minimum number of keys that a node should have (which is t-1)
    private int minKeyNum;
    // except the root node, the maximum number of key that a node should have (which is 2*t-1)
    private int maxKeyNum;
    /* Number of currently used nodes. */
    public int countNodes;
    /* Number of currently used values. */
    public int countValues;

    // Constructor to initiate a B tree with maximum node size 5 (order of 6)
    public Btree() {
        this.nodeSize = 5;
        this.t = (nodeSize + 1) / 2;
        this.minKeyNum = t - 1;
        this.maxKeyNum = 2 * t - 1;
        this.root = new BTreeNode();
        this.countValues = 0;
        this.countNodes = 1;
    }

    // Construction to initiate a B tree with given node size
    public Btree(int nodeSize) {
        if (nodeSize < 2) {
            System.out.println("Node size cannot be less than 2. Please provide valid node size.");
            return;
        }
        this.nodeSize = nodeSize;
        this.t = (nodeSize + 1) / 2;
        this.minKeyNum = t - 1;
        this.maxKeyNum = 2 * t - 1;
        this.root = new BTreeNode();
        this.countValues = 0;
        this.countNodes = 1;
    }

    // Function to insert a specified value to B tree
    public void insert(int key) {
        // Find whether we have that key inserted before, if so simply return
        if(findNode(root, key) != null) return;
        // Increase number of currently used values
        this.countValues++;
        BTreeNode rootCopy = root;
        if (root.countKeys == maxKeyNum) {
            BTreeNode newRoot = new BTreeNode();
            root = newRoot;
            countNodes++;
            newRoot.isLeaf = false;
            newRoot.insertChild(0, rootCopy);
            split(newRoot, 0);
            insertNoFull(newRoot, key);
        } else {
            insertNoFull(rootCopy, key);
        }
    }

    //Function to insert a key to specific node if node not full
    private void insertNoFull(BTreeNode node, int key) {

        // Traverse from end to front of the array to locate insert position
        int i = node.countKeys - 1;
        // If the node is leaf just insert the new key
        if (node.isLeaf) {
            while (i >= 0 && key < node.keys.get(i)) i--;
            node.insertKey(i + 1, key);
        } else {
            // if not, we search in its children
            while (i >= 0 && key < node.keys.get(i)) i--;
            i = i + 1;
            // if children is full, split the node
            if (node.children.get(i).countKeys == maxKeyNum) {
                split(node, i);
                if (key > node.keys.get(i)) i = i + 1;
            }
            // Recursively search and insert in children
            insertNoFull(node.children.get(i), key);
        }
    }

    // Function to split a node at specific index if it contains more than maximum keys
    private void split(BTreeNode node, int index) {

        BTreeNode child = node.children.get(index);
        BTreeNode newNode = new BTreeNode();
        countNodes++;
        newNode.isLeaf = child.isLeaf;
        // Insert into new node to ensure minKeyNum (t is minimum keys that a node should have)
        for (int j = 0; j < minKeyNum; j++) {
            newNode.insertKey(j, child.keys.get(j + t));
        }
        if (!child.isLeaf) {
            for (int j = 0; j < t; j++) {
                newNode.insertChild(j, child.children.get(j + t));
            }
        }
        newNode.countKeys = minKeyNum;
        child.countKeys = minKeyNum;
        node.insertChild(index + 1, newNode);
        node.insertKey(index, child.keys.get(minKeyNum));
    }

    // Function to find a specified value. If the value exists, returning value is True.
    public boolean lookUp(int value) {
        return lookUpHelper(root, value);
    }

    private boolean lookUpHelper(BTreeNode node, int value) {
        int i = 0;
        while (i < node.countKeys && value > node.keys.get(i)) i++;
        if (i < node.countKeys && value == node.keys.get(i)) {
            System.out.println("Node " + value + " is in this B tree");
            return true;
        }
        if(node.isLeaf) {
            System.out.println("Node " + value + " not found in this B tree");
            return false;
        }
        BTreeNode cur = node.children.get(i);
        return(lookUpHelper(cur, value));
    }

    // Function to print out the indexing tree structure under specified value
    public void display(int value) {
        // if the node does not exist in the tree, draw nothing and return
        BTreeNode node = findNode(root, value);
        if(node == null) {
            System.out.println("CANNOT DISPLAY because Node " + value + " is not in this B tree");
            return;
        };
        System.out.println("print out the indexing tree structure under specified node: " + value + ": ");
        print(node);
    }

    // Function to find whether a specific value exist in the B Tree, if so return the BTreeNode it exists
    private BTreeNode findNode(BTreeNode node, int value) {
        int i = 0;
        while (i < node.countKeys && value > node.keys.get(i)) i++;
        if (i < node.countKeys && value == node.keys.get(i)) {
            //   System.out.println("found node: " + value);
            return node;
        }
        if(node.isLeaf) return null;
        BTreeNode cur = node.children.get(i);
        return(findNode(cur, value));
    }

    // Helper function to print out the indexing tree structure under specified BTree node
    private void print(BTreeNode n) {

        Queue q = new LinkedList<>();
        q.offer(n);
        // count how many nodes in cur level so as to print in same line
        int curLevel = 1;
        // count how many nodes in next level so as to print in same line
        int nextLevel = 0;

        while (!q.isEmpty()) {
            BTreeNode node = (BTreeNode) q.poll();
            for (int i = 0; i < node.countKeys; i++) {
                if(i == 0) System.out.print("(");
                System.out.print(node.keys.get(i));
                if(i != node.countKeys - 1) System.out.print(",");
                if(i == node.countKeys - 1) System.out.print(") ");
            }
            nextLevel += node.children.size();
            curLevel--;
            if(curLevel == 0) {
                System.out.printf("\n");
                curLevel = nextLevel;
                nextLevel = 0;
            }
            if (!node.isLeaf) {
                for (int i = 0; i < node.countKeys + 1; i++) {
                    q.offer(node.children.get(i));
                }
            }
        }
        System.out.printf("\n");
    }

    // Function to print out the current entire B tree
    public void displayBTree() {
        System.out.println("print out the current B tree:");
        // Use q to print level by level
        Queue q = new LinkedList<>();
        q.offer(root);
        // count how many nodes in cur level so as to print in same line
        int curLevel = 1;
        // count how many nodes in next level so as to print in same line
        int nextLevel = 0;

        while (!q.isEmpty()) {
            BTreeNode node = (BTreeNode) q.poll();
            for (int i = 0; i < node.countKeys; i++) {
                if(i == 0) System.out.print("(");
                System.out.print(node.keys.get(i));
                if(i != node.countKeys - 1) System.out.print(",");
                if(i == node.countKeys - 1) System.out.print(") ");
            }
            nextLevel += node.children.size();
            curLevel--;
            if(curLevel == 0) {
                System.out.printf("\n");
                curLevel = nextLevel;
                nextLevel = 0;
            }
            if (!node.isLeaf) {
                for (int i = 0; i < node.countKeys + 1; i++) {
                    q.offer(node.children.get(i));
                }
            }
        }
        System.out.printf("\n");
    }

    class BTreeNode {
        // Children of the BTreeNode
        public List<BTreeNode> children = new ArrayList<>();
        // Keys stored in the BTreeNode
        public List<Integer> keys = new ArrayList<>();
        // Number of keys that currently stored in this BTreeNode
        public int countKeys = 0;
        // Whether this BTreeNode is leaf of its BTree
        public boolean isLeaf = true;

        // Function to insert a new key in the node in specific index position
        public void insertKey(int index, int key) {
            keys.add(index, key);
            countKeys++;
        }

        // Function to insert a new child to the node in specific index position
        public void insertChild(int index, BTreeNode child) {
            children.add(index, child);
        }
    }
}
