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

    ScoreWeights scoreWeightsAtack = new ScoreWeights();
    ScoreWeights scoreWeightsDefense = new ScoreWeights();

    @BeforeEach
    public void setup() {
        scoreWeightsAtack.set(ScorePatternType.FIVE, 100000);
        scoreWeightsAtack.set(ScorePatternType.FOUR_OPEN, 10000);
        scoreWeightsAtack.set(ScorePatternType.FOUR_SEMI_OPEN, 1000);
        scoreWeightsAtack.set(ScorePatternType.THREE_OPEN, 500);
        scoreWeightsAtack.set(ScorePatternType.THREE_SEMI_OPEN, 100);
        scoreWeightsAtack.set(ScorePatternType.TWO_OPEN, 50);
        scoreWeightsAtack.set(ScorePatternType.TWO_SEMI_OPEN, 10);
        scoreWeightsAtack.set(ScorePatternType.ONE_OPEN, 5);
        scoreWeightsAtack.set(ScorePatternType.ONE_SEMI_OPEN, 1);
        scoreWeightsAtack.set(ScorePatternType.CLOSED, 0);


        scoreWeightsDefense.set(ScorePatternType.FIVE, 120000);
        scoreWeightsDefense.set(ScorePatternType.FOUR_OPEN, 15000);
        scoreWeightsDefense.set(ScorePatternType.FOUR_SEMI_OPEN, 2000);
        scoreWeightsDefense.set(ScorePatternType.THREE_OPEN, 700);
        scoreWeightsDefense.set(ScorePatternType.THREE_SEMI_OPEN, 150);
        scoreWeightsDefense.set(ScorePatternType.TWO_OPEN, 50);
        scoreWeightsDefense.set(ScorePatternType.TWO_SEMI_OPEN, 10);
        scoreWeightsDefense.set(ScorePatternType.ONE_OPEN, 5);
        scoreWeightsDefense.set(ScorePatternType.ONE_SEMI_OPEN, 1);
        scoreWeightsDefense.set(ScorePatternType.CLOSED, 0);
    }

    // 5 в ряд - 100000
    @Test
    public void testScoreFive() {
        Board board = new Board();
       // ScoreWeights scoreWeights = new ScoreWeights();
        Score scoreCalculator = new Score(board, scoreWeightsAtack, scoreWeightsDefense);

       // 4 открытых и будет 5
        board.setPlayer(7, 6, Player.PLAYER);
        board.setPlayer(7, 7, Player.PLAYER);
        board.setPlayer(7, 8, Player.PLAYER);
        board.setPlayer(7, 10, Player.PLAYER);

        scoreCalculator.calculateBoard(Player.PLAYER);

        int result = scoreCalculator.getScore(7, 9, Player.PLAYER);

        assertEquals(result, scoreWeightsAtack.get(ScorePatternType.FIVE));
    }


    // 2 в ряд открытых -
    @Test
    public void testScoreTwo() {
        System.out.println("testScoreTwo");
        Board board = new Board();
       // ScoreWeights scoreWeights = new ScoreWeights();
        Score scoreCalculator = new Score(board, scoreWeightsAtack, scoreWeightsDefense);

        // 1 открытая и будет 2
        board.setPlayer(7, 8, Player.PLAYER);
        scoreCalculator.calculateBoard(Player.PLAYER);

        int result = scoreCalculator.getScore(7, 9, Player.PLAYER);

        assertEquals(result, scoreWeightsAtack.get(ScorePatternType.TWO_OPEN));
    }


// 2 со всех сторон закрытых
    @Test
    public void testScoreCloseTwo() {
        Board board = new Board();
        Score scoreCalculator = new Score(board, scoreWeightsAtack, scoreWeightsDefense);

        // 1 открытая и будет 2
        board.setPlayer(7, 7, Player.ENEMY);
        board.setPlayer(7, 10, Player.ENEMY);

        board.setPlayer(6, 7, Player.ENEMY);
        board.setPlayer(6, 8, Player.ENEMY);
        board.setPlayer(6, 9, Player.ENEMY);
        board.setPlayer(6, 10, Player.ENEMY);

        board.setPlayer(8, 7, Player.ENEMY);
        board.setPlayer(8, 8, Player.ENEMY);
        board.setPlayer(8, 9, Player.ENEMY);
        board.setPlayer(8, 10, Player.ENEMY);

        board.setPlayer(7, 8, Player.PLAYER);

        int result = scoreCalculator.getScore(7, 9, Player.PLAYER);

        assertEquals(result, scoreWeightsAtack.get(ScorePatternType.CLOSED));
    }

}
