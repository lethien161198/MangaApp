package com.example.mangaapp.modules.manga.popular;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.models.Manga;

import java.util.List;

public class PopularPresenter extends Presenter<PopularContract.View, PopularContract.Interactor> implements PopularContract.Presenter {
    public PopularPresenter(PopularContract.View view, PopularContract.Interactor interactor, Bundle parameters) {
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
