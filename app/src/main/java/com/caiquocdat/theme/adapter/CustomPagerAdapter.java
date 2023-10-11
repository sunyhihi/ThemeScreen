package com.caiquocdat.theme.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.caiquocdat.theme.model.FavouritesModel;

import java.util.ArrayList;

public class CustomPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<FavouritesModel> mListDrawable;

    public CustomPagerAdapter(Context context, ArrayList<FavouritesModel> listDrawable) {
        mContext = context;
        mListDrawable = listDrawable;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ImageView imageView = new ImageView(mContext);
        Glide.with(mContext)
                .load(mListDrawable.get(position).getPath()) // Lấy đường dẫn hình ảnh từ đối tượng theme
                .into(imageView);
//        imageView.setImageDrawable(mListDrawable.get(position));
        collection.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mListDrawable.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
