package com.example.myapplication.DataBase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.myapplication.Models.MoviesDB;

@Database(entities={MoviesDB.class},version=1,exportSchema = false)
public abstract class MoviesDataBase extends RoomDatabase {
    private static MoviesDataBase moviesDataBaseInstance;
    public abstract MoviesDao moviesDao();
    public static synchronized MoviesDataBase getInstance(Context context){
        if(moviesDataBaseInstance==null){
            moviesDataBaseInstance= Room.databaseBuilder(context.getApplicationContext(),MoviesDataBase.class
                    ,"movies_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return moviesDataBaseInstance;
    }
}
