package com.example.mangaapp.modules.reading.favourite;

import com.example.mangaapp.common.MVP;


public interface FavouriteContract {
    interface View extends MVP.View<Presenter> {

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {

    }

    interface Interactor extends MVP.Interactor<Presenter> {

    }
}
