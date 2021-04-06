package com.example.mangaapp.modules.reading.recent;

import com.example.mangaapp.common.MVP;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Recent;

import java.util.List;

public interface RecentContract {
    interface View extends MVP.View<Presenter> {
        void loadListManga(List<Recent> list);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getListManga();

        void onFinishGetListManga(List<Recent> list);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void crawl();
    }
}
