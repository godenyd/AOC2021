package hu.davidkaa.aoc2021.day4.model;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Board {

	private int checkedCount = 0;

	private int[][] boardMatrix = new int[5][5];

	private SortedSet<Integer> numberSet = new TreeSet<>();
	
	private int winningTurn = 0;
	private int winningNumber = 0;

	public Board(String[] numbers) {

		for (int i = 0; i < 5; i++) {
			String[] row = numbers[i].split(" ");

			for (int j = 0; j < 5; j++) {
				int number = Integer.parseInt(row[j]);
				boardMatrix[i][j] = number;
				numberSet.add(number);
			}
		}
	}

	/**
	 * Returns the index of the number that achieves bingo on this board. In case
	 * there would be no bingo on this board, returns and empty optional
	 * 
	 * @param numbersDrawn
	 * @return index of the number finishing bingo, otherwise Optional.empty
	 */
	public int calculateBingo(List<Integer> numbersDrawn) {

		Iterator<Integer> numIter = numbersDrawn.iterator();
		int turnCount = 0;	// is 1-based
		boolean bingo = false;
		int lastNumber = 0;

		// check for bingo
		while (!bingo && numIter.hasNext()) {
			turnCount++;
			lastNumber = numIter.next();

			// check current number on the board
			checkNumberOnBoard(lastNumber);
			bingo = checkBingo();
		}
		
		winningNumber = bingo ? lastNumber : -1;
		winningTurn = bingo ? turnCount : -1;
		
		return bingo ? calculateScore(lastNumber) : -1;
	}

	private boolean checkBingo() {
		if (checkedCount < 5) {
			return false;
		}

		return checkColumns() || checkRows();
	}

	private boolean checkRows() {

		for (int i = 0; i < 5; i++)
			if (checkRow(i))
				return true;

		return false;
	}

	private boolean checkRow(int index) {

		for (int i = 0; i < 5; i++)
			if (boardMatrix[index][i] >= 0)
				return false;

		return true;
	}

	private boolean checkColumns() {

		for (int i = 0; i < 5; i++)
			if (checkColumn(i))
				return true;

		return false;
	}

	private boolean checkColumn(int index) {

		for (int i = 0; i < 5; i++)
			if (boardMatrix[i][index] >= 0)
				return false;

		return true;
	}

	private void checkNumberOnBoard(int number) {
		if (numberSet.contains(number)) {

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (boardMatrix[i][j] == number) {
						boardMatrix[i][j] = -1;
						checkedCount++;
						return;
					}
				}
			}
		}
	}
	
	private int calculateScore(int lastNumber) {
		int sum = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (boardMatrix[i][j] > 0) {
					sum += boardMatrix[i][j];
				}
				
			}
		}
		
		return sum * lastNumber;
	}
	
	public int getWinningNumber() {
		return winningNumber;
	}
	
	public int getWinningTurn() {
		return winningTurn;
	}
}
