public class W1A2_MazeSolver {

    public static boolean isSafe(int[][] maze, int x, int y, int n) {

        if (x < n && y < n && maze[x][y] == 1) {
            return true;
        }
        return false;
    }

    public static boolean MazeSolution(int[][] maze, int x, int y, int n, int[][] solve) {

        if (x == n - 1 && y == n - 1) {
            solve[x][y] = 1;
            return true;
        }

        if (isSafe(maze, x, y, n)) {
            solve[x][y] = 1;
            if (MazeSolution(maze, x + 1, y, n, solve)) {
                return true;
            }
            if (MazeSolution(maze, x, y + 1, n, solve)) {
                return true;
            }
            solve[x][y] = 0;
            return false;

        }
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1 },
                { 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1 },
                { 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1 },
                { 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 }

        };
        int n = maze.length;
        int[][] solvmaze = new int[10][13];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                solvmaze[i][j] = 0;
            }
        }
        if (MazeSolution(maze, 0, 0, n, solvmaze)) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(" " + solvmaze[i][j]);
                }
                System.out.println();
            }
        }
    }
}