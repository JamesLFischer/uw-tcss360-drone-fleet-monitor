package official;

import java.util.Scanner;

/**
 * Unit tests for OfficialMinesweeper.
 *
 * Authors: Matthew Park, James Fischer, Dat Tran
 */
public class OfficialTest {

    private static int myPassed = 0;
    private static int myFailed = 0;

    private static void assertEquals(final int theExpected,
                                      final int theActual,
                                      final String theTestName) {
        if (theExpected == theActual) {
            System.out.println("  PASS: " + theTestName);
            myPassed++;
        } else {
            System.out.println("  FAIL: " + theTestName
                    + " — expected " + theExpected + " but got " + theActual);
            myFailed++;
        }
    }

    private static void assertEquals(final char theExpected,
                                      final char theActual,
                                      final String theTestName) {
        if (theExpected == theActual) {
            System.out.println("  PASS: " + theTestName);
            myPassed++;
        } else {
            System.out.println("  FAIL: " + theTestName
                    + " — expected '" + theExpected + "' but got '" + theActual + "'");
            myFailed++;
        }
    }

    private static void assertTrue(final boolean theCondition,
                                    final String theTestName) {
        if (theCondition) {
            System.out.println("  PASS: " + theTestName);
            myPassed++;
        } else {
            System.out.println("  FAIL: " + theTestName + " — expected true");
            myFailed++;
        }
    }

    private static void assertFalse(final boolean theCondition,
                                     final String theTestName) {
        if (!theCondition) {
            System.out.println("  PASS: " + theTestName);
            myPassed++;
        } else {
            System.out.println("  FAIL: " + theTestName + " — expected false");
            myFailed++;
        }
    }

    private static void testIsInBounds_topLeftCorner() {
        assertTrue(OfficialMinesweeper.isInBounds(0, 0, 3, 3),
                "isInBounds_topLeftCorner");
    }

    private static void testIsInBounds_bottomRightCorner() {
        assertTrue(OfficialMinesweeper.isInBounds(2, 2, 3, 3),
                "isInBounds_bottomRightCorner");
    }

    private static void testIsInBounds_negativeRow() {
        assertFalse(OfficialMinesweeper.isInBounds(-1, 0, 3, 3),
                "isInBounds_negativeRow");
    }

    private static void testIsInBounds_negativeCol() {
        assertFalse(OfficialMinesweeper.isInBounds(0, -1, 3, 3),
                "isInBounds_negativeCol");
    }

    private static void testIsInBounds_rowEqualsRowCount() {
        assertFalse(OfficialMinesweeper.isInBounds(3, 0, 3, 3),
                "isInBounds_rowEqualsRowCount");
    }

    private static void testIsInBounds_colEqualsColCount() {
        assertFalse(OfficialMinesweeper.isInBounds(0, 3, 3, 3),
                "isInBounds_colEqualsColCount");
    }

    private static void testReadField_basicGrid() {
        final Scanner sc = new Scanner("*..\n.*.\n");
        final char[][] grid = OfficialMinesweeper.readField(sc, 2, 3);
        assertEquals('*', grid[0][0], "readField_basicGrid [0][0]");
        assertEquals('.', grid[0][1], "readField_basicGrid [0][1]");
        assertEquals('.', grid[1][0], "readField_basicGrid [1][0]");
        assertEquals('*', grid[1][1], "readField_basicGrid [1][1]");
    }

    private static void testReadField_oneByOneMine() {
        final Scanner sc = new Scanner("*");
        final char[][] grid = OfficialMinesweeper.readField(sc, 1, 1);
        assertEquals('*', grid[0][0], "readField_oneByOneMine");
    }

    private static void testReadField_oneByOneSafe() {
        final Scanner sc = new Scanner(".");
        final char[][] grid = OfficialMinesweeper.readField(sc, 1, 1);
        assertEquals('.', grid[0][0], "readField_oneByOneSafe");
    }

    private static void testReadField_singleRowAllMines() {
        final String row = "*".repeat(100);
        final Scanner sc = new Scanner(row);
        final char[][] grid = OfficialMinesweeper.readField(sc, 1, 100);
        assertEquals('*', grid[0][0],  "readField_singleRowAllMines first cell");
        assertEquals('*', grid[0][99], "readField_singleRowAllMines last cell");
        assertEquals(100, grid[0].length, "readField_singleRowAllMines length");
    }

    private static void testCountAdjacentMines_allEightNeighbors() {
        final char[][] grid = {
            "***".toCharArray(),
            "*.*".toCharArray(),
            "***".toCharArray()
        };
        assertEquals(8, OfficialMinesweeper.countAdjacentMines(grid, 3, 3, 1, 1),
                "countAdjacentMines_allEightNeighbors");
    }

    private static void testCountAdjacentMines_noNeighbors() {
        final char[][] grid = {
            "...".toCharArray(),
            "...".toCharArray(),
            "...".toCharArray()
        };
        assertEquals(0, OfficialMinesweeper.countAdjacentMines(grid, 3, 3, 1, 1),
                "countAdjacentMines_noNeighbors");
    }

    private static void testCountAdjacentMines_cornerOneNeighbor() {
        final char[][] grid = {
            "*..".toCharArray(),
            "...".toCharArray()
        };
        assertEquals(1, OfficialMinesweeper.countAdjacentMines(grid, 2, 3, 0, 1),
                "countAdjacentMines_cornerOneNeighbor");
    }

    private static void testCountAdjacentMines_topLeftCorner() {
        final char[][] grid = {
            ".*".toCharArray(),
            "..".toCharArray()
        };
        assertEquals(1, OfficialMinesweeper.countAdjacentMines(grid, 2, 2, 0, 0),
                "countAdjacentMines_topLeftCorner");
    }

    private static void testComputeHints_oneByOneMine() {
        final char[][] grid = { "*".toCharArray() };
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 1, 1);
        assertEquals(0, hints[0][0], "computeHints_oneByOneMine");
    }

    private static void testComputeHints_oneByOneSafe() {
        final char[][] grid = { ".".toCharArray() };
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 1, 1);
        assertEquals(0, hints[0][0], "computeHints_oneByOneSafe");
    }

    private static void testComputeHints_allMines() {
        final char[][] grid = {
            "***".toCharArray(),
            "***".toCharArray(),
            "***".toCharArray()
        };
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 3, 3);
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                assertEquals(0, hints[r][c],
                        "computeHints_allMines [" + r + "][" + c + "]");
    }

    private static void testComputeHints_allSafe() {
        final char[][] grid = {
            "....".toCharArray(),
            "....".toCharArray(),
            "....".toCharArray()
        };
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 3, 4);
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 4; c++)
                assertEquals(0, hints[r][c],
                        "computeHints_allSafe [" + r + "][" + c + "]");
    }

    private static void testComputeHints_specSampleField1() {
        final char[][] grid = {
            "*...".toCharArray(),
            "....".toCharArray(),
            ".*..".toCharArray(),
            "....".toCharArray()
        };
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 4, 4);
        assertEquals(1, hints[0][1], "specField1 [0][1]=1");
        assertEquals(0, hints[0][2], "specField1 [0][2]=0");
        assertEquals(0, hints[0][3], "specField1 [0][3]=0");
        assertEquals(2, hints[1][0], "specField1 [1][0]=2");
        assertEquals(2, hints[1][1], "specField1 [1][1]=2");
        assertEquals(1, hints[1][2], "specField1 [1][2]=1");
        assertEquals(0, hints[1][3], "specField1 [1][3]=0");
        assertEquals(1, hints[2][0], "specField1 [2][0]=1");
        assertEquals(1, hints[2][2], "specField1 [2][2]=1");
        assertEquals(0, hints[2][3], "specField1 [2][3]=0");
        assertEquals(1, hints[3][0], "specField1 [3][0]=1");
        assertEquals(1, hints[3][1], "specField1 [3][1]=1");
        assertEquals(1, hints[3][2], "specField1 [3][2]=1");
        assertEquals(0, hints[3][3], "specField1 [3][3]=0");
    }

    private static void testComputeHints_specSampleField2() {
        final char[][] grid = {
            "**...".toCharArray(),
            ".....".toCharArray(),
            ".*...".toCharArray()
        };
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 3, 5);
        assertEquals(3, hints[1][0], "specField2 [1][0]=3");
        assertEquals(3, hints[1][1], "specField2 [1][1]=3");
        assertEquals(2, hints[1][2], "specField2 [1][2]=2");
        assertEquals(0, hints[1][3], "specField2 [1][3]=0");
        assertEquals(0, hints[1][4], "specField2 [1][4]=0");
        assertEquals(1, hints[2][0], "specField2 [2][0]=1");
        assertEquals(1, hints[2][2], "specField2 [2][2]=1");
        assertEquals(0, hints[2][3], "specField2 [2][3]=0");
        assertEquals(0, hints[2][4], "specField2 [2][4]=0");
    }

    private static void testComputeHints_singleRow() {
        final char[][] grid = { "*...".toCharArray() };
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 1, 4);
        assertEquals(1, hints[0][1], "singleRow hints[0][1]=1");
        assertEquals(0, hints[0][2], "singleRow hints[0][2]=0");
        assertEquals(0, hints[0][3], "singleRow hints[0][3]=0");
    }

    private static void testComputeHints_singleColumn() {
        final char[][] grid = {
            "*".toCharArray(),
            ".".toCharArray(),
            ".".toCharArray(),
            ".".toCharArray()
        };
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 4, 1);
        assertEquals(1, hints[1][0], "singleColumn hints[1][0]=1");
        assertEquals(0, hints[2][0], "singleColumn hints[2][0]=0");
        assertEquals(0, hints[3][0], "singleColumn hints[3][0]=0");
    }

    private static void testComputeHints_maxSizeAllSafe() {
        final char[][] grid = new char[100][100];
        for (int r = 0; r < 100; r++)
            for (int c = 0; c < 100; c++)
                grid[r][c] = '.';
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 100, 100);
        boolean allZero = true;
        for (int r = 0; r < 100; r++)
            for (int c = 0; c < 100; c++)
                if (hints[r][c] != 0) allZero = false;
        assertTrue(allZero, "computeHints_maxSizeAllSafe all zeros");
    }

    private static void testComputeHints_maxSizeAllMines() {
        final char[][] grid = new char[100][100];
        for (int r = 0; r < 100; r++)
            for (int c = 0; c < 100; c++)
                grid[r][c] = '*';
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 100, 100);
        boolean allZero = true;
        for (int r = 0; r < 100; r++)
            for (int c = 0; c < 100; c++)
                if (hints[r][c] != 0) allZero = false;
        assertTrue(allZero, "computeHints_maxSizeAllMines all zeros");
    }

    private static void testComputeHints_checkerboard() {
        final char[][] grid = {
            "*.*.*".toCharArray(),
            ".*.*.".toCharArray(),
            "*.*.*".toCharArray(),
            ".*.*.".toCharArray(),
            "*.*.*".toCharArray()
        };
        final int[][] hints = OfficialMinesweeper.computeHints(grid, 5, 5);
        assertEquals(4, hints[1][2], "checkerboard hints[1][2]=4");
        assertEquals(4, hints[3][2], "checkerboard hints[3][2]=4");
        assertEquals(3, hints[1][0], "checkerboard hints[1][0]=3");
    }

    public static void main(final String[] theArgs) {
        System.out.println("=== OfficialTest ===");

        System.out.println("\n-- isInBounds --");
        testIsInBounds_topLeftCorner();
        testIsInBounds_bottomRightCorner();
        testIsInBounds_negativeRow();
        testIsInBounds_negativeCol();
        testIsInBounds_rowEqualsRowCount();
        testIsInBounds_colEqualsColCount();

        System.out.println("\n-- readField --");
        testReadField_basicGrid();
        testReadField_oneByOneMine();
        testReadField_oneByOneSafe();
        testReadField_singleRowAllMines();

        System.out.println("\n-- countAdjacentMines --");
        testCountAdjacentMines_allEightNeighbors();
        testCountAdjacentMines_noNeighbors();
        testCountAdjacentMines_cornerOneNeighbor();
        testCountAdjacentMines_topLeftCorner();

        System.out.println("\n-- computeHints --");
        testComputeHints_oneByOneMine();
        testComputeHints_oneByOneSafe();
        testComputeHints_allMines();
        testComputeHints_allSafe();
        testComputeHints_specSampleField1();
        testComputeHints_specSampleField2();
        testComputeHints_singleRow();
        testComputeHints_singleColumn();
        testComputeHints_maxSizeAllSafe();
        testComputeHints_maxSizeAllMines();
        testComputeHints_checkerboard();

        System.out.println("\n====================");
        System.out.println("Results: " + myPassed + " passed, " + myFailed + " failed.");
        if (myFailed > 0) System.exit(1);
    }
}
