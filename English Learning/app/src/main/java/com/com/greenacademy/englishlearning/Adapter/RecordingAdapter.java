package com.com.greenacademy.englishlearning.Adapter;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.com.greenacademy.englishlearning.Holder.ItemViewRecordingHolder;
import com.com.greenacademy.englishlearning.Interface.HiddenRecord;
import com.com.greenacademy.englishlearning.Model.QuestionDone;
import com.greenacademy.englishlearning.R;

import java.util.ArrayList;
import java.util.List;

public class RecordingAdapter extends RecyclerView.Adapter<ItemViewRecordingHolder> {

    String[] listOfText;
    int positionChoosed = -1;
    List<Integer> listNumberIcon = new ArrayList<>();
    List<Integer> listNumberIconChecked = new ArrayList<>();
    List<QuestionDone> listQuestionDone = new ArrayList<>();
    HiddenRecord hiddenRecord;

    public void setPositionChoosed(int positionChoosed) {
        this.positionChoosed = positionChoosed;
    }

    public void addQuestionDone(QuestionDone questionDone) {
        if (listQuestionDone.size() == 0) {
            listQuestionDone.add(questionDone);
        } else {
            for (int i = 0; i < listQuestionDone.size(); i++) {
                if (listQuestionDone.get(i).getNumberOfQuestion() == questionDone.getNumberOfQuestion()) {
                    return;
                }
            }
            listQuestionDone.add(questionDone);
        }
    }

    public void setListOfText(String[] listOfText) {
        this.listOfText = listOfText;
    }

    public void setHiddenRecord(HiddenRecord hiddenRecord) {
        this.hiddenRecord = hiddenRecord;
    }

    public boolean checkQuestionDone(int position) {
        for (int i = 0; i < listQuestionDone.size(); i++) {
            if (listQuestionDone.get(i).getNumberOfQuestion() == position) {
                return true;
            }
        }
        return false;
    }

    public void addPathFile(int numberOfQuestion, String pathFile) {
        for (int i = 0; i < listQuestionDone.size(); i++) {
            if (listQuestionDone.get(i).getNumberOfQuestion() == numberOfQuestion) {
                listQuestionDone.get(i).setPathFile(pathFile);
                break;
            }
        }
    }

    public boolean checkNullRecord(int position) {
        for (int i = 0; i < listQuestionDone.size(); i++) {
            if (listQuestionDone.get(i).getNumberOfQuestion() == position) {
                if (listQuestionDone.get(i).getPathFile() != null)
                    return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public ItemViewRecordingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recordding_recycle_view, parent, false);
        TypedArray list = view.getResources().obtainTypedArray(R.array.number);
        for (int i = 0; i < list.length(); i++) {
            listNumberIcon.add(list.getResourceId(i, -1));
        }
        TypedArray listChecked = view.getResources().obtainTypedArray(R.array.number_checked);
        for (int i = 0; i < list.length(); i++) {
            listNumberIconChecked.add(listChecked.getResourceId(i, -1));
        }

        return new ItemViewRecordingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewRecordingHolder holder, final int position) {

        holder.imgChinh.setEnabled(false);


        if (position == listOfText.length - 1) {
            holder.imgPhu.setVisibility(View.GONE);
        }

        if (position == positionChoosed && !checkNullRecord(position)) {
            hiddenRecord.setAbleBtnRecord();
            holder.imgChinh.setImageResource(R.drawable.music);
            holder.imgPhu.setImageResource(R.color.imgPhu_2);
        } else if (checkQuestionDone(position)) {
            if (listQuestionDone.get(position).getPathFile() != null) {
                holder.imgChinh.setImageResource(R.drawable.checking);
                if (position == positionChoosed) {
                    hiddenRecord.setUnableBtnRecord();
                    hiddenRecord.chooseQuestion(listQuestionDone.get(position));
                }
            } else if (position != positionChoosed && listQuestionDone.get(position).getPathFile() == null) {
                holder.imgChinh.setImageResource(listNumberIconChecked.get(position));
            }

            if (position == listQuestionDone.size() - 1) {
                hiddenRecord.hideBtnRecord(); // vi tri cuoi cung
            }
        } else {
            hiddenRecord.hideBtnRecord(); // an nut play record
            holder.imgChinh.setImageResource(listNumberIcon.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return listOfText.length;
    }
}