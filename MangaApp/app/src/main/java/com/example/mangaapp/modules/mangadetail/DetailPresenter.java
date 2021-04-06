package com.example.mangaapp.modules.mangadetail;

import android.os.Bundle;
import android.util.Log;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.models.MangaDetail;

public class DetailPresenter extends Presenter<DetailContract.View, DetailContract.Interactor> implements DetailContract.Presenter {
    public DetailPresenter(DetailContract.View view, DetailContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getMangaDetail() {
        String url = getBundle().getString(Utilities.KEY_URL_DETAIL);
        getInteractor().crawlDetail(url);
    }

    @Override
    public void onFinishGetDetail(MangaDetail mangaDetail) {
        String[] rating = mangaDetail.getRating().split("\\s");
        Float ratingbar = Float.parseFloat(rating[1]);
        mangaDetail.setRatingbar(ratingbar);
        getView().loadInfo(mangaDetail);
    }
}
