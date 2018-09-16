package com.com.greenacademy.englishlearning.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.com.greenacademy.englishlearning.Fragment.ListLessonFragment;
import com.com.greenacademy.englishlearning.Fragment.ListenSkillFragment;
import com.greenacademy.englishlearning.R;

public class LessonOfListenSkillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_of_listen_skill);

        ListenSkillFragment listenSkillFragment = new ListenSkillFragment();
        listenSkillFragment.setIdLesson(getIntent().getIntExtra("idLesson", -1));
        listenSkillFragment.setResourceBg(getIntent().getIntExtra("resourceBg", -1));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, listenSkillFragment).commit();
    }
}
