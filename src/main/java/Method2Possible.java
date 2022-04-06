import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Method2Possible {
    static void addObvious(int[][] board) {
        if (Helper.isFinished(board)) {
            Helper.printLine();
            System.out.println("Finished result:");
            Helper.print(board);
            return;
        }
        Helper.printLine();
        System.out.println("Sudoku.addObvious");
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int num = board[y][x];
                if (num != 0) {
                    continue;
                }
                Helper.printLine();
                int[] possible = getPossible(board, x, y);
                if (possible.length == 1) {
                    board[y][x] = possible[0];
                    System.out.println("found: " + possible[0]);
                    Method1Shadow.print(board, x, y);
                    addObvious(board);
                    return;
                }
            }
        }
        System.out.println("Unfinished result:");
        Helper.print(board);
    }

    static int[] getPossible(int[][] board, int x, int y) {
        if (board[y][x] != 0) {
            return new int[0];
        }
        int[] possibleLine = getPossibleLine(board, x, y);
        int[] possibleBlock = getPossibleBlock(board, x, y);
        int[] intersection = Arrays.stream(possibleLine)
                .distinct()
                .filter(j -> Arrays.stream(possibleBlock).anyMatch(i -> i == j))
                .toArray();
        //noinspection ConstantConditions
        if (true) {
            System.out.println("I'm here: [" + x + "," + y + "]=" + board[y][x]);
            System.out.println("Sudoku.possibleLine");
            System.out.println(Arrays.toString(possibleLine));
            System.out.println("Sudoku.possibleBlock");
            System.out.println(Arrays.toString(possibleBlock));
            System.out.println("Sudoku.possible");
            System.out.println(Arrays.toString(intersection));
        }
//        if (intersection.length == 0) {
//            Helper.print(board, x, y);
//            throw new Exception("Sonmething is wrong here!");
//        }
        return intersection;
    }

    static int[] getPossibleLine(int[][] board, int x, int y) {
        if (board[y][x] != 0) {
            return new int[0];
        }
        Set<Integer> tmp = new HashSet<>();
        outer:
        for (int i = 1; i <= 9; i++) {
            for (int w = 0; w < 9; w++) {
                if (board[w][x] == i) {
                    continue outer;
                }
            }
            for (int h = 0; h < 9; h++) {
                if (board[y][h] == i) {
                    continue outer;
                }
            }
            tmp.add(i);
        }
        return tmp.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] getPossibleBlock(int[][] board, int x, int y) {
        if (board[y][x] != 0) {
            return new int[0];
        }
        int upX = x - (x % 3);
        int upY = y - (y % 3);
        int downX = upX + 2;
        int downY = upY + 2;
        Set<Integer> set = new HashSet<>();
        for (int x2 = upX; x2 <= downX; x2++) {
            for (int y2 = upY; y2 <= downY; y2++) {
                int num = board[y2][x2];
                if (num == 0) {
                    continue;
                }
                set.add(num);
            }
        }
        Set<Integer> result = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toSet());
        result.removeAll(set);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
