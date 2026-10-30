/**
 * Individual Minesweeper solution.
 *
 * Author: James Fischer
 *
 * Reads one or more Minesweeper fields from standard input and prints
 * the solved fields in the exact format required by the project spec.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class fischerj {

    /**
     * Private constructor to prevent instantiation.
     */
    private fischerj() {
        // Do nothing.
    }

    /**
     * Main method.
     *
     * @param theArgs command line arguments
     * @throws IOException if input fails
     */
    public static void main(final String[] theArgs) throws IOException {
        final FastReader input = new FastReader();
        final PrintWriter output = new PrintWriter(System.out);

        int fieldNumber = 1;

        while (true) {
            final int rows = input.nextInt();
            final int columns = input.nextInt();

            if (rows == 0 && columns == 0) {
                break;
            }

            final char[][] field = new char[rows][columns];
            for (int row = 0; row < rows; row++) {
                field[row] = input.next().toCharArray();
            }

            final char[][] solvedField = solveField(field);

            output.println("Field #" + fieldNumber + ":");
            for (int row = 0; row < rows; row++) {
                output.println(new String(solvedField[row]));
            }
            output.println();

            fieldNumber++;
        }

        output.flush();
    }

    /**
     * Solves one Minesweeper field.
     *
     * @param theField original field
     * @return solved field
     */
    private static char[][] solveField(final char[][] theField) {
        final int rows = theField.length;
        final int columns = theField[0].length;
        final char[][] solved = new char[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (theField[row][column] == '*') {
                    solved[row][column] = '*';
                } else {
                    solved[row][column] =
                            (char) ('0' + countAdjacentMines(theField, row, column));
                }
            }
        }

        return solved;
    }

    /**
     * Counts adjacent mines for one cell.
     *
     * @param theField original field
     * @param theRow row index
     * @param theColumn column index
     * @return number of adjacent mines
     */
    private static int countAdjacentMines(final char[][] theField,
                                          final int theRow,
                                          final int theColumn) {
        int count = 0;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }

                final int nextRow = theRow + rowOffset;
                final int nextColumn = theColumn + columnOffset;

                if (isInBounds(theField, nextRow, nextColumn)
                        && theField[nextRow][nextColumn] == '*') {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Checks whether a position is inside the field.
     *
     * @param theField field
     * @param theRow row index
     * @param theColumn column index
     * @return true if in bounds, false otherwise
     */
    private static boolean isInBounds(final char[][] theField,
                                      final int theRow,
                                      final int theColumn) {
        return theRow >= 0
                && theRow < theField.length
                && theColumn >= 0
                && theColumn < theField[0].length;
    }

    /**
     * Simple token-based input reader.
     */
    private static final class FastReader {

        /**
         * Reader for standard input.
         */
        private final BufferedReader myReader;

        /**
         * Current tokenizer.
         */
        private StringTokenizer myTokenizer;

        /**
         * Constructs a FastReader.
         */
        FastReader() {
            myReader = new BufferedReader(new InputStreamReader(System.in));
        }

        /**
         * Reads the next token.
         *
         * @return next token
         * @throws IOException if input fails
         */
        String next() throws IOException {
            while (myTokenizer == null || !myTokenizer.hasMoreTokens()) {
                final String line = myReader.readLine();
                if (line == null) {
                    return null;
                }
                myTokenizer = new StringTokenizer(line);
            }
            return myTokenizer.nextToken();
        }

        /**
         * Reads the next integer.
         *
         * @return next integer
         * @throws IOException if input fails
         */
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
