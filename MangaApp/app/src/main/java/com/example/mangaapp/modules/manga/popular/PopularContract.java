package com.example.mangaapp.modules.manga.popular;

import com.example.mangaapp.common.MVP;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.modules.manga.MangaContract;

import java.util.List;

public interface PopularContract {
    interface View extends MVP.View<Presenter> {
        void loadListManga(List<Manga> list);

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getListManga();
        void onFinishGetListManga(List<Manga> list);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void crawl();
    }
}
