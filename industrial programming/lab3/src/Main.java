import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        processString(line);
        scanner.close();
    }

    public static void processString(String input) {
        String str = input.replaceAll("[^a-zA-Z]", "");
        char[] charArray = str.toCharArray();

        for (char c : charArray) {
            System.out.print(c + "  ");
        }
        System.out.println();

        for (char c : charArray) {
            int ascii = c;
            if (ascii < 95) ascii -= 64;
            else ascii -= 96;
            System.out.print(ascii + "  ");
        }
    }
}
