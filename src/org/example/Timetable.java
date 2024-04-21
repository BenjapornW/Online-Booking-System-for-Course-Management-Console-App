package org.example;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class Timetable {
    private final String name;
    private CourseRecord courseRecord;
    private ManageEnrolment manageEnrolment;
    public Timetable(String name, CourseRecord courseRecord, ManageEnrolment manageEnrolment) {
        this.name = name;
        this.courseRecord = courseRecord;
        this.manageEnrolment = manageEnrolment;
    }
    public String getName() {
        return this.name;
    }
    /*
     * The method to operate the online Booking System for Course management.
     */
    public void run() throws DuplicatedEnrolException, NoMatchingCoursesException {
        //when the program starts it will read the course list from the csv file first.
        courseRecord.readCourse("course.csv");
        mainMenu();
    }
    private void mainMenu() throws DuplicatedEnrolException, NoMatchingCoursesException {
        boolean exit = false;
        do {
            printMenu(this.getName());
            String stringInput;
            try {
                stringInput = String.valueOf(readUserInput(System.in));
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Please select a valid menu option.");
                continue;
            }
            char choice = stringInput.charAt(0);

            switch (choice) {
                case '1':
                    try {
                    ArrayList<Course> searchedCourses = courseRecord.searchCourse(System.in);
                    System.out.println("------------------------------------------------------------------------------------------"
                            + "\nSelect from matching list:"
                            + "\n------------------------------------------------------------------------------------------");
                        for (int i = 0; i < searchedCourses.size(); i++) {
                            System.out.printf("\t%d) %s\n", i + 1, searchedCourses.get(i).getCourseName());
                        }
                        System.out.printf("\t%d) %s\n", searchedCourses.size() + 1, "Go to main menu");

                        manageEnrolment.enrolSubject(searchedCourses,System.in);

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please select a valid menu option.");
                        continue;
                    } catch (NoMatchingCoursesException e) {
                        System.out.println("Error: no matching courses!.");
                        continue;
                    }
                    // Use break when the program finds the right function to be executed
                    // The program will ignore the rest of the code. Hence,this will save a lot of execution time
                    break;
                case '2':
                    System.out.println("\n------------------------------------------------------------------------------------------"
                            + "\nYou have enrolled in the following course(s):"
                            + "\n------------------------------------------------------------------------------------------");
                    manageEnrolment.displayEnrolment();
                    break;
                case '3':
                    System.out.println("------------------------------------------------------------------------------------------"
                            + "\nPlease choose a course to withdraw:"
                            + "\n------------------------------------------------------------------------------------------");
                    manageEnrolment.displayEnrolment();
                    manageEnrolment.deleteCourse(System.in);
                    break;
                case '4':
                    exit = true;
                    break;
                //default case when user inserts the choice number that does not exist in our program
                default:
                    System.out.println("Please select a valid menu option.");
                    break;
            }
        } while (!exit);
    }
    /**
     * The utility method to read user input.
     */
    public static char readUserInput(InputStream input) throws IndexOutOfBoundsException {
        //Call scanner to read the inserted value
        Scanner sc = new Scanner(input);
        String stringInput = sc.nextLine();
        return stringInput.charAt(0);
    }
    /**
     * The utility method to print menu options.
     */
    public static void printMenu(String name){
        System.out.println();
        String banner = new String(new char[90]).replace('\u0000', '-');
        System.out.println(banner + "\n" + "Welcome to " + name + "\n" + banner);
        System.out.printf("   %s%n", "1) Search by keyword to enroll");
        System.out.printf("   %s%n", "2) Show my enrolled courses");
        System.out.printf("   %s%n", "3) Withdraw from a course");
        System.out.printf("   %s%n", "4) Exit");
        System.out.print("Please select: ");
    }
}