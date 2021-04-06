package com.example.mangaapp.modules.mangadetail;




import com.example.mangaapp.common.DisposableManager;
import com.example.mangaapp.common.Interactor;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.models.Chapter;
import com.example.mangaapp.models.MangaDetail;
import com.example.mangaapp.models.Version;
import com.example.mangaapp.modules.api.Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailInteractor extends Interactor<DetailContract.Presenter> implements DetailContract.Interactor {

    @Override
    public void crawlDetail(String url) {
        Client.getMangaDetail(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MangaDetail>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onNext(@NonNull MangaDetail mangaDetail) {
                        getPresenter().onFinishGetDetail(mangaDetail);
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
