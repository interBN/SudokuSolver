import helper.Validation;
import methods.M1Shadow;
import methods.M2Possible;
import methods.M3Backtrack;
import methods.Method;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    private final List<Method> methods;

    public Sudoku() {
        this.methods = new ArrayList<>();
    }

    public int[][] solve(int[][] board) {

        // step 1
        Method method1Shadow = new M1Shadow(board);
        this.methods.add(method1Shadow);
        int[][] step1 = method1Shadow.solve();
        if (Validation.isFinished(step1)) {
            return step1;
        }

        // step 2
        Method method2Possible = new M2Possible(step1);
        this.methods.add(method2Possible);
        int[][] step2 = method2Possible.solve();
        if (Validation.isFinished(step2)) {
            return step2;
        }

        // step 3
        Method method3Backtrack = new M3Backtrack(step2);
        methods.add(method3Backtrack);
        return method3Backtrack.solve();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        long totalTime = 0;
        final String BREAK = "\n";
        final String line = "----------------------------------------------------" + BREAK;
        for (int i = 0; i < this.methods.size(); i++) {
            long duration = this.methods.get(i).duration;
            totalTime += duration;
            result.append(line)
                    .append("Step: ").append(i + 1).append("/").append(this.methods.size()).append(BREAK)
                    .append("Runs: ").append(this.methods.get(i).iteration).append(BREAK)
                    .append("Time: ").append(duration).append(" ms").append(BREAK)
                    .append("Meth: ").append(this.methods.get(i)).append(BREAK);
        }
        result.append(line).append("Total time: ").append(totalTime).append(" ms").append(BREAK);
        return result.toString();
    }
}