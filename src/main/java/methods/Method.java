package methods;

import helper.Validation;

import java.util.Date;

public abstract class Method {

    final String name;
    final int[][] original;
    final boolean print;
    public int iteration;
    public long duration;
    int[][] result;

    public Method(int[][] original, String name) {
        this(original, name, false);
    }

    public Method(int[][] original, String name, boolean print) {
        this.name = name;
        this.original = original;
        this.result = original;
        this.iteration = 0;
        this.duration = -1;
        this.print = print;
    }

    public static String comparePrint(int[][] original, int[][] result) { // prints the sudoku
//        int found = 0;
//        int open = 0;
        StringBuilder returner = new StringBuilder();
        for (int y = 0; y < 9; y++) {
            if (y % 3 == 0 && y != 0) {
                returner.append("----------|---------|----------" + "\n");
            }
            for (int x = 0; x < 9; x++) {
                if (x % 3 == 0) {
                    returner.append("|");
                }
                int originalNum = original[y][x];
                int resultNum = result[y][x];
                if (originalNum != resultNum) {
                    returner.append(" " + "\u001B[31m").append(resultNum).append("\u001B[0m").append(" ");
//                    found++;
                } else {
                    if (resultNum == 0) {
                        returner.append(" " + "\u001B[43m" + "-" + "\u001B[0m" + " ");
//                        open++;
                    } else {
                        returner.append(" ").append(resultNum).append(" ");
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
//        System.out.println("found = " + found);
//        System.out.println("open = " + open);
        return returner.toString();
    }

    abstract int[][] go2();

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

    public int[][] go() {

        boolean finished = Validation.isFinished(this.original);
        if (finished) return this.original;

        Date startDate = new Date();
        printLine();
        System.out.println("\u001B[34m" + "Run " + name + "\u001B[0m");

        int[][] result = go2();
        Date endDate = new Date();
        duration = (int) ((endDate.getTime() - startDate.getTime()));
        return result;
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
        return name + "\n" +
                comparePrint(original, result);
    }
}