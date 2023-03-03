package com.example.c196lynnweidman.DOA;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.c196lynnweidman.ENTITY.ObjectiveAssessment;

import java.util.List;

@Dao
public abstract interface ObjectiveDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ObjectiveAssessment objectiveAssessment);

    @Update
    void update(ObjectiveAssessment objectiveAssessment);

    @Delete
    void delete(ObjectiveAssessment objectiveAssessment);

    @Query("SELECT * FROM objectiveAssessment ")
    List<ObjectiveAssessment> getAllObjectiveAssessments();

}
