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
                    System.out.print(" " +
                            (i == y && j == x ? "\u001B[31m" + "-" + "\u001B[0m" : "-") +
                            " ");
                } else {
                    System.out.print(" " +
                            (i == y && j == x ? "\u001B[31m" + arr[i][j] + "\u001B[0m" : arr[i][j]) +
                            " ");
                }
            }
            System.out.println();
        }
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
