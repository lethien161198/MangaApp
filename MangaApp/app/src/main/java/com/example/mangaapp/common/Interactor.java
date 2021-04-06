package com.example.mangaapp.common;


public abstract class Interactor<P extends MVP.Presenter> implements MVP.Interactor<P> {
    private P presenter;
    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }
}
