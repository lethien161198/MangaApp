package com.example.mangaapp.modules.manga.latest;

import com.example.mangaapp.common.MVP;
import com.example.mangaapp.models.Manga;

import java.util.List;

public interface LatestContract {
    interface View extends MVP.View<Presenter> {
        void loadListManga(List<Manga> list);
        void loadPage(List<String> list);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getListManga(int page);

        void onFinishGetListManga(List<Manga> list);

        void onFinishPage(int page);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void crawl(int page);
    }
}
