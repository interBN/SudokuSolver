public class Method3Backtrack {

    static boolean check(int[][] board) {
        Helper.printLine();
        System.out.println("Sudoku.check");
        if (!Helper.isValid(board)) {
            System.out.println("Something is wrong with the input.");
        }
        Method2Possible.addObvious(board);
        return check(board, Helper.clone(board), 0, 0);
    }

    static boolean check(int[][] board, final int[][] original, int x, int y) {
        board = Helper.clone(board);

        if (x > 8 || y > 8) {
            Helper.print(board);
            return false;
        }
        if (Helper.isFinished(board)) {
            System.out.println("Finished:");
            Helper.print(board);
            return true;
        }
        Helper.printLine();
        System.out.println("pos x=" + x + " y=" + y + " num=" + board[y][x]);
        int newX = x + 1;
        int newY = y;
        if (newX > 8) {
            newX = 0;
            newY++;
        }
        if (original[y][x] != 0) {
            System.out.println("Skip!");
            return check(board, original, newX, newY);
        }
        int[] possible;
        try {
            possible = Method2Possible.getPossible(board, x, y);
        } catch (Exception e) {
            System.out.println("original");
            Helper.print(original);
            throw e;
        }
        if (possible.length == 1) {
            board[y][x] = possible[0];
            System.out.println("found: " + possible[0]);
//            print(board, x, y);
            return check(board, original, newX, newY);
        } else {
            for (int checkNum : possible) {
                board[y][x] = checkNum;
                System.out.println("x=" + x + " y=" + y + " check: " + checkNum);
                boolean check = check(board, original, newX, newY);
                if (check) {
                    return true;
                }
            }
        }
        System.out.println("RETURN false");
        return false;
    }
}
