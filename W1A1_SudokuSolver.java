public class W1A1_SudokuSolver {
    public static boolean helper(int[][] board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int val = 1; val <= 9; val++) {
                        if (isSafe(board, i, j, val)) {
                            board[i][j] = val;
                            boolean isPossible = helper(board);
                            if (isPossible) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        for (int j = 0; j < board.length; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;

    }

    public static void sudokuPrint(int[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (col == 3 || col == 6) {
                    System.out.print(" | ");
                }
                System.out.print(board[row][col] + " ");
            }
            if (row == 2 || row == 5) {
                System.out.println();
                for (int i = 0; i < 9; i++) {
                    System.out.print("---");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] sudokuBoard = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 0, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

        System.out.println("This is a Question code of Sudoku");
        sudokuPrint(sudokuBoard);

        boolean helper = helper(sudokuBoard);
        System.out.println("This is a solution of above sudoku pattern");
        if (helper == true) {
            sudokuPrint(sudokuBoard);
        } else {
            System.out.println("Can not get a Solution");
        }

    }
}
