package com.orzechowski.lab2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ElementDao {
    @Insert()
    void insert(Phones element);

    @Query("DELETE FROM phones")
    void deleteAll();

    @Query("SELECT * FROM phones ORDER BY producent ASC")
    LiveData<List<Phones>> getAlphabetizedElements();
}