public class Sudoku {

    static int[][] easy = {
            {0, 3, 4, 8, 7, 0, 0, 0, 0},
            {0, 0, 2, 3, 4, 0, 1, 5, 8},
            {1, 8, 0, 2, 0, 0, 0, 0, 0},
            {9, 0, 0, 0, 3, 0, 5, 0, 6},
            {0, 1, 0, 0, 0, 0, 4, 0, 0},
            {0, 5, 3, 7, 6, 0, 0, 0, 2},
            {0, 2, 0, 6, 0, 3, 0, 0, 0},
            {7, 0, 8, 0, 0, 5, 0, 0, 3},
            {3, 0, 1, 4, 0, 7, 8, 6, 5},
    };

    /**
     * Unsolvable?
     */
    static int[][] hard = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

    /**
     * Unsolvable?
     */
    static int[][] hard2 = {
            {9, 0, 0, 1, 0, 0, 0, 0, 5},
            {0, 0, 5, 0, 9, 0, 2, 0, 1},
            {8, 0, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 0, 7, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 6, 0, 0, 9},
            {2, 0, 0, 3, 0, 0, 0, 0, 6},
            {0, 0, 0, 2, 0, 0, 9, 0, 0},
            {0, 0, 1, 9, 0, 4, 5, 7, 0},
    };

    /**
     * Unsolvable
     */
    static int[][] medium2 = {
            {1, 0, 0, 6, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 3, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 4, 0, 0, 0, 1, 0},
            {0, 4, 0, 0, 5, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 6, 0, 9, 0},
            {0, 0, 2, 0, 0, 0, 7, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 8, 0},
            {3, 0, 0, 5, 0, 0, 0, 0, 9}
    };

    static int[][] easy2 = {
            {5, 8, 0, 2, 0, 0, 4, 7, 0},
            {0, 2, 0, 0, 0, 0, 0, 3, 0},
            {0, 3, 0, 0, 5, 4, 0, 0, 0},
            {0, 0, 0, 5, 6, 0, 0, 0, 0},
            {0, 0, 7, 0, 3, 0, 9, 0, 0},
            {0, 0, 0, 0, 9, 1, 0, 0, 0},
            {0, 0, 0, 8, 2, 0, 0, 6, 0},
            {0, 7, 0, 0, 0, 0, 0, 8, 0},
            {0, 9, 4, 0, 0, 6, 0, 1, 5}
    };

    static int[][] easy3 = {
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
    };

    static int[][] medium = {
            {7, 0, 0, 0, 0, 0, 2, 0, 0},
            {4, 0, 2, 0, 0, 0, 0, 0, 3},
            {0, 0, 0, 2, 0, 1, 0, 0, 0},
            {3, 0, 0, 1, 8, 0, 0, 9, 7},
            {0, 0, 9, 0, 7, 0, 6, 0, 0},
            {6, 5, 0, 0, 3, 2, 0, 0, 1},
            {0, 0, 0, 4, 0, 9, 0, 0, 0},
            {5, 0, 0, 0, 0, 0, 1, 0, 6},
            {0, 0, 6, 0, 0, 0, 0, 0, 8}
    };

    public static void main(String[] args) {
//        check(original2);
//        System.out.println();
//        System.out.println("------------------------------------------------------------------------------------");

//        check(medium);

        go(medium);

    }

    private static int[][] go(int[][] board) {

        int[][] original = Helper.clone(board);

        int[][] pattern = new Method1Shadow(board).go();

        if (Helper.isFinished(pattern)) {
            return pattern;
        }

//        System.out.println("+++++++++++++++++++++++++++++++++++++++");
//        Helper.print(pattern);
//        Method2.addObvious(pattern);
//
//        System.out.println("+++++++++++++++++++++++++++++++++++++++");
//        Helper.print(pattern);
//
//        boolean isEqual = Arrays.deepEquals(original, pattern);
//        if (!isEqual) {
//            go(pattern);
//        }


        return pattern;
    }


}