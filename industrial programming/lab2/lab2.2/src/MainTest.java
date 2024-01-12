import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class MainTest {

    @Test
    public void testReadMatrixFromFile() throws IOException {
        int[][] expectedMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        assertArrayEquals(expectedMatrix, Main.readMatrixFromFile("test_input.txt"));
    }

    @Test
    public void testCountOccurrences() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        Map<Integer, Integer> occurrences = Main.countOccurrences(matrix);

        assertEquals(1, occurrences.get(1).intValue());
        assertEquals(1, occurrences.get(2).intValue());
        assertEquals(1, occurrences.get(3).intValue());
        assertEquals(1, occurrences.get(4).intValue());
        assertEquals(1, occurrences.get(5).intValue());
        assertEquals(1, occurrences.get(6).intValue());
        assertEquals(1, occurrences.get(7).intValue());
        assertEquals(1, occurrences.get(8).intValue());
        assertEquals(1, occurrences.get(9).intValue());
    }

    @Test
    public void testFindMaxDuplicateNumber() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        Map<Integer, Integer> occurrences = Main.countOccurrences(matrix);
        int maxDuplicate = Main.findMaxDuplicateNumber(occurrences);

        assertEquals(0, maxDuplicate);
    }

    @Test
    public void testWriteMaxDuplicateNumberToFile() throws IOException {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        Map<Integer, Integer> occurrences = Main.countOccurrences(matrix);
        int maxDuplicate = Main.findMaxDuplicateNumber(occurrences);

        String expectedOutput = "there are no such numbers";
        String filePath = "test_output.txt";

        Main.writeMaxDuplicateNumberToFile(filePath, maxDuplicate);

        String actualOutput = new String(Files.readAllBytes(Paths.get(filePath)));

        assertEquals(expectedOutput, actualOutput);
    }

}
