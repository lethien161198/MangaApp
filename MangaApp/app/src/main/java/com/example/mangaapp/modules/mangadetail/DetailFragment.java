package com.example.mangaapp.modules.mangadetail;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangaapp.R;
import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.components.CustomProgress;
import com.example.mangaapp.databinding.FragmentDetailBinding;
import com.example.mangaapp.models.Chapter;
import com.example.mangaapp.models.MangaDetail;
import com.example.mangaapp.models.Version;
import com.example.mangaapp.modules.adapter.ViewPagerAdapter;
import com.example.mangaapp.modules.mangadetail.chapter.ChapterFragment;
import com.example.mangaapp.modules.mangadetail.description.DescriptionContract;
import com.example.mangaapp.modules.mangadetail.description.DescriptionFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class DetailFragment extends FragmentView<DetailContract.Presenter, FragmentDetailBinding> implements DetailContract.View {
    private TabLayout tabLayout1;
    private ViewPager2 viewPager2;
    @Override
    protected FragmentDetailBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {

        return FragmentDetailBinding.inflate(inflater, container, false);
    }

    @Override
    protected void init() {
        CustomProgress.FadingCircle(getBinding().progressCircular);
        getBinding().headerTitle.iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_ios_24));
        getBinding().headerTitle.iconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        viewPager2 = getBinding().viewPagerDetail;
        tabLayout1 = getBinding().tab;
        showBottomBar(false);
        getPresenter().getMangaDetail();

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
    public void loadInfo(MangaDetail mangaDetail) {
        getBinding().author.setText(mangaDetail.getAuthor());
        getBinding().artist.setText(mangaDetail.getArtist());
        getBinding().alternative.setText(mangaDetail.getAlternative());
        getBinding().status.setText(mangaDetail.getStatus());
        getBinding().genre.setText(mangaDetail.getGenre());
        getBinding().rating.setText(mangaDetail.getRating());
        getBinding().headerTitle.title.setText(mangaDetail.getName());
        getBinding().ratingbar.setRating(mangaDetail.getRatingbar()/2);
        Utilities.loadImage(getContext(),mangaDetail.getImage(),getBinding().image);

        //Setup ViewPager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        Bundle sendDes = new Bundle();
        sendDes.putString(Utilities.KEY_DES,mangaDetail.getDescription());
        viewPagerAdapter.addFragment((Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_DESCRIPTION,sendDes));

        Bundle sendChapter = new Bundle();

        sendChapter.putSerializable(Utilities.KEY_MAP_CHAPTER, mangaDetail.getListMap());
        viewPagerAdapter.addFragment((Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_CHAPTER,sendChapter));

        viewPager2.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout1, viewPager2,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText(R.string.description);
                    }
                    if (position == 1) {
                        tab.setText(R.string.chapter);
                    }
                }
        ).attach();

        hideProgress();
    }
}