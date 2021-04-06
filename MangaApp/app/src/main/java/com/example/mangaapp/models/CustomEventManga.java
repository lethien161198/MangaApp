package com.example.mangaapp.models;

public class CustomEventManga {
    private Manga manga;
    private String from;

    public CustomEventManga(Manga manga, String from) {
        this.manga = manga;
        this.from = from;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
