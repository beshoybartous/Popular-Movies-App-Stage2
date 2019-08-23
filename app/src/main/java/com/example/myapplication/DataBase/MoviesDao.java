package com.example.myapplication.DataBase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Models.MoviesDB;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(MoviesDB movies);
    @Update
    void Update (MoviesDB movies);
    @Delete
    void Delete(MoviesDB movies);
    @Query( "Delete From movies_table" )
    void DeleteALLNotes();
    @Query( "Select * from movies_table" )
    LiveData<List<MoviesDB>> getAllMovies();
    @NonNull
    @Query( "Select id from movies_table where id= :id" )
    List<Integer> checkMovie(int id);

    @Query( "Select * from movies_table where id= :id" )
    List<MoviesDB> getMovie(int id);

}
