package com.example.mangaapp.modules.manga;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mangaapp.R;
import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.databinding.FragmentMangaBinding;

import com.example.mangaapp.models.Manga;
import com.example.mangaapp.modules.adapter.ViewPagerAdapter;
import com.example.mangaapp.modules.database.MangaDatabase;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class MangaFragment extends FragmentView<MangaContract.Presenter, FragmentMangaBinding> implements MangaContract.View {
    private TabLayout tabLayout1;
    private ViewPager2 viewPager2;


    @Override
    protected FragmentMangaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMangaBinding.inflate(inflater, container, false);
    }

    @Override
    protected void init() {
        showBottomBar(true);

        getBinding().headerTitle.title.setText(R.string.manga);

        getBinding().headerTitle.iconRight.setImageDrawable(getContext().getDrawable(R.drawable.ic_baseline_search_24));

        getBinding().headerTitle.iconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(AppNavigator.ROUTE_SEARCH);
                //MangaDatabase.getInstance().mangaDAO().deleteAll();

            }
        });

        viewPager2 = getBinding().pager;

        tabLayout1 = getBinding().tabLayout;

        setUpViewPager(viewPager2, tabLayout1);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    public void setUpViewPager(ViewPager2 viewPager, TabLayout tabLayout) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPagerAdapter.addFragment((Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_LATEST));
        viewPagerAdapter.addFragment((Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_UPDATES));
        viewPagerAdapter.addFragment((Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_POPULAR));
        viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText(R.string.latest);
                    }
                    if (position == 1) {
                        tab.setText(R.string.updates);
                    }
                    if (position == 2) {
                        tab.setText(R.string.popular);
                    }

                }
        ).attach();
    }
}