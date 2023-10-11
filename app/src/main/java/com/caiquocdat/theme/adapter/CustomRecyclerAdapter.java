package com.caiquocdat.theme.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.caiquocdat.theme.R;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder> {
    private ArrayList<Drawable> mListDrawable;
    private Context mContext;

    public CustomRecyclerAdapter(Context context, ArrayList<Drawable> listDrawable) {
        mContext = context;
        mListDrawable = listDrawable;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.themeImg);
        }
    }

    @Override
    public CustomRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme_rcv, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageView.setImageDrawable(mListDrawable.get(position));
    }

    @Override
    public int getItemCount() {
        return mListDrawable.size();
    }
}