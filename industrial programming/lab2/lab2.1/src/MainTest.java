import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;

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
    public void testIsSymmetricalWithSymmetricalMatrix() {
        int[][] symmetricalMatrix = {
            {1, 2, 3},
            {2, 4, 5},
            {3, 5, 6}
        };
        assertTrue(Main.isSymmetrical(symmetricalMatrix));
    }

    @Test
    public void testIsSymmetricalWithNonSymmetricalMatrix() {
        int[][] nonSymmetricalMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        assertFalse(Main.isSymmetrical(nonSymmetricalMatrix));
    }

    @Test
    public void testSwapMaxWithCenter() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] expectedMatrix = {
            {9, 2, 3},
            {4, 5, 6},
            {7, 8, 1}
        };

        Main.swapMaxWithCenter(matrix);
        assertArrayEquals(expectedMatrix, matrix);
    }

    @Test
    public void testWriteMatrixToFile() throws IOException {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        String expectedOutput = "non-symmetrical matrix\n1 2 3 \n4 5 6 \n7 8 9 \n";

        Main.writeMatrixToFile("test_output.txt", matrix, false);
        String actualOutput = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("test_output.txt")));

        assertEquals(expectedOutput, actualOutput);
    }

}
