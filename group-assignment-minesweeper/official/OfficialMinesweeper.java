package official;

/**
 * TCSS 360 Group Assignment 1 - Official Team Solution.
 *
 * Authors: Matthew Park, James Fischer, Dat Tran
 */
import java.util.Scanner;

public final class OfficialMinesweeper {

    /** Row offsets for the 8 neighbors of a cell. */
    private static final int[] ROW_OFFSETS = {-1, -1, -1, 0, 0, 1, 1, 1};

    /** Column offsets for the 8 neighbors of a cell. */
    private static final int[] COL_OFFSETS = {-1, 0, 1, -1, 1, -1, 0, 1};

    /**
     * Private constructor — utility class, not instantiated.
     */
    private OfficialMinesweeper() {
        // Do nothing.
    }

    /**
     * Program entry point.
     *
     * @param theArgs command-line arguments (not used)
     */
    public static void main(final String[] theArgs) {
        final Scanner sc = new Scanner(System.in);
        int fieldNumber = 1;

        while (sc.hasNextInt()) {
            final int rows = sc.nextInt();
            final int cols = sc.nextInt();

            if (rows == 0 && cols == 0) {
                break;
            }

            final char[][] grid = readField(sc, rows, cols);
            final int[][] hints = computeHints(grid, rows, cols);

            printSolvedField(grid, hints, rows, cols, fieldNumber);
            System.out.println();
            fieldNumber++;
        }

        sc.close();
    }

    /**
     * Reads a single Minesweeper field from the scanner.
     *
     * @param theScanner the Scanner to read from
     * @param theRows    number of rows in this field
     * @param theCols    number of columns in this field
     * @return a 2-D char array representing the raw field
     */
    public static char[][] readField(final Scanner theScanner,
                                     final int theRows,
                                     final int theCols) {
        final char[][] grid = new char[theRows][theCols];
        for (int r = 0; r < theRows; r++) {
            grid[r] = theScanner.next().toCharArray();
        }
        return grid;
    }

    /**
     * Computes hint values for every safe cell in the grid.
     *
     * @param theGrid the raw grid
     * @param theRows number of rows
     * @param theCols number of columns
     * @return a parallel 2-D int array of hint values
     */
    public static int[][] computeHints(final char[][] theGrid,
                                       final int theRows,
                                       final int theCols) {
        final int[][] hints = new int[theRows][theCols];
        for (int r = 0; r < theRows; r++) {
            for (int c = 0; c < theCols; c++) {
                if (theGrid[r][c] == '.') {
                    hints[r][c] = countAdjacentMines(theGrid, theRows, theCols, r, c);
                }
            }
        }
        return hints;
    }

    /**
     * Counts the mines immediately surrounding a single cell.
     *
     * @param theGrid the raw grid
     * @param theRows total row count
     * @param theCols total column count
     * @param theRow  row index of the target cell
     * @param theCol  column index of the target cell
     * @return the number of adjacent mines (0-8)
     */
    public static int countAdjacentMines(final char[][] theGrid,
                                          final int theRows,
                                          final int theCols,
                                          final int theRow,
                                          final int theCol) {
        int count = 0;
        for (int d = 0; d < ROW_OFFSETS.length; d++) {
            final int nr = theRow + ROW_OFFSETS[d];
            final int nc = theCol + COL_OFFSETS[d];
            if (isInBounds(nr, nc, theRows, theCols) && theGrid[nr][nc] == '*') {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks whether a cell position is within the grid boundaries.
     *
     * @param theRow  row index to check
     * @param theCol  column index to check
     * @param theRows total number of rows
     * @param theCols total number of columns
     * @return true if the position is valid, false otherwise
     */
    public static boolean isInBounds(final int theRow,
                                      final int theCol,
                                      final int theRows,
                                      final int theCols) {
        return theRow >= 0 && theRow < theRows && theCol >= 0 && theCol < theCols;
    }

    /**
     * Prints one solved field to standard output.
     *
     * @param theGrid        the raw grid
     * @param theHints       hint values from computeHints
     * @param theRows        number of rows
     * @param theCols        number of columns
     * @param theFieldNumber 1-based field index for the header line
     */
    public static void printSolvedField(final char[][] theGrid,
                                         final int[][] theHints,
                                         final int theRows,
                                         final int theCols,
                                         final int theFieldNumber) {
        System.out.println("Field #" + theFieldNumber + ":");
        for (int r = 0; r < theRows; r++) {
            for (int c = 0; c < theCols; c++) {
                if (theGrid[r][c] == '*') {
                    System.out.print('*');
                } else {
                    System.out.print(theHints[r][c]);
                }
            }
            System.out.println();
        }
    }
}
