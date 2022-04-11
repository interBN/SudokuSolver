package methods;

import helper.Validation;

import java.util.Arrays;

public class M1Shadow extends Method {

    public M1Shadow(int[][] original) {
        this(original, false);
    }

    public M1Shadow(int[][] original, boolean print) {
        super(original, M1Shadow.class.getSimpleName(), print);
    }

    @Override
    public int[][] solve2() {
        int[][] result = this.solve3(super.result);
        super.result = result;
        return result;
    }

    private int[][] solve3(int[][] board) {
        if (Validation.isFinished(board)) {
            this.isComplete = true;
            return board;
        }
        this.iteration++;
        for (int num = 1; num <= 9; num++) {
            this.print(String.valueOf(num == 1 ? num : " > " + num));
            int[][] boardMarked = this.clone(board);
            int[][] before = this.clone(board);
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    if (boardMarked[y][x] == num) {
                        boardMarked = this.xMark(boardMarked, x, y);
                    }
                }
            }
            boardMarked = this.findLonelyFields(boardMarked, num);
            this.removeMinuses(boardMarked);
            boolean isEqual = Arrays.deepEquals(before, boardMarked);
            if (!isEqual) {
                return this.solve3(boardMarked);
            }
        }
        this.println("");
        return board;
    }

    private int[][] xMark(int[][] board, int x, int y) {
        board = this.clone(board);
        int num = board[y][x];
        for (int x2 = 0; x2 < 9; x2++) {
            if (board[y][x2] == 0) {
                board[y][x2] = num * -1;
            }
        }
        for (int y2 = 0; y2 < 9; y2++) {
            if (board[y2][x] == 0) {
                board[y2][x] = num * -1;
            }
        }
        return board;
    }

    private int[][] findLonelyFields(int[][] board, int searchNum) {
        for (int i = 0; i < 3; i++) {
            outer:
            for (int j = 0; j < 3; j++) {
                int yNew = i * 3;
                int xNew = j * 3;
                int countZero = 0, foundY = -1, foundX = -1;
                for (int y = yNew; y <= yNew + 2; y++) {
                    for (int x = xNew; x <= xNew + 2; x++) {
                        if (board[y][x] == searchNum) {
                            continue outer;
                        }
                        if (board[y][x] == 0) {
                            countZero++;
                            foundY = y;
                            foundX = x;
                        }
                    }
                }
                if (countZero == 1) {
                    board[foundY][foundX] = searchNum;
                    this.println("");
                    this.printAndMarkPos(board, foundX, foundY);
                    this.printLine();
                    int[][] marked = this.xMark(board, foundX, foundY);
                    return this.findLonelyFields(marked, searchNum);
                }
            }
        }
        return board;
    }

    private void removeMinuses(int[][] board) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[y][x] < 0) {
                    board[y][x] = 0;
                }
            }
        }
    }
}