package org.dangerwind;

import org.dangerwind.score.ScoreWeights;
import org.dangerwind.score.ScorePatternType;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        ScoreWeights scoreWeights = new ScoreWeights();
        
        scoreWeights.set(ScorePatternType.FIVE, 10000);
        scoreWeights.set(ScorePatternType.FOUR_OPEN, 10000);
        scoreWeights.set(ScorePatternType.FOUR_SEMI_OPEN, 1000);
        scoreWeights.set(ScorePatternType.THREE_OPEN, 500);
        scoreWeights.set(ScorePatternType.THREE_SEMI_OPEN, 100);
        scoreWeights.set(ScorePatternType.TWO_OPEN, 50);
        scoreWeights.set(ScorePatternType.TWO_SEMI_OPEN, 10);
        scoreWeights.set(ScorePatternType.ONE_OPEN, 5);
        scoreWeights.set(ScorePatternType.ONE_SEMI_OPEN, 1);
        scoreWeights.set(ScorePatternType.CLOSED, 0);

        ConsoleUI ui = new ConsoleUI();
        Player current = Player.BLACK;

        while (true) {
            ui.printBoard(board);
            ui.printCurrentTurn(current);

            int[] move;
            while (true) {
                move = ui.readMove();
                if (move == null) {
                    ui.printInvalidInput();
                    continue;
                }
                boolean placed = board.setPlayer(move[0], move[1], current);
                if (!placed) {
                    ui.printCellOccupied();
                    continue;
                }
                break;
            }

            current = (current == Player.BLACK) ? Player.WHITE : Player.BLACK;
        }
    }
}