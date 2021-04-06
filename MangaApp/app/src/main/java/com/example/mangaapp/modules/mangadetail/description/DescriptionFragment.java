package com.example.mangaapp.modules.mangadetail.description;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangaapp.R;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.databinding.FragmentDescriptionBinding;

public class DescriptionFragment extends FragmentView<DescriptionContract.Presenter, FragmentDescriptionBinding> implements DescriptionContract.View {

    @Override
    protected FragmentDescriptionBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentDescriptionBinding.inflate(inflater,container,false);
    }

    @Override
    protected void init() {
        getPresenter().getDescription();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadDescription(String text) {
        getBinding().description.setText(text);
    }
}