package Sudoku;

import java.util.TreeSet;

public class SudokuSolver {
private int[][] board;
private boolean[][] immutableBoard;
public TreeSet<Integer> correctInput;

	/***
	 * Constructor - fills board with rows and colums and sets them to zero.
	 *
	 */
	public SudokuSolver() {
		// Accepterade input
		correctInput = new TreeSet<Integer>();
		for(int n = 0; n < 10 ; n++) {
			correctInput.add(n);
		}

		board = new int[9][9];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; i < 9; i++) {
				board[i][j] = 0;
			}
		}


		immutableBoard = new boolean[9][9];
	}

	/***
	 * Gets the value at row, colum.
	 * @param row
	 * @param colum
	 * @return int at location.
	 */
	public int getValue(int row, int col) {
		if(!correctInput.contains(row + 1)
			|| !correctInput.contains(col + 1)){
				throw new IllegalArgumentException();
		} else {
			return board[row][col];
		}
	}

	/***
	 * Sets the value at row, colum.
	 * @param value
	 * @param row
	 * @param column
	 */
	public void setValue(int value, int row, int col) {
		if(correctInput.contains(value)
			&& correctInput.contains(row + 1)
			&& correctInput.contains(col + 1)){
				board[row][col] = value;
		} else {
			System.out.println("Funktion setValue: value " + value + ", row " + row + ", col " + col);
		}
	}

	/***
	 * Sets the value at row, colum.
	 * @param value
	 * @param row
	 * @param column
	 */
	private void setValueRecursive(int value, int row, int col) {
		if(correctInput.contains(value)
			&& correctInput.contains(row + 1)
			&& correctInput.contains(col + 1)){
				board[row][col] = value;
		} else {
			//System.out.println("Funktion setValueRecursive: value " + value + ", row " + row + ", col " + col);
		}
	}

	/***
	 * Looks if currentplacement is in conflict with another number acordning to the laws of Sudoko
	 * @param row
	 * @param column
	 * @return boolean
	 */
	private boolean isInConflict(int row, int col) {
		//3 rules: region, rad och kolumn - i rader och j kolumner
		if(row > 8 || col > 8) {
			return false;
		}
		int value = board[row][col];

		//regionen check.
		int rowRegionStart = (row - row % 3);
		int colRegionStart = (col - col % 3);
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(board[(rowRegionStart + i)][(colRegionStart + j)] == value
					&& !((rowRegionStart + i) == row)
					&& !((colRegionStart + j) == col)) {
					return true;
				}
			}
		}

		//Row check.
		for(int j = 0; j < 9; j++){
			if(board[row][j] == value && !(j == col)){
				return true;
			}
		}

		//Col check.
		for(int i = 0; i < 9; i++){
			if(board[i][col] == value && !(i == row)){
				return true;
			}
		}

		return false;
	}

	/***
	 * Solves the soduko.
	 * @return false if no solution, true if solution was found.
	 */
	public boolean solve() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] != 0) {
					immutableBoard[i][j] = true;
				}
			}
		}
		System.out.println();
		printSudokuImmutable();
		System.out.println();
		return solve(0, 0);
	}

	/***
	 * Recursive algorithm for solving the soduko.
	 * @param row
	 * @param column
	 * @return boolean
	 */
	public boolean solve(int row, int col) {

		if(row == 9) {
			return true;
		} else {
			if(col > 8) {
				return solve(row + 1, 0);
			} else if (!immutableBoard[row][col]){
				for(int v = 1; v < 10; v++ ) {
					setValueRecursive(v, row, col);
					System.out.println();
					printSudoku();
					if(!isInConflict(row, col)) {
						if(solve(row, col + 1)) {
							return true;
						} else{
							setValueRecursive(0, row, col);
						}
					}
					setValueRecursive(0, row, col);
				}
			} else {
				return solve(row, col + 1);
			}
		}
		return false;
	}

	/***
	 * Prints the board to console.
	 */
	public void printSudoku(){
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	/***
	 * Prints the immutableBoardto console.
	 */
	public void printSudokuImmutable(){
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(immutableBoard[i][j] + " ");
			}
			System.out.println();
		}
	}
}
