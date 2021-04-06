package com.example.mangaapp.modules.search.result;

import com.example.mangaapp.common.MVP;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Result;

import java.util.List;

public interface ResultContract {
    interface View extends MVP.View<Presenter> {
        void loadResult(List<Manga> list);

        void loadPage(List<String> list);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {

        void getResult(int page,String sort);

        void onFinishResult(List<Manga> list);

        void onFinishPage(int page);

    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void crawlResult(String url,int page);

    }
}
