import static org.junit.Assert.*;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class st_recordbookTest {

    @Test
    public void testCheckingForExcellentStudent() {
        st_recordbook stRecordbook = new st_recordbook();

        // Set up a session with an excellent student
        st_recordbook.session session = new st_recordbook.session();
        st_recordbook.session.exam exam = new st_recordbook.session.exam();
        exam.mark = 10;
        session.exams.add(exam);
        st_recordbook.session.zachet zachet = new st_recordbook.session.zachet();
        zachet.zachet = true;
        session.zachets.add(zachet);
        stRecordbook.sessions.add(session);

        assertTrue(stRecordbook.checking_for_excellent_student());
    }

    @Test
    public void testAverageMark() {
        st_recordbook stRecordbook = new st_recordbook();

        // Set up a session with marks
        st_recordbook.session session = new st_recordbook.session();
        st_recordbook.session.exam exam1 = new st_recordbook.session.exam();
        exam1.mark = 8;
        session.exams.add(exam1);
        st_recordbook.session.exam exam2 = new st_recordbook.session.exam();
        exam2.mark = 9;
        session.exams.add(exam2);
        stRecordbook.sessions.add(session);

        double expectedAverage = (8 + 9) / 2.0;
        assertEquals(expectedAverage, session.average_mark(), 0.001);
    }

    @Test
    public void testInputAndOutput() throws IOException {
        st_recordbook stRecordbook = new st_recordbook();
        String input = "123\nJohn Doe\n2\n1\nMath 10\n1\nEnglish true\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        stRecordbook.input(new Scanner(inputStream));

        assertEquals(123, stRecordbook.get_zachetka());
        assertEquals("John Doe", stRecordbook.get_name());
        assertEquals(2, stRecordbook.sessions.size());
        assertEquals(1, stRecordbook.sessions.get(0).number_of_exams);
        assertEquals(1, stRecordbook.sessions.get(0).number_of_zachets);
        assertTrue(stRecordbook.sessions.get(0).exams.get(0).checking_for_excellent_student());
        assertTrue(stRecordbook.sessions.get(0).zachets.get(0).checking_for_excellent_student());

        // Set up the output stream for testing output
        StringBuilder outputBuilder = new StringBuilder();
        stRecordbook.output(new PrintWriter(System.out) {
            @Override
            public void print(String s) {
                outputBuilder.append(s);
            }
        });

        String expectedOutput = "number of recordbook: 123\n" +
                                "student's full name: John Doe\n" +
                                "number of sessions: 2\n\n" +
                                "number of session: 1\n" +
                                "number of exams: 1\n" +
                                "subject: Math, mark: 10\n" +
                                "number of zachets: 1\n" +
                                "subject: English, zachet: true\n\n";

        assertEquals(expectedOutput, outputBuilder.toString());
    }

    @Test
    public void testComparable() {
        st_recordbook recordbook1 = new st_recordbook(123);
        st_recordbook recordbook2 = new st_recordbook(456);
        st_recordbook recordbook3 = new st_recordbook(789);

        assertTrue(recordbook1.compareTo(recordbook2) < 0);
        assertTrue(recordbook2.compareTo(recordbook3) < 0);
        assertTrue(recordbook1.compareTo(recordbook3) < 0);
    }

}
