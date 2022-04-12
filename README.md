# SudokuSolver

This is an easy Sudoku Solver that takes < 1 sec to solve any Sudoku game.

## Setup

Clone project:

```
git clone https://github.com/interBN/SudokuSolver.git
```

Use Java >=8.

Run the [Client](src/main/java/Client.java).

Have fun.

## Before

```
| 9  -  - | 1  -  - | -  -  5 |
| -  -  5 | -  9  - | 2  -  1 |
| 8  -  - | -  4  - | -  -  - |
----------|---------|----------
| -  -  - | -  8  - | -  -  - |
| -  -  - | 7  -  - | -  -  - |
| -  -  - | -  2  6 | -  -  9 |
----------|---------|----------
| 2  -  - | 3  -  - | -  -  6 |
| -  -  - | 2  -  - | 9  -  - |
| -  -  1 | 9  -  4 | 5  7  - |
```

## After

```
| 9  3  4 | 1  7  2 | 6  8  5 |
| 7  6  5 | 8  9  3 | 2  4  1 |
| 8  1  2 | 6  4  5 | 3  9  7 |
----------|---------|----------
| 4  2  9 | 5  8  1 | 7  6  3 |
| 6  5  8 | 7  3  9 | 1  2  4 |
| 1  7  3 | 4  2  6 | 8  5  9 |
----------|---------|----------
| 2  9  7 | 3  5  8 | 4  1  6 |
| 5  4  6 | 2  1  7 | 9  3  8 |
| 3  8  1 | 9  6  4 | 5  7  2 |
```

## Project Structure

![UML](./uml.png)
