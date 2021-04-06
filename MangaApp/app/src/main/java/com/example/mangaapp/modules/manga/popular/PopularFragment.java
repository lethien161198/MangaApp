package com.example.mangaapp.modules.manga.popular;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangaapp.R;
import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.components.CustomProgress;
import com.example.mangaapp.databinding.FragmentLastestBinding;
import com.example.mangaapp.databinding.FragmentPopularBinding;
import com.example.mangaapp.models.CustomEventManga;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Recent;
import com.example.mangaapp.modules.adapter.MangaAdapter;
import com.example.mangaapp.modules.database.MangaDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends FragmentView<PopularContract.Presenter, FragmentPopularBinding> implements PopularContract.View {
    private MangaAdapter mangaAdapter;
    private List<Manga> listManga = new ArrayList<>();

    @Override
    protected FragmentPopularBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentPopularBinding.inflate(inflater, container, false);
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
        if (!event.getFrom().equals(Utilities.POPULAR)) {
            listenerManga(event.getManga());
        }

    }

    @Override
    protected void init() {
        CustomProgress.FadingCircle(getBinding().progressCircular);
        getPresenter().getListManga();

    }

    @Override
    public void loadListManga(List<Manga> list) {
        listManga = list;
        mangaAdapter = new MangaAdapter(list, getContext(), new MangaAdapter.OnClickItem() {
            @Override
            public void sendUrl(String url) {
                Log.d("123", "sendUrl: Popular " + url);
                showToast(url);
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
                CustomEventManga customEventManga = new CustomEventManga(manga, Utilities.POPULAR);
                EventBus.getDefault().postSticky(customEventManga);
            }

            @Override
            public void saveRecent(Manga manga) {
                Recent recent = new Recent(manga.getName(), manga.getImage(), manga.getLatestChapter(), manga.getUrl(), manga.getUrlChapter());
                recent.setFavourite(manga.getFavourite());

                boolean check = MangaDatabase.getInstance().recentDAO().isRecordExistsUserId(recent.getUrl());
                if (check) {
                    MangaDatabase.getInstance().recentDAO().deleteRecent(recent);
                    Log.d("1234", "delete + saveRecent: " + MangaDatabase.getInstance().recentDAO().getAll().size());
                }

                MangaDatabase.getInstance().recentDAO().insertRecent(recent);
                Log.d("1234", "saveRecent: " + MangaDatabase.getInstance().recentDAO().getAll().size());

                EventBus.getDefault().postSticky("recent");
            }
        });
        getBinding().popularRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        getBinding().popularRecycler.setAdapter(mangaAdapter);
        mangaAdapter.notifyDataSetChanged();
        hideProgress();
    }

    @Override
    public void showProgress() {
        getBinding().progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        getBinding().progressCircular.setVisibility(View.INVISIBLE);
    }

    public void listenerManga(Manga event) {

        for (Manga a : listManga) {
            if (a.getName().equals(event.getName())) {
                a.setFavourite(event.getFavourite());
                break;
            }
        }
        loadListManga(listManga);
    }


}