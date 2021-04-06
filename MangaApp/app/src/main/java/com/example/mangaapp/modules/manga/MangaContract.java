package com.example.mangaapp.modules.manga;

import com.example.mangaapp.common.MVP;

public interface MangaContract {
    interface View extends MVP.View<Presenter> {

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {

    }

    interface Interactor extends MVP.Interactor<Presenter> {

    }
}
