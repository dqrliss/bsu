package org.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

class factory_of_toysTest {

    @org.junit.jupiter.api.Test
    public void plastic_toysTest() {
        factory_of_toys plastic_toy = new plastic_toys();

        piggy plastic_piggy = plastic_toy.create_piggy();
        duck plastic_duck = plastic_toy.create_duck();

        assertEquals("свинка из пластика", print_piggy(plastic_piggy));
        assertEquals("уточка из пластика", print_duck(plastic_duck));
    }

    @org.junit.jupiter.api.Test
    public void plush_toysTest() {
        factory_of_toys plush_toy = new plush_toys();

        piggy plush_piggy = plush_toy.create_piggy();
        duck plush_duck = plush_toy.create_duck();

        assertEquals("плюшевая свинка", print_piggy(plush_piggy));
        assertEquals("плюшевая уточка", print_duck(plush_duck));
    }


    private String print_piggy(piggy piggy) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os);
        PrintStream old_stream = System.out;
        System.setOut(stream);
        piggy.print();
        System.setOut(old_stream);
        return os.toString().trim();
    }

    private String print_duck(duck duck) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os);
        PrintStream old_stream = System.out;
        System.setOut(stream);
        duck.print();
        System.setOut(old_stream);
        return os.toString().trim();
    }
}