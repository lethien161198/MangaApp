package com.example.mangaapp.models;

import java.util.List;

public class Result {
    private List<Manga> mangaList;
    private int page;

    public Result(List<Manga> mangaList, int page) {
        this.mangaList = mangaList;
        this.page = page;
    }

    public List<Manga> getMangaList() {
        return mangaList;
    }

    public void setMangaList(List<Manga> mangaList) {
        this.mangaList = mangaList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
