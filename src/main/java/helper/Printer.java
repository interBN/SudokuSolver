package helper;

public class Printer {

    public static void print(int[][] board) {
        print(board, -1, -1);
    }

    public static void print(int[][] arr, int x, int y) {// prints the sudoku
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("----------|---------|----------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print("|");
                }
                if (arr[i][j] == 0) {
                    if (i == y && j == x) {
                        System.out.print(" " + "\u001B[31m" + "-" + "\u001B[0m" + " ");
                    } else {
                        System.out.print(" " + "-" + " ");
                    }
                } else {
                    if (i == y && j == x) {
                        System.out.print(" " + "\u001B[31m" + arr[i][j] + "\u001B[0m" + " ");
                    } else {
                        System.out.print(" " + arr[i][j] + " ");
                    }
                }
            }
            if (x == 8) {
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void comparePrint(int[][] original, int[][] result) {// prints the sudoku
        int found = 0;
        int open = 0;
        System.out.println("Comparison:");
        for (int y = 0; y < 9; y++) {
            if (y % 3 == 0 && y != 0) {
                System.out.println("----------|---------|----------");
            }
            for (int x = 0; x < 9; x++) {
                if (x % 3 == 0) {
                    System.out.print("|");
                }
                int originalNum = original[y][x];
                int resultNum = result[y][x];
                if (originalNum != resultNum) {
                    System.out.print(" " + "\u001B[31m" + resultNum + "\u001B[0m" + " ");
                    found++;
                } else {
                    if (resultNum == 0) {
                        System.out.print(" " + "\u001B[43m" + "-" + "\u001B[0m" + " ");
                        open++;
                    } else {
                        System.out.print(" " + resultNum + " ");
                    }
                }
                if (x == 8) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.println("found = " + found);
        System.out.println("open = " + open);
    }

    public static void printAndMarkPos(int[][] board, int x, int y) {
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

    public static void printLine() {
        System.out.println("###############################");
    }

}
