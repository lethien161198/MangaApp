package com.example.mangaapp.modules.reading.favourite;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;

public class FavouritePresenter extends Presenter<FavouriteContract.View, FavouriteContract.Interactor> implements FavouriteContract.Presenter {
    public FavouritePresenter(FavouriteContract.View view, FavouriteContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }
}
