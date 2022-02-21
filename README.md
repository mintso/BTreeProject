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
• Insert(int value): insert the specified value.
• Display(int node): print out the indexing tree structure under specified node.

In the BtreeTest.java, I've included various of test cases. 

For the demostration of a B Tree, I use () to indicate a B tree node, and Integers inside () are keys to this node. B Tree nodes of the same level will be printed out 
in the same level:

E.g, 
print out the current B tree:
(9) 
(3,6) (12,15) 
(1,2) (4,5) (7,8) (10,11) (13,14) (16,17,18,19,20) 

