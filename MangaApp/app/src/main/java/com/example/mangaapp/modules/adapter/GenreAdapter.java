package com.example.mangaapp.modules.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.databinding.ItemGenreBinding;
import com.example.mangaapp.databinding.ItemLatestBinding;
import com.example.mangaapp.models.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder>{
    private List<Genre> mData;
    private OnClickItem onClickItem;

    public GenreAdapter(List<Genre> mData, OnClickItem onClickItem) {
        this.mData = mData;
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem{
        void sendGenre(Genre genre);
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenreAdapter.GenreViewHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.itemGenreBinding.nameGenre.setText(mData.get(position).getNameGen());
        holder.itemGenreBinding.checkbox.setChecked(mData.get(position).isChecked());
        holder.itemGenreBinding.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.itemGenreBinding.checkbox.isChecked()){
                    onClickItem.sendGenre(new Genre(mData.get(position).getNameGen(),true));
                }
                else onClickItem.sendGenre(new Genre(mData.get(position).getNameGen(),false));
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mData != null){
            return mData.size();
        }
        return 0;
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder{
        private ItemGenreBinding itemGenreBinding;
        public GenreViewHolder(@NonNull ItemGenreBinding itemView) {
            super(itemView.getRoot());
            this.itemGenreBinding = itemView;
        }
    }
}
