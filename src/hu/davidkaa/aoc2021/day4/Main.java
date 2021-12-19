package hu.davidkaa.aoc2021.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hu.davidkaa.aoc2021.day4.model.Board;

public class Main {

	public static void main(String[] args) {

		List<Integer> numbersDrawn = Arrays.stream(Data.getDrawnNumbers()).boxed().toList();

		List<String> boardlets = Arrays.stream(Data.getBoards()).toList();

		List<Board> boardList = compileBoards(boardlets);

		int lowestRounds = numbersDrawn.size();
		int winningScore = 0;
		int highestRounds = 0;
		int lastWinningScore = 0;
		
		for (int i = 0; i < boardList.size(); i++) {
			
			Board currentBoard = boardList.get(i);
			
			int boardScore = currentBoard.calculateBingo(numbersDrawn);
			
			if (currentBoard.getWinningTurn() < lowestRounds) {
				lowestRounds = currentBoard.getWinningTurn();
				winningScore = boardScore;
			}
			
			if (currentBoard.getWinningTurn() > highestRounds) {
				highestRounds = currentBoard.getWinningTurn();
				lastWinningScore = boardScore;
			}
		}
		
		System.out.println(winningScore);
		System.out.println(lastWinningScore);
	}

	private static List<Board> compileBoards(List<String> boardlets) {
		List<Board> boardList = new ArrayList<>(boardlets.size() / 5);

		for (int i = 0; i < boardlets.size() - 5; i += 5) {
			String[] rows = {
					boardlets.get(i)
					, boardlets.get(i + 1)
					, boardlets.get(i + 2)
					, boardlets.get(i + 3)
					, boardlets.get(i + 4)};

			boardList.add(new Board(rows));
		}
		
		return boardList;
	}
	

}
