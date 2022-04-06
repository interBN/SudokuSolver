import java.util.Arrays;

public class Method1Shadow extends Method {

    public Method1Shadow(int[][] original) {
        super(original);
    }

    static void print(int[][] board, int x, int y) {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("----------|---------|----------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("|");
                int num = board[i][j];
                if (num == 0) {
                    System.out.print(" " + "\u001B[43m" + "-" + "\u001B[0m" + " ");
                } else if (num < 0) {
                    System.out.print(" " + "\u001B[34m" + "X" + "\u001B[0m" + " ");
                } else if (i == y && j == x) {
                    System.out.print(" " + "\u001B[31m" + num + "\u001B[0m" + " ");
                } else {
                    System.out.print(" " + num + " ");
                }
                if (j == 8) System.out.print("|");
            }
            System.out.println();
        }
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
                    print(board, foundX, foundY);
                    System.out.println("###############################");
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

    @Override
    public int[][] go() {
        System.out.println("Method1Shadow.go");
        Helper.printLine();
        this.result = go(super.result);
        return this.result;
    }

    private int[][] go(int[][] board) {
        if (Helper.isFinished(board)) {
            return board;
        }
        for (int num = 1; num <= 9; num++) {
            System.out.print(num == 1 ? num : " > " + num);
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
                return go(boardMarked);
            }
        }
        System.out.println();
        return board;
    }
}
