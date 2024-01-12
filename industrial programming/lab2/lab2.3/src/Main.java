import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("input.txt"));
        int n = Integer.parseInt(in.nextLine());
        int[][] matrix = readMatrixFromFile(n, in);
        in.close();

        int[][] dp = countLocalMaxima(matrix);
        int minLocalMaxima = findMinLocalMaxima(matrix, dp);

        writeResultToFile("output.txt", minLocalMaxima);
    }

    public static int[][] readMatrixFromFile(int n, Scanner in) {
        int[][] matrix = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            matrix[i][0] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < n + 1; i++) {
            matrix[0][i] = Integer.MIN_VALUE;
        }
        Pattern pattern = Pattern.compile("\\s");
        for (int i = 1; i < n + 1; i++) {
            String[] row = pattern.split(in.nextLine().trim());
            for (int j = 1; j < n + 1; j++) {
                matrix[i][j] = Integer.parseInt(row[j - 1]);
            }
        }
        return matrix;
    }

    public static int[][] countLocalMaxima(int[][] matrix) {
        int n = matrix.length - 1;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == n && i == n) {
                    if (dp[i][j - 1] == 1 || dp[i - 1][j - 1] == 1 || dp[i - 1][j] == 1) {
                        dp[i][j] = 0;
                        continue;
                    }
                    if (matrix[i][j] > matrix[i - 1][j - 1] && matrix[i][j] > matrix[i - 1][j] && matrix[i][j] > matrix[i][j - 1]) {
                        dp[i][j] = 1;
                        continue;
                    }
                    dp[i][j] = 0;
                    continue;
                }
                if (j == n) {
                    if (dp[i][j - 1] == 1 || dp[i - 1][j - 1] == 1 || dp[i - 1][j] == 1) {
                        dp[i][j] = 0;
                        continue;
                    }
                    if (matrix[i][j] > matrix[i - 1][j - 1] && matrix[i][j] > matrix[i - 1][j] && matrix[i][j] > matrix[i][j - 1] && matrix[i][j] > matrix[i + 1][j - 1] && matrix[i][j] > matrix[i + 1][j]) {
                        dp[i][j] = 1;
                        continue;
                    }
                    dp[i][j] = 0;
                    continue;
                }
                if (i == n) {
                    if (dp[i][j - 1] == 1 || dp[i - 1][j - 1] == 1 || dp[i - 1][j] == 1 || dp[i - 1][j + 1] == 1) {
                        dp[i][j] = 0;
                        continue;
                    }
                    if (matrix[i][j] > matrix[i - 1][j - 1] && matrix[i][j] > matrix[i - 1][j] && matrix[i][j] > matrix[i][j - 1] && matrix[i][j] > matrix[i - 1][j + 1] && matrix[i][j] > matrix[i][j + 1]) {
                        dp[i][j] = 1;
                        continue;
                    }
                    dp[i][j] = 0;
                    continue;
                }
                if (dp[i][j - 1] == 1 || dp[i - 1][j - 1] == 1 || dp[i - 1][j] == 1 || dp[i - 1][j + 1] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (matrix[i][j] > matrix[i - 1][j - 1] && matrix[i][j] > matrix[i - 1][j] && matrix[i][j] > matrix[i - 1][j + 1] && matrix[i][j] > matrix[i][j - 1] && matrix[i][j] > matrix[i][j + 1] && matrix[i][j] > matrix[i + 1][j - 1] && matrix[i][j] > matrix[i + 1][j] && matrix[i][j] > matrix[i + 1][j + 1]) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = 0;
            }
        }
        return dp;
    }

    public static int findMinLocalMaxima(int[][] matrix, int[][] dp) {
        int minLocalMaxima = Integer.MAX_VALUE;
        int n = matrix.length - 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == 1 && matrix[i][j] < minLocalMaxima) {
                    minLocalMaxima = matrix[i][j];
                }
            }
        }
        return minLocalMaxima;
    }

    public static void writeResultToFile(String fileName, int minLocalMaxima) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        if (minLocalMaxima == Integer.MAX_VALUE) {
            out.print("there are no local maxima");
        } else {
            out.print(minLocalMaxima);
        }
        out.close();
    }
}
