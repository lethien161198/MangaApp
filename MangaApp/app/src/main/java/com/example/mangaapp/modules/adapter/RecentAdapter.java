package com.example.mangaapp.modules.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mangaapp.R;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.databinding.ItemLatestBinding;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.models.Recent;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.MyViewHolder> {
    private List<Recent> mData;
    private Context mContext;
    private OnClickItem onClickItem;

    public interface OnClickItem {
        void sendUrl(String url);

        void sendUrlChapter(String urlChapter);
    }

    public RecentAdapter(List<Recent> mData, Context mContext, OnClickItem onClickItem) {
        List<Recent> newData = new ArrayList<>();
        for (int i = mData.size() - 1; i >= 0;i-- ){
            newData.add(mData.get(i));
        }


        this.mData = newData;
        this.mContext = mContext;
        this.onClickItem = onClickItem;
    }

    public void removeAll() {
        mData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecentAdapter.MyViewHolder(ItemLatestBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecentAdapter.MyViewHolder holder, int position) {

        holder.binding.nameManga.setText(mData.get(position).getName());
        Utilities.loadImage(mContext, mData.get(position).getImage(), holder.binding.image);
        holder.binding.chapManga.setText(mData.get(position).getLatestChapter());
        holder.binding.imageManga.setOnClickListener(v -> {
            onClickItem.sendUrl(mData.get(position).getUrl());
        });
        holder.binding.chapManga.setOnClickListener(v -> onClickItem.sendUrlChapter(mData.get(position).getUrlChapter()));
        if(mData.get(position).getFavourite()){
            holder.binding.favourite.setImageResource(R.drawable.ic_favorite_true_24);
        }else {
            holder.binding.favourite.setImageResource(R.drawable.ic_favorite_false_24);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemLatestBinding binding;

        public MyViewHolder(@NonNull ItemLatestBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

        }

    }
}
