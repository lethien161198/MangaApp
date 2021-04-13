package com.example.mangaapp.modules.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mangaapp.R;
import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.components.CustomProgress;
import com.example.mangaapp.databinding.FragmentSearchBinding;
import com.example.mangaapp.models.Genre;
import com.example.mangaapp.models.SearchRequest;
import com.example.mangaapp.modules.adapter.GenreAdapter;
import com.example.mangaapp.modules.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends FragmentView<SearchContract.Presenter, FragmentSearchBinding> implements SearchContract.View {
    private SpinnerAdapter spinnerAdapterStatus;
    private SpinnerAdapter spinnerAdapterRelease;
    private GenreAdapter genreAdapter;
    private SearchRequest searchRequest = new SearchRequest();
    List<String> genres = new ArrayList<>();
    @Override
    protected FragmentSearchBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSearchBinding.inflate(inflater,container,false);
    }

    @Override
    protected void init() {

        CustomProgress.Wave(getBinding().progressCircular);
        showProgress();
        showBottomBar(false);
        getBinding().headertitle.title.setText(R.string.search);
        getBinding().headertitle.iconLeft.setImageDrawable(getContext().getDrawable(R.drawable.ic_baseline_arrow_back_ios_24));


        getPresenter().getGenre();
        getPresenter().getRelease();
        getPresenter().getStatus();

        getBinding().headertitle.iconLeft.setOnClickListener(v -> {
            getActivity().onBackPressed();
            showBottomBar(true);
        });
        getBinding().spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchRequest.setStatus(spinnerAdapterStatus.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getBinding().spinnerRelease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchRequest.setRelease(spinnerAdapterRelease.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getBinding().btnSearch.setOnClickListener(v -> {
            searchRequest.setStatus(getBinding().spinnerStatus.getSelectedItem().toString());
            searchRequest.setRelease(getBinding().spinnerRelease.getSelectedItem().toString());
            searchRequest.setTitle(getBinding().edtTitle.getText().toString());
            searchRequest.setAuthor(getBinding().edtAuthorArtist.getText().toString());
            searchRequest.setGenres(genres);

            Bundle bundle = new Bundle();
            bundle.putSerializable(Utilities.SEARCH_REQUEST,searchRequest);
            navigate(AppNavigator.ROUTE_RESULT,bundle);
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
    public void loadStatus(List<String> status) {
        spinnerAdapterStatus = new SpinnerAdapter(getContext(),R.layout.item_selected,status);
        getBinding().spinnerStatus.setAdapter(spinnerAdapterStatus);

    }

    @Override
    public void loadGenre(List<Genre> genre) {
        genreAdapter = new GenreAdapter(genre, new GenreAdapter.OnClickItem() {
            @Override
            public void sendGenre(Genre genre) {
                if(genre.isChecked()){
                    genres.add(genre.getNameGen());
                }
                else{
                    for(String s:genres){
                        if(s.equals(genre.getNameGen())){
                            genres.remove(s);
                            break;
                        }
                    }
                }
            }
        });
        getBinding().genreRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        getBinding().genreRecycler.setAdapter(genreAdapter);
        genreAdapter.notifyDataSetChanged();
        hideProgress();
    }

    @Override
    public void loadRelease(List<String> release) {
        spinnerAdapterRelease = new SpinnerAdapter(getContext(),R.layout.item_selected,release);
        getBinding().spinnerRelease.setAdapter(spinnerAdapterRelease);

    }
}