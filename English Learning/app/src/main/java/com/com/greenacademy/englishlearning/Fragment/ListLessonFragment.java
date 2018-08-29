package com.com.greenacademy.englishlearning.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.com.greenacademy.englishlearning.Activity.LessonOfListenSkillActivity;
import com.com.greenacademy.englishlearning.Activity.ListenSkillActivity;
import com.com.greenacademy.englishlearning.Adapter.LessonDetailAdapter;
import com.com.greenacademy.englishlearning.AsyncTask.GetBackgroundAsyncTask;
import com.com.greenacademy.englishlearning.AsyncTask.GetDataAsyncTask;
import com.com.greenacademy.englishlearning.Interface.GetterData;
import com.com.greenacademy.englishlearning.Model.Lesson;
import com.greenacademy.englishlearning.R;

import java.util.ArrayList;
import java.util.List;

public class ListLessonFragment extends Fragment implements GetterData {

    RecyclerView recyclerView;
    LessonDetailAdapter lessonDetailAdapter;
    ProgressBar progressBar;

    List<Lesson> listLesson = new ArrayList<>();
    List<Bitmap> listBackground = new ArrayList<>();

    TextView tvWaiting;
    ImageView imgWaiting;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_lesson_fragment, container, false);

        GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask();
        getDataAsyncTask.setGetterData(this);
        getDataAsyncTask.execute(0);

        recyclerView = view.findViewById(R.id.recycleView);
        progressBar = view.findViewById(R.id.progressLoadInfo);
        tvWaiting = view.findViewById(R.id.tvWaiting);
        imgWaiting = view.findViewById(R.id.imageWaiting);


        return view;
    }

    @Override
    public void getData(List<Lesson> list) {
        this.listLesson = list;

        GetBackgroundAsyncTask getBackgroundAsyncTask = new GetBackgroundAsyncTask();
        getBackgroundAsyncTask.setGetterData(this);
        getBackgroundAsyncTask.execute(listLesson);

    }

    @Override
    public void getBackground(List<Bitmap> list) {
        this.listBackground = list;
//        thread.stop();
        progressBar.setVisibility(View.GONE);
        tvWaiting.setVisibility(View.GONE);
        imgWaiting.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelper = new PagerSnapHelper(); // dung nhu ViewPager. Dang cap
        snapHelper.attachToRecyclerView(recyclerView);

        lessonDetailAdapter = new LessonDetailAdapter();
        lessonDetailAdapter.setListBackground(listBackground);
        lessonDetailAdapter.setListLesson(listLesson);
        lessonDetailAdapter.setListLessonFragment(this);

        recyclerView.setAdapter(lessonDetailAdapter);
    }

    public void startLesson(int position) {
        Intent intent = new Intent(getActivity(), LessonOfListenSkillActivity.class);
        intent.putExtra("idLesson", position + 1);
        startActivity(intent);
    }
}
