package methods;

import helper.Validation;

import java.util.Arrays;

public class
M1Shadow extends Method {

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
        this.printLine();
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
            int[][] cleaned = this.removeMinuses(boardMarked);
            if (!Arrays.deepEquals(before, cleaned)) {
                return this.solve3(cleaned);
            }
        }
        this.println("");
        return board;
    }

    private int[][] xMark(int[][] board, int x, int y) {
        board = this.clone(board);
        int mark = board[y][x] * -1;
        for (int i = 0; i < 9; i++) {
            if (board[y][i] == 0) {
                board[y][i] = mark;
            }
            if (board[i][x] == 0) {
                board[i][x] = mark;
            }
        }
        return board;
    }

    private int[][] findLonelyFields(int[][] board, int searchNum) {
        board = this.clone(board);
        for (int i = 0; i < 3; i++) {
            outer:
            for (int j = 0; j < 3; j++) {
                int yNew = i * 3, xNew = j * 3;
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

    private int[][] removeMinuses(int[][] board) {
        board = this.clone(board);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[y][x] < 0) {
                    board[y][x] = 0;
                }
            }
        }
        return board;
    }
}