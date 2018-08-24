package com.com.greenacademy.englishlearning.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.greenacademy.englishlearning.R;

public class CancelFragment extends Fragment{
    TextView tvNameTopic;
    Button btnCancel;
    String[] arrResNameTopic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cancel_chatting_bot,container,false);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        tvNameTopic = view.findViewById(R.id.tv_name_topic);
        btnCancel = view.findViewById(R.id.btn_cancel);

        arrResNameTopic = getResources().getStringArray(R.array.arr_name_topic);
        ChattingFragment chattingFragment = (ChattingFragment) fragmentManager.findFragmentByTag("Second Chatting");
        tvNameTopic.setText(arrResNameTopic[chattingFragment.position]);

        return view;
    }
}
