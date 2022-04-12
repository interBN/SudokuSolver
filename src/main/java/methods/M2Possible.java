package methods;

import helper.Validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class M2Possible extends Method {

    public M2Possible(int[][] original) {
        this(original, false);
    }

    public M2Possible(int[][] original, boolean print) {
        super(original, M2Possible.class.getSimpleName(), print);
    }

    static int[] getPossible(int[][] board, int x, int y, boolean print) {
        if (board[y][x] != 0) {
            return new int[0];
        }
        int[] possibleLine = getPossibleCross(board, x, y);
        int[] possibleBlock = getPossibleBlock(board, x, y);
        int[] intersection = Arrays.stream(possibleLine)
                .distinct()
                .filter(j -> Arrays.stream(possibleBlock).anyMatch(i -> i == j))
                .toArray();
        if (print) {
            String coordinate = "(" + x + "," + y + ")";
            System.out.println(coordinate + ": possibleLine");
            System.out.println(Arrays.toString(possibleLine));
            System.out.println(coordinate + ": possibleBlock");
            System.out.println(Arrays.toString(possibleBlock));
            System.out.println(coordinate + ": possible");
            System.out.println(Arrays.toString(intersection));
        }
        return intersection;
    }

    static int[] getPossibleCross(int[][] board, int x, int y) {
        if (board[y][x] != 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
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
            set.add(i);
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
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

    @Override
    int[][] solve2() {
        this.result = this.solve3(this.original);
        return this.result;
    }

    int[][] solve3(int[][] board) {
        if (Validation.isFinished(board)) {
            this.isComplete = true;
            this.println("Method2Possible.solve3: Return finished result. Iterations: " + this.iteration);
            return board;
        }
        board = this.clone(board);

        this.iteration++;

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[y][x] == 0) {
                    this.printLine();
                    int[] possible = getPossible(board, x, y, this.print);
                    if (possible.length == 1) {
                        board[y][x] = possible[0];
                        this.println("\u001B[32m" + "found: " + possible[0] + "\u001B[0m");
                        this.printAndMarkPos(board, x, y);
                        return this.solve3(board);
                    }
                }
            }
        }
        this.printLine();
        this.println("Method2Possible.solve3: Return unfinished result. Iterations: " + this.iteration);
        return board;
    }
}