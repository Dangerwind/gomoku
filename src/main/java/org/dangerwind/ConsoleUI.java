package org.dangerwind;

import org.dangerwind.score.ScorePatternType;
import org.dangerwind.score.ScoreWeights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final String RESET = "\u001B[0m";
    private static final String[] COLORS_BRIGHT = {
            "\u001B[38;2;0;200;0m",      // 0 - глубокий зелёный
            "\u001B[38;2;80;215;0m",     // 1
            "\u001B[38;2;140;225;0m",    // 2
            "\u001B[38;2;190;235;0m",    // 3
            "\u001B[38;2;230;240;0m",    // 4
            "\u001B[38;2;255;220;0m",    // 5 - тёплый жёлтый
            "\u001B[38;2;255;190;0m",    // 6
            "\u001B[38;2;255;145;0m",    // 7
            "\u001B[38;2;255;90;0m",     // 8
            "\u001B[38;2;220;40;40m"     // 9 - мягкий красный
    };

    private static final String[] COLORS_SEMI = {
            "\u001B[38;2;0;90;0m",       // 0 - тёмно-зелёный
            "\u001B[38;2;25;100;0m",     // 1
            "\u001B[38;2;50;110;0m",     // 2
            "\u001B[38;2;75;120;0m",     // 3
            "\u001B[38;2;100;110;0m",    // 4
            "\u001B[38;2;120;100;0m",    // 5 - тёмный оливковый
            "\u001B[38;2;130;80;0m",     // 6
            "\u001B[38;2;135;60;0m",     // 7
            "\u001B[38;2;140;40;0m",     // 8
            "\u001B[38;2;120;25;25m"     // 9 - тёмно-красный
    };

    private static final String[] COLORS = {
            "\u001B[38;2;35;60;35m",
            "\u001B[38;2;45;68;35m",
            "\u001B[38;2;58;76;40m",
            "\u001B[38;2;72;82;48m",
            "\u001B[38;2;88;86;58m",
            "\u001B[38;2;98;82;60m",
            "\u001B[38;2;104;74;58m",
            "\u001B[38;2;108;62;52m",
            "\u001B[38;2;108;50;46m",
            "\u001B[38;2;96;40;40m"
    };

    private static final String[] GRAYS = {
            "\u001B[38;2;200;200;200m",
            "\u001B[38;2;160;160;160m",
            "\u001B[38;2;120;120;120m",
            "\u001B[38;2;80;80;80m",
            "\u001B[38;2;60;60;60m"
    };

    private void clearDisplay() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    // ┌ ┬ ┐ ├ ┼ ┤ └ ┴ ┘ │ ─
    public void displayBoard(Board board, ScoreWeights scoreWeights) {

        clearDisplay();
        System.out.print("\u001B[38;2;255;0;0m" + "                    Attack field" + RESET);
        System.out.print("         ");
        System.out.print("\u001B[38;2;0;255;0m" + "             Dependency field" + RESET);
        System.out.println("");


        System.out.print("  ");
        for (int i = 0; i < board.getBOARD_WIDTH(); i++) {
            if ((i + 1)/10 == 0) System.out.print("  ");
            else System.out.print(" " + (i + 1)/10);
        }
        System.out.print("      ");
        System.out.print("  ");
        for (int i = 0; i < board.getBOARD_WIDTH(); i++) {
            if ((i + 1)/10 == 0) System.out.print("  ");
            else System.out.print(" " + (i + 1)/10);
        }



        System.out.print("\n  ");
        for (int i = 0; i < board.getBOARD_WIDTH(); i++) {
            System.out.print(" " + (i + 1) % 10);
        }
        System.out.print("        ");
        for (int i = 0; i < board.getBOARD_WIDTH(); i++) {
            System.out.print(" " + (i + 1) % 10);
        }


        System.out.println("");


        for (int y = 0; y < board.getBOARD_HEIGHT(); y++) {

            for (int x = 0; x < board.getBOARD_WIDTH(); x++) {

                if (x == 0) {
                    System.out.print(String.format("%02d ", y+1));
                }

                Player player = board.getPlayer(x, y);
                if (player == null) {
                    int score = board.getScore(x, y, 0);
                    int color = 0;

                    if  (score == scoreWeights.get(ScorePatternType.FIVE)) color = 8;
                    if  (score == scoreWeights.get(ScorePatternType.FOUR_OPEN)) color = 7;
                    if  (score == scoreWeights.get(ScorePatternType.FOUR_SEMI_OPEN)) color = 6;
                    if  (score == scoreWeights.get(ScorePatternType.THREE_OPEN)) color = 5;
                    if  (score == scoreWeights.get(ScorePatternType.THREE_SEMI_OPEN)) color = 4;
                    if  (score == scoreWeights.get(ScorePatternType.TWO_OPEN)) color = 3;
                    if  (score == scoreWeights.get(ScorePatternType.TWO_SEMI_OPEN)) color = 2;
                    if  (score == scoreWeights.get(ScorePatternType.ONE_OPEN)) color = 1;

                    System.out.print(COLORS[color] + score + " " + RESET);

                } else if (player == Player.BLACK) {
                    System.out.print("\u001B[38;2;255;0;0m" + "● " + RESET);
                } else {
                    System.out.print("\u001B[38;2;0;0;255m" + "● " + RESET); // ○
                }
            }

            System.out.print("     ");

            for (int x = 0; x < board.getBOARD_WIDTH(); x++) {

                if (x == 0) {
                    System.out.print(String.format("%02d ", y+1));
                }

                Player player = board.getPlayer(x, y);
                if (player == null) {
                    int score = board.getScore(x, y, 1);
                    int color = 0;

                    if  (score == scoreWeights.get(ScorePatternType.FIVE)) color = 8;
                    if  (score == scoreWeights.get(ScorePatternType.FOUR_OPEN)) color = 7;
                    if  (score == scoreWeights.get(ScorePatternType.FOUR_SEMI_OPEN)) color = 6;
                    if  (score == scoreWeights.get(ScorePatternType.THREE_OPEN)) color = 5;
                    if  (score == scoreWeights.get(ScorePatternType.THREE_SEMI_OPEN)) color = 4;
                    if  (score == scoreWeights.get(ScorePatternType.TWO_OPEN)) color = 3;
                    if  (score == scoreWeights.get(ScorePatternType.TWO_SEMI_OPEN)) color = 2;
                    if  (score== scoreWeights.get(ScorePatternType.ONE_OPEN)) color = 1;

                    System.out.print(COLORS[color] + score + " " + RESET);

                } else if (player == Player.BLACK) {
                    System.out.print("\u001B[38;2;255;0;0m" + "● " + RESET);
                } else {
                    System.out.print("\u001B[38;2;0;0;255m" + "● " + RESET); // ○
                }
            }


            System.out.println();
        }


    }

    public int[] getUserXY() throws IOException {
        System.out.println("Введите координаты хода (x y): ");
        String input = reader.readLine();
        String[] parts = input.trim().split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid input. Please enter two numbers separated by space.");
        }
        int x = Integer.parseInt(parts[0]) - 1;
        int y = Integer.parseInt(parts[1]) - 1;
        return new int[]{x, y};



    }


}
