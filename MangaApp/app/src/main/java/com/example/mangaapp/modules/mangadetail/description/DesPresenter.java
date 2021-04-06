package com.example.mangaapp.modules.mangadetail.description;

import android.os.Bundle;
import android.util.Log;

import com.example.mangaapp.common.Presenter;
import com.example.mangaapp.common.Utilities;

public class DesPresenter extends Presenter<DescriptionContract.View, DescriptionContract.Interactor> implements DescriptionContract.Presenter {

    public DesPresenter(DescriptionContract.View view, DescriptionContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void onFinishDescription(String text) {
        getView().loadDescription(text);
    }

    @Override
    public void getDescription() {
        String des = getBundle().getString(Utilities.KEY_DES);
        onFinishDescription(des);
    }
}
