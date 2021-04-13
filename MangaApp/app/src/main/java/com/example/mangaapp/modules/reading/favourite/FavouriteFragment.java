package com.example.mangaapp.modules.reading.favourite;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.components.CustomProgress;
import com.example.mangaapp.databinding.FragmentFavouriteBinding;
import com.example.mangaapp.models.CustomEvent;
import com.example.mangaapp.models.CustomEventManga;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.modules.adapter.MangaAdapter;
import com.example.mangaapp.modules.database.MangaDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FavouriteFragment extends FragmentView<FavouriteContract.Presenter, FragmentFavouriteBinding> implements FavouriteContract.View {
    private MangaAdapter mangaAdapter;
    private List<Manga> list;

    @Override
    protected FragmentFavouriteBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentFavouriteBinding.inflate(inflater, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnCustomEvent(CustomEventManga event) {
        if (!event.getFrom().equals(Utilities.FAVOURITE)) {
            list.clear();
            if (!event.getManga().getFavourite()) {
                if (MangaDatabase.getInstance().mangaDAO().isRecordExistsUserId(event.getManga().getUrl())) {
                    MangaDatabase.getInstance().mangaDAO().delete(event.getManga().getUrl());
                }

            } else {
                if (MangaDatabase.getInstance().mangaDAO().isRecordExistsUserId(event.getManga().getUrl())) {
                    MangaDatabase.getInstance().mangaDAO().updateManga(event.getManga());

                } else {
                    MangaDatabase.getInstance().mangaDAO().insertManga(event.getManga());
                }
            }
            list = MangaDatabase.getInstance().mangaDAO().getAll();
            setAdapter();
        }
    }

    @Override
    protected void init() {
        CustomProgress.FadingCircle(getBinding().progressCircular);
        list = MangaDatabase.getInstance().mangaDAO().getAll();
        setAdapter();

    }

    @Override
    public void showProgress() {
        getBinding().progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        getBinding().progressCircular.setVisibility(View.INVISIBLE);
    }

    public void setAdapter() {
        mangaAdapter = new MangaAdapter(list, getContext(), new MangaAdapter.OnClickItem() {
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

            @Override
            public void sendFavorite(Manga manga) {
                boolean checkData = MangaDatabase.getInstance().mangaDAO().isRecordExistsUserId(manga.getName());

                if (checkData) {
                    if (manga.getFavourite()) {
                        MangaDatabase.getInstance().mangaDAO().updateManga(manga);
                    } else {
                        MangaDatabase.getInstance().mangaDAO().deleteManga(manga);
                    }
                } else {
                    if (manga.getFavourite()) {
                        MangaDatabase.getInstance().mangaDAO().insertManga(manga);
                    } else {
                        MangaDatabase.getInstance().mangaDAO().deleteManga(manga);
                    }
                }
                list = MangaDatabase.getInstance().mangaDAO().getAll();
                setAdapter();
                CustomEventManga customEventManga = new CustomEventManga(manga, Utilities.FAVOURITE);
                EventBus.getDefault().postSticky(customEventManga);

            }

            @Override
            public void saveRecent(Manga manga) {

            }
        });
        getBinding().favouriteRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        getBinding().favouriteRecycler.setAdapter(mangaAdapter);
        mangaAdapter.notifyDataSetChanged();
        hideProgress();
    }
}