import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String str = line.replaceAll("[^a-zA-Z]", "");
        char[] charArray = str.toCharArray();
        for (char c: charArray) {
            System.out.print(c + "  ");
        }
        System.out.println();
        for (char c : charArray) {
            int ascii = c;
            if (ascii < 95) ascii -= 64;
            else ascii -= 96;
            System.out.print(ascii + "  ");
        }
        scanner.close();
    }
}