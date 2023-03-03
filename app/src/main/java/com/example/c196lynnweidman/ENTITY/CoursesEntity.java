package com.example.c196lynnweidman.ENTITY;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")

public class CoursesEntity {

    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseName;
    private String start;
    private String end;
    private String status;
    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;
    private int termID;
    private String note;




    public CoursesEntity(int courseID, String courseName, String start, String end, String status, String instructorName, String instructorPhone, String instructorEmail, int termID, String note) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.start = start;
        this.end = end;
        this.status = status;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.termID = termID;
        this.note = note;
    }



    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



    @Override
    public String toString() {
        return "courses{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", status='" + status + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorPhone='" + instructorPhone + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                ", termID=" + termID + '\'' +
                ", note=" + note+
                '}';
    }
}
