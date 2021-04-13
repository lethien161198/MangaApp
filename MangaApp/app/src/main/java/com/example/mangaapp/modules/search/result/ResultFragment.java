package com.example.mangaapp.modules.search.result;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.example.mangaapp.R;
import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.components.CustomProgress;
import com.example.mangaapp.databinding.FragmentResultBinding;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.modules.adapter.MangaAdapter;
import com.example.mangaapp.modules.adapter.ResultAdapter;
import com.example.mangaapp.modules.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ResultFragment extends FragmentView<ResultContract.Presenter, FragmentResultBinding> implements ResultContract.View {
    private ResultAdapter resultAdapter;
    private SpinnerAdapter spinnerAdapter;
    private int currentPage=1;
    private String sort = "";
    @Override
    protected FragmentResultBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentResultBinding.inflate(inflater, container, false);
    }

    @Override
    protected void init() {
        CustomProgress.Wave(getBinding().progressCircular);
        showProgress();
        showBottomBar(false);showBottomBar(false);
        getBinding().headertitle.title.setText(R.string.result);
        getBinding().headertitle.iconLeft.setImageDrawable(getContext().getDrawable(R.drawable.ic_baseline_arrow_back_ios_24));
        getBinding().headertitle.iconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                showBottomBar(false);
            }
        });
        getPresenter().getResult(1,sort);
        getBinding().radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == getBinding().radio1.getId()) {

                sort = "a-z";
                getPresenter().getResult(1,sort);
            }
            if (checkedId == getBinding().radio2.getId()) {

                sort = "rating";
                getPresenter().getResult(1,sort);
            }
            if (checkedId == getBinding().radio3.getId()) {
                sort = "update";
                getPresenter().getResult(1,sort);
            }
            if (checkedId == getBinding().radio4.getId()) {
                sort = "create";
                getPresenter().getResult(1,sort);
            }
        });
        getBinding().choosePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                resultAdapter.removeAll();
                getPresenter().getResult(currentPage,sort);

            }
        });
        getBinding().page.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                currentPage=Integer.parseInt(spinnerAdapter.getItem(position));
                //showToast(currentPage+"");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showProgress() {
        getBinding().progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        getBinding().progressCircular.setVisibility(View.INVISIBLE);
    }

    @Override
    public void loadResult(List<Manga> list) {

        resultAdapter = new ResultAdapter(list, getContext(), new ResultAdapter.OnClickItem() {
            @Override
            public void sendUrl(String url) {
                showToast(Utilities.WAITING);
                Bundle bundle = new Bundle();
                bundle.putString(Utilities.KEY_URL_DETAIL, url);
                navigate(AppNavigator.ROUTE_DETAIL, bundle);
            }

            @Override
            public void sendUrlChapter(String urlChapter) {
                showToast(urlChapter);
            }
        });
        setResultAdapter(resultAdapter);
        hideProgress();
    }

    @Override
    public void loadPage(List<String> list) {
        spinnerAdapter = new SpinnerAdapter(getContext(), R.layout.item_selected, list);
        getBinding().page.setAdapter(spinnerAdapter);
        getBinding().page.setSelection(currentPage-1);
    }

    public void setResultAdapter(ResultAdapter resultAdapter1) {

        getBinding().resultRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false));
        getBinding().resultRecycler.setAdapter(resultAdapter1);
        resultAdapter1.notifyDataSetChanged();

    }
}