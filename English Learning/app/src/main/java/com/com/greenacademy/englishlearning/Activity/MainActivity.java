package com.com.greenacademy.englishlearning.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.com.greenacademy.englishlearning.Fragment.ChattingFragment;
import com.com.greenacademy.englishlearning.Fragment.MainBackgroundFragment;
import com.greenacademy.englishlearning.R;

public class MainActivity extends AppCompatActivity implements MainBackgroundFragment.PositionClickTopic {
    FragmentManager fragmentManager = getSupportFragmentManager();
    MainBackgroundFragment mainBackgroundChattingFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_bot);
        mainBackgroundChattingFragment = new MainBackgroundFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_chatting_bot, mainBackgroundChattingFragment, "Main Chatting");
        fragmentTransaction.commit();

    }

    @Override
    public void positionTopic(int position, boolean isChoosedTopic) {
        if (!isChoosedTopic) {
            Toast.makeText(getApplicationContext(), "Please choose Topic First !!", Toast.LENGTH_LONG).show();
        } else {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_chatting_bot, new ChattingFragment(position), "Second Chatting");
            fragmentTransaction.addToBackStack("Second Chatting");
            fragmentTransaction.commit();
        }
    }
}
