package org.dangerwind;

public class Board {
    private final int BOARD_WIDTH = 15;
    private final int BOARD_HEIGHT = 15;

    private Player[][] board;
    private int[][][] score;

    public Board() {
        board = new Player[BOARD_WIDTH][BOARD_HEIGHT];
        score = new int[BOARD_WIDTH][BOARD_HEIGHT][2];
    }

    public boolean setPlayer(int x, int y, Player player) {
        if  (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            throw new IllegalArgumentException();
        }
        if (board[x][y] != null) {
            return false;
        }
        board[x][y] = player;
        return true;
    }

    public Player getPlayer(int x, int y) {
        if  (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
           throw new IllegalArgumentException();
        }
        return board[x][y];
    }



    public int getScore(int x, int y, int level ) {
        if  (x < 0 || y < 0 || x >= board.length || y >= board[0].length || level < 0 || level > 1) {
            throw new IllegalArgumentException();
        }
        return score[x][y][level];
    }

    public void setScore(int x, int y, int level, int score) {
        if  (x < 0 || y < 0 || x >= board.length || y >= board[0].length || level < 0 || level > 1) {
            throw new IllegalArgumentException();
        }
        this.score[x][y][level] = score;
    }




    public int getBOARD_WIDTH() {
        return BOARD_WIDTH;
    }
    public int getBOARD_HEIGHT() {
        return BOARD_HEIGHT;
    }
}
