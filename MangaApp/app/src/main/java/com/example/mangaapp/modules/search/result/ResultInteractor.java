package com.example.mangaapp.modules.search.result;

import android.util.Log;

import com.example.mangaapp.common.DisposableManager;
import com.example.mangaapp.common.Interactor;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Result;
import com.example.mangaapp.modules.api.Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ResultInteractor extends Interactor<ResultContract.Presenter> implements ResultContract.Interactor {
    @Override
    public void crawlResult(String url,int page) {
        Client.getResultSearch(url,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Result result) {
                        getPresenter().onFinishResult(result.getMangaList());
                        getPresenter().onFinishPage(result.getPage());
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
