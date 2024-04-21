package org.example;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;
public class TimetableTest {
    private ByteArrayOutputStream errorMessage;
    @Before
    public void setUp() throws Exception {
        errorMessage = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorMessage));
    }
    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testReadUserInput_ValidInput() {
        char input = Timetable.readUserInput(new ByteArrayInputStream("1234".getBytes()));
        assertEquals('1', input);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void testReadUserInput_EmptyInput() {
        Timetable.readUserInput(new ByteArrayInputStream("\n".getBytes()));
    }
}