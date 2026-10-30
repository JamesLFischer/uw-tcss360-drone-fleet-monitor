// Author: Matthew Park

import java.util.Scanner;

public class parkmTest {

    private static int passed = 0;
    private static int failed = 0;

    private static void assertEquals(int expected, int actual, String testName) {
        if (expected == actual) {
            System.out.println("  PASS: " + testName);
            passed++;
        } else {
            System.out.println("  FAIL: " + testName
                    + " — expected " + expected + " but got " + actual);
            failed++;
        }
    }

    private static void assertEquals(char expected, char actual, String testName) {
        if (expected == actual) {
            System.out.println("  PASS: " + testName);
            passed++;
        } else {
            System.out.println("  FAIL: " + testName
                    + " — expected '" + expected + "' but got '" + actual + "'");
            failed++;
        }
    }

    private static void testReadField_basicGrid() {
        Scanner sc = new Scanner("*..\n.*.\n");
        char[][] grid = parkm.readField(sc, 2, 3);
        assertEquals('*', grid[0][0], "readField_basicGrid [0][0] is mine");
        assertEquals('.', grid[0][1], "readField_basicGrid [0][1] is safe");
        assertEquals('.', grid[0][2], "readField_basicGrid [0][2] is safe");
        assertEquals('.', grid[1][0], "readField_basicGrid [1][0] is safe");
        assertEquals('*', grid[1][1], "readField_basicGrid [1][1] is mine");
        assertEquals('.', grid[1][2], "readField_basicGrid [1][2] is safe");
    }

    private static void testReadField_oneByOneMine() {
        Scanner sc = new Scanner("*\n");
        char[][] grid = parkm.readField(sc, 1, 1);
        assertEquals('*', grid[0][0], "readField_oneByOneMine");
    }

    private static void testReadField_oneByOneSafe() {
        Scanner sc = new Scanner(".\n");
        char[][] grid = parkm.readField(sc, 1, 1);
        assertEquals('.', grid[0][0], "readField_oneByOneSafe");
    }

    private static void testCountAdjacentMines_cornerOneNeighborMine() {
        char[][] grid = {
            "*..".toCharArray(),
            "...".toCharArray()
        };
        assertEquals(1, parkm.countAdjacentMines(grid, 2, 3, 0, 1),
                "countAdjacentMines_cornerOneNeighborMine");
    }

    private static void testCountAdjacentMines_surroundedByMines() {
        char[][] grid = {
            "***".toCharArray(),
            "*.*".toCharArray(),
            "***".toCharArray()
        };
        assertEquals(8, parkm.countAdjacentMines(grid, 3, 3, 1, 1),
                "countAdjacentMines_surroundedByMines");
    }

    private static void testCountAdjacentMines_noNeighborMines() {
        char[][] grid = {
            "...".toCharArray(),
            "...".toCharArray(),
            "...".toCharArray()
        };
        assertEquals(0, parkm.countAdjacentMines(grid, 3, 3, 1, 1),
                "countAdjacentMines_noNeighborMines");
    }

    private static void testComputeHints_allMines() {
        char[][] grid = {
            "**".toCharArray(),
            "**".toCharArray()
        };
        int[][] hints = parkm.computeHints(grid, 2, 2);
        for (int r = 0; r < 2; r++)
            for (int c = 0; c < 2; c++)
                assertEquals(0, hints[r][c],
                        "computeHints_allMines [" + r + "][" + c + "]");
    }

    private static void testComputeHints_allSafe() {
        char[][] grid = {
            "...".toCharArray(),
            "...".toCharArray()
        };
        int[][] hints = parkm.computeHints(grid, 2, 3);
        for (int r = 0; r < 2; r++)
            for (int c = 0; c < 3; c++)
                assertEquals(0, hints[r][c],
                        "computeHints_allSafe [" + r + "][" + c + "]");
    }

    private static void testComputeHints_specSample4x4() {
        char[][] grid = {
            "*...".toCharArray(),
            "....".toCharArray(),
            ".*..".toCharArray(),
            "....".toCharArray()
        };
        int[][] hints = parkm.computeHints(grid, 4, 4);
        assertEquals(1, hints[0][1], "specSample4x4 [0][1]=1");
        assertEquals(0, hints[0][2], "specSample4x4 [0][2]=0");
        assertEquals(0, hints[0][3], "specSample4x4 [0][3]=0");
        assertEquals(2, hints[1][0], "specSample4x4 [1][0]=2");
        assertEquals(2, hints[1][1], "specSample4x4 [1][1]=2");
        assertEquals(1, hints[1][2], "specSample4x4 [1][2]=1");
        assertEquals(0, hints[1][3], "specSample4x4 [1][3]=0");
        assertEquals(1, hints[2][0], "specSample4x4 [2][0]=1");
        assertEquals(1, hints[2][2], "specSample4x4 [2][2]=1");
        assertEquals(0, hints[2][3], "specSample4x4 [2][3]=0");
        assertEquals(1, hints[3][0], "specSample4x4 [3][0]=1");
        assertEquals(1, hints[3][1], "specSample4x4 [3][1]=1");
        assertEquals(1, hints[3][2], "specSample4x4 [3][2]=1");
        assertEquals(0, hints[3][3], "specSample4x4 [3][3]=0");
    }

    private static void testComputeHints_singleRow() {
        char[][] grid = { "*...".toCharArray() };
        int[][] hints = parkm.computeHints(grid, 1, 4);
        assertEquals(1, hints[0][1], "singleRow hints[0][1]=1");
        assertEquals(0, hints[0][2], "singleRow hints[0][2]=0");
        assertEquals(0, hints[0][3], "singleRow hints[0][3]=0");
    }

    private static void testComputeHints_singleColumn() {
        char[][] grid = {
            "*".toCharArray(),
            ".".toCharArray(),
            ".".toCharArray(),
            ".".toCharArray()
        };
        int[][] hints = parkm.computeHints(grid, 4, 1);
        assertEquals(1, hints[1][0], "singleColumn hints[1][0]=1");
        assertEquals(0, hints[2][0], "singleColumn hints[2][0]=0");
        assertEquals(0, hints[3][0], "singleColumn hints[3][0]=0");
    }

    private static void testComputeHints_oneByOneMine() {
        char[][] grid = { "*".toCharArray() };
        int[][] hints = parkm.computeHints(grid, 1, 1);
        assertEquals(0, hints[0][0], "computeHints_oneByOneMine");
    }

    private static void testComputeHints_oneByOneSafe() {
        char[][] grid = { ".".toCharArray() };
        int[][] hints = parkm.computeHints(grid, 1, 1);
        assertEquals(0, hints[0][0], "computeHints_oneByOneSafe");
    }

    public static void main(String[] args) {
        System.out.println("=== parkmTest ===");

        System.out.println("\n-- readField --");
        testReadField_basicGrid();
        testReadField_oneByOneMine();
        testReadField_oneByOneSafe();

        System.out.println("\n-- countAdjacentMines --");
        testCountAdjacentMines_cornerOneNeighborMine();
        testCountAdjacentMines_surroundedByMines();
        testCountAdjacentMines_noNeighborMines();

        System.out.println("\n-- computeHints --");
        testComputeHints_allMines();
        testComputeHints_allSafe();
        testComputeHints_specSample4x4();
        testComputeHints_singleRow();
        testComputeHints_singleColumn();
        testComputeHints_oneByOneMine();
        testComputeHints_oneByOneSafe();

        System.out.println("\n=================");
        System.out.println("Results: " + passed + " passed, " + failed + " failed.");
        if (failed > 0) System.exit(1);
    }
}
