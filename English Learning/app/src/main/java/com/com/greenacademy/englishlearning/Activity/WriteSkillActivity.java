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

        WritingSkillFragment writingSkillFragment = new WritingSkillFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, writingSkillFragment).commit();
    }
}
