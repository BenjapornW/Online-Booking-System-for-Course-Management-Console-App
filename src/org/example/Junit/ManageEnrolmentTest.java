package org.example;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
public class ManageEnrolmentTest {
    private ManageEnrolment manageEnrolment;
    private ByteArrayOutputStream errorMessage;
    private ByteArrayOutputStream outputMessage;
    @Before
    public void setUp() throws Exception {
        manageEnrolment = new ManageEnrolment();
        errorMessage = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorMessage));
        outputMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream( outputMessage));
        Course course = new Course("Java programming", 120, "Year 1",
                "Face-to-face", "Wednesday", "11:30", 2);
        manageEnrolment.getEnrolmentList().add(course);
    }
    @After
    public void tearDown() throws Exception {
        this.manageEnrolment = null;
    }
    //-------------------deleteCourse----------------------------
    // case when the course matches
    @Test
    public void testDeleteCourse_ValidInput() {
        manageEnrolment.deleteCourse(new ByteArrayInputStream("1".getBytes()));
    }
    // case when the course does not match
    // only have 1 course in the enrolmentList but want to delete the 2nd course --> should throw an error
    @Test
    public void testDeleteCourse_InvalidInput() throws IndexOutOfBoundsException {
        manageEnrolment.deleteCourse(new ByteArrayInputStream("2".getBytes()));
        assertEquals("Please select a valid menu option.\n", errorMessage.toString());
    }
    //-------------------displayEnrolment----------------------------
    @Test
    public void testdisplayEnrolment_Valid() {
        manageEnrolment.displayEnrolment();
        assertEquals("\t1) \tJava programming               \tFace-to-face \tWednesday  \t11:30 - 13:30\n", outputMessage.toString());
    }
    //-------------------enrolSubject----------------------------
    @Test
    public void testEnrolCourse_ValidInput() throws DuplicatedEnrolException, NoMatchingCoursesException {
        // Initialize courseRecord object to use its methods
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.readCourse("course.csv");
        //get searchResultList to use as a 1st parameter required by enrolSubject() method
        ArrayList<Course> searchResultList = courseRecord.searchCourse(new ByteArrayInputStream("skills".getBytes()));

        manageEnrolment.enrolSubject(searchResultList, new ByteArrayInputStream("1".getBytes()));
        //check if the program enrol in the intended course
        assertEquals("Programming skills", manageEnrolment.getEnrolmentList().get(1).getCourseName());
    }
    @Test
    public void testEnrolSubject_InvalidInput() throws IndexOutOfBoundsException, DuplicatedEnrolException {
        // Create searchResultList (ArrayList) to use as a 1st parameter required by enrolSubject() method
        ArrayList<Course> searchResultList = new ArrayList<>(Arrays.asList(new Course("Java programming",
                120, "Year 1", "Face-to-face", "Wednesday",
                "11:30", 2), new Course("Programming skills",
                500, "Year 1", "Face-to-face", "Tuesday", "8:30",
                1), new Course("Advanced Python programming", 40, "Year 2",
                "Face-to-face", "Friday", "16:00", 1.5F)
        ));
        // we know from the list that there are only 3 options. The  4th is to go to main menu.
        // So, we will try choosing the 5th choice
        manageEnrolment.enrolSubject(searchResultList, new ByteArrayInputStream("5".getBytes()));
        assertEquals("Out of range!.Please select a valid menu option.\n", errorMessage.toString());
    }
    @Test
    public void testEnrolSubject_Duplicated() throws IndexOutOfBoundsException, DuplicatedEnrolException {
        manageEnrolment.getEnrolmentList().clear();
        // Create searchResultList (ArrayList) to use as a 1st parameter required by enrolSubject() method
        ArrayList<Course> searchedCourses= new ArrayList<>(Arrays.asList(new Course("Java programming",
                120, "Year 1", "Face-to-face", "Wednesday",
                "11:30", 2), new Course("Programming skills",
                500, "Year 1", "Face-to-face", "Tuesday", "8:30",
                1), new Course("Advanced Python programming", 40, "Year 2",
                "Face-to-face", "Friday", "16:00", 1.5F)
        ));
        // we know from the list that there are only 3 options. Then, we will try to choose enrol with the same course
        manageEnrolment.setEnrolmentList(searchedCourses);
        manageEnrolment.enrolSubject(searchedCourses, new ByteArrayInputStream("1".getBytes()));
        assertEquals("Error: you have already enrolled in this course!\n", errorMessage.toString());
    }
}




