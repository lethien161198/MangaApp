package com.example.mangaapp.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.mangaapp.BuildConfig;
import com.example.mangaapp.R;
import com.example.mangaapp.components.BitmapScaler;
import com.github.chrisbanes.photoview.PhotoView;

public class Utilities {
    public static final String LATEST = "latest";
    public static final String POPULAR = "popular";
    public static final String UPDATE = "update";
    public static final String FAVOURITE = "favourite";
    public static final String baseUrl = BuildConfig.BASE_URL;
    public static final String KEY_URL_DETAIL = "url_detail";
    public static final String KEY_DES = "des";
    public static final String KEY_MAP_CHAPTER = "map_chapter";
    public static final String URL_LATEST = "latest/";
    public static final String URL_SEARCH = "search";
    public static final String URL_RESULT = "search?";
    public static final String SEARCH_REQUEST = "search_request";
    public static final String KEY_URL_CHAPTER = "url_chapter";
    public static void loadImage(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url)
                .error(R.drawable.ic_launcher_background)
                .override(Target.SIZE_ORIGINAL)
                .into(img);
    }
    public static void loadImage(Context context, String url, PhotoView img) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        Bitmap scaleWidth = BitmapScaler.scaleToFitWidth(bitmap,1080);
                        img.setImageBitmap(scaleWidth);
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                    }
                });
    }

    public static boolean checkConnection(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo info;
        boolean flag = false;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            info = connectivityManager.getActiveNetworkInfo();

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                System.out.println(info.getTypeName());
                flag = true;
            }
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                System.out.println(info.getTypeName());
                flag = true;
            }
        } catch (Exception exception) {
            System.out.println("Exception at network connection....."
                    + exception);
        }
        return flag;
    }


}
