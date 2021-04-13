package com.example.mangaapp.modules.database;

import android.content.Context;

import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Recent;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Manga.class, Recent.class},version = 1,exportSchema = false)
public abstract class MangaDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "manga.db";
    private static MangaDatabase instance;


    public synchronized static  void initialize(Context context){
        instance = Room.databaseBuilder(context, MangaDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

    public static MangaDatabase getInstance(){
        return instance;
    }

    public abstract MangaDAO mangaDAO();
    public abstract RecentDAO recentDAO();
}
