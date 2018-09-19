package com.com.greenacademy.englishlearning.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.com.greenacademy.englishlearning.Fragment.ChattingFragment;
import com.greenacademy.englishlearning.R;

public class PracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);



        toolBar();

        ChattingFragment chattingFragment = new ChattingFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, chattingFragment).commit();
    }

    public void toolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Practice");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
