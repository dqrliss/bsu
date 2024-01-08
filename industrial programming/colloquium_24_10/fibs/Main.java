package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        System.out.println("enter the number of first Fibonacci numbers:");
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        ArrayList<BigInteger> fiblist = new ArrayList<>();
        try {
            String inputStr = bufferedReader.readLine();
            BigInteger bigNumbersAmount = new BigInteger(inputStr);
            calc calc = new calc();
            fiblist = calc.fibnums(bigNumbersAmount);
        }
        catch (big_num exception) {
            System.out.println("num is too big");
            System.exit(0);
        }
        catch (NumberFormatException exception) {
            System.out.println("invalid number format");
            System.exit(0);
        }
        catch (IOException exception) {
            System.out.println("wrong number");
            System.exit(0);
        }
        for (BigInteger el : fiblist)
            System.out.print(el + " ");

    }
}