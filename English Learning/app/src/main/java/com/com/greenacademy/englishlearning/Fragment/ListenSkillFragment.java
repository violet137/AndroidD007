package com.com.greenacademy.englishlearning.Fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.com.greenacademy.englishlearning.AsyncTask.GetListenSkillAsyncTask;
import com.com.greenacademy.englishlearning.Model.ListenSkill;
import com.greenacademy.englishlearning.R;

import java.util.List;

public class ListenSkillFragment extends Fragment {

    VideoView videoView;
    TextView tvSaying, tvDecs;
    SeekBar seekBar;
    FrameLayout frameLayout;
    ImageView imgPlay, imgLoading;
    CardView cardView;

    List<String> listWord;
    List<Integer> listTime;

    private MediaController mediacontroller;
    private Uri uri;

    int idLesson;
    int resourceBg;
    String desc;

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public void setResourceBg(int resourceBg) {
        this.resourceBg = resourceBg;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen_skill, container, false);

        videoView = view.findViewById(R.id.videoView);
        imgPlay = view.findViewById(R.id.imgPlay);
        seekBar = view.findViewById(R.id.seekBar);
        tvSaying = view.findViewById(R.id.tvSaying);
        tvDecs = view.findViewById(R.id.tvSub);
        frameLayout = view.findViewById(R.id.sceenLoading);
        imgLoading = view.findViewById(R.id.bgLoading);
        cardView = view.findViewById(R.id.cardView);

        imgLoading.setImageResource(resourceBg);


        mediacontroller = new MediaController(getContext());
        mediacontroller.setAnchorView(videoView);

        GetListenSkillAsyncTask getListenSkillAsyncTask = new GetListenSkillAsyncTask();
        getListenSkillAsyncTask.setListenSkillFragment(this);
        getListenSkillAsyncTask.execute(idLesson);

        tvDecs.setText(desc);

        return view;
    }


    public void playVideo(ListenSkill listenSkill) {

        listWord = listenSkill.getListSub();
        listTime = listenSkill.getListTime();

        String uriPath = listenSkill.getVideoUrl(); //update package name
        uri = Uri.parse(uriPath);
        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(uri);
        videoView.requestFocus();


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                frameLayout.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
                videoView.start();
                updateUI();
            }
        });


    }

    public void updateUI() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (videoView.isPlaying()) {
                    final int currentPosition = videoView.getCurrentPosition();
                    final int index = listTime.indexOf(currentPosition);

                    if (index >= 0) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvSaying.setText(listWord.get(index));
                            }
                        });
                    }
                }
            }
        });
        thread.start();
    }
}
