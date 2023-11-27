import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("input.txt"));
        int n = Integer.parseInt(in.nextLine());
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(in.nextLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        in.close();
        Map <Integer, Integer> q = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int counter = q.getOrDefault(matrix[i][j], 0) + 1;
                q.put(matrix[i][j], counter);
                if (counter > 1 && matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        if (max == Integer.MIN_VALUE) out.print("there are no such numbers");
        else out.print(max);
        out.close();
    }
}