package com.com.greenacademy.englishlearning.Fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.com.greenacademy.englishlearning.Adapter.VocabularyAdapter;
import com.com.greenacademy.englishlearning.AsyncTask.GetVocabularyAsyncTask;
import com.com.greenacademy.englishlearning.Model.Vocabulary;
import com.greenacademy.englishlearning.R;

import java.util.List;

public class ListenSkillFragment extends Fragment {

    VideoView videoView;
    TextView tvSaying, tvDecs;
    SeekBar seekBar;
    FrameLayout frameLayout;
    ImageView imgPlay, imgLoading;
    RecyclerView recyclerView;
    VocabularyAdapter vocabularyAdapter;
    CardView cardView;

    List<String> listWord;
    List<String> listMeaning;
    List<Integer> listTime;

    private MediaController mediacontroller;
    private Uri uri;

    int idLesson;
    int resourceBg;

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
        recyclerView = view.findViewById(R.id.recycleView);
        cardView = view.findViewById(R.id.cardView);

        imgLoading.setImageResource(resourceBg);


        mediacontroller = new MediaController(getContext());
        mediacontroller.setAnchorView(videoView);

        GetVocabularyAsyncTask getVocabularyAsyncTask = new GetVocabularyAsyncTask();
//        getVocabularyAsyncTask.setVocabularySkillFragment(this);
        getVocabularyAsyncTask.execute(idLesson);


        return view;
    }


    public void playVideo(Vocabulary vocabulary) {

        listWord = vocabulary.getListNewWord();
        listMeaning = vocabulary.getListMeaning();
        listTime = vocabulary.getListTime();

        String uriPath = vocabulary.getUrlVideo(); //update package name
        uri = Uri.parse(uriPath);
        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(uri);
        videoView.requestFocus();



        vocabularyAdapter = new VocabularyAdapter(listWord.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(vocabularyAdapter);


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
                                tvDecs.setText(listMeaning.get(index));
                                vocabularyAdapter.setIndexOfWord(index);
                                vocabularyAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        });
        thread.start();
    }
}
