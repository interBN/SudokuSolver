public class Client {

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.solve(SudokuBoards.evil);
        System.out.println(sudoku);
    }
}