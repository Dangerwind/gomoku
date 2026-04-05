package org.dangerwind;

import org.dangerwind.score.Score;
import org.dangerwind.score.ScoreWeights;
import org.dangerwind.score.ScorePatternType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Board board = new Board();
        ScoreWeights scoreWeightsAtack = new ScoreWeights();
        ScoreWeights scoreWeightsDefense = new ScoreWeights();
        ConsoleUI ui = new ConsoleUI();


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


        Player current = Player.PLAYER;

        Score score = new Score(board, scoreWeightsAtack, scoreWeightsDefense);

        while(true) {
// рисует игровое поле
            ui.displayBoard(board, scoreWeightsAtack,  scoreWeightsDefense);
// спрашивает игрока о его ходе, игрок пишет его ход
            int[] turn;
            do {
                turn = ui.getUserXY(current);
                if (board.getPlayer(turn[0] - 1, turn[1] - 1) != null) {
                    System.out.println("Поле уже занято, выберите другое");
                }
            }
            while(board.getPlayer(turn[0] - 1, turn[1] - 1) != null);

// ставится фишка игрока на поле
            board.setPlayer(turn[0] - 1, turn[1] - 1, current);
// рассчитываются коэффициенты каждого хода
            score.calculateBoard(current);

// компьютер рассчитывает куда ему пойти
            int[] enemyTurn = score.calculatBestTurn();
// ставит свою фишку
            board.setPlayer(enemyTurn[0], enemyTurn[1], Player.ENEMY);
            System.out.println("Мой ход X=" + (enemyTurn[0]+1) + " Y=" + (enemyTurn[1]+1));
// пересчет поля и коэффициентов
            score.calculateBoard(current);
        }




    }
}