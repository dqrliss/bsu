import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainTest {

    @Test
    public void testReadMatrixFromFile() throws IOException {
        int[][] expectedMatrix = {
            {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE},
            {Integer.MIN_VALUE, 1, 2},
            {Integer.MIN_VALUE, 3, 4}
        };

        assertArrayEquals(expectedMatrix, Main.readMatrixFromFile("test_input.txt"));
    }

    @Test
    public void testCountLocalMaxima() {
        int[][] matrix = {
            {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE},
            {Integer.MIN_VALUE, 1, 2},
            {Integer.MIN_VALUE, 3, 4}
        };

        int[][] dp = Main.countLocalMaxima(matrix);

        int[][] expectedDP = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 0, 0}
        };

        assertArrayEquals(expectedDP, dp);
    }

    @Test
    public void testWriteResultToFile() throws IOException {
        int[][] matrix = {
            {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE},
            {Integer.MIN_VALUE, 1, 2},
            {Integer.MIN_VALUE, 3, 4}
        };

        int[][] dp = Main.countLocalMaxima(matrix);

        String expectedOutput = "2";
        String filePath = "test_output.txt";

        Main.writeResultToFile(filePath, dp);

        String actualOutput = new String(Files.readAllBytes(Paths.get(filePath)));

        assertEquals(expectedOutput, actualOutput.trim());
    }

}
