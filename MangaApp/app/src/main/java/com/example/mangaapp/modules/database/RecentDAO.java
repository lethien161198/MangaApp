package com.example.mangaapp.modules.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Recent;

import java.util.List;

@Dao
public interface RecentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecent(Recent recent);

    @Query("SELECT * FROM recent")
    List<Recent> getAll();

    @Update
    void updateRecent(Recent recent);

    @Delete
    void deleteRecent(Recent recent);

    @Query("SELECT EXISTS(SELECT * FROM recent WHERE url = :url)")
    boolean isRecordExistsUserId(String url);
}
