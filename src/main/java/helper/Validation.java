package helper;

import java.util.HashSet;
import java.util.Set;

public class Validation {

    public static boolean isFinished(int[][] board) {
        for (int[] line : board) {
            for (int num : line) {
                if (num == 0) {
                    return false;
                }
            }
        }
        return isValid(board);
    }

    public static boolean isValid(int[][] board) {
        return isLineValid(board) && isBlockValid(board);
    }

    private static boolean isLineValid(int[][] board) {
        for (int[] line : board) {
            Set<Integer> tmp = new HashSet<>();
            for (int num : line) {
                if (num == 0) {
                    continue;
                }
                boolean isUnique = tmp.add(num);
                if (!isUnique) {
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
                boolean isUnique = tmp.add(num);
                if (!isUnique) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isBlockValid(int[][] board) {
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