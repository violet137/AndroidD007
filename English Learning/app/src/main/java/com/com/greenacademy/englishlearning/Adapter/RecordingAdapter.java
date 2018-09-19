package com.com.greenacademy.englishlearning.Adapter;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.com.greenacademy.englishlearning.AsyncTask.SendRecordingAsyncTask;
import com.com.greenacademy.englishlearning.Fragment.ConversionSkillFragment;
import com.com.greenacademy.englishlearning.Holder.ItemViewRecordingHolder;
import com.com.greenacademy.englishlearning.Model.QuestionDone;
import com.com.greenacademy.englishlearning.Model.Recording;
import com.greenacademy.englishlearning.R;

import java.util.ArrayList;
import java.util.List;

public class RecordingAdapter extends RecyclerView.Adapter<ItemViewRecordingHolder> {
    private List<String> listOfText;
    private int positionChoosed = -1;
    private List<Integer> listNumberIcon = new ArrayList<>();
    private List<Integer> listNumberIconChecked = new ArrayList<>();
    private List<QuestionDone> listQuestionDone = new ArrayList<>();
    private ConversionSkillFragment conversionSkillFragment;
//    private List<QuestionDone> listQSDone = new ArrayList<>();

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

    public void addQuestionExist(List<QuestionDone> list) {
        boolean isFileExist = false;
        for (int i = 0; i < listOfText.size(); i++) {
            listQuestionDone.add(new QuestionDone(i, null));
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPathFile() != null) {
                listQuestionDone.get(list.get(i).getNumberOfQuestion()).setPathFile(list.get(i).getPathFile());
                isFileExist = true;
            }
        }
        if (!isFileExist) {
            listQuestionDone.clear();
        }

    }

    public void setListOfText(List<String> listOfText) {
        this.listOfText = listOfText;
    }

    public void setConversionSkillFragment(ConversionSkillFragment conversionSkillFragment) {
        this.conversionSkillFragment = conversionSkillFragment;
    }

    private boolean checkQuestionDone(int position) {
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

                SendRecordingAsyncTask sendRecordingAsyncTask = new SendRecordingAsyncTask();
                sendRecordingAsyncTask.setConversionSkillFragment(conversionSkillFragment);
                sendRecordingAsyncTask.setIdLesson(conversionSkillFragment.getIdLesson());
                sendRecordingAsyncTask.execute(new Recording(numberOfQuestion, pathFile));

                break;
            }
        }
    }

    private boolean checkNullRecord(int position) {
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
    public void onBindViewHolder(@NonNull ItemViewRecordingHolder holder, final int position) {

        holder.imgChinh.setEnabled(false);


        if (position == listOfText.size() - 1) {
            holder.imgPhu.setVisibility(View.GONE);
        } else {
            holder.imgPhu.setVisibility(View.VISIBLE);
        }

        if (position == positionChoosed && !checkNullRecord(position)) {
            conversionSkillFragment.setAbleBtnRecord();
            holder.imgChinh.setImageResource(R.drawable.music);
            holder.imgPhu.setImageResource(R.color.imgPhu_2);
        } else if (checkQuestionDone(position)) {
            if (listQuestionDone.get(position).getPathFile() != null) {
                System.out.println("pos: " + position + " : " + listQuestionDone.get(position).getPathFile());
                holder.imgChinh.setImageResource(R.drawable.checking);
                if (position == positionChoosed) {
                    conversionSkillFragment.setUnableBtnRecord();
                    conversionSkillFragment.chooseQuestion(listQuestionDone.get(position));
                }
            } else if (position != positionChoosed) {
                if (listQuestionDone.get(position).getPathFile() == null)
                    holder.imgChinh.setImageResource(listNumberIconChecked.get(position));
            }

            if (position == listQuestionDone.size() - 1) {
                conversionSkillFragment.hideBtnRecord(); // vi tri cuoi cung
            }
        } else {
            conversionSkillFragment.hideBtnRecord(); // an nut play record
            holder.imgChinh.setImageResource(listNumberIcon.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return listOfText.size();
    }
}
