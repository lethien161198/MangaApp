package com.example.mangaapp.modules.manga.latest;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.mangaapp.R;
import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.components.CustomProgress;
import com.example.mangaapp.databinding.FragmentLastestBinding;
import com.example.mangaapp.models.CustomEvent;
import com.example.mangaapp.models.CustomEventManga;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Recent;
import com.example.mangaapp.modules.adapter.MangaAdapter;
import com.example.mangaapp.modules.adapter.SpinnerAdapter;
import com.example.mangaapp.modules.database.MangaDatabase;
import com.example.mangaapp.modules.mangadetail.DetailFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class LatestFragment extends FragmentView<LatestContract.Presenter, FragmentLastestBinding> implements LatestContract.View, SwipeRefreshLayout.OnRefreshListener {
    private MangaAdapter mangaAdapter;
    private List<Manga> listManga = new ArrayList<>();
    private SpinnerAdapter spinnerAdapter;
    int currentPage = 1;

    @Override
    protected FragmentLastestBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentLastestBinding.inflate(inflater, container, false);
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
        if (!event.getFrom().equals(Utilities.LATEST)) {
            listenerManga(event.getManga());
        }
    }

    @Override
    protected void init() {
        CustomProgress.FadingCircle(getBinding().progressCircular);
        getPresenter().getListManga(1);
        getBinding().refresh.setOnRefreshListener(this);

        getBinding().choosePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                mangaAdapter.removeAll();
                getPresenter().getListManga(currentPage);

            }
        });
        getBinding().page.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                currentPage = Integer.parseInt(spinnerAdapter.getItem(position));
                //showToast(currentPage + "");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void loadListManga(List<Manga> list) {
        listManga = list;
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
                CustomEventManga customEventManga = new CustomEventManga(manga, Utilities.LATEST);
                EventBus.getDefault().postSticky(customEventManga);
            }

            @Override
            public void saveRecent(Manga manga) {
                Recent recent = new Recent(manga.getName(), manga.getImage(), manga.getLatestChapter(), manga.getUrl(), manga.getUrlChapter());
                recent.setFavourite(manga.getFavourite());

                boolean check = MangaDatabase.getInstance().recentDAO().isRecordExistsUserId(recent.getUrl());
                if (check) {
                    MangaDatabase.getInstance().recentDAO().deleteRecent(recent);
                }

                MangaDatabase.getInstance().recentDAO().insertRecent(recent);

                EventBus.getDefault().postSticky("recent");
            }
        });
        getBinding().latestRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        getBinding().latestRecycler.setAdapter(mangaAdapter);
        mangaAdapter.notifyDataSetChanged();
        hideProgress();


    }

    @Override
    public void loadPage(List<String> list) {
        spinnerAdapter = new SpinnerAdapter(getContext(), R.layout.item_selected, list);
        getBinding().page.setAdapter(spinnerAdapter);
        getBinding().page.setSelection(currentPage - 1);
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
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getBinding().refresh.setRefreshing(false);
            }
        }, 3000);
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