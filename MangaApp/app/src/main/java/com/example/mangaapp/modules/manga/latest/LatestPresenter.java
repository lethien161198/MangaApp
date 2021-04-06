package com.example.mangaapp.modules.manga.latest;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.models.Manga;

import java.util.ArrayList;
import java.util.List;

public class LatestPresenter extends Presenter<LatestContract.View, LatestContract.Interactor> implements LatestContract.Presenter {
    public LatestPresenter(LatestContract.View view, LatestContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getListManga(int page) {
        getInteractor().crawl(page);
    }

    @Override
    public void onFinishGetListManga(List<Manga> list) {
        getView().loadListManga(list);
    }

    @Override
    public void onFinishPage(int page) {
        List<String> list = new ArrayList<>();
        for(int i = 1; i <= page; i++){
            list.add(i+"");
        }
        getView().loadPage(list);
    }
}
