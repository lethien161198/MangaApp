package com.example.mangaapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recent")
public class Recent {

    private String name;
    private String image;
    private String latestChapter;
    @PrimaryKey()
    @NonNull
    private String url;
    private String urlChapter;
    private Boolean isFavourite;

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public Recent(String name, String image, String latestChapter, String url, String urlChapter) {
        this.name = name;
        this.image = image;
        this.latestChapter = latestChapter;
        this.url = url;
        this.urlChapter = urlChapter;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLatestChapter() {
        return latestChapter;
    }

    public void setLatestChapter(String latestChapter) {
        this.latestChapter = latestChapter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlChapter() {
        return urlChapter;
    }

    public void setUrlChapter(String urlChapter) {
        this.urlChapter = urlChapter;
    }
}
