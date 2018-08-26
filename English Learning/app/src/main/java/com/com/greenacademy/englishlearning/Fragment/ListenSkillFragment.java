package com.com.greenacademy.englishlearning.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.com.greenacademy.englishlearning.Adapter.RecordingAdapter;
import com.com.greenacademy.englishlearning.Interface.HiddenRecord;
import com.com.greenacademy.englishlearning.Model.QuestionDone;
import com.greenacademy.englishlearning.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ListenSkillFragment extends Fragment implements HiddenRecord {
    final int PLAY = 1;
    final int STOP = -1;
    final int PAUSE = 0;
    final int REQUEST_PERMISSION_CODE = 147;
    private MediaPlayer mediaPlayer;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayerOfRecord;
    TextView tvDuration, tvEnglishSaying, tvEnglishPrepare, tvVietnamese, tvNgheLai, tvNgheMau, tvRecordingText, tvSaying, tvRecord;
    SeekBar seekBar;
    ImageView imgPlay, imgRecord, imgListen, imgListenSample;
    int isPlaying = STOP;

    String[] english;
    String[] vietsub;
    String[] recordingText;

    List block;
    List recourdingTime;

    int numberOfQuestion = -1;
    int milisecordOfQuestion = 0;
    int currenPossition = 0;

    String pathSave;

    RecyclerView recyclerView;
    RecordingAdapter recordingAdapter;

    public List convertToList(int[] strings) {
        List list = new ArrayList();
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
        return list;
    }

    public boolean checkPermission() {
        int write_ex = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO);
        return write_ex == PackageManager.PERMISSION_GRANTED && record_audio == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);

        if (!checkPermission()) {
            requestPermission();
        }

        imgPlay = view.findViewById(R.id.imgPlay);
        imgListen = view.findViewById(R.id.imgLissen);
        imgListenSample = view.findViewById(R.id.imgPlaySample);
        imgRecord = view.findViewById(R.id.imgRecording);
        seekBar = view.findViewById(R.id.seekBar);
        tvDuration = view.findViewById(R.id.tvDuration);
        tvEnglishSaying = view.findViewById(R.id.tvEnglishSaying);
        tvEnglishPrepare = view.findViewById(R.id.tvEnglishPrepare);
        tvVietnamese = view.findViewById(R.id.tvVietnamese);
        tvNgheLai = view.findViewById(R.id.tvNgheLai);
        tvNgheMau = view.findViewById(R.id.tvNgheMau);
        tvSaying = view.findViewById(R.id.tvSaying);
        tvRecordingText = view.findViewById(R.id.tvRecordingText);
        tvRecord = view.findViewById(R.id.tvRecord);
        english = getActivity().getResources().getStringArray(R.array.conversation);
        vietsub = getActivity().getResources().getStringArray(R.array.vietsub);
        block = convertToList(getActivity().getResources().getIntArray(R.array.block));

        recordingText = getActivity().getResources().getStringArray(R.array.recording_text);
        recourdingTime = convertToList(getActivity().getResources().getIntArray(R.array.recording_time));

        seekBar.setEnabled(false);
        imgRecord.setVisibility(View.INVISIBLE);


        this.mediaPlayer = MediaPlayer.create(getContext(), R.raw.dailylife001);

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying == PAUSE && seekBar.isEnabled() == false) {
                    recordingAdapter.setPositionChoosed(-1);
                    recordingAdapter.notifyDataSetChanged();
                    numberOfQuestion = -1;
                    milisecordOfQuestion = 0;
                    isPlaying = PLAY;
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.pause);
                    seekBar.setEnabled(true);
                } else if (isPlaying == STOP) {
                    isPlaying = PLAY;
                    playRecord(currenPossition);
                    updateUI();
                    imgPlay.setImageResource(R.drawable.pause);
                    seekBar.setEnabled(true);
                } else if (isPlaying == PLAY) {
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.play_button);
                    isPlaying = PAUSE;
                } else if (isPlaying == PAUSE) {
                    isPlaying = PLAY;
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.pause);
                    seekBar.setEnabled(true);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    if (progress > Integer.valueOf(block.get(block.size() - 2).toString()) && progress <= Integer.valueOf(block.get(block.size() - 1).toString())) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvEnglishSaying.setText(english[block.size() - 2]);
                                tvEnglishPrepare.setText("");
                                tvVietnamese.setText(vietsub[block.size() - 2]);
                            }
                        });
                    } else if (progress == 0) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvEnglishSaying.setText(english[0]);
                                tvEnglishPrepare.setText(english[0 + 1]);
                                tvVietnamese.setText(vietsub[0]);
                            }
                        });
                    } else {
                        for (int i = 0; i < block.size(); i++) {
                            final int index = i;
                            if (progress > Integer.valueOf(block.get(i).toString()) && progress < Integer.valueOf(block.get(i + 1).toString())) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvEnglishSaying.setText(english[index]);
                                        tvEnglishPrepare.setText(english[index + 1]);
                                        tvVietnamese.setText(vietsub[index]);
                                    }
                                });
                            }
                        }
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//              isChanged = true;
            }
        });

        recyclerView = view.findViewById(R.id.recycleView);
        recordingAdapter = new RecordingAdapter();
        recordingAdapter.setListOfText(recordingText);
        recordingAdapter.setHiddenRecord(this);
//        recordingAdapter.setPositionChoosed(2);
//        recordingAdapter.addQuestionDone(0);
//        recordingAdapter.addQuestionDone(4);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(recordingAdapter);


        imgRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        imgListen.setVisibility(View.GONE);
                        imgListenSample.setVisibility(View.GONE);
                        tvNgheLai.setVisibility(View.GONE);
                        tvNgheMau.setVisibility(View.GONE);
                        pathSave = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + UUID.randomUUID().toString() + "_audio_record.3gp";
                        setUpMediaRecord();
                        try {
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgRecord.setImageResource(R.drawable.radio);
                        Toast.makeText(getContext(), "Recording...", Toast.LENGTH_LONG).show();
                        break;

                    case MotionEvent.ACTION_UP:
                        mediaRecorder.stop();
                        imgListen.setVisibility(View.VISIBLE);
                        imgListenSample.setVisibility(View.VISIBLE);
                        tvNgheLai.setVisibility(View.VISIBLE);
                        tvNgheMau.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Stop Recording...", Toast.LENGTH_LONG).show();
                        // add question done
                        if (numberOfQuestion > -1) {
                            recordingAdapter.addPathFile(numberOfQuestion, pathSave);
                            recordingAdapter.notifyDataSetChanged();
                        }

                        break;
                }
                return false;
            }
        });

        imgListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayerOfRecord = new MediaPlayer();
                try {
                    mediaPlayerOfRecord.setDataSource(pathSave);
                    mediaPlayerOfRecord.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayerOfRecord.start();
                Toast.makeText(getContext(), "Play Record...", Toast.LENGTH_LONG).show();
            }
        });


        return view;


    }

    public void setUpMediaRecord() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }


    public void playRecord(int currentPosition) {
        int duration = mediaPlayer.getDuration();

        seekBar.setMax(duration);
        mediaPlayer.seekTo(currenPossition);

        mediaPlayer.start();
    }

    // Chuyển số lượng milli giây thành một String có ý nghĩa.
    private String millisecondsToString(int milliseconds) {
        int minutes = ((milliseconds / 1000) / 60);
        int seconds = ((milliseconds / 1000) % 60);
        return minutes + ":" + seconds;
    }

    public void updateUI() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isPlaying != STOP) {
                    int duration = mediaPlayer.getDuration();
                    final int currentPosition = mediaPlayer.getCurrentPosition();
//                    System.out.println("------------" + currentPosition + "----------------");
                    final String maxTimeString = millisecondsToString(duration);
                    final String currentTimeString = millisecondsToString(currentPosition);
                    final int index = block.indexOf(currentPosition);
                    final int indexOfRecording = recourdingTime.indexOf(currentPosition);
                    seekBar.setProgress(currentPosition);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvDuration.setText(currentTimeString + "/" + maxTimeString);
                            if (index > -1) {
                                if (index == english.length - 1) {
                                    tvEnglishSaying.setText(english[index]);
                                    tvEnglishPrepare.setText("");
                                    tvVietnamese.setText(vietsub[index]);
                                } else if (index < english.length - 1) {
                                    tvEnglishSaying.setText(english[index]);
                                    tvEnglishPrepare.setText(english[index + 1]);
                                    tvVietnamese.setText(vietsub[index]);
                                }
                            }
                        }
                    });


                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (indexOfRecording > -1) {
                                recordingAdapter.setPositionChoosed(indexOfRecording);
                                mediaPlayer.pause();
                                tvRecordingText.setText(recordingText[indexOfRecording]);
                                isPlaying = PAUSE;
                                imgPlay.setImageResource(R.drawable.play_button);
                                seekBar.setEnabled(false);
                                numberOfQuestion = indexOfRecording;
                                milisecordOfQuestion = currentPosition;
                                recordingAdapter.addQuestionDone(new QuestionDone(milisecordOfQuestion, numberOfQuestion, null));
                                recordingAdapter.notifyDataSetChanged();
                            }
                        }
                    });


                    if (currentPosition >= duration) {
                        isPlaying = STOP;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imgPlay.setImageResource(R.drawable.play_button);
                            }
                        });
                    }

//                    try {
//                        Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void hideBtnRecord() {
        if (numberOfQuestion == -1) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvSaying.setVisibility(View.INVISIBLE);
                    tvRecordingText.setVisibility(View.INVISIBLE);
                    tvNgheLai.setVisibility(View.GONE);
                    tvNgheMau.setVisibility(View.GONE);
                    tvRecord.setVisibility(View.INVISIBLE);
                    imgListen.setVisibility(View.GONE);
                    imgListenSample.setVisibility(View.GONE);
                    imgRecord.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    @Override
    public void setUnableBtnRecord() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvSaying.setVisibility(View.VISIBLE);
                tvRecordingText.setVisibility(View.VISIBLE);
                tvNgheLai.setVisibility(View.VISIBLE);
                tvNgheMau.setVisibility(View.VISIBLE);
                tvRecord.setVisibility(View.INVISIBLE);
                imgListen.setVisibility(View.VISIBLE);
                imgListenSample.setVisibility(View.VISIBLE);
                imgRecord.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void setAbleBtnRecord() {
        if (numberOfQuestion > -1) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvSaying.setVisibility(View.VISIBLE);
                    tvRecordingText.setVisibility(View.VISIBLE);
                    tvRecord.setVisibility(View.VISIBLE);
                    imgRecord.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    public void chooseQuestion(final QuestionDone questionDone) {
        pathSave = questionDone.getPathFile();
    }

}
