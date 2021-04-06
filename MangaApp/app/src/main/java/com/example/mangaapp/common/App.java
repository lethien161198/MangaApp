package com.example.mangaapp.common;

import android.app.Application;

import com.example.mangaapp.modules.database.MangaDatabase;

import androidx.work.Configuration;
import androidx.work.WorkManager;

import java.util.concurrent.Executors;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        WorkManager.initialize(
//                this,
//                new Configuration.Builder()
//                        .setExecutor(Executors.newFixedThreadPool(8))
//                        .build());
        //WorkManagerHelper.initialize1(this);
        MangaDatabase.initialize(getApplicationContext());
    }
}
