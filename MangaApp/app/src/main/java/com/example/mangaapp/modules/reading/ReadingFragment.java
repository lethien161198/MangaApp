package com.example.mangaapp.modules.reading;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangaapp.R;
import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.databinding.FragmentReadingBinding;
import com.example.mangaapp.modules.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


public class ReadingFragment extends FragmentView<ReadingContract.Presenter, FragmentReadingBinding> implements ReadingContract.View {
    private TabLayout tabLayout1;
    private ViewPager2 viewPager2;

    @Override
    protected FragmentReadingBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentReadingBinding.inflate(inflater,container,false);
    }

    @Override
    protected void init() {
        showBottomBar(true);

        getBinding().headertitle.title.setText(R.string.reading);

        getBinding().headertitle.iconRight.setImageDrawable(getContext().getDrawable(R.drawable.ic_baseline_search_24));

        getBinding().headertitle.iconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(AppNavigator.ROUTE_SEARCH);
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

        viewPagerAdapter.addFragment((Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_FAVOURITE));
        viewPagerAdapter.addFragment((Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_RECENT));
        viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText(R.string.favourite);
                    }
                    if (position == 1) {
                        tab.setText(R.string.recent);
                    }


                }
        ).attach();
    }
}