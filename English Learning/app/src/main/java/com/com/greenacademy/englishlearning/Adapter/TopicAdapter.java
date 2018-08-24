package com.com.greenacademy.englishlearning.Adapter;

import android.content.Context;
import android.graphics.ColorFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.com.greenacademy.englishlearning.Holder.TopicHolder;
import com.com.greenacademy.englishlearning.Interface.ItemClickTopic;
import com.greenacademy.englishlearning.R;

public class TopicAdapter extends RecyclerView.Adapter<TopicHolder> {
    private LayoutInflater layoutInflater;
    private int[] imgResTopic;
    private String[] tvResTopic;
    public ItemClickTopic itemClickTopic;

    public TopicAdapter(Context context, int[] imgResTopic, String[] tvResTopic, ItemClickTopic itemClickTopic) {
        this.layoutInflater = LayoutInflater.from(context);
        this.imgResTopic = imgResTopic;
        this.tvResTopic = tvResTopic;
        this.itemClickTopic = itemClickTopic;
    }


    @NonNull
    @Override
    public TopicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TopicHolder(layoutInflater.inflate(R.layout.frame_item_topic_chatting_bot,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TopicHolder topicHolder, final int i) {
        final int imgResTopicId = imgResTopic[i];
        final String name = tvResTopic[i];
        topicHolder.setData(imgResTopicId,name);

        topicHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemClickTopic.onItemClick(view,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvResTopic.length;
    }
}
