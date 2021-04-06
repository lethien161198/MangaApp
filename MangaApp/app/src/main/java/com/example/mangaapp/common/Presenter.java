package com.example.mangaapp.common;

import android.os.Bundle;
import android.util.Log;

public abstract class Presenter<V extends MVP.View, I extends MVP.Interactor> implements MVP.Presenter<V, I> {
    private final V view;
    private final I interactor;
    private Bundle bundle;

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public Presenter(V view, I interactor, Bundle parameters) {
        this.view = view;
        this.view.setPresenter(this);
        this.interactor = interactor;
        this.interactor.setPresenter(this);
        setBundle(parameters);
        //this.view.setParameters(parameters);

    }

    public void viewGetBundle(){
        this.view.setParameters(this.bundle);
    }

    @Override
    public V getView() {
        return view;
    }

    @Override
    public I getInteractor() {
        return interactor;
    }
}
