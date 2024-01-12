import static org.junit.Assert.*;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class MainTest {

    @Test
    public void testProcessString() throws IOException {
        String input = "Hello, World! 123";
        String expectedOutput = "H  e  l  l  o  W  o  r  l  d  \n8  5  12  12  15  23  15  18  12  4  ";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        Main.processString(input);

        System.setOut(System.out); 

        String actualOutput = new String(outputStream.toByteArray(), StandardCharsets.UTF_8).trim();

        assertEquals(expectedOutput, actualOutput);
    }

}
