package com.com.greenacademy.englishlearning.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.com.greenacademy.englishlearning.Fragment.WritingSkillFragment;
import com.greenacademy.englishlearning.R;

public class WriteSkillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_skill);

        getSupportActionBar().setTitle("Writing Skill");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WritingSkillFragment writingSkillFragment = new WritingSkillFragment();
        writingSkillFragment.setIdLesson(getIntent().getIntExtra("idLesson", -1));
        writingSkillFragment.setResourceBg(getIntent().getIntExtra("resourceBg", -1));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, writingSkillFragment).commit();
    }
}
