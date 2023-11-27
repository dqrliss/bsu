import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("input.txt"));
        boolean flag = true;
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        while (in.hasNextInt()) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = in.nextInt();
                }
            }
        }
        in.close();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    flag = false;
                    break;
                }
            }
        }
        int max = matrix[0][0];
        int max_row = 0; int max_column = 0;
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] > max) {
                max = matrix[i][i];
                max_row = i; max_column = i;
            }
            if (matrix[i][n - i - 1] > max) {
                max = matrix[i][n - i - 1];
                max_row = i; max_column = n - i - 1;
            }
        }
        int temp = matrix[max_row][max_column];
        matrix [max_row][max_column] = matrix[n / 2][n / 2];
        matrix [n / 2][n / 2] = temp;
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        if (flag) out.println("symmetrical matrix");
        else out.println("non-symmetrical matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }
        out.close();
    }
}