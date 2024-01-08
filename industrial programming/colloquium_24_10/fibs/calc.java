package org.example;

import java.math.BigInteger;
import java.util.ArrayList;

public class calc {
    public ArrayList<BigInteger> fibnums(BigInteger inputAmount) throws big_num, NumberFormatException {
        if (inputAmount.compareTo (new BigInteger(Integer.toString(Integer.MAX_VALUE))) > 0)
            throw new big_num();
        else if (inputAmount.compareTo (new BigInteger(Integer.toString(Integer.MIN_VALUE))) < 0)
            throw new NumberFormatException();

        int amount = inputAmount.intValue();
        ArrayList<BigInteger> fiblist = new ArrayList<>();

        if (amount == 0)
            return fiblist;

        else if (amount == 1) {
            fiblist.add(new BigInteger("1"));
            return fiblist;
        }

        else {
            fiblist.add(new BigInteger("1"));
            fiblist.add(new BigInteger("1"));

            for (int i = 2; i < amount; i++) {
                BigInteger previousNumber = fiblist.get(i-1);
                BigInteger prePreviousNumber = fiblist.get(i-2);
                BigInteger result = previousNumber.add(prePreviousNumber);
                fiblist.add(result);
            }
        }
        return fiblist;
    }
}