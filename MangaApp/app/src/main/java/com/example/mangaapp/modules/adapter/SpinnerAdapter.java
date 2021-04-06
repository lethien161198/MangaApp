package com.example.mangaapp.modules.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mangaapp.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {
    private List<String> mList;
    public SpinnerAdapter(@NonNull Context context, int resource, List<String> list) {
        super(context, resource, list);
        this.mList = list;
    }

    @Override
    public int getCount() {
        if(mList.size()!=0){
            return mList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
        TextView textView = convertView.findViewById(R.id.tv_selected);
        String string = this.getItem(position);
        if(string != null){
            textView.setText(string);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner,parent,false);
        TextView textView = convertView.findViewById(R.id.tv_category);
        String string = this.getItem(position);
        if(string != null){
            textView.setText(string);
        }
        return convertView;
    }
}
