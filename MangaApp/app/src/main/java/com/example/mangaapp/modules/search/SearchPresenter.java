package com.example.mangaapp.modules.search;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.models.Genre;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter extends Presenter<SearchContract.View, SearchContract.Interactor> implements SearchContract.Presenter {

    public SearchPresenter(SearchContract.View view, SearchContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getGenre() {
        getInteractor().crawlGenre();
    }

    @Override
    public void onFinishGetGenre(List<Genre> genre) {
        getView().loadGenre(genre);
    }

    @Override
    public void getStatus() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("Ongoing");
        list.add("Complete");
        onFinishGetStatus(list);
    }

    @Override
    public void onFinishGetStatus(List<String> status) {
        getView().loadStatus(status);
    }

    @Override
    public void getRelease() {
        List<String> list = new ArrayList<>();
        list.add("");
        for(int i = 2017; i >=1905; i--){
            list.add(""+i);
        }
        onFinishGetRelease(list);
    }

    @Override
    public void onFinishGetRelease(List<String> release) {
        getView().loadRelease(release);
    }
}
