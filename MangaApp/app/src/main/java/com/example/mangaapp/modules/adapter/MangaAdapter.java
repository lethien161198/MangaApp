package com.example.mangaapp.modules.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.databinding.ItemLatestBinding;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.modules.database.MangaDatabase;

import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MyViewHolder> {
    private List<Manga> mData ;
    private Context mContext;
    private OnClickItem onClickItem;
    public interface OnClickItem{
        void sendUrl(String url);
        void sendUrlChapter(String urlChapter);
        void sendFavorite(Manga manga);
        void saveRecent(Manga manga);
    }
    public MangaAdapter(List<Manga> mData, Context mContext,OnClickItem onClickItem) {
        this.mData = mData;
        this.mContext = mContext;
        this.onClickItem = onClickItem;
    }
    public void removeAll(){
        mData.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemLatestBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.nameManga.setText(mData.get(position).getName());
        Utilities.loadImage(mContext,mData.get(position).getImage(),holder.binding.image);
        holder.binding.chapManga.setText(mData.get(position).getLatestChapter());
        holder.binding.imageManga.setOnClickListener(v -> {
            onClickItem.sendUrl(mData.get(position).getUrl());
            onClickItem.saveRecent(mData.get(position));
        });
        holder.binding.chapManga.setOnClickListener(v -> onClickItem.sendUrlChapter(mData.get(position).getUrlChapter()));
        if(mData.get(position).getFavourite()){
            holder.binding.favourite.setImageResource(R.drawable.ic_favorite_true_24);
        }else {
            holder.binding.favourite.setImageResource(R.drawable.ic_favorite_false_24);
        }
        holder.binding.favourite.setOnClickListener(view -> {

            if(mData.get(position).getFavourite()){
                holder.binding.favourite.setImageResource(R.drawable.ic_favorite_false_24);
                mData.get(position).setFavourite(false);
            }else {
                holder.binding.favourite.setImageResource(R.drawable.ic_favorite_true_24);
                mData.get(position).setFavourite(true);
            }
            onClickItem.sendFavorite(mData.get(position));
        });
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
