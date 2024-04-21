package org.example;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class ManageEnrolment {
    private ArrayList<Course> enrolmentList = new ArrayList<>();
    public void deleteCourse(InputStream input) {
        try {
            System.out.println("Please select: ");
            Scanner scanner = new Scanner(input);
            int withdraw_choice = Integer.parseInt(scanner.nextLine());
            Course chosen_withdraw = enrolmentList.get(withdraw_choice - 1);
            String chosen_withdraw_name = enrolmentList.get(withdraw_choice - 1).getCourseName();
            // add the object to the list for future use
            enrolmentList.remove(chosen_withdraw);
            //display the course that user just chose to withdraw
            System.out.println("You have withdrawn from " + chosen_withdraw_name + "!");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Please select a valid menu option.");
        } catch (InputMismatchException e) {
            System.err.println("Error: please type again!");
        }
    }
    public void displayEnrolment(){
        // Inspiration for time calculation:
        // https://stackoverflow.com/questions/24296532/calculate-difference-between-two-hours-strings
        if (enrolmentList.size() != 0) {
            for (int i = 0; i < enrolmentList.size(); i++) {
                String start_time = enrolmentList.get(i).getTimeOfLecture();
                String[] start_time_split = start_time.split(":");
                //convert hr from string to integer
                int start_hr = Integer.parseInt(start_time_split[0]);
                //calculate minutes
                int start_min = Integer.parseInt(start_time_split[1]);
                float duration = enrolmentList.get(i).getDurationOfLecture();
                int duration_int= (int)duration;
                float end_min = (duration - duration_int) * 60;
                int end_time_hr = start_hr + duration_int;
                int end_time_min = (int) (start_min + end_min);
                System.out.printf("\t%d) \t%-30s \t%-10s \t%-10s \t%-5s %s %d:%d\n",i+1,enrolmentList.get(i).getCourseName(),
                        enrolmentList.get(i).getDeliveryMode(),enrolmentList.get(i).getDayOfLecture(),
                        enrolmentList.get(i).getTimeOfLecture(),"-",end_time_hr,end_time_min);
            }
        } else {
            System.out.println("You don't have any courses!");
        }
    }
    //pasted the list of matching choice to use un enrolSubject method
    public boolean enrolSubject(ArrayList<Course> searchedCourses, InputStream input) throws DuplicatedEnrolException, IndexOutOfBoundsException {
        Scanner scanner = new Scanner(input);
        int subject_choice = Integer.parseInt(scanner.nextLine());
        //use class name as a type of data
        //assign object to variable
        try {
            // Course is course object
            Course chosen_subject = searchedCourses.get(subject_choice-1);
            //assign name(string) to var
            String chosen_subject_name = chosen_subject.getCourseName();
            // check if the student has enrolled in the same course before
            boolean duplicated_course = false;
            for (Course e : enrolmentList){
                if (e.getCourseName().equals(chosen_subject_name)) {
                    duplicated_course = true;
                }
            }
            if (!duplicated_course) {
                // add the object to the list for future use
                enrolmentList.add(chosen_subject);
                //display the course that user just chose to enrol
                System.out.println("You have enrolled in the course " + chosen_subject_name + "!");
            } else {
                throw new DuplicatedEnrolException("Error: you have already enrolled in this course!");//                System.err.println("Error: you have already enrolled in " + chosen_subject_name + "!");
            }
        } catch (DuplicatedEnrolException e) {
            System.err.println("Error: you have already enrolled in this course!");
        } catch (IndexOutOfBoundsException e) {
            if ( subject_choice == searchedCourses.size() + 1) {
                return true;
            } System.err.println("Out of range!.Please select a valid menu option.");
        }
        return false;
    }
    public ArrayList<Course> getEnrolmentList() {
        return enrolmentList;
    }
    public void setEnrolmentList(ArrayList<Course> enrolmentList) {
        this.enrolmentList = enrolmentList;
    }
}