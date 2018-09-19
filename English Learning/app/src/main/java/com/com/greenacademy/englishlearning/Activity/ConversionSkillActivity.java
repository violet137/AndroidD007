package com.com.greenacademy.englishlearning.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.com.greenacademy.englishlearning.Fragment.ConversionSkillFragment;
import com.greenacademy.englishlearning.R;

public class ConversionSkillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_of_listen_skill);

        getSupportActionBar().setTitle("Conversion Skill");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ConversionSkillFragment conversionSkillFragment = new ConversionSkillFragment();
        conversionSkillFragment.setIdLesson(getIntent().getIntExtra("idLesson", -1));
        conversionSkillFragment.setResourceBg(getIntent().getIntExtra("resourceBg", -1));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, conversionSkillFragment).commit();
    }
}
