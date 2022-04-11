public class Client {

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.solve(SudokuBoards.hard);
        System.out.println(sudoku);
    }
}