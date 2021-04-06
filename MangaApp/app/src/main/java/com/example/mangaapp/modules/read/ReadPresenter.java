package com.example.mangaapp.modules.read;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.models.Read;

import java.util.List;

public class ReadPresenter extends Presenter<ReadContract.View, ReadContract.Interactor> implements ReadContract.Presenter {
    public ReadPresenter(ReadContract.View view, ReadContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getAllImage() {
        String url = getBundle().getString(Utilities.KEY_URL_CHAPTER);
        getInteractor().crawlImage(url);
    }

    @Override
    public void onFinishGetAllImage(List<Read> list) {
        getView().loadImage(list);
    }
}
