package com.com.greenacademy.englishlearning.Fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.greenacademy.englishlearning.R;

public class ListenSkillFragment extends Fragment {

    VideoView videoView;
    TextView tvSaying, tvDecs;
    SeekBar seekBar;
    FrameLayout frameLayout;
    ImageView imgPlay, imgLoading;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen_skill, container, false);

        videoView = view.findViewById(R.id.videoView);
        imgPlay = view.findViewById(R.id.imgPlay);
        seekBar = view.findViewById(R.id.seekBar);
        tvSaying = view.findViewById(R.id.tvSaying);
        tvDecs = view.findViewById(R.id.tvDecs);
        frameLayout = view.findViewById(R.id.sceenLoading);
        imgLoading = view.findViewById(R.id.bgLoading);



        MediaController mediaController;
        // Tạo bộ điều khiển

            mediaController = new MediaController(getActivity().getApplicationContext());
// Neo vị trí của MediaController với VideoView.
            mediaController.setAnchorView(videoView);
// Sét đặt bộ điều khiển cho VideoView.
            videoView.setMediaController(mediaController);

        try {
// ID của file video.
            videoView.setVideoURI(Uri.parse("android.resource://" +
                    getActivity().getPackageName() + "/" + R.raw.ta_say));
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        videoView.requestFocus();
// Sự kiện khi file video sẵn sàng để chơi.
        final MediaController finalMediaController = mediaController;
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
// Khi màn hình Video thay đổi kích thước
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int
                            height) {
// Neo lại vị trí của bộ điều khiển của nó vào VideoView.
                        finalMediaController.setAnchorView(videoView);
                    }
                });
            }
        });



        return view;
    }
}
