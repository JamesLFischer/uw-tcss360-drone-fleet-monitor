// Author: Matthew Park

import java.util.Scanner;

public class parkm {

    private static final int[] DR = {-1, -1, -1,  0,  0,  1,  1,  1};
    private static final int[] DC = {-1,  0,  1, -1,  1, -1,  0,  1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int fieldNumber = 1;

        while (sc.hasNextInt()) {
            int rows = sc.nextInt();
            int cols = sc.nextInt();

            if (rows == 0 && cols == 0) {
                break;
            }

            char[][] grid = readField(sc, rows, cols);
            int[][] hints = computeHints(grid, rows, cols);

            printSolvedField(grid, hints, rows, cols, fieldNumber);
            System.out.println();
            fieldNumber++;
        }

        sc.close();
    }

    public static char[][] readField(Scanner sc, int rows, int cols) {
        char[][] grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            grid[r] = sc.next().toCharArray();
        }
        return grid;
    }

    public static int[][] computeHints(char[][] grid, int rows, int cols) {
        int[][] hints = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '.') {
                    hints[r][c] = countAdjacentMines(grid, rows, cols, r, c);
                }
            }
        }
        return hints;
    }

    public static int countAdjacentMines(char[][] grid, int rows, int cols,
                                          int row, int col) {
        int count = 0;
        for (int d = 0; d < DR.length; d++) {
            int nr = row + DR[d];
            int nc = col + DC[d];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                    && grid[nr][nc] == '*') {
                count++;
            }
        }
        return count;
    }

    public static void printSolvedField(char[][] grid, int[][] hints,
                                         int rows, int cols, int fieldNumber) {
        System.out.println("Field #" + fieldNumber + ":");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '*') {
                    System.out.print('*');
                } else {
                    System.out.print(hints[r][c]);
                }
            }
            System.out.println();
        }
    }
}
