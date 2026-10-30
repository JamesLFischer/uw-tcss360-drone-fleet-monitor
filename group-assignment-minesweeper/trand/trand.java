// Author: Dat Tran

import java.util.Scanner;

public class trand {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int fieldNumber = 1;
    boolean firstOutput = true;

    while (true) {
      int rows = input.nextInt();
      int cols = input.nextInt();

      if (rows == 0 && cols == 0) break;

      char[][] field = new char[rows][cols];

      for (int i = 0; i < rows; i++) {
        String line = input.next();
        for (int j = 0; j < cols; j++) {
          field[i][j] = line.charAt(j);
        }
      }

      char[][] result = new char[rows][cols];

      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          if (field[i][j] == '*') {
            result[i][j] = '*';
          } else {
            result[i][j] = (char) ('0' + count(field, i, j));
          }
        }
      }

      if (!firstOutput) System.out.println();

      System.out.println("Field #" + fieldNumber + ":");

      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          System.out.print(result[i][j]);
        }
        System.out.println();
      }

      firstOutput = false;
      fieldNumber++;
    }

    input.close();
  }

  public static int count(char[][] field, int r, int c) {
    int count = 0;

    for (int i = r - 1; i <= r + 1; i++) {
      for (int j = c - 1; j <= c + 1; j++) {
        if (i >= 0 && i < field.length && j >= 0 && j < field[0].length) {
          if (!(i == r && j == c) && field[i][j] == '*') {
            count++;
          }
        }
      }
    }

    return count;
  }
}
