import java.util.HashSet;
import java.util.Set;

public class Helper {

    public static int[][] clone(int[][] original) {
        int[][] clone = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            clone[i] = original[i].clone();
        }
        return clone;
    }

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

    public static void printLine() {
        System.out.println("-------------------------------");
    }

    static boolean isFinished(int[][] board) {
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return false;
                }
            }
        }
        return isLineValid(board) && isBlockValid(board);
    }

    static boolean isValid(int[][] board) {
        return isLineValid(board) && isBlockValid(board);
    }

    static boolean isLineValid(int[][] board) {
        for (int[] ints : board) {
            Set<Integer> tmp = new HashSet<>();
            for (int num : ints) {
                if (num == 0) {
                    continue;
                }
                boolean ok = tmp.add(num);
                if (!ok) {
                    return false;
                }
            }
        }
        for (int x = 0; x < 9; x++) {
            Set<Integer> tmp = new HashSet<>();
            for (int y = 0; y < 9; y++) {
                int num = board[y][x];
                if (num == 0) {
                    continue;
                }
                boolean ok = tmp.add(num);
                if (!ok) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isBlockValid(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int upY = i * 3;
                int upX = j * 3;
                int downY = upY + 2;
                int downX = upX + 2;
                Set<Integer> tmp = new HashSet<>();
                for (int y = upY; y <= downY; y++) {
                    for (int x = upX; x <= downX; x++) {
                        int num = board[y][x];
                        if (num == 0) {
                            continue;
                        }
                        boolean isUnique = tmp.add(num);
                        if (!isUnique) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
