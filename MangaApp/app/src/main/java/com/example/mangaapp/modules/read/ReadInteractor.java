package com.example.mangaapp.modules.read;

import android.util.Log;

import com.example.mangaapp.common.DisposableManager;
import com.example.mangaapp.common.Interactor;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.models.Chapter;
import com.example.mangaapp.models.MangaDetail;
import com.example.mangaapp.models.Read;
import com.example.mangaapp.models.Version;
import com.example.mangaapp.modules.api.Client;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReadInteractor extends Interactor<ReadContract.Presenter> implements ReadContract.Interactor {
    @Override
    public void crawlImage(String url) {
        Client.getReadImage(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Read>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Read> list) {
                        getPresenter().onFinishGetAllImage(list);
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
