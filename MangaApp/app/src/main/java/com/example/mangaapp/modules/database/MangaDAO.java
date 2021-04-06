package com.example.mangaapp.modules.database;

import com.example.mangaapp.models.Manga;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MangaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertManga(Manga manga);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllManga(List<Manga> manga);

    @Query("SELECT * FROM manga")
    List<Manga> getAll();

    @Update
    void updateManga(Manga manga);

    @Delete
    void deleteManga(Manga manga);

    @Query("DELETE FROM manga")
    void deleteAll();

    @Query("DELETE FROM manga WHERE url = :url")
    void delete(String url);

    @Query("SELECT EXISTS(SELECT * FROM manga WHERE url = :url)")
    boolean isRecordExistsUserId(String url);
}
