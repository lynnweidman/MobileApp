package com.example.c196lynnweidman.ENTITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.os.Bundle;

import com.example.c196lynnweidman.R;

@Entity(tableName = "performanceAssessment")

public class PerformanceAssessment{
@PrimaryKey(autoGenerate = true)

private int performanceID;
private String performanceAssessmentName;
private String performanceAssessmentDate;
private int courseID;

        public PerformanceAssessment(int performanceID, String performanceAssessmentName, String performanceAssessmentDate, int courseID) {

                this.performanceID = performanceID;
                this.performanceAssessmentName = performanceAssessmentName;
                this.performanceAssessmentDate = performanceAssessmentDate;
                this.courseID = courseID;
        }

        public int getPerformanceID() {
                return performanceID;
        }

        public void setPerformanceID(int performanceID) {
                this.performanceID = performanceID;
        }

        public String getPerformanceAssessmentName() {
                return performanceAssessmentName;
        }

        public void setPerformanceAssessmentName(String performanceAssessmentName) {
                this.performanceAssessmentName = performanceAssessmentName;
        }

        public String getPerformanceAssessmentDate() {
                return performanceAssessmentDate;
        }

        public void setPerformanceAssessmentDate(String performanceAssessmentDate) {
                this.performanceAssessmentDate = performanceAssessmentDate;
        }

        public int getCourseID() {
                return courseID;
        }

        public void setCourseID(int courseID) {
                this.courseID = courseID;
        }

        @Override
        public String toString() {
                return "PerformanceAssessment{" +
                        "performanceID=" + performanceID +
                        ", performanceAssessmentName='" + performanceAssessmentName + '\'' +
                        ", performanceAssessmentDate='" + performanceAssessmentDate + '\'' +
                        ", courseID=" + courseID +
                        '}';
        }
}
