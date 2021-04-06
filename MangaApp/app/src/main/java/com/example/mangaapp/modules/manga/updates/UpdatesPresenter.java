package com.example.mangaapp.modules.manga.updates;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.models.Manga;

import java.util.List;

public class UpdatesPresenter extends Presenter<UpdateContract.View, UpdateContract.Interactor> implements UpdateContract.Presenter {
    public UpdatesPresenter(UpdateContract.View view, UpdateContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getListManga() {
        getInteractor().crawl();
    }

    @Override
    public void onFinishGetListManga(List<Manga> list) {
        getView().loadListManga(list);
    }
}
