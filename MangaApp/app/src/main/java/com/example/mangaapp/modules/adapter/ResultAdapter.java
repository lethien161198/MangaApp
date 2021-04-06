package com.example.mangaapp.modules.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.databinding.ItemMangBinding;
import com.example.mangaapp.models.Manga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder>{
    private List<Manga> mData = new ArrayList<>();
    private Context mContext;
    private ResultAdapter.OnClickItem onClickItem;
    public interface OnClickItem{
        void sendUrl(String url);
        void sendUrlChapter(String urlChapter);
    }
    public ResultAdapter(List<Manga> mData, Context mContext, ResultAdapter.OnClickItem onClickItem) {
        this.mData = mData;
        this.mContext = mContext;
        this.onClickItem = onClickItem;
    }
    public void removeAll(){
        mData.clear();
        notifyDataSetChanged();
    }
    public List<Manga> getmData() {
        return mData;
    }

    public void setmData(List<Manga> mData) {
        this.mData = mData;
    }

    public void sortAz(){
        List<Manga> list = getmData();
        Collections.sort(list, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        setmData(list);
    }
    public void sortRating(){

        List<Manga> list = getmData();
        Collections.sort(list, (o1, o2) -> o2.getRating().compareTo(o1.getRating()));
        setmData(list);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResultAdapter.MyViewHolder(ItemMangBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.nameManga.setText(mData.get(position).getName());
        Utilities.loadImage(mContext,mData.get(position).getImage(),holder.binding.image);
        holder.binding.chapManga.setText(mData.get(position).getLatestChapter());
        holder.binding.imageManga.setOnClickListener(v -> onClickItem.sendUrl(mData.get(position).getUrl()));
        holder.binding.chapManga.setOnClickListener(v -> onClickItem.sendUrlChapter(mData.get(position).getUrlChapter()));
        holder.binding.ratingbar.setRating(mData.get(position).getRating()/2);
        holder.binding.txtRate.setText(mData.get(position).getRating()+"/10.0");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemMangBinding binding;

        public MyViewHolder(@NonNull ItemMangBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

        }

    }
}
