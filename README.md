# BTreeProject

CS 7280: Special Topics in Database Management
Project 1: B-tree Indexing Structure
Objectives:
1. Understand B-tree Indexing Structure
2. Work with tree data structure
3. Become familiar with database algorithm
4. Gain experience with algorithms in DBMS

In this project, I have implemented a B-tree indexing structure in Java. This main memory B-tree indexing structure including the following functions (methods):

• Lookup(int value): find the specified value. If the value exists, returning value is True.

• Insert(int value): insert the specified value. If the value is already inserted, do nothing.

• Display(int node): print out the indexing tree structure under specified node. I also print out all keys that in the same node of the given int node.


I have also implement other functions to better build and demonstrate B tree:

• displayBTree(): print out the current B tree.

• B tree constructors: there are 2 constructors, one require the maximum node size (and will check validation), and another one will use default node size of 5.

In the BtreeTest.java, I've included various of test cases. 

For the demostration of a B Tree, I use () to indicate a B tree node, and Integers inside () are keys to this node. B Tree nodes of the same level will be printed out in the same level:

E.g,  int[] test1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

Building B tree with provided Node size: 5

print out the current B tree:

(9) 

(3,6) (12,15) 

(1,2) (4,5) (7,8) (10,11) (13,14) (16,17,18,19,20) 

Other explanations; 
1. I follow the rule that if root leaf is full, add nodes from leaf, and if full, split the node. I didn't pop up until the root and make the root full first.
2. Limitations of my project: I didn't print out the arrows that indicate the "children" relationships. But it will be easy to guess according to the value of each keys.
