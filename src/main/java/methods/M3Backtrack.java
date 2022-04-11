package methods;

import helper.Validation;

import java.util.Arrays;

public class M3Backtrack extends Method {

    boolean doPrint;

    public M3Backtrack(int[][] original) {
        this(original, false);
    }

    public M3Backtrack(int[][] original, boolean print) {
        super(original, M3Backtrack.class.getSimpleName(), print);
    }

    @Override
    int[][] solve2() {
        printLine();

        if (!Validation.isValid(super.original)) {
            System.out.println("Something is wrong with the input.");
        }
        int[][] result = solve3(super.original, 0, 0);
        super.result = result;
        return result;
    }

    int[][] solve3(int[][] board, int x, int y) {
        iteration++;
        if (doPrint) {
            comparePrint(original, board);
        }
        board = clone(board);
        int[][] before = clone(board);

        if (Validation.isFinished(board)) {
            return board;
        }
        if (doPrint) printLine();

        println("I'm here: [" + x + "," + y + "]=" + board[y][x]);

        int newX = x + 1;
        int newY = y;
        if (newX > 8) {
            newX = 0;
            newY++;
        }
        if (super.original[y][x] != 0) {
            println("Skip!");
            return solve3(board, newX, newY);
        }
        int[] possible;

        possible = M2Possible.getPossible(board, x, y, doPrint);

        if (possible.length == 0) {
            println("I'm here: [" + x + "," + y + "]=> No possibles: Go back.");
            return before;
        }

        for (int i = 0; i < possible.length; i++) {

            int checkNum = possible[i];
            board[y][x] = checkNum;

            int[][] before2 = clone(board);

            if (i == 0) {
                println("try: " + checkNum + " from: " + Arrays.toString(possible));
            } else {
                println("try next: " + checkNum + " from: " + Arrays.toString(possible));
            }

            int[][] check = solve3(board, newX, newY);

            if (Validation.isFinished(check)) {
                return check;
            }

            boolean isEqual = Arrays.deepEquals(before2, check);
            if (!isEqual) { // seems ok
                return check;
            }

            if (doPrint) printLine();
            println("I'm back here: [" + x + "," + y + "]");
//            if (checkNum != possible[possible.length - 1]) {
//                println("=> try next number");
//            }
        }

        println("Go back 2.");
        return before;
    }
}