package com.example.mangaapp.modules.search;

import com.example.mangaapp.common.MVP;
import com.example.mangaapp.models.Genre;

import java.util.List;

public interface SearchContract {
    interface View extends MVP.View<Presenter> {
        void loadStatus(List<String> status);

        void loadGenre(List<Genre> genre);

        void loadRelease(List<String> release);

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getGenre();
        void onFinishGetGenre(List<Genre> genre);

        void getStatus();
        void onFinishGetStatus(List<String> status);

        void getRelease();
        void onFinishGetRelease(List<String> release);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void crawlGenre();
    }
}
