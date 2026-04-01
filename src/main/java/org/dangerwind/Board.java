package org.dangerwind;

class Board {
    private final int BOARD_WIDTH = 15;
    private final int BOARD_HEIGHT = 15;

    private Player[][] board;

    public Board() {
        board = new Player[BOARD_WIDTH][BOARD_HEIGHT];
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
}
