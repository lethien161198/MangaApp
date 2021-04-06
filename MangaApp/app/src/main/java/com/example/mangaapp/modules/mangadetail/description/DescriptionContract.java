package com.example.mangaapp.modules.mangadetail.description;

import com.example.mangaapp.common.MVP;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.modules.manga.updates.UpdateContract;

import java.util.List;

public interface DescriptionContract {
    interface View extends MVP.View<Presenter> {
        void loadDescription(String text);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void onFinishDescription(String text);
        void getDescription();
    }

    interface Interactor extends MVP.Interactor<Presenter> {
    }
}
