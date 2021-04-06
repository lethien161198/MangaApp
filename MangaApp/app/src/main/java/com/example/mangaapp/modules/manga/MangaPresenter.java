package com.example.mangaapp.modules.manga;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;

public class MangaPresenter extends Presenter<MangaContract.View, MangaContract.Interactor> implements MangaContract.Presenter{
    public MangaPresenter(MangaContract.View view, MangaContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }
}
