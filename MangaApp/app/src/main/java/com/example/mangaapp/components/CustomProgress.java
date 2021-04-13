package com.example.mangaapp.components;

import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.Wave;

public class CustomProgress {
    public static void FadingCircle(ProgressBar progressBar) {
        progressBar.setIndeterminateDrawable(new FadingCircle());
    }

    public static void RotatingPlane(ProgressBar progressBar) {
        progressBar.setIndeterminateDrawable(new RotatingPlane());
    }

    public static void RotatingCircle(ProgressBar progressBar) {
        progressBar.setIndeterminateDrawable(new RotatingCircle());
    }

    public static void Wave(ProgressBar progressBar) {
        progressBar.setIndeterminateDrawable(new Wave());
    }

    public static void ThreeBounce(ProgressBar progressBar) {
        progressBar.setIndeterminateDrawable(new ThreeBounce());
    }


}
