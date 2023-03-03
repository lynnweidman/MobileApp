package com.example.c196lynnweidman.ENTITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.os.Bundle;

import com.example.c196lynnweidman.R;

@Entity(tableName = "objectiveAssessment")

public class ObjectiveAssessment  {
    @PrimaryKey(autoGenerate = true)


    private int objectiveID;
    private String objectiveAssessmentName;
    private String objectiveAssessmentDate;
    private int courseID;

    public ObjectiveAssessment(int objectiveID, String objectiveAssessmentName, String objectiveAssessmentDate, int courseID) {
        this.objectiveID = objectiveID;
        this.objectiveAssessmentName = objectiveAssessmentName;
        this.objectiveAssessmentDate = objectiveAssessmentDate;
        this.courseID = courseID;
    }

    public int getObjectiveID() {
        return objectiveID;
    }

    public void setObjectiveID(int objectiveID) {
        this.objectiveID = objectiveID;
    }

    public String getObjectiveAssessmentName() {
        return objectiveAssessmentName;
    }

    public void setObjectiveAssessmentName(String objectiveAssessmentName) {
        this.objectiveAssessmentName = objectiveAssessmentName;
    }

    public String getObjectiveAssessmentDate() {
        return objectiveAssessmentDate;
    }

    public void setObjectiveAssessmentDate(String objectiveAssessmentDate) {
        this.objectiveAssessmentDate = objectiveAssessmentDate;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "ObjectiveAssessment{" +
                "objectiveID=" + objectiveID + '\'' +
                ", objectiveAssessmentName='" + objectiveAssessmentName + '\'' +
                ", objectiveAssessmentDate='" + objectiveAssessmentDate + '\'' +
                ", courseID=" + courseID +
                '}';
    }
}