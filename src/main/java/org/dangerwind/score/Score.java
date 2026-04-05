package org.dangerwind.score;

import org.dangerwind.Board;
import org.dangerwind.Player;
import org.dangerwind.score.ScoreWeights;

import java.util.HashMap;

public class Score {

    private static final int X_POSITION = 0;
    private static final int Y_POSITION = 1;

    Board board;
    ScoreWeights scoreWeightsAtack;
    ScoreWeights scoreWeightsDefense;


    public Score(Board board, ScoreWeights scoreWeightsAtack,  ScoreWeights scoreWeightsDefense) {
        this.board = board;
        this.scoreWeightsAtack = scoreWeightsAtack;
        this.scoreWeightsDefense = scoreWeightsDefense;
    }

    public int getScore(int x, int y, Player player) {
        if (x < 0 || y < 0 || x >= board.getBOARD_WIDTH() || y >= board.getBOARD_HEIGHT()) {
            throw new IllegalArgumentException();
        }
        return board.getScore(x, y, player == Player.PLAYER ? 0 : 1);
    }

    public int calculatePointScore(int x, int y, Player player, ScoreWeights scoreWeights) {
        if (x < 0 || y < 0 || x >= board.getBOARD_WIDTH() || y >= board.getBOARD_HEIGHT()) {
            throw new IllegalArgumentException();
        }

        if (board.getPlayer(x, y) != null) return 0;

// Есть 8 потоков от заданной точки (4 направления) по 2 координаты x, y
// 0, 1 - горизонтальный поток, 2, 3 - вертикальный, 4, 5 - диагональ \, 6, 7 - диагональ /
        int[][] scores = new int[8][2];
        Boolean[] isClosed = new Boolean[8];
        int[] score = new int[8];
// сколько потоков закрылось, 0 - все в работе, 8 - все отработали
        int finishedFlow = 0;

        for  (int i = 0; i < 8; i++) {
            scores[i][X_POSITION] = x;
            scores[i][Y_POSITION] = y;
        }

        while (finishedFlow < 8) {
            for (int i = 0; i < 8; i++) {
                if (isClosed[i] != null) continue;

                int deltaX = 0;
                int deltaY = 0;

                switch (i) {
                    case 0: deltaX = -1; break;
                    case 1: deltaX = 1; break;
                    case 2: deltaY = -1; break;
                    case 3: deltaY = 1; break;
                    case 4: deltaX = -1; deltaY = -1; break;
                    case 5: deltaX = 1; deltaY = 1; break;
                    case 6: deltaX = -1; deltaY = 1; break;
                    case 7: deltaX = 1; deltaY = -1; break;
                }

                scores[i][X_POSITION] += deltaX;
                scores[i][Y_POSITION] += deltaY;

                if (scores[i][X_POSITION] < 0 ||
                        scores[i][Y_POSITION] < 0 ||
                        scores[i][X_POSITION] >= board.getBOARD_WIDTH() ||
                        scores[i][Y_POSITION] >= board.getBOARD_HEIGHT()) {
                    isClosed[i] = true;
                    finishedFlow++;
                    continue;
                }

                Player currentPlayer = board.getPlayer(scores[i][X_POSITION], scores[i][Y_POSITION]);
                if (currentPlayer == null) {
                    isClosed[i] = false;
                    finishedFlow++;
                } else if (currentPlayer != player) {
                    isClosed[i] = true;
                    finishedFlow++;
                } else {
                    score[i]++;
                }
            }
        }
        int finalScore = 0;

        for (int i = 0; i < 4; i++) {
            int intScore = 0;
            int summScore = score[i*2] + score[i*2 + 1] +1;



            if (summScore >= 5) {
               intScore = scoreWeights.get(ScorePatternType.FIVE);
            }

            for (var combination = 4; combination > 0; combination--) {
                if (summScore == combination) {
                    if (!isClosed[i * 2] && !isClosed[i * 2 + 1]) {
                            switch (combination) {
                                case 4:intScore = scoreWeights.get(ScorePatternType.FOUR_OPEN); break;
                                case 3:intScore = scoreWeights.get(ScorePatternType.THREE_OPEN); break;
                                case 2:intScore = scoreWeights.get(ScorePatternType.TWO_OPEN); break;
                                case 1:intScore = scoreWeights.get(ScorePatternType.ONE_OPEN); break;
                                default: intScore = 0; break;
                            }
                    } else {
                        if (isClosed[i * 2] ^ isClosed[i * 2 + 1]) {
                            switch (combination) {
                                case 4: intScore = scoreWeights.get(ScorePatternType.FOUR_SEMI_OPEN); break;
                                case 3: intScore = scoreWeights.get(ScorePatternType.THREE_SEMI_OPEN); break;
                                case 2: intScore = scoreWeights.get(ScorePatternType.TWO_SEMI_OPEN); break;
                                case 1: intScore = scoreWeights.get(ScorePatternType.ONE_SEMI_OPEN); break;
                                default: intScore = 0; break;
                            }
                        } else {
                            intScore = 0;
                        }
                    }
                }
            }

            if (finalScore < intScore) {
                finalScore = intScore;

            }

        }
        return finalScore;
    }

    public void calculateBoard(Player player) {
        Player enemy = player == Player.PLAYER ? Player.ENEMY : Player.PLAYER;

        for (int x = 0; x < board.getBOARD_WIDTH(); x++) {
            for (int y = 0; y < board.getBOARD_HEIGHT(); y++) {
                if (board.getPlayer(x, y) == null) {
                    int attackScore = calculatePointScore(x, y, enemy, scoreWeightsAtack);
                    int defenseScore = calculatePointScore(x, y, player, scoreWeightsDefense);
                    board.setScore(x, y, 0, attackScore);
                    board.setScore(x, y, 1, defenseScore);
                }
            }
        }
    }

    public int[] calculatBestTurn() {
        int maxX = -1;
        int maxY = -1;
        int maxScore = -1;

        for(var i = 0; i < board.getBOARD_WIDTH(); i++) {
            for(var j = 0; j < board.getBOARD_HEIGHT(); j++) {
                if (board.getPlayer(i, j) != null) {
                    continue;
                }

                int scr01 = board.getScore(i, j, 0);
                int scr02 = board.getScore(i, j, 1);
                scr01 = Math.max(scr01, scr02);

                if (maxScore < scr01) {
                    maxX = i;
                    maxY = j;
                    maxScore = scr01;
                }

            }
        }
        return new int[]{maxX, maxY};
    }




}
