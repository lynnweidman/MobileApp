package com.example.c196lynnweidman.DOA;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196lynnweidman.ENTITY.CoursesEntity;

import java.util.List;
@Dao
public abstract interface CourseDAO  {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CoursesEntity courses );

    @Update
    void update(CoursesEntity courses);

    @Delete
    void delete(CoursesEntity courses);

    @Query("SELECT * FROM courses")
    List<CoursesEntity> getAllCourses();
}
