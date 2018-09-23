package com.com.greenacademy.englishlearning.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.com.greenacademy.englishlearning.Fragment.VocabularySkillFragment;
import com.greenacademy.englishlearning.R;

public class VocabularySkillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_of_listen_skill);

        getSupportActionBar().setTitle("Vocabulary Skill");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        VocabularySkillFragment vocabularySkillFragment = new VocabularySkillFragment();
        vocabularySkillFragment.setIdLesson(getIntent().getIntExtra("idLesson", -1));
        vocabularySkillFragment.setResourceBg(getIntent().getIntExtra("resourceBg", -1));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, vocabularySkillFragment).commit();
    }
}
