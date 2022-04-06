public abstract class Method {

    final int[][] original;
    int[][] result;

    public Method(int[][] original) {
        this.original = original;
        this.result = original;
    }

    public abstract int[][] go();
}
