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


        Score score = new Score(board, scoreWeightsAtack, scoreWeightsDefense);


        // просто для отладки поставил несколько камней на поле куда нельзя ставить фигуры
        // board.setPlayer(8, 9, Player.WALL);
       // board.setPlayer(8, 10, Player.WALL);
       // board.setPlayer(9, 9, Player.WALL);

        while(true) {
// рисует игровое поле
            ui.displayBoard(board, scoreWeightsAtack,  scoreWeightsDefense);
// спрашивает игрока о его ходе, игрок пишет его ход
            int[] turn;
            do {
                turn = ui.getUserXY(Player.PLAYER);
                if (board.getPlayer(turn[0] - 1, turn[1] - 1) != null) {
                    System.out.println("Поле уже занято, выберите другое");
                }
            }
            while(board.getPlayer(turn[0] - 1, turn[1] - 1) != null);
            System.out.println("Ваш ход X=" + turn[0] + " Y=" + turn[1] + " score=" + board.getScore(turn[0] - 1, turn[1] - 1, 1));


            if (board.getScore(turn[0] - 1, turn[1] - 1, 1) >= scoreWeightsDefense.get(ScorePatternType.FIVE)) {
                ui.displayBoard(board, scoreWeightsAtack,  scoreWeightsDefense);
                System.out.println("Вы победили!");
                break;
            }

// ставится фишка игрока на поле
            board.setPlayer(turn[0] - 1, turn[1] - 1, Player.PLAYER);
// рассчитываются коэффициенты каждого хода
            score.calculateBoard(Player.PLAYER);

// компьютер рассчитывает куда ему пойти
            int[] enemyTurn = score.calculatBestTurn();
// ставит свою фишку
            board.setPlayer(enemyTurn[0], enemyTurn[1], Player.ENEMY);
            System.out.println("Мой ход X=" + (enemyTurn[0]+1) + " Y=" + (enemyTurn[1]+1) + " score=" + board.getScore(enemyTurn[0], enemyTurn[1], 0));
            if (board.getScore(enemyTurn[0], enemyTurn[1], 0) >= scoreWeightsAtack.get(ScorePatternType.FIVE)) {
                ui.displayBoard(board, scoreWeightsAtack,  scoreWeightsDefense);
                System.out.println("Вы проиграли!");
                break;
            }
// пересчет поля и коэффициентов
            score.calculateBoard(Player.PLAYER);
        }




    }
}