package com.example.c196lynnweidman.DATABASE;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196lynnweidman.DOA.CourseDAO;
import com.example.c196lynnweidman.DOA.ObjectiveDAO;
import com.example.c196lynnweidman.DOA.PerformanceDAO;
import com.example.c196lynnweidman.DOA.TermDAO;
import com.example.c196lynnweidman.ENTITY.CoursesEntity;
import com.example.c196lynnweidman.ENTITY.ObjectiveAssessment;
import com.example.c196lynnweidman.ENTITY.PerformanceAssessment;
import com.example.c196lynnweidman.ENTITY.TermsEntity;

@Database(entities={TermsEntity.class, CoursesEntity.class, ObjectiveAssessment.class, PerformanceAssessment.class}, version=9, exportSchema = false)

public abstract class TermDatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();

    public abstract ObjectiveDAO objectiveDAO();
    public abstract PerformanceDAO performanceDAO();

    private static volatile TermDatabaseBuilder INSTANCE;

    static TermDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE==null) {
        synchronized (TermDatabaseBuilder.class) {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TermDatabaseBuilder.class, "myTermDatabase.db")
                        //.allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
    }
        return INSTANCE;
    }
}
