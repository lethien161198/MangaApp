package com.example.mangaapp.modules.read;

import com.example.mangaapp.common.MVP;
import com.example.mangaapp.models.Read;

import java.util.List;

public interface ReadContract {
    interface View extends MVP.View<Presenter> {
        void loadImage(List<Read> list);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getAllImage();
        void onFinishGetAllImage(List<Read> list);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void crawlImage(String url);
    }
}
