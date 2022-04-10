package methods;

public abstract class Method {

    final int[][] original;
    int[][] result;
    int iteration;

    public Method(int[][] original) {
        this.original = original;
        this.result = original;
        this.iteration = 0;
    }

    public abstract int[][] go();
}
