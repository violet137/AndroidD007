package com.com.greenacademy.englishlearning.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.greenacademy.englishlearning.R;

public class ChattingFragment extends Fragment{
    int position;
    public ChattingFragment() {
    }

    @SuppressLint("ValidFragment")
    public ChattingFragment(int position) {
        this.position = position;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting_bot,container,false);
        return view;
    }

}
