package org.dangerwind;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
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