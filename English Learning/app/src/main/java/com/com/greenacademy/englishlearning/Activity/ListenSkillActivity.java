package com.com.greenacademy.englishlearning.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.com.greenacademy.englishlearning.Fragment.ListenSkillFragment;
import com.greenacademy.englishlearning.R;

public class ListenSkillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_of_listen_skill);

        getSupportActionBar().setTitle("Listening Skill");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListenSkillFragment listenSkillFragment = new ListenSkillFragment();
        listenSkillFragment.setIdLesson(getIntent().getIntExtra("idLesson", -1));
        listenSkillFragment.setResourceBg(getIntent().getIntExtra("resourceBg", -1));
        listenSkillFragment.setDesc(getIntent().getStringExtra("desc"));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, listenSkillFragment).commit();
    }
}
