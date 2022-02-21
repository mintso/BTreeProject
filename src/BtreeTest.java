/*
 * CS7280 Special Topics in Database Management
 * Project 1: B-tree Test program.
 *
 * The test data will be changed when TA grades.
 * Thus, you need to test various cases.
 */
public class BtreeTest {
    public static void main(String[] args) {

        /** Test simple string array. */
        int[] test1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] test2 = new int[] {10, 20, 30, 40, 50, 15, 60, 85, 95, 100, 11, 12, 13, 22, 32, 33, 34, 1, 2, 3, 4, 5, 6};
        int[] test3 = new int[]{7, 6, 5, 4, 3, 2, 1};
        int[] test4 = new int[]{7, 6, 5, 4, 4, 10, 90, 100, 2, 1, 100};

        // Test following the given sudo code
        testWithStepPrint(test1);
        testWithStepPrint(test2);

        // test different node size
        testGivenArray(test1, 8);
        testGivenArray(test1, 7);
        testGivenArray(test1, 6);
        testGivenArray(test1, 5);
        testGivenArray(test1, 4);
        testGivenArray(test1, 3);

        testGivenArray(test2, 8);
        testGivenArray(test2, 7);
        testGivenArray(test2, 6);
        testGivenArray(test2, 5);
        testGivenArray(test2, 4);
        testGivenArray(test2, 3);

        testGivenArray(test3, 8);
        testGivenArray(test3, 7);
        testGivenArray(test3, 6);
        testGivenArray(test3, 5);
        testGivenArray(test3, 4);
        testGivenArray(test3, 3);

        testGivenArray(test4, 8);
        testGivenArray(test4, 7);
        testGivenArray(test4, 6);
        testGivenArray(test4, 5);
        testGivenArray(test4, 4);
        testGivenArray(test4, 3);

        testInsertDisplayEachTime(5);
        testLookUp(5);
        testInsertDuplicates(5);
        testGivenArray(test1, 5);
        testGivenArray(test2, 5);
        testDisplayUnderCertainValue(5);

        debugTest();
    }

    public static void testWithStepPrint(int[] values) {

        // nodeSize is default 5
        System.out.println("testWithStepPrint: Building B tree with default Node size 5:");

        // number of strings to be inserted
        int insertValues = values.length;

        System.out.println("Create B-tree with " + insertValues + " Values...");
        Btree tree = new Btree();

        System.out.println("Insert Values...");
        for(int v: values) tree.insert(v);
        int countNode = tree.countNodes;
        System.out.println("There are " + countNode + " nodes stored in the B tree.");
        int countValues = tree.countValues;
        System.out.println("There are " + countValues + " different values stored in the B tree.");

        // Display the current B Tree
        tree.displayBTree();

        System.out.println("Finding Values...");
        int found = 0;
        for(int v : values) if(tree.lookUp(v)) found++;
        System.out.println(found + " found, " + insertValues + " expected.");

        System.out.println("Reinsert Values... ");
        for(int v : values) tree.insert(v);
        System.out.println(tree.countValues + " stored, " + insertValues + " expected.");
        // Display the current B Tree
        tree.displayBTree();

        System.out.println("Finding Values...");
        found = 0;
        for(int v : values) if(tree.lookUp(v)) found++;
        System.out.println(found + " found, " + insertValues + " expected.");
        System.out.println();
    }

    public static void testInsertDisplayEachTime(int nodeSize) {

        System.out.println("testInsertDisplayEachTime: Building B tree with provided Node size: " + nodeSize);
        Btree bTree = new Btree(nodeSize);

        int countNode = bTree.countNodes;
        int countValues = bTree.countValues;

        bTree.insert(1);
        bTree.displayBTree();
        bTree.insert(2);
        bTree.displayBTree();
        bTree.insert(3);
        bTree.displayBTree();
        countNode = bTree.countNodes;
        countValues = bTree.countValues;
        System.out.println("There are " + countNode + " nodes stored in the B tree.");
        System.out.println("There are " + countValues + " different values stored in the B tree.");
        System.out.println("Check whether nodes and values are correct: ");
        bTree.displayBTree();
        System.out.println();

        bTree.insert(4);
        bTree.displayBTree();
        bTree.insert(5);
        bTree.displayBTree();
        bTree.insert(6);
        bTree.displayBTree();
        bTree.insert(7);
        countNode = bTree.countNodes;
        countValues = bTree.countValues;
        System.out.println("There are " + countNode + " nodes stored in the B tree.");
        System.out.println("There are " + countValues + " different values stored in the B tree.");
        System.out.println("Check whether nodes and values are correct: ");
        bTree.displayBTree();
        System.out.println();

        bTree.insert(8);
        bTree.displayBTree();
        bTree.insert(9);
        bTree.displayBTree();
        bTree.insert(10);
        bTree.displayBTree();
        bTree.insert(11);
        countNode = bTree.countNodes;
        countValues = bTree.countValues;
        System.out.println("There are " + countNode + " nodes stored in the B tree.");
        System.out.println("There are " + countValues + " different values stored in the B tree.");
        System.out.println("Check whether nodes and values are correct: ");
        bTree.displayBTree();
        System.out.println();
    }

    public static void testLookUp(int nodeSize) {
        System.out.println("testLookUp: Building B tree with provided Node size: " + nodeSize);
        Btree bTree = new Btree(nodeSize);
        bTree.insert(3);
        bTree.insert(4);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(7);
        bTree.insert(8);
        bTree.insert(9);
        bTree.insert(10);
        bTree.insert(11);
        System.out.println(bTree.lookUp(1));
        System.out.println(bTree.lookUp(2));
        System.out.println(bTree.lookUp(3));
        System.out.println(bTree.lookUp(4));
        System.out.println(bTree.lookUp(5));
        System.out.println(bTree.lookUp(6));
        System.out.println(bTree.lookUp(7));
        System.out.println(bTree.lookUp(8));
        System.out.println(bTree.lookUp(9));
        System.out.println(bTree.lookUp(10));
        System.out.println(bTree.lookUp(11));
        System.out.println(bTree.lookUp(15));
        System.out.println(bTree.lookUp(-100));

        System.out.println();
    }

    public static void testInsertDuplicates(int nodeSize) {
        System.out.println("testInsertDuplicates: Building B tree with provided Node size: " + nodeSize);
        Btree bTree = new Btree(nodeSize);
        bTree.insert(1);
        bTree.displayBTree();
        bTree.insert(2);
        bTree.displayBTree();
        bTree.insert(1);
        bTree.displayBTree();
        bTree.insert(2);
        System.out.println();
    }

    public static void testGivenArray(int[] values, int nodeSize) {
        System.out.println("testGivenArray: Building B tree with provided Node size: " + nodeSize);
        Btree bTree = new Btree(nodeSize);
        for(int i : values) bTree.insert(i);
        System.out.println("Display testTree1:");
        bTree.displayBTree();
        System.out.println();
    }

    public static void testDisplayUnderCertainValue (int nodeSize) {
        System.out.println("testDisplayUnderCertainValue: Building B tree with provided Node size: " + nodeSize);
        Btree bTree = new Btree(nodeSize);
        int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        for(int i : values) bTree.insert(i);
        System.out.println("Display testTree1:");
        bTree.displayBTree();
        bTree.display(1);
        bTree.display(2);
        bTree.display(6);
        bTree.display(7);
        bTree.display(200);
        System.out.println();
    }

    public static void debugTest() {
        /** Test simple string array. */
        int[] test1 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Btree testTree1 = new Btree(5);
        for(int i: test1) testTree1.insert(i);
        System.out.println("Display testTree1:");
        testTree1.displayBTree();
        testTree1.display(2);
        testTree1.display(1);
        testTree1.display(3);
        testTree1.display(6);
        testTree1.display(8);

        Btree testTree2 = new Btree(6);
        for(int i: test1) testTree2.insert(i);
        System.out.println("Display testTree2:");
        testTree2.displayBTree();
        testTree2.display(2);
        testTree2.display(1);
        testTree2.display(3);
        testTree2.display(6);
        testTree2.display(8);
    }
}
