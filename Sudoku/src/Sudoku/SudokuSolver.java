package Sudoku;

import java.util.TreeSet;

public class SudokuSolver {
private int[][] board;
private boolean[][] immutableBoard;
public TreeSet<Integer> correctInput;

	/***
	 * Constructor - fills board with rows and colums and set them to zero.
	 */
	public SudokuSolver() {
		// Accepterade input
		correctInput = new TreeSet<Integer>();
		for(int n = 1; n < 10 ; n++) {
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
	 * @return
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
			System.out.println("Funktion setValueRecursive: value " + value + ", row " + row + ", col " + col);
		}
	}

	/***
	 * Looks if currentplacement is in conflict with another number acordning to the laws of Sudoko
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
	
	public void fill() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] != 0) {
					immutableBoard[i][j] = true;
				}
			}
		}
	}

	/***
	 * Anropar den privata rekursiva metoden.

	 */
	public void solve() {
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
		solve2(0, 0);
	}

	/***
	 * Den privata solve methoden. Här löses det nuvarande brädet.
	 * @param board
	 */
	public boolean solve2(int row, int col) {
		if(row == 9) {
			return true;
		} else {
			if(col > 8) {
				return solve2(row + 1, 0);	
			} else if(immutableBoard[row][col]) {
				return solve2(row, col + 1);
			} else {
				for(int v = 1; v < 10; v++ ) {
					setValueRecursive(v, row, col);
					System.out.println();
					printSudoku(); 
					if(!isInConflict(row, col)) {
						if(solve2(row, col + 1)) {
							return true; 
						}
					}
					setValueRecursive(0, row, col);
				}	
			} 
		}
		return false;
	}
	
	/***
	 * Den privata solve methoden. Här löses det nuvarande brädet.
	 * @param board
	 */
//	private boolean solve(int value, int row, int col) {
//		if(row == 9) {
//			return true;
//		} else {
//			if(col < 9 && row < 9 && immutableBoard[row][col]) {
//				return solve(1, row, col + 1);
//			}
//			setValueRecursive(value, row, col);
//			System.out.println();
//			printSudoku();
//			
//			if (isInConflict(row, col)) {
//				return solve(value + 1, row, col);
//			} else if (col == 9) {
//				return solve(1, row + 1, 0);
//			} else {
//				return solve(1, row, col + 1);
//			}
//		}
//	}

	/***
	 * Prints the board.
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
	 * Prints the immutableBoard.
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
