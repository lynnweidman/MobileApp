package com.example.c196lynnweidman.DOA;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import com.example.c196lynnweidman.ENTITY.PerformanceAssessment;

import java.util.List;

@Dao
public abstract interface PerformanceDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PerformanceAssessment performanceAssessment);

    @Update
    void update(PerformanceAssessment performanceAssessment);

    @Delete
    void delete(PerformanceAssessment performanceAssessment);

    @Query("SELECT * FROM performanceAssessment ")
    List<PerformanceAssessment> getAllPerformanceAssessments();
}
