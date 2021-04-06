package com.example.mangaapp.modules.reading;

import com.example.mangaapp.common.MVP;

public interface ReadingContract {
    interface View extends MVP.View<Presenter> {

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {

    }

    interface Interactor extends MVP.Interactor<Presenter> {

    }
}
