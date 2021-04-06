package com.example.mangaapp.modules.reading;

import android.os.Bundle;

import com.example.mangaapp.common.Presenter;

public class ReadingPresenter extends Presenter<ReadingContract.View, ReadingContract.Interactor> implements ReadingContract.Presenter{
    public ReadingPresenter(ReadingContract.View view, ReadingContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public ReadingContract.View getView() {
        return null;
    }

    @Override
    public ReadingContract.Interactor getInteractor() {
        return null;
    }
}
