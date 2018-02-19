package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			return add(x, root, null);
		}
	}

	private boolean add(E x, BinaryNode<E> node, BinaryNode<E> prev){
		if(node == null){
			int comparison = x.compareTo(prev.element);
			size++;
			if(comparison < 0){
				prev.left = new BinaryNode<E>(x);
				return true;
			} else {
				prev.right = new BinaryNode<E>(x);
				return true;
			}
		} else {
			int leftOrRight = x.compareTo(node.element);
			if(leftOrRight < 0){
				return add(x, node.left, node);
			} else if(leftOrRight > 0){
				return add(x, node.right, node);
			} else {
				return false;
			}
		}
	}

	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> root) {
		if(root == null) {
			return 0;
		} else {
			return 1 + Math.max(height(root.left), height(root.right));
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	//inorder
	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
//	public void rebuild() {
//		E[] a = (E[]) new Comparable[size];
//	 	toArray(root, a, 0);
//	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	public int toArray(BinaryNode<Integer> n, int[] a, int index) {
		if(n == null){
			return index;
		} else {
			int index1 = toArray(n.left, a, index);
			a[index1] = n.element;
			index1 = toArray(n.right, a, index1 + 1);
			return index1;
		}
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		return null;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(6);
		tree.add(4);
		tree.add(7);
		int[] a = new int[3];
	 	System.out.println(tree.toArray(tree.root, a, 0));
	}
}
