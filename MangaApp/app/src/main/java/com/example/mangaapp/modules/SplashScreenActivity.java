package com.example.mangaapp.modules;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mangaapp.R;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.components.CustomProgress;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progress_circular);
        CustomProgress.Wave(progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Timer timer = new Timer();
        boolean checkConnection = Utilities.checkConnection(this);
        if (checkConnection) {
            timer.schedule(new SplashScreenActivity.splash(), 3000);
        } else {
            Toast.makeText(SplashScreenActivity.this,
                    "Connection not found ... Please check connection", Toast.LENGTH_LONG).show();
        }
    }

    class splash extends TimerTask {
        @Override
        public void run() {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}