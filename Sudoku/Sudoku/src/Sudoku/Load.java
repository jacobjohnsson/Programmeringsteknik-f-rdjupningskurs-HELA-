package Sudoku;

public class Load {

    public static void main(String[] args) {
    	SudokuSolver s = exampleBoard();
    	s.printSudoku();
    	s.solve();
    	System.out.println();
    	s.printSudoku();
    }

    public static SudokuSolver emptyBoard() {
    	return new SudokuSolver();
    }

    public static SudokuSolver unsolveableBoard() {
        SudokuSolver s = new SudokuSolver();
    	// row 0
    	for(int i = 0; i < 8; i++){
            s.setValue(i + 1, 0, i);
        }
    	// row 1
        s.setValue(9, 1, 8);
        return s;
    }

    public static SudokuSolver exampleBoard() {
        SudokuSolver s = new SudokuSolver();
        // row 0
        s.setValue(8, 0, 2);
		s.setValue(9, 0, 5);
		s.setValue(6, 0, 7);
		s.setValue(2, 0, 8);
        // row 1
        s.setValue(5, 1, 8);
        // row 2
        s.setValue(1, 2, 0);
        s.setValue(2, 2, 2);
        s.setValue(5, 2, 3);
        // row 3
        s.setValue(2, 3, 3);
        s.setValue(1, 3, 4);
        s.setValue(9, 3, 7);
        // row 4
        s.setValue(5, 4, 1);
        s.setValue(6, 4, 6);
        // row 5
        s.setValue(6, 5, 0);
        s.setValue(2, 5, 7);
        s.setValue(8, 5, 8);
        // row 6
        s.setValue(4, 6, 0);
        s.setValue(1, 6, 1);
        s.setValue(6, 6, 3);
        s.setValue(8, 6, 5);
        // row 7
        s.setValue(8, 7, 0);
        s.setValue(6, 7, 1);
        s.setValue(3, 7, 4);
        s.setValue(1, 7, 6);
        // row 8
        s.setValue(4, 8, 6);
        return s;
    }
}
