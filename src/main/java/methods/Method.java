package methods;

import helper.Validation;

import java.util.Arrays;
import java.util.Date;

public abstract class Method {

    public final String name;
    final int[][] original;
    final boolean print;
    public int iteration;
    public long duration;
    public boolean isComplete;
    int[][] result;

    public Method(int[][] original, String name, boolean print) {
        this.name = name;
        this.original = original;
        this.result = original;
        this.iteration = 0;
        this.duration = 0;
        this.print = print;
        this.isComplete = false;
    }

    abstract int[][] solve2();

    String getComparison(int[][] original, int[][] result) {
        final String SPACE = " ";
        final String YELLOW = "\u001B[43m";
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";
        StringBuilder returner = new StringBuilder();
        for (int y = 0; y < 9; y++) {
            if (y % 3 == 0 && y != 0) {
                returner.append("----------|---------|----------").append("\n");
            }
            for (int x = 0; x < 9; x++) {
                if (x % 3 == 0) {
                    returner.append("|");
                }
                int originalNum = original[y][x];
                int resultNum = result[y][x];
                if (originalNum != resultNum) {
                    returner.append(SPACE).append(RED).append(resultNum).append(RESET).append(SPACE);
                } else {
                    if (resultNum == 0) {
                        returner.append(SPACE).append(YELLOW).append("-").append(RESET).append(SPACE);
                    } else {
                        returner.append(SPACE).append(resultNum).append(SPACE);
                    }
                }
                if (x == 8) {
                    returner.append("|");
                }
            }
            if (y != 8) {
                returner.append("\n");
            }
        }
        return returner.toString();
    }

    int[][] clone(int[][] original) {
        return Arrays.stream(original).map(int[]::clone).toArray(int[][]::new);
    }

    public int[][] solve() {

        Date startDate = new Date();

        if (Validation.isFinished(this.original)) {
            return this.original;
        }

        this.printLine();
        System.out.println("\u001B[34m" + "Run " + this.name + "\u001B[0m");

        int[][] result = this.solve2();
        Date endDate = new Date();
        this.duration = (int) ((endDate.getTime() - startDate.getTime()));
        return result;
    }

    /**
     * 1 -1 -1
     * <p>
     * 2 -1 -1
     * <p>
     * 3 -1 -1
     *
     * @return 6
     */
    public int countOpenFields() {
        int open = 0;
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (this.result[y][x] == 0) {
                    open++;
                }
            }
        }
        return open;
    }

    /**
     * 1 -1 -1
     * <p>
     * 2 -1 -1
     * <p>
     * 3 -1 -1
     *
     * @return 3
     */
    public int countFoundFields() {
        int found = 0;
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (this.result[y][x] != 0 && this.result[y][x] != this.original[y][x]) {
                    found++;
                }
            }
        }
        return found;
    }


    void printAndMarkPos(int[][] board, int x, int y) {
        if (this.print) {
            for (int i = 0; i < 9; i++) {
                if (i % 3 == 0 && i != 0) {
                    System.out.println("----------|---------|----------");
                }
                for (int j = 0; j < 9; j++) {
                    if (j % 3 == 0) System.out.print("|");
                    int num = board[i][j];
                    if (num == 0) {
                        System.out.print(" " + "\u001B[43m" + "-" + "\u001B[0m" + " ");
                    } else if (num < 0) {
                        System.out.print(" " + "\u001B[34m" + "X" + "\u001B[0m" + " ");
                    } else if (i == y && j == x) {
                        System.out.print(" " + "\u001B[31m" + num + "\u001B[0m" + " ");
                    } else {
                        System.out.print(" " + num + " ");
                    }
                    if (j == 8) System.out.print("|");
                }
                System.out.println();
            }
        }
    }

    void print(String s) {
        if (this.print) {
            System.out.print(s);
        }
    }

    void println(String s) {
        if (this.print) {
            System.out.println(s);
        }
    }

    void printLine() {
        if (this.print) {
            System.out.println("###############################");
        }
    }

    @Override
    public String toString() {
        return this.getComparison(this.original, this.result);
    }
}