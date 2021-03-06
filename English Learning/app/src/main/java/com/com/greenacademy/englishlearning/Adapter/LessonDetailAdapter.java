package com.com.greenacademy.englishlearning.Adapter;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.com.greenacademy.englishlearning.Fragment.ListLessonFragment;
import com.com.greenacademy.englishlearning.Holder.LessonHolder;
import com.com.greenacademy.englishlearning.Model.Lesson;
import com.greenacademy.englishlearning.R;

import java.util.ArrayList;
import java.util.List;

public class LessonDetailAdapter extends RecyclerView.Adapter<LessonHolder> {

    private List<Lesson> listLesson = new ArrayList<>();
    private List<Integer> listBackground = new ArrayList<>();

    private ListLessonFragment listLessonFragment;

    public void setListLessonFragment(ListLessonFragment listLessonFragment) {
        this.listLessonFragment = listLessonFragment;
    }

    public void setListLesson(List<Lesson> listLesson) {
        this.listLesson = listLesson;
    }

    @NonNull
    @Override
    public LessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_detail, parent, false);

        @SuppressLint("Recycle") TypedArray list = view.getResources().obtainTypedArray(R.array.nameOfImg);
        for (int i = 0; i < list.length(); i++) {
            listBackground.add(list.getResourceId(i, -1));
        }

        return new LessonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LessonHolder holder, final int position) {

        holder.tvTitle.setText(listLesson.get(position).getTitle());
        holder.tvDesc.setText(listLesson.get(position).getDesc());

        holder.background.setImageResource(listBackground.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listLessonFragment.startLesson(position, listLesson.get(position).getTitle(), listLesson.get(position).getDesc(), listBackground.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return listLesson.size();
    }


}
