import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("input.txt"));
        int n = Integer.parseInt(in.nextLine());
        int[][] matrix = readMatrixFromFile(n, in);
        in.close();
        
        Map<Integer, Integer> occurrences = countOccurrences(matrix);
        int maxDuplicate = findMaxDuplicateNumber(occurrences);
        
        writeMaxDuplicateNumberToFile("output.txt", maxDuplicate);
    }

    public static int[][] readMatrixFromFile(int n, Scanner in) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(in.nextLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        return matrix;
    }

    public static Map<Integer, Integer> countOccurrences(int[][] matrix) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int counter = occurrences.getOrDefault(matrix[i][j], 0) + 1;
                occurrences.put(matrix[i][j], counter);
            }
        }
        return occurrences;
    }

    public static int findMaxDuplicateNumber(Map<Integer, Integer> occurrences) {
        int maxDuplicate = 0;
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            if (entry.getValue() > 1 && entry.getKey() > maxDuplicate) {
                maxDuplicate = entry.getKey();
            }
        }
        return maxDuplicate;
    }

    public static void writeMaxDuplicateNumberToFile(String fileName, int maxDuplicate) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        if (maxDuplicate == 0) {
            out.print("there are no such numbers");
        } else {
            out.print(maxDuplicate);
        }
        out.close();
    }
}
