import helper.Helper;
import helper.Printer;
import methods.Method3Backtrack;

public class Sudoku {

    public static void main(String[] args) {

        int[][] m = SudokuBoards.easy;
        int[][] go = go(Helper.clone(m));
//        int[][] go =  Method3Backtrack.check(m);

        Printer.printLine();
        Printer.comparePrint(m, go);
//        Printer.comparePrint(m, go);


        // methods.Method3Backtrack.check(go);
        // helper.Helper.comparePrint(m, go);
    }

    private static int[][] go(int[][] board) {

        int[][] original = Helper.clone(board);

//        int[][] step1 = new Method1Shadow(board).go();
//
//        if (Validation.isFinished(step1)) {
//            return step1;
//        }
//
//        int[][] step2 = new Method2Possible(step1).go();
//
//        if (Validation.isFinished(step2)) {
//            return step2;
//        }
//
//        boolean isEqual = Arrays.deepEquals(original, step2);
//
//        if (!isEqual) {
//            go(step2);
//        }


        int[][] step3 = new Method3Backtrack(board).go();

//        if (Validation.isFinished(step3)) {
//            return step3;
//        }
//
//        boolean isEqual2 = Arrays.deepEquals(board, step3);

        return step3;
    }
}