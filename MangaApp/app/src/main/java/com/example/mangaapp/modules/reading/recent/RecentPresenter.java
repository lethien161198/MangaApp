package com.example.mangaapp.modules.reading.recent;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Recent;

import java.util.List;

public class RecentPresenter extends Presenter<RecentContract.View, RecentContract.Interactor> implements RecentContract.Presenter {
    public RecentPresenter(RecentContract.View view, RecentContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getListManga() {
        getInteractor().crawl();
    }

    @Override
    public void onFinishGetListManga(List<Recent> list) {
        getView().loadListManga(list);
    }
}
