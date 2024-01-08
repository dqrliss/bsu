package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;

public class calcTest {

    @Test
    public void fibnumsTest() throws NullElementsException, big_num, NumberFormatException {
        calc calc = new calc();
        ArrayList<BigInteger> expectedList = new ArrayList<>();
        expectedList.add(new BigInteger("1"));
        expectedList.add(new BigInteger("1"));
        expectedList.add(new BigInteger("2"));
        expectedList.add(new BigInteger("3"));
        expectedList.add(new BigInteger("5"));

        ArrayList<BigInteger> reslist = calc.fibnums(new BigInteger("5"));

        for (int i = 0; i < expectedList.size(); i++)
            assertEquals(expectedList.get(i), reslist.get(i));
    }

    @Test
    public void emptylistfibnumsTest() {
        calc calc = new calc();
        try {
            ArrayList<BigInteger> result = calc.fibnums(BigInteger.ZERO);
            assertTrue(result.isEmpty());
        } catch (Exception e) {
            fail("exception thrown");
        }
    }

    @Test
    public void largefibnumsTest() {
        calc calc = new calc();
        try {
            calc.fibnums(new BigInteger("100000"));
        } catch (big_num e) {
            fail("number was too big");
        } catch (Exception e) {
            fail("exception thrown");
        }
    }
}