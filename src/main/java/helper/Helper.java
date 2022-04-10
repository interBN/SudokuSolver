package helper;

import java.util.HashSet;
import java.util.Set;

public class Helper {

    public static int[][] clone(int[][] original) {
        int[][] clone = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            clone[i] = original[i].clone();
        }
        return clone;
    }

}
