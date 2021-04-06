package com.example.mangaapp.components;

import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.FadingCircle;

public class CustomProgress {
    public static void FadingCircle(ProgressBar progressBar){
        progressBar.setIndeterminateDrawable(new FadingCircle());
    }
}
