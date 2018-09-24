package com.com.greenacademy.englishlearning.Adapter;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.com.greenacademy.englishlearning.Fragment.WritingSkillFragment;
import com.com.greenacademy.englishlearning.Holder.ItemViewRecordingHolder;
import com.greenacademy.englishlearning.R;

import java.util.ArrayList;
import java.util.List;

public class WritingAdapter extends RecyclerView.Adapter<ItemViewRecordingHolder> {

    private List<Integer> listNumberIcon = new ArrayList<>();
    private List<Integer> listNumberIconChecked = new ArrayList<>();
    int numberOfWord;
    WritingSkillFragment writingSkillFragment;

    public void setWritingSkillFragment(WritingSkillFragment writingSkillFragment) {
        this.writingSkillFragment = writingSkillFragment;
    }

    public WritingAdapter(int numberOfWord) {
        this.numberOfWord = numberOfWord;
    }



    @NonNull
    @Override
    public ItemViewRecordingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recordding_recycle_view, viewGroup, false);
        @SuppressLint("Recycle") TypedArray list = view.getResources().obtainTypedArray(R.array.number);
        for (int i = 0; i < list.length(); i++) {
            listNumberIcon.add(list.getResourceId(i, -1));
        }
        @SuppressLint("Recycle") TypedArray listChecked = view.getResources().obtainTypedArray(R.array.number_checked);
        for (int i = 0; i < list.length(); i++) {
            listNumberIconChecked.add(listChecked.getResourceId(i, -1));
        }

        return new ItemViewRecordingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewRecordingHolder holder, final int position) {
        holder.imgChinh.setEnabled(true);


        if (position == numberOfWord - 1) {
            holder.imgPhu.setVisibility(View.GONE);
        } else {
            holder.imgPhu.setVisibility(View.VISIBLE);
        }



        holder.imgChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgChinh.setImageResource(listNumberIconChecked.get(position));
                holder.imgPhu.setImageResource(R.color.imgPhu_2);
                writingSkillFragment.loadAudio(position);
            }
        });
        holder.imgChinh.setImageResource(listNumberIcon.get(position));



    }

    @Override
    public int getItemCount() {
        return numberOfWord;
    }
}
