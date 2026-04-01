package org.dangerwind;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final PrintStream out;

    public ConsoleUI() {
        this(System.in, System.out);
    }

    public ConsoleUI(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    public void printBoard(Board board) {
        out.print("   ");
        for (int x = 0; x < 15; x++) {
            out.printf("%2d ", x);
        }
        out.println();
        for (int y = 0; y < 15; y++) {
            out.printf("%2d ", y);
            for (int x = 0; x < 15; x++) {
                Player p = board.getPlayer(x, y);
                char symbol = (p == null) ? '.' : (p == Player.BLACK) ? 'X' : 'O';
                out.printf("%2c ", symbol);
            }
            out.println();
        }
    }

    public void printCurrentTurn(Player player) {
        out.println((player == Player.BLACK ? "Black" : "White") + "'s turn");
    }

    public void printWinner(Player player) {
        out.println((player == Player.BLACK ? "Black" : "White") + " wins!");
    }

    public void printDraw() {
        out.println("Draw!");
    }

    public void printCellOccupied() {
        out.println("That cell is already occupied. Try again.");
    }

    public void printInvalidInput() {
        out.println("Invalid input. Enter two numbers: x y (0-14)");
    }

    public int[] readMove() {
        out.print("Enter x y: ");
        if (!scanner.hasNextLine()) return null;
        String[] parts = scanner.nextLine().trim().split("\\s+");
        if (parts.length != 2) return null;
        try {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            if (x < 0 || x > 14 || y < 0 || y > 14) return null;
            return new int[]{x, y};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void close() {
        scanner.close();
    }
}
