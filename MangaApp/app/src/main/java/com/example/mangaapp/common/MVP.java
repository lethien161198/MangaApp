package com.example.mangaapp.common;

import android.os.Bundle;

public class MVP {
    public interface Presenter<V extends View, I extends Interactor> {
        V getView();

        I getInteractor();

    }

    public interface View<P extends Presenter> {
        void setPresenter(P presenter);

        P getPresenter();

        void setParameters(Bundle parameters);

        void navigate(String route, Bundle parameters);

        void navigateReplaced(String route, Bundle parameters);
    }

    public interface Interactor<P extends Presenter> {
        void setPresenter(P presenter);

        P getPresenter();
    }
}
