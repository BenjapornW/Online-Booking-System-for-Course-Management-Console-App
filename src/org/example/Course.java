package org.example;
public class Course {
    private String courseName;
    private int capacity;
    private String year;
    private String deliveryMode;
    private String dayOfLecture;
    private String timeOfLecture;
    private float durationOfLecture;
    public Course(String courseName, int capacity, String year, String deliveryMode, String dayOfLecture,
                  String timeOfLecture, float durationOfLecture) {
        this.courseName = courseName;
        this.capacity = capacity;
        this.year = year;
        this.deliveryMode = deliveryMode;
        this.dayOfLecture = dayOfLecture;
        this.timeOfLecture = timeOfLecture;
        this.durationOfLecture = durationOfLecture;
    }
    //use getter to access private var
    public String getCourseName() {
        return courseName;
    }
    public int getCapacity() { return capacity; }
    public String getDeliveryMode() {
        return deliveryMode;
    }
    public String getDayOfLecture() {
        return dayOfLecture;
    }
    public float getDurationOfLecture() {
        return durationOfLecture;
    }
    public String getTimeOfLecture() {
        return timeOfLecture;
    }
}
