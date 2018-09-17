package com.com.greenacademy.englishlearning.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.com.greenacademy.englishlearning.Activity.AllSkillActivity;
import com.com.greenacademy.englishlearning.Activity.PracticeActivity;
import com.com.greenacademy.englishlearning.Adapter.LessonDetailAdapter;
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

    TextView tvWaiting;
    ImageView imgWaiting;
    FrameLayout frameLayout;


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
        frameLayout = view.findViewById(R.id.frameLayout);


        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PracticeActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void getData(List<Lesson> list) {
        this.listLesson = list;

        getBackground();
    }

    public void getBackground() {
        progressBar.setVisibility(View.GONE);
        tvWaiting.setVisibility(View.GONE);
        imgWaiting.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

//        SnapHelper snapHelper = new PagerSnapHelper(); // dung nhu ViewPager. Dang cap
//        snapHelper.attachToRecyclerView(recyclerView);

        lessonDetailAdapter = new LessonDetailAdapter();
        lessonDetailAdapter.setListLesson(listLesson);
        lessonDetailAdapter.setListLessonFragment(this);

        recyclerView.setAdapter(lessonDetailAdapter);
    }

    public void startLesson(int position, String title, int resourceBg) {
        Intent intent = new Intent(getActivity(), AllSkillActivity.class);
        intent.putExtra("idLesson", position + 1);
        intent.putExtra("title", title);
        intent.putExtra("resourceBg", resourceBg);
        startActivity(intent);
    }
}
