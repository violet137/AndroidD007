package com.com.greenacademy.englishlearning.Fragment;


import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.com.greenacademy.englishlearning.Adapter.WritingAdapter;
import com.com.greenacademy.englishlearning.Model.WritingQuestion;
import com.greenacademy.englishlearning.R;

import java.util.List;


public class WritingSkillFragment extends Fragment implements View.OnClickListener {
    ImageView btnHint, btnSpeak;
    Button btnDone;
    ConstraintLayout layoutShowResult, layoutShowHint;
    EditText edtInput;
    TextView tvHint, tvShowResult;
    String strHint;
    MediaPlayer sound;
    RecyclerView recycleView;
    WritingAdapter writingAdapter;

    public WritingSkillFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_writingskills, container, false);
        btnHint = view.findViewById(R.id.btn_hint);
        btnSpeak = view.findViewById(R.id.btn_speak);
        btnDone = view.findViewById(R.id.btn_done);
        layoutShowHint = view.findViewById(R.id.layout_showhint);
        layoutShowResult = view.findViewById(R.id.layout_showresult);
        edtInput = view.findViewById(R.id.edt_inputanswer);
        tvHint = view.findViewById(R.id.tv_hint);

        tvShowResult = view.findViewById(R.id.tv_showresult);
        recycleView = view.findViewById(R.id.recycleView);

        sound = MediaPlayer.create(getContext(), R.raw.dailylife001);


        return view;
    }

    public void setUp(List<WritingQuestion> list) {
        strHint = "...";
        tvHint.setText(strHint);

        writingAdapter = new WritingAdapter(list.size());
        recycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycleView.setAdapter(writingAdapter);

        btnHint.setOnClickListener(this);
        btnSpeak.setOnClickListener(this);
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_hint:
                layoutShowHint.setVisibility(View.VISIBLE);
                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long l) {
                    }

                    @Override
                    public void onFinish() {
                        layoutShowHint.setVisibility(View.INVISIBLE);
                    }
                }.start();
                break;
            case R.id.btn_speak:
                sound.start();
                if (sound.isPlaying()) {
                    sound.seekTo(0);
                    sound.start();
                }
                break;
            case R.id.btn_done:
                if (edtInput.getText().toString().trim().equalsIgnoreCase(strHint)) {
                    tvShowResult.setText("Correct");
                    layoutShowResult.setBackgroundColor(Color.parseColor("#41bef8"));
                    btnDone.setBackgroundColor(Color.parseColor("#41bef8"));
                } else {
                    tvShowResult.setText("Wrong");
                    layoutShowResult.setBackgroundColor(Color.parseColor("#ff0000"));
                    btnDone.setBackgroundColor(Color.parseColor("#fe6500"));
                }
                layoutShowResult.setVisibility(View.VISIBLE);
                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long l) {
                    }

                    @Override
                    public void onFinish() {
                        layoutShowResult.setVisibility(View.INVISIBLE);
                    }
                }.start();
                break;
        }
    }
}
