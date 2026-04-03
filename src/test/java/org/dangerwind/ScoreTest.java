package org.dangerwind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.dangerwind.Board;
import org.dangerwind.Player;
import org.dangerwind.score.Score;
import org.dangerwind.score.ScorePatternType;
import org.dangerwind.score.ScoreWeights;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    ScoreWeights scoreWeights = new ScoreWeights();

    @BeforeEach
    public void setup() {
        scoreWeights.set(ScorePatternType.FIVE, 100000);
        scoreWeights.set(ScorePatternType.FOUR_OPEN, 10000);
        scoreWeights.set(ScorePatternType.FOUR_SEMI_OPEN, 1000);
        scoreWeights.set(ScorePatternType.THREE_OPEN, 500);
        scoreWeights.set(ScorePatternType.THREE_SEMI_OPEN, 100);
        scoreWeights.set(ScorePatternType.TWO_OPEN, 50);
        scoreWeights.set(ScorePatternType.TWO_SEMI_OPEN, 10);
        scoreWeights.set(ScorePatternType.ONE_OPEN, 5);
        scoreWeights.set(ScorePatternType.ONE_SEMI_OPEN, 1);
        scoreWeights.set(ScorePatternType.CLOSED, 0);
    }

    // 5 в ряд - 100000
    @Test
    public void testScoreFive() {
        Board board = new Board();
       // ScoreWeights scoreWeights = new ScoreWeights();
        Score scoreCalculator = new Score(board, scoreWeights);

       // 4 открытых и будет 5
        board.setPlayer(7, 6, Player.BLACK);
        board.setPlayer(7, 7, Player.BLACK);
        board.setPlayer(7, 8, Player.BLACK);
        board.setPlayer(7, 10, Player.BLACK);

        int result = scoreCalculator.getScore(7, 9, Player.BLACK);

        assertEquals(result, scoreWeights.get(ScorePatternType.FIVE));
    }


    // 2 в ряд открытых -
    @Test
    public void testScoreTwo() {
        System.out.println("testScoreTwo");
        Board board = new Board();
       // ScoreWeights scoreWeights = new ScoreWeights();
        Score scoreCalculator = new Score(board, scoreWeights);

        // 1 открытая и будет 2
        board.setPlayer(7, 8, Player.BLACK);

        int result = scoreCalculator.getScore(7, 9, Player.BLACK);

        assertEquals(result, scoreWeights.get(ScorePatternType.TWO_OPEN));
    }


// 2 со всех сторон закрытых
    @Test
    public void testScoreCloseTwo() {
        Board board = new Board();
        Score scoreCalculator = new Score(board, scoreWeights);

        // 1 открытая и будет 2
        board.setPlayer(7, 7, Player.WHITE);
        board.setPlayer(7, 10, Player.WHITE);

        board.setPlayer(6, 7, Player.WHITE);
        board.setPlayer(6, 8, Player.WHITE);
        board.setPlayer(6, 9, Player.WHITE);
        board.setPlayer(6, 10, Player.WHITE);

        board.setPlayer(8, 7, Player.WHITE);
        board.setPlayer(8, 8, Player.WHITE);
        board.setPlayer(8, 9, Player.WHITE);
        board.setPlayer(8, 10, Player.WHITE);

        board.setPlayer(7, 8, Player.BLACK);

        int result = scoreCalculator.getScore(7, 9, Player.BLACK);

        assertEquals(0, scoreWeights.get(ScorePatternType.CLOSED));
    }

}
