package com.example.c196lynnweidman.DOA;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196lynnweidman.ENTITY.TermsEntity;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TermsEntity term);

    @Update
    void update(TermsEntity term);

    @Delete
    void delete(TermsEntity term);

    @Query("SELECT * FROM terms ORDER BY termID ASC")
    List<TermsEntity> getAllTerms();

}
