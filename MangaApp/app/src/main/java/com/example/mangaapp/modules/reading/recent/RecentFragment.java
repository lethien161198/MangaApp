package com.example.mangaapp.modules.reading.recent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.components.CustomProgress;
import com.example.mangaapp.databinding.FragmentRecentBinding;
import com.example.mangaapp.models.CustomEventManga;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Recent;
import com.example.mangaapp.modules.adapter.MangaAdapter;
import com.example.mangaapp.modules.adapter.RecentAdapter;
import com.example.mangaapp.modules.database.MangaDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;


public class RecentFragment extends FragmentView<RecentContract.Presenter, FragmentRecentBinding> implements RecentContract.View {
    private RecentAdapter recentAdapter;
    private List<Recent> listRecent;
    @Override
    protected FragmentRecentBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentRecentBinding.inflate(inflater,container,false);
    }

    @Override
    protected void init() {
        showBottomBar(true);
        CustomProgress.FadingCircle(getBinding().progressCircular);
        getPresenter().getListManga();
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
    public void OnCustomEvent(String customEvent) {
        if (customEvent.equals("recent")) {
            listRecent.clear();
            listRecent = MangaDatabase.getInstance().recentDAO().getAll();
            setAdapter(listRecent);
        }
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
    public void loadListManga(List<Recent> list) {
        listRecent = list ;
        setAdapter(list);
    }
    public void setAdapter(List<Recent> list){
        recentAdapter = new RecentAdapter(list, getContext(), new RecentAdapter.OnClickItem() {
            @Override
            public void sendUrl(String url) {
                showToast(url);
                Bundle bundle = new Bundle();
                bundle.putString(Utilities.KEY_URL_DETAIL, url);
                navigate(AppNavigator.ROUTE_DETAIL, bundle);
            }

            @Override
            public void sendUrlChapter(String urlChapter) {

            }
        });
        getBinding().recentRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        getBinding().recentRecycler.setAdapter(recentAdapter);
        recentAdapter.notifyDataSetChanged();
        hideProgress();
    }
}