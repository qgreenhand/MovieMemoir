package com.example.mymovie.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mymovie.dao.WatchListMovieDao;
import com.example.mymovie.entity.WatchListMovie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {WatchListMovie.class}, version = 2, exportSchema = false)
public abstract  class WatchListMovieDatabase extends RoomDatabase {
    public abstract WatchListMovieDao watchlistmovieDao();
    private static WatchListMovieDatabase INSTANCE;
    //we create an ExecutorService with a fixed thread pool so we can later run database operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static synchronized WatchListMovieDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WatchListMovieDatabase.class, "WatchListMovieDatabase").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }



}
