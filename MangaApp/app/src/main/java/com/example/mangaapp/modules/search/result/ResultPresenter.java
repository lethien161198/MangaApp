package com.example.mangaapp.modules.search.result;

import android.os.Bundle;
import android.util.Log;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.models.Genre;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Result;
import com.example.mangaapp.models.SearchRequest;

import java.util.ArrayList;
import java.util.List;

public class ResultPresenter extends Presenter<ResultContract.View, ResultContract.Interactor> implements ResultContract.Presenter {

    public ResultPresenter(ResultContract.View view, ResultContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getResult(int page,String sort) {
        String url = "";
        String urlGenre = "";
        SearchRequest searchRequest = (SearchRequest) getBundle().getSerializable(Utilities.SEARCH_REQUEST);
        if (searchRequest.getGenres().size() > 0) {
            urlGenre = searchRequest.getGenres().get(0);
        }

        if (searchRequest.getGenres().size() >= 2) {
            for (int i = 1; i < searchRequest.getGenres().size(); i++) {
                urlGenre = urlGenre + "," + searchRequest.getGenres().get(i);
            }
        }
        url = "q=" + searchRequest.getTitle().toLowerCase()
                + "&" + "autart=" + searchRequest.getAuthor().toLowerCase()
                + "&" + "genres=" + urlGenre.toLowerCase()
                + "&" + "status=" + searchRequest.getStatus().toLowerCase()
                + "&" + "years=" + searchRequest.getRelease().toLowerCase()
                + "&" + "page=" + page
                + "&" + "orderby=" + sort;
        getInteractor().crawlResult(url,page);
    }


    @Override
    public void onFinishResult(List<Manga> list) {
        getView().loadResult(list);

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
