package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

class testTree {
BinarySearchTree<Integer> myTree;

	@BeforeEach
	void setUp() throws Exception {
		myTree = new BinarySearchTree<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		myTree = null;
	}

	@Test
	void add1() {
		myTree.add(1);
		myTree.add(1);
		assertEquals("height skall vara == o", myTree.size(), 1);
	}

	@Test
	void testEmptyHeight() {
		assertEquals("height skall vara == o", myTree.height(), 0);
	}

	@Test
	void testNonEmptyHeight() {
		myTree.add(6);
		myTree.add(4);
		myTree.add(9);
		myTree.add(8);
		assertEquals("height skall vara == 3", myTree.height(), 3);
	}

	@Test
	void testEmptySize() {
		assertEquals("size skall vara == o", myTree.size(), 0);
	}

	@Test
	void testNonEmptySize() {
		myTree.add(6);
		myTree.add(4);
		myTree.add(9);
		myTree.add(8);
		assertEquals("height skall vara == 4", myTree.size(), 4);
	}
}
