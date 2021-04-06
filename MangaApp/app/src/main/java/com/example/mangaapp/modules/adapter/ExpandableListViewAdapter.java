package com.example.mangaapp.modules.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.mangaapp.R;
import com.example.mangaapp.models.Chapter;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Version;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<Version> mListGroup;
    private HashMap<Version, List<Chapter>> mListItem;
    private OnClickItem onClickItem;

    public interface OnClickItem {
        void sendUrl(String url);
    }

    public ExpandableListViewAdapter(List<Version> mListGroup, HashMap<Version, List<Chapter>> mListItem, OnClickItem onClickItem) {
        this.mListGroup = mListGroup;
        this.mListItem = mListItem;
        this.onClickItem = onClickItem;
    }

    @Override
    public int getGroupCount() {
        if (mListGroup != null) {
            return mListGroup.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mListGroup != null && mListItem != null) {
            return mListItem.get(mListGroup.get(groupPosition)).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        Version version = mListGroup.get(groupPosition);
        return version.getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Chapter chapter = mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
        return chapter.getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_group, parent, false);


        }
        TextView tvGroup = convertView.findViewById(R.id.tvGroup);
        Version version = mListGroup.get(groupPosition);
        tvGroup.setText(version.getNameGroup());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        }
        TextView tvItem = convertView.findViewById(R.id.tvItem);
        Chapter chapter = mListItem.get(mListGroup.get(groupPosition)).get(childPosition);
        tvItem.setText(chapter.getNameChapter());
        tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.sendUrl(chapter.getUrlChapter());
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
//https://thedeveloperworldisyours.com/android/expandable-listview-inside-scrollview/
