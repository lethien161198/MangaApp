package com.example.mangaapp.modules.manga.popular;


import com.example.mangaapp.common.DisposableManager;
import com.example.mangaapp.common.Interactor;

import com.example.mangaapp.models.Manga;

import com.example.mangaapp.modules.api.Client;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PopularInteractor extends Interactor<PopularContract.Presenter> implements PopularContract.Interactor {

    @Override
    public void crawl() {
        Client.getPopularManga().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Manga>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Manga> mangas) {
                        getPresenter().onFinishGetListManga(mangas);
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
