import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int[][] matrix = readMatrixFromFile("input.txt");
        boolean isSymmetrical = isSymmetrical(matrix);
        swapMaxWithCenter(matrix);

        writeMatrixToFile("output.txt", matrix, isSymmetrical);
    }

    private static int[][] readMatrixFromFile(String fileName) throws IOException {
        Scanner in = new Scanner(Paths.get(fileName));
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
        return matrix;
    }

    private static boolean isSymmetrical(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void swapMaxWithCenter(int[][] matrix) {
        int n = matrix.length;
        int max = matrix[0][0];
        int maxRow = 0;
        int maxColumn = 0;

        for (int i = 0; i < n; i++) {
            if (matrix[i][i] > max) {
                max = matrix[i][i];
                maxRow = i;
                maxColumn = i;
            }
            if (matrix[i][n - i - 1] > max) {
                max = matrix[i][n - i - 1];
                maxRow = i;
                maxColumn = n - i - 1;
            }
        }

        int temp = matrix[maxRow][maxColumn];
        matrix[maxRow][maxColumn] = matrix[n / 2][n / 2];
        matrix[n / 2][n / 2] = temp;
    }

    private static void writeMatrixToFile(String fileName, int[][] matrix, boolean isSymmetrical) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        if (isSymmetrical) {
            out.println("symmetrical matrix");
        } else {
            out.println("non-symmetrical matrix");
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }
        out.close();
    }
}
