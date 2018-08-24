package com.com.greenacademy.englishlearning.Holder;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.englishlearning.R;

public class TopicHolder extends RecyclerView.ViewHolder {
    private ImageView imgTopic;
    private TextView tvTopic;

    public TopicHolder(@NonNull View itemView) {
        super(itemView);
        this.imgTopic = itemView.findViewById(R.id.img_topic);
        this.tvTopic = itemView.findViewById(R.id.tv_topic);
    }

    public void setData(int imgRes,String name){
        imgTopic.setImageResource(imgRes);
        tvTopic.setText(name);
    }
}
