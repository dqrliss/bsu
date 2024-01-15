package com.example.sem_project_javafx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calculate_by_regexpsTest {

    @Test
    void calculate1() {
        calculate_helper calc = new calculate_helper();
        calc.setCalc(new calculate_by_library());

        double result = calc.calculate("8 + 3 * 2");

        assertEquals(14.0, result);
    }

    @Test
    void calculate2() {
        calculate_helper calc = new calculate_helper();
        calc.setCalc(new calculate_by_library());

        double result = calc.calculate("6 + 7 - 22");

        assertEquals(-9.0, result);
    }

    @Test
    void calculate3() {
        calculate_helper calc = new calculate_helper();
        calc.setCalc(new calculate_by_library());

        double result = calc.calculate("2 * (1 - 6) / 14");

        assertEquals((double) -5/7, result);
    }

    @Test
    void calculate4() {
        calculate_helper calc = new calculate_helper();
        calc.setCalc(new calculate_by_library());

        double result = calc.calculate("2.15 + 1.2 * 3");

        assertEquals(5.75, result);
    }

    @Test
    void calculate5() {
        calculate_helper calc = new calculate_helper();
        calc.setCalc(new calculate_by_library());

        double result = calc.calculate("-18 / (3 * 2)");

        assertEquals(-3, result);
    }

    @Test
    void calculate6() {
        calculate_helper calc = new calculate_helper();
        calc.setCalc(new calculate_by_library());

        double result = calc.calculate("-18 / 3 * 2");

        assertEquals(-12, result);
    }

    @Test
    void calculate7() {
        calculate_helper calc = new calculate_helper();
        calc.setCalc(new calculate_by_library());

        double result = calc.calculate("2.8 * 1.4 + 3.2 / 0.4 - 1");

        assertEquals(10.92, result);
    }
}