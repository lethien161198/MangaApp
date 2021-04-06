package com.example.mangaapp.common;

import android.content.Context;

import androidx.work.Configuration;
import androidx.work.WorkManager;

import java.util.concurrent.Executors;

public class WorkManagerHelper {
    private static WorkManager mInstance;
    public synchronized static void initialize1(Context context) {
        if (context == null) {
            throw new NullPointerException("ApplicationContext is null");
        }
        if (mInstance == null) {
            synchronized ((WorkManagerHelper.class)) {
                if (mInstance == null) {

                    mInstance = WorkManager.getInstance(context);

                }
            }
        }
    }

    public static WorkManager getmInstance() {
        return mInstance;
    }
}
