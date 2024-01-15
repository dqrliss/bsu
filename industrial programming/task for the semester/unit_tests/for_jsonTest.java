package com.example.sem_project_javafx;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class for_jsonTest {

    @Test
    void parse1() {
        List<String> expected = new ArrayList<>();
        expected.add("2 - 2 * 2");

        for_json json = new for_json();
        assertEquals(expected, json.parse("for_mini_test.json"));
    }

    @Test
    void parse2() {
        List<String> expected = new ArrayList<>();
        expected.add("2 - 21 / 3");
        expected.add("-8 + 11");
        expected.add("3 + 2 * 2");
        expected.add("4 * (2 - 8)");

        for_json json = new for_json();
        assertEquals(expected, json.parse("for_test.json"));
    }
}