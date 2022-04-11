package methods;

import helper.Helper;
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
    public int[][] go2() {
        int[][] result = go3(super.result);
        super.result = result;
        return result;
    }

    private int[][] go3(int[][] board) {
        if (Validation.isFinished(board)) {
            return board;
        }
        super.iteration++;
        for (int num = 1; num <= 9; num++) {
            print(String.valueOf(num == 1 ? num : " > " + num));
            int[][] boardMarked = Helper.clone(board);
            int[][] before = Helper.clone(board);
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    if (boardMarked[y][x] == num) {
                        boardMarked = xMark(boardMarked, x, y);
                    }
                }
            }
            boardMarked = findLonelyFields(boardMarked, num);
            removeMinuses(boardMarked);
            boolean isEqual = Arrays.deepEquals(before, boardMarked);
            if (!isEqual) {
                return go3(boardMarked);
            }
        }
        println("");
        return board;
    }

    private int[][] xMark(int[][] board, int x, int y) {
        board = Helper.clone(board);
        int num = board[y][x];
//        println("find num: " + num);
        for (int x2 = 0; x2 < 9; x2++) {
//            println(board[y][x2]);
            if (board[y][x2] == 0) {
                board[y][x2] = num * -1;
            }
        }
//        helper.Helper.printLine();
        for (int y2 = 0; y2 < 9; y2++) {
//            println(board[y2][x]);
            if (board[y2][x] == 0) {
                board[y2][x] = num * -1;
            }
        }
        return board;
    }

    private int[][] findLonelyFields(int[][] board, int set) {
        @SuppressWarnings("unused") int blockId = 0;
        for (int i = 0; i < 3; i++) {
            outer:
            for (int j = 0; j < 3; j++) {
                blockId++;
                int yNew = i * 3;
                int xNew = j * 3;
                int countZero = 0, foundY = -1, foundX = -1;
                for (int y = yNew; y <= yNew + 2; y++) {
                    for (int x = xNew; x <= xNew + 2; x++) {
                        if (board[y][x] == set) {
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
                    board[foundY][foundX] = set;
                    println("");
                    printAndMarkPos(board, foundX, foundY);
                    printLine();
                    int[][] marked = xMark(board, foundX, foundY);
                    return findLonelyFields(marked, set);
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