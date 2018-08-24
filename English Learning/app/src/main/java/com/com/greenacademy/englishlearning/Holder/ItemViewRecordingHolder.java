package com.com.greenacademy.englishlearning.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.greenacademy.englishlearning.R;

public class ItemViewRecordingHolder extends RecyclerView.ViewHolder {
    View view;
    public ImageView imgChinh;
    public ImageView imgPhu;

    public ItemViewRecordingHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        imgChinh = view.findViewById(R.id.profile_image);
        imgPhu = view.findViewById(R.id.imageView);
    }
}
