package org.dangerwind;

import org.dangerwind.score.Score;
import org.dangerwind.score.ScoreWeights;
import org.dangerwind.score.ScorePatternType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Board board = new Board();
        ScoreWeights scoreWeights = new ScoreWeights();
        ConsoleUI ui = new ConsoleUI();

        /*
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


         */

        scoreWeights.set(ScorePatternType.FIVE, 9);
        scoreWeights.set(ScorePatternType.FOUR_OPEN, 8);
        scoreWeights.set(ScorePatternType.FOUR_SEMI_OPEN, 7);
        scoreWeights.set(ScorePatternType.THREE_OPEN, 6);
        scoreWeights.set(ScorePatternType.THREE_SEMI_OPEN, 5);
        scoreWeights.set(ScorePatternType.TWO_OPEN, 4);
        scoreWeights.set(ScorePatternType.TWO_SEMI_OPEN, 3);
        scoreWeights.set(ScorePatternType.ONE_OPEN, 2);
        scoreWeights.set(ScorePatternType.ONE_SEMI_OPEN, 1);
        scoreWeights.set(ScorePatternType.CLOSED, 0);

        Player current = Player.BLACK;

        Score score = new Score(board, scoreWeights);

        while(true) {
            ui.displayBoard(board, scoreWeights);
            int[] turn = ui.getUserXY();
            board.setPlayer(turn[0], turn[1], current);
            score.calculateBoard(current);
        }





      //  while (true) {
         //   int[] move = ui.readMove(board, current);
        //    board.setPlayer(move[0], move[1], current);

       //     current = (current == Player.BLACK) ? Player.WHITE : Player.BLACK;

         //   score.calculateBoard(current);
      //  }
    }
}