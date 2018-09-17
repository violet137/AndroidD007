package com.com.greenacademy.englishlearning.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.com.greenacademy.englishlearning.Fragment.ListLessonFragment;
import com.greenacademy.englishlearning.R;

public class ListLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);

        ListLessonFragment listLessonFragment = new ListLessonFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, listLessonFragment).commit();
    }
}
