package com.example.sem_project_javafx;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class for_xmlTest {

    @Test
    void parse1() {
        List<String> expected = new ArrayList<>();
        expected.add("2 - 2 * 2");

        for_xml xml = new for_xml();
        assertEquals(expected, xml.parse("for_mini_test.xml"));
    }

    @Test
    void parse2() {
        List<String> expected = new ArrayList<>();
        expected.add("2 - 21 / 3");
        expected.add("-8 + 11");
        expected.add("3 + 2 * 2");
        expected.add("4 * (2 - 8)");

        for_xml xml = new for_xml();
        assertEquals(expected, xml.parse("for_test.xml"));
    }
}
