package inputGenerator;

import java.util.Random;
import java.util.Scanner;

public class InputGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of fields to generate: ");
        int fields = sc.nextInt();

        System.out.print("Rows per field (1-100): ");
        int rows = sc.nextInt();

        System.out.print("Columns per field (1-100): ");
        int cols = sc.nextInt();

        System.out.print("Mine percentage (0-100): ");
        int minePct = sc.nextInt();

        sc.close();

        Random rng = new Random();

        for (int f = 0; f < fields; f++) {
            System.out.println(rows + " " + cols);
            for (int r = 0; r < rows; r++) {
                StringBuilder row = new StringBuilder();
                for (int c = 0; c < cols; c++) {
                    row.append(rng.nextInt(100) < minePct ? '*' : '.');
                }
                System.out.println(row);
            }
        }

        System.out.println("0 0");
    }
}
