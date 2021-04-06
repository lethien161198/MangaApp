package com.example.mangaapp.models;

import java.io.Serializable;

public class Chapter implements Serializable {
    private long id;
    private String nameChapter;
    private String urlChapter;

    public Chapter(String nameChapter, String urlChapter) {
        this.nameChapter = nameChapter;
        this.urlChapter = urlChapter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameChapter() {
        return nameChapter;
    }

    public void setNameChapter(String nameChapter) {
        this.nameChapter = nameChapter;
    }

    public String getUrlChapter() {
        return urlChapter;
    }

    public void setUrlChapter(String urlChapter) {
        this.urlChapter = urlChapter;
    }
}
