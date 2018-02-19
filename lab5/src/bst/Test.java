package bst;

public class Test {

    public BinarySearchTree<Integer> loadSkevt() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        return tree;
    }

    public BinarySearchTree<Integer> loadBalanced() {
    	BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(7);
        tree.add(3);
        tree.add(1);
        tree.add(5);
        tree.add(11);
        tree.add(9);
        tree.add(13);
        return tree;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(6);
        tree.add(6);
        tree.add(4);
        tree.add(9);
        tree.add(8);
        tree.add(5);
        tree.add(12);
        tree.add(11);
        tree.printTree();

        System.out.println();
        System.out.println("size: " + tree.size());
        System.out.println("height: " + tree.height());

    }
}
