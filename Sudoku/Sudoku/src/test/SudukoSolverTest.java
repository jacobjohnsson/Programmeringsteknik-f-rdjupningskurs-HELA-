package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Sudoku.SudokuSolver;
import Sudoku.Load;

public class SudukoSolverTest {
	SudokuSolver empty;
	SudokuSolver example;
	SudokuSolver unsolveable;

	@Before
	public void setUp() throws Exception {
		empty = Load.emptyBoard();
		example = Load.exampleBoard();
		unsolveable = Load.unsolveableBoard();
	}

	@After
	public void tearDown() throws Exception {
		empty = example = unsolveable = null;
	}

	@Test
	public void testSolveEmpty() {
		empty.solve();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; i < 9; i++) {
				assertFalse("Empty board should be solveable.", empty.isInConflict(i, j));
			}
		}
	}

	@Test
	public void testSolveExample() {
		example.solve();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; i < 9; i++) {
				assertFalse("Empty board should be solveable.", example.isInConflict(i, j));
			}
		}
	}

	@Test
	public void testSolveUnsolvable() {
		unsolveable.solve();
		assertTrue("Should not be solvable.", unsolveable.getValue(0, 8) == 0);
	}
}
