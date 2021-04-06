package com.example.mangaapp.modules.mangadetail.chapter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.mangaapp.common.App;
import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.databinding.FragmentChapterBinding;
import com.example.mangaapp.models.Chapter;
import com.example.mangaapp.models.Version;
import com.example.mangaapp.modules.adapter.ExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChapterFragment extends FragmentView<ChapterContract.Presenter, FragmentChapterBinding> implements ChapterContract.View {
    private ExpandableListView expandableListView;
    private List<Version> versionList;
    private ExpandableListAdapter expandableListAdapter;

    @Override
    protected FragmentChapterBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentChapterBinding.inflate(inflater, container, false);
    }

    @Override
    protected void init() {
        expandableListView = getBinding().expandable;
        getPresenter().getListChapter();

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }



    @Override
    public void loadListChapter(HashMap<Version, List<Chapter>> listMap) {
        versionList = new ArrayList<>(listMap.keySet());
        expandableListAdapter = new ExpandableListViewAdapter(versionList, listMap, url -> {
            Bundle bundle = new Bundle();
            bundle.putString(Utilities.KEY_URL_CHAPTER,url);
            navigate(AppNavigator.ROUTE_READ,bundle);
        });
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void setExpandableListViewHeight(ExpandableListView listView,
                                             int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }
}