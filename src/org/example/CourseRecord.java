package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class CourseRecord {
    private ArrayList<Course> courseList = new ArrayList<>();
    //Reference: "Java: Read a CSV File into an Array"
    // https://www.youtube.com/watch?v=-Aud0cDh-J8
    // Reading from a CSV File and Searching for a Record in Java
    // https://www.youtube.com/watch?v=bIjMDpspzog
    public void readCourse(String path)  {
        //use try catch to catch just in case the file does not exist
        try {
        String line = "";
        //Rather than using scanner, we use buffer instead because just in case of dealing with the big file.
            BufferedReader br = new BufferedReader(new FileReader(path));
            String[] courseDetail = new String[0];
            while ((line = br.readLine()) != null) {
                courseDetail = line.split(",");
                //skip the first line start with course name then create object
                //Skip the first line while creating the course object
                if (!courseDetail[0].equals("Course name")) {
                    Course course = new Course(courseDetail[0], Integer.parseInt(courseDetail[1]), courseDetail[2],
                            courseDetail[3], courseDetail[4], courseDetail[5], Float.parseFloat(courseDetail[6]));
                    courseList.add(course);
                }
            }
        } catch (NullPointerException e) {
            System.err.println("File is null.");
        } catch (FileNotFoundException e){
            System.err.println("File not found.");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Course> searchCourse(InputStream input) throws NoMatchingCoursesException {
            ArrayList<Course> matchingList = new ArrayList<>();
            System.out.println("Please provide a course: ");
            Scanner scanner = new Scanner(input);
            String searchTerm = scanner.nextLine().toLowerCase().strip();
            //loop through course object in the courseList
            for (Course c : courseList) {
                if (c.getCourseName().toLowerCase().contains(searchTerm)) {
                    matchingList.add(c);
                }
            }
            if (matchingList.size() == 0) {
                throw new NoMatchingCoursesException("Error: no matching courses!");
            }
        // return matchingList in order to call from other class
            return matchingList;
            }
    public ArrayList<Course> getCourseList() {
        return courseList;
    }
}