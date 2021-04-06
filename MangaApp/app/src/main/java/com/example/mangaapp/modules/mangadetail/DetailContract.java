package com.example.mangaapp.modules.mangadetail;

import com.example.mangaapp.common.MVP;
import com.example.mangaapp.models.MangaDetail;

public interface DetailContract {
    interface View extends MVP.View<Presenter> {
        void loadInfo(MangaDetail mangaDetail);

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getMangaDetail();
        void onFinishGetDetail(MangaDetail mangaDetail);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void crawlDetail(String url);
    }
}
