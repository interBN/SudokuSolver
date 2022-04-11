import helper.Helper;
import helper.Validation;
import methods.M1Shadow;
import methods.M2Possible;
import methods.M3Backtrack;
import methods.Method;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    List<Method> methods;

    public Sudoku() {
        this.methods = new ArrayList<>();
    }

    public static void main(String[] args) {
        int[][] original = SudokuBoards.evil3;
        Sudoku sudoku = new Sudoku();
        @SuppressWarnings("unused")
        int[][] result = sudoku.go(Helper.clone(original));

        long totalTime = 0;
        for (int i = 0; i < sudoku.methods.size(); i++) {
            System.out.println("----------------------------------------------------");
            System.out.println("Step: " + (i + 1) + "/" + sudoku.methods.size());
            System.out.println("Runs: " + sudoku.methods.get(i).iteration);
            long duration = sudoku.methods.get(i).duration;
            System.out.println("Time: " + duration + " ms");
            System.out.println("Meth: " + sudoku.methods.get(i));
            totalTime += duration;
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Total time: " + totalTime + " ms");
    }


    private int[][] go(int[][] board) {

//        int[][] original = Helper.clone(board);

        Method method1Shadow = new M1Shadow(board);
        this.methods.add(method1Shadow);

        int[][] step1 = method1Shadow.go();

        if (Validation.isFinished(step1)) {
            return step1;
        }

        Method method2Possible = new M2Possible(step1);
        this.methods.add(method2Possible);

        int[][] step2 = method2Possible.go();

        if (Validation.isFinished(step2)) {
            return step2;
        }

//        boolean isEqual = Arrays.deepEquals(step1, step2);
//        if (!isEqual) {
//            return go(step2);
//        }

        Method method3Backtrack = new M3Backtrack(step2);
        methods.add(method3Backtrack);

        return method3Backtrack.go();
    }
}