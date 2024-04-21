
//Assignment 1 - Online Booking System for Course Management
//S3961136
//Benjaporn Wongmayura

package org.example;
public class Main {
    public static void main(String args[]) throws DuplicatedEnrolException, NoMatchingCoursesException {
        CourseRecord courseRecord = new CourseRecord();
        ManageEnrolment manageEnrolment = new ManageEnrolment();
        Timetable mytimetable = new Timetable("MyTimetable!", courseRecord, manageEnrolment);
        mytimetable.run();
    }
}