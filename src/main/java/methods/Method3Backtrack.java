package methods;

import helper.Helper;
import helper.Printer;
import helper.Validation;

import java.util.Arrays;

public class Method3Backtrack extends Method {

    public Method3Backtrack(int[][] original) {
        super(original);
    }

    public int[][] check(int[][] board) {
        Printer.printLine();
        System.out.println("Method3Backtrack.check");

        if (!Validation.isValid(board)) {
            System.out.println("Something is wrong with the input.");
        }
//        new Method2Possible(board).go();
        return check(board, 0, 0);
    }

    int[][] check(int[][] board, int x, int y) {
        board = Helper.clone(board);
        int[][] before = Helper.clone(board);

        if (x > 8 || y > 8) {
            Printer.print(board);
            return board;
        }
        if (Validation.isFinished(board)) {
            System.out.println("Finished:");
            Printer.print(board);
            return board;
        }
        Printer.printLine();
        System.out.println("pos x=" + x + " y=" + y + " num=" + board[y][x]);
        int newX = x + 1;
        int newY = y;
        if (newX > 8) {
            newX = 0;
            newY++;
        }
        if (super.original[y][x] != 0) {
            System.out.println("Skip!");
            return check(board, newX, newY);
        }
        int[] possible;

        possible = Method2Possible.getPossible(board, x, y);

        if (possible.length == 0) {
            return before;
        }

//        if (possible.length == 1) {
//            board[y][x] = possible[0];
//            System.out.println("found: " + possible[0]);
//            int[][] check = check(board, newX, newY);
//            boolean isEqual = Arrays.deepEquals(before, check);
//            if (isEqual) {
//                return before;
//            } else {
//                return check;
//            }
//        }

        for (int checkNum : possible) {
            board[y][x] = checkNum;
            System.out.println("I'm here: [" + x + "," + y + "]=> try: " + checkNum);
            int[][] check = check(board, newX, newY);
            boolean isEqual = Arrays.deepEquals(before, check);
            if (isEqual) {
                return check;
            }
            System.out.println("I'm back here: [" + x + "," + y + "]");
            if (checkNum != possible[possible.length - 1]) {
                System.out.println("=> try next number");
            }
        }

        System.out.println("RETURN fail");
        return before;
    }

    @Override
    public int[][] go() {
        return check(super.original);
    }
}
