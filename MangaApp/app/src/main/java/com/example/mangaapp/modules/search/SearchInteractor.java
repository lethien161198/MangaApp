package com.example.mangaapp.modules.search;


import com.example.mangaapp.common.DisposableManager;
import com.example.mangaapp.common.Interactor;

import com.example.mangaapp.models.Genre;

import com.example.mangaapp.modules.api.Client;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchInteractor extends Interactor<SearchContract.Presenter> implements SearchContract.Interactor {
    @Override
    public void crawlGenre() {
        Client.getGenreSearch().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Genre>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Genre> strings) {
                        getPresenter().onFinishGetGenre(strings);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
