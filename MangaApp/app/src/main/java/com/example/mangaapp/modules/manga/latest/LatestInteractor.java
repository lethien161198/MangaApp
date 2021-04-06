package com.example.mangaapp.modules.manga.latest;


import android.util.Log;


import com.example.mangaapp.common.DisposableManager;
import com.example.mangaapp.common.Interactor;
import com.example.mangaapp.common.Utilities;

import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Result;

import com.example.mangaapp.modules.api.Client;
import com.example.mangaapp.modules.database.MangaDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LatestInteractor extends Interactor<LatestContract.Presenter> implements LatestContract.Interactor {
    @Override
    public void crawl(int page) {
        Client.getLatestManga(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Result mangas) {
                        getPresenter().onFinishGetListManga(mangas.getMangaList());
                        getPresenter().onFinishPage(mangas.getPage());
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
