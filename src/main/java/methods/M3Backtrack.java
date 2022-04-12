package methods;

import helper.Validation;

import java.util.Arrays;

public class M3Backtrack extends Method {

    public M3Backtrack(int[][] original) {
        this(original, false);
    }

    public M3Backtrack(int[][] original, boolean print) {
        super(original, M3Backtrack.class.getSimpleName(), print);
    }

    @Override
    int[][] solve2() {
        this.printLine();
        int[][] result = this.solve3(this.original, 0, 0);
        super.result = result;
        return result;
    }

    int[][] solve3(int[][] board, int x, int y) {
        this.iteration++;
        board = this.clone(board);
        int[][] before = this.clone(board);

        if (Validation.isFinished(board)) {
            this.isComplete = true;
            return board;
        }
        this.printLine();

        this.println("I'm here: (" + x + "," + y + ") = " + board[y][x]);

        int newX = x + 1;
        int newY = y;
        if (newX > 8) {
            newX = 0;
            newY++;
        }
        if (this.original[y][x] != 0) {
            this.println("Skip!");
            return this.solve3(board, newX, newY);
        }
        int[] possible = M2Possible.getPossible(board, x, y, this.print);

        if (possible.length == 0) {
            this.println("I'm here: (" + x + "," + y + ") => No possibles: Go back.");
            return before;
        }

        for (int i = 0; i < possible.length; i++) {

            int checkNum = possible[i];
            board[y][x] = checkNum;

            int[][] before2 = this.clone(board);

            if (i == 0) {
                this.println("try: " + checkNum + " from " + Arrays.toString(possible));
            } else {
                this.println("try next: " + checkNum + " from " + Arrays.toString(possible));
            }

            int[][] check = this.solve3(board, newX, newY);

            if (Validation.isFinished(check)) {
                this.isComplete = true;
                return check;
            }

            if (!Arrays.deepEquals(before2, check)) {
                return check;
            }

            this.printLine();
            this.println("I'm back here: [" + x + "," + y + "]");
        }

        this.println("End of method => Go back.");
        return before;
    }
}