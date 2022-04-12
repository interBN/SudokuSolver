package helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validation {

    public static boolean isFinished(int[][] board) {
        return Arrays.stream(board).flatMapToInt(Arrays::stream).noneMatch(num -> num == 0) && isLineValid(board);
    }

    public static boolean isValid(int[][] board) {
        return isLineValid(board) && isBlockValid(board);
    }

    @SuppressWarnings({"DuplicatedCode"})
    private static boolean isLineValid(int[][] board) {
        for (int y = 0; y < 9; y++) {
            Set<Integer> set = new HashSet<>();
            for (int x = 0; x < 9; x++) {
                if (board[y][x] != 0 && !set.add(board[y][x])) {
                    return false;
                }
            }
        }
        for (int x = 0; x < 9; x++) {
            Set<Integer> set = new HashSet<>();
            for (int y = 0; y < 9; y++) {
                if (board[y][x] != 0 && !set.add(board[y][x])) {
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
                Set<Integer> set = new HashSet<>();
                for (int y = upY; y <= downY; y++) {
                    for (int x = upX; x <= downX; x++) {
                        if (board[y][x] != 0 && !set.add(board[y][x])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}