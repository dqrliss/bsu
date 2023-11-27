import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("input.txt"));
        int n = Integer.parseInt(in.nextLine());
        int[][] matrix = new int[n + 1][n + 1];
        for(int i = 0; i < n + 1; i++) {
            matrix[i][0] = Integer.MIN_VALUE;
        }
        for(int i = 0; i < n + 1; i++) {
            matrix[0][i] = Integer.MIN_VALUE;
        }
        Pattern pattern = Pattern.compile("\\s");
        for (int i = 1; i < n + 1; i++) {
            String[] row = pattern.split(in.nextLine().trim());
            for (int j = 1; j < n + 1; j++) {
                matrix[i][j] = Integer.parseInt(row[j - 1]);
            }
        }
        in.close();
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for(int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j == n && i == n) {
                    if (dp[i][j - 1] == 1 || dp[i - 1][j - 1] == 1 || dp[i - 1][j] == 1) {
                        dp[i][j] = 0;
                        continue;
                    }
                    if (matrix[i][j] > matrix[i - 1][j - 1] && matrix[i][j] > matrix[i - 1][j] && matrix[i][j] > matrix[i][j - 1]) {
                        dp[i][j] = 1;
                        if (matrix[i][j] < min) min = matrix[i][j];
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
                        if (matrix[i][j] < min) min = matrix[i][j];
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
                        if (matrix[i][j] < min) min = matrix[i][j];
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
                    if (matrix[i][j] < min) min = matrix[i][j];
                    continue;
                }
                dp[i][j] = 0;
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        if (min == Integer.MAX_VALUE) out.print("there are no local maxima");
        else out.print(min);
        out.close();
    }
}