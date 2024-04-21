package org.example;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.Assert.*;
public class CourseRecordTest {
    private CourseRecord courseRecord;
    private ByteArrayOutputStream errorMessage;
    @Before
    public void setUp() throws Exception {
        courseRecord = new CourseRecord();
        errorMessage = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorMessage));
    }
    @After
    public void tearDown() throws Exception {
            this.courseRecord = null;
    }
    //-------------------readCourse----------------------------
    @Test
    public void testReadCourse_NullPath() {
        courseRecord.readCourse(null);
        assertEquals("File is null.\n", errorMessage.toString());
    }
    @Test
    public void testReadCourse_FileNotFound() {
        courseRecord.readCourse("FileDoesNotExist.txt");
        assertEquals("File not found.\n",errorMessage.toString());
    }
    // create object and use getter to test with assertEquals
    @Test
    public void testReadCourse_ValidPath() throws IOException {
        courseRecord.readCourse("course.csv");
        assertEquals(7, courseRecord.getCourseList().size());
        Course course = new Course("Java programming", 120, "Year 1",
                "Face-to-face", "Wednesday", "11:30",2);
        assertEquals(course.getCourseName(), courseRecord.getCourseList().get(0).getCourseName());
        assertEquals(course.getCapacity(), courseRecord.getCourseList().get(0).getCapacity(), 0.0);
    }
    //-------------------searchCourse----------------------------
    // case when the course does not match
    @Test (expected = NoMatchingCoursesException.class)
    public void testSearchCourse_InvalidInput() throws NoMatchingCoursesException {
        courseRecord.searchCourse(new ByteArrayInputStream("jfr".getBytes()));
    }
    // case when the course matches
    @Test
    public void testSearchCourse_ValidInput() throws NoMatchingCoursesException {
        courseRecord.readCourse("course.csv");
        ArrayList<Course> searchResultList = courseRecord.searchCourse(new ByteArrayInputStream("programming".getBytes()));
        assertEquals("Java programming", searchResultList.get(0).getCourseName());
    }

}