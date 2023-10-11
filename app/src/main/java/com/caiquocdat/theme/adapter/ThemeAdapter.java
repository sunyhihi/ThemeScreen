package com.caiquocdat.theme.adapter;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.caiquocdat.theme.DetailThemeActivity;
import com.caiquocdat.theme.databinding.ItemThemeGridBinding;
import com.caiquocdat.theme.model.ThemeModel;

import java.io.IOException;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {

    private Context context;
    private List<ThemeModel> themeList;


    public ThemeAdapter(Context context, List<ThemeModel> themeList) {
        this.context = context;
        this.themeList = themeList;
    }

    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemThemeGridBinding itemThemeGridBinding = ItemThemeGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ThemeViewHolder(itemThemeGridBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, int position) {
        ThemeModel theme = themeList.get(position);
        holder.itemAnswerBinding.itemTv.setText(theme.getTitle());
        Glide.with(holder.itemAnswerBinding.themeImg.getContext())
                .load(theme.getImagePath()) // Lấy đường dẫn hình ảnh từ đối tượng theme
                .into(holder.itemAnswerBinding.themeImg);
        holder.itemAnswerBinding.themeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetailThemeActivity.class);
                intent.putExtra("potition",position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    public static class ThemeViewHolder extends RecyclerView.ViewHolder {
        ItemThemeGridBinding itemAnswerBinding;


        public ThemeViewHolder(@NonNull ItemThemeGridBinding itemAnswerBinding) {
            super(itemAnswerBinding.getRoot());
            this.itemAnswerBinding = itemAnswerBinding;
        }
    }
}
