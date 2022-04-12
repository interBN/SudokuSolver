import methods.M1Shadow;
import methods.M2Possible;
import methods.M3Backtrack;
import methods.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static helper.Validation.isValid;

public class Sudoku {

    private final List<Method> methods;
    private final boolean print;

    public Sudoku() {
        this(false);
    }

    public Sudoku(boolean print) {
        this.print = print;
        this.methods = new ArrayList<>();
    }

    @SuppressWarnings("UnusedReturnValue")
    public int[][] solve(int[][] board) {

        if (!isValid(board)) {
            System.out.println("Something is wrong with the input.");
            return board;
        }

        // step 1
        Method method1Shadow = new M1Shadow(board, this.print);
        this.methods.add(method1Shadow);
        int[][] step1 = method1Shadow.solve();
        if (method1Shadow.isComplete) {
            return step1;
        }

        // step 2
        Method method2Possible = new M2Possible(step1, this.print);
        this.methods.add(method2Possible);
        int[][] step2 = method2Possible.solve();
        if (method2Possible.isComplete) {
            return step2;
        }

        // step 3
        Method method3Backtrack = new M3Backtrack(step2, this.print);
        this.methods.add(method3Backtrack);
        return method3Backtrack.solve();
    }

    @Override
    public String toString() {
        List<Long> ms = new ArrayList<>();
        List<Integer> iterations = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        final String BREAK = "\n";
        final String LINE = "----------------------------------------------------";
        for (int i = 0; i < this.methods.size(); i++) {
            long duration = this.methods.get(i).duration;
            ms.add(duration);
            result.append(LINE).append(BREAK)
                    .append("      Step: ").append(i + 1).append("/").append(this.methods.size()).append(BREAK)
                    .append("    Method: ").append(this.methods.get(i).name).append(BREAK)
                    .append("Iterations: ").append(this.methods.get(i).iteration).append(BREAK)
                    .append("      Time: ").append(duration).append(" ms").append(BREAK)
                    .append("     Found: ").append(this.methods.get(i).countFoundFields()).append(" (red)").append(BREAK)
                    .append("      Open: ").append(this.methods.get(i).countOpenFields()).append(" (yellow)").append(BREAK)
                    .append(this.methods.get(i)).append(BREAK);
            iterations.add(this.methods.get(i).iteration);
        }
        result.append(LINE).append(BREAK).append("Times: ");
        long totalTime = 0;
        for (int i = 0; i < ms.size(); i++) {
            result.append(ms.get(i)).append(" ms");
            totalTime += ms.get(i);
            if (i != ms.size() - 1) result.append(", ");
        }
        result.append(BREAK)
                .append("Total: ").append(totalTime).append(" ms").append(BREAK)
                .append("Iterations: ");
        IntStream.range(0, ms.size()).forEach(i -> {
            result.append(iterations.get(i));
            if (i != ms.size() - 1) result.append(", ");
        });
        return result.toString();
    }
}