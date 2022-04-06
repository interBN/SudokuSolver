import java.util.Arrays;

public class Method1Shadow extends Method {

    public Method1Shadow(int[][] original) {
        super(original);
    }

    private int[][] xMark(int[][] board, int x, int y) {
        board = Helper.clone(board);
        int num = board[y][x];
//        System.out.println("find num: " + num);
        for (int x2 = 0; x2 < 9; x2++) {
//            System.out.println(board[y][x2]);
            if (board[y][x2] == 0) {
                board[y][x2] = num * -1;
            }
        }
//        Helper.printLine();
        for (int y2 = 0; y2 < 9; y2++) {
//            System.out.println(board[y2][x]);
            if (board[y2][x] == 0) {
                board[y2][x] = num * -1;
            }
        }
        return board;
    }

    private int[][] findLonelyFields(int[][] board, int set) {
        @SuppressWarnings("unused")
        int blockId = 0;
        for (int i = 0; i < 3; i++) {
            outer:
            for (int j = 0; j < 3; j++) {
                blockId++;
                int upY = i * 3;
                int upX = j * 3;
                int downY = upY + 2;
                int downX = upX + 2;
                int countZero = 0;
                int foundY = -1;
                int foundX = -1;
                for (int y = upY; y <= downY; y++) {
                    for (int x = upX; x <= downX; x++) {
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
                    int[][] marked = xMark(board, foundX, foundY);
                    System.out.println("+++++++++++++++++++++++++++++++++++++++");
                    Helper.print(marked, foundX, foundY);
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

    @Override
    public int[][] go() {
        int[][] result = go(super.result);
        this.result = result;
        return result;
    }

    private int[][] go(int[][] board) {
        for (int num = 1; num <= 9; num++) {
            int[][] boardMarked = Helper.clone(board);
            int[][] before = Helper.clone(board);
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    if (boardMarked[y][x] == num) {
                        boardMarked = xMark(boardMarked, x, y);
                    }
                }
            }
//            Helper.print(board);
//            Helper.print(boardMarked);
            boardMarked = findLonelyFields(boardMarked, num);
            removeMinuses(boardMarked);
            boolean isEqual = Arrays.deepEquals(before, boardMarked);
            if (!isEqual) {
                return go(boardMarked);
            }
        }
        return board;
    }
}
