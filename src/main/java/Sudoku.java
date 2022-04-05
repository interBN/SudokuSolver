import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static void main(String[] args) {
//        check(original2);
//        System.out.println();
//        System.out.println("------------------------------------------------------------------------------------");
        addObvious(hard);
    }

    private static void addObvious(int[][] board) {
        if (isFinished(board)) {
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("Finished result:");
            print(board);
            return;
        }
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Sudoku.addObvious");
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int num = board[y][x];
                if (num != 0) {
                    continue;
                }

                int[] possible = possible(board, x, y);
                if (possible != null && possible.length == 1) {
                    System.out.println("found: " + possible[0]);
                    board[y][x] = possible[0];

                    print(board, x, y);
                    addObvious(board);
                    return;
                }
            }
        }
        System.out.println("Unfinished result:");
        print(board);
    }

    private static int[][] clone(int[][] original) {
        int[][] clone = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            clone[i] = original[i].clone();
        }
        return clone;
    }

    static boolean check(int[][] board) {
        if (!isValid(board)) {
            System.out.println("Something is wrong with the input.");
        }
        return check(board, 0, 0);
    }

    static boolean check(int[][] board, int x, int y) {
        board = clone(board);
        print(board);
        if (x == 0) {
            System.out.println("------------------------------------------------------------------------------------");
        }
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("pos x=" + x + " y=" + y);

        if ((x > 8 || y > 8)) {
            print(board);
            return false;
        }

        if (isFinished(board)) {
            System.out.println("Finished:");
            print(board);
            return true;
        }

        int newX = x + 1;
        int newY = y;
        if (newX > 8) {
            newX = 0;
            newY++;
        }

        int num = board[y][x];

        System.out.println(num);

        // TODO: uncomment and fix
//        if (original2[y][x] != 0) {
//            System.out.println("Skip!");
//            return check(board, newX, newY);
//        }


        for (int i = 1; i <= 10; i++) {
            board[y][x] = i;


            if (i > 9) {
                System.out.println("Return");
                System.out.println("-------------");
                return false;
            }
            System.out.println("x=" + x + " y=" + y + " check: " + i);

            boolean valid = isValid(board);
            if (!valid) {
                ++board[y][x];
                continue;
            }
            System.out.println("maybe valid: " + board[y][x]);

            for (int j = i; j <= 10; j++) {
                if (j > 9) {
                    System.out.println("Return");
                    System.out.println("-------------");
                    return false;
                }
                board[y][x] = j;
                boolean check = check(board, newX, newY);
                if (check) {
                    return true;
                }
            }
        }

        System.out.println("End");
        print(board);
        return true;
    }

    static boolean isFinished(int[][] board) {
        boolean haveNull = false;
        outer:
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    haveNull = true;
                    break outer;
                }
            }
        }
        return isLineValid(board) && isBlockValid(board) && !haveNull;
    }

    static boolean isValid(int[][] board) {
        return isLineValid(board) && isBlockValid(board);
    }

    static boolean isLineValid(int[][] board) {
        for (int[] ints : board) {
            Set<Integer> tmp = new HashSet<>();
            for (int num : ints) {
                if (num == 0) {
                    continue;
                }
                boolean ok = tmp.add(num);
                if (!ok) {
                    return false;
                }
            }
        }
        for (int j = 0; j < board.length; j++) {
            Set<Integer> tmp = new HashSet<>();
            for (int i = 0; i < board[j].length; i++) {
                int num = board[i][j];
                if (num == 0) {
                    continue;
                }
                boolean ok = tmp.add(num);
                if (!ok) {
                    return false;
                }
            }
        }
        return true;
    }

    static void print(int[][] board) {
        print(board, -1, -1);
    }

    static void print(int[][] board, int x, int y) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == y && j == x) {
                    System.out.print("\u001B[31m" + board[i][j] + "\u001B[0m");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.println();
        }
    }

    static boolean isBlockValid(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int upX = i * 3;
                int upY = j * 3;
                int downX = upX + 2;
                int downY = upY + 2;
                Set<Integer> tmp = new HashSet<>();
                for (int x = upX; x <= downX; x++) {
                    for (int y = upY; y <= downY; y++) {
                        int num = board[x][y];
                        if (num == 0) {
                            continue;
                        }
                        boolean isUnique = tmp.add(num);
                        if (!isUnique) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    static int[] possible(int[][] board, int x, int y) {

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("[" + x + "," + y + "]=" + board[y][x]);

        if (board[y][x] != 0) return null;

        int[] possibleLine = possibleLine(board, x, y);
        int[] possibleBlock = possibleBlock(board, x, y);

        int[] intersection = Arrays.stream(Objects.requireNonNull(possibleLine))
                .distinct()
                .filter(j -> Arrays.stream(Objects.requireNonNull(possibleBlock)).anyMatch(i -> i == j))
                .toArray();

        System.out.println("Sudoku.possible");
        System.out.println(Arrays.toString(intersection));

        return intersection;
    }

    static int[] possibleLine(int[][] board, int x, int y) {
        Set<Integer> tmp = new HashSet<>();

        if (board[y][x] != 0) {
            return null;
        }

        outer:
        for (int i = 1; i <= 9; i++) {
            for (int w = 0; w < 9; w++) {
                if (board[w][x] == i) {
                    continue outer;
                }
            }
            for (int h = 0; h < 9; h++) {
                if (board[y][h] == i) {
                    continue outer;
                }
            }
            tmp.add(i);
        }
        System.out.println("Sudoku.possibleLine");
        System.out.println(tmp);
        return tmp.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] possibleBlock(int[][] board, int x, int y) {
        if (board[y][x] != 0) {
            return null;
        }
        int upX = x - (x % 3);
        int upY = y - (y % 3);
        int downX = upX + 2;
        int downY = upY + 2;

        Set<Integer> set = new HashSet<>();

        for (int i = upX; i <= downX; i++) {
            for (int j = upY; j <= downY; j++) {
                int num = board[j][i];
                if (num == 0) {
                    continue;
                }
                set.add(num);
            }
        }
        Set<Integer> result = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toSet());
        result.removeAll(set);
        int[] array = result.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("Sudoku.possibleBlock");
        System.out.println(Arrays.toString(array));
        return array;
    }

}