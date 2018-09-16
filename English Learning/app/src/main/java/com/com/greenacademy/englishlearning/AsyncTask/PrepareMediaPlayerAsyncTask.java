package com.com.greenacademy.englishlearning.AsyncTask;

import android.media.AudioManager;
import android.os.AsyncTask;
import android.view.View;

import com.com.greenacademy.englishlearning.Fragment.ListenSkillFragment;

import java.io.IOException;

public class PrepareMediaPlayerAsyncTask extends AsyncTask<String, Void, Boolean> {
    private ListenSkillFragment listenSkillFragment;

    public void setListenSkillFragment(ListenSkillFragment listenSkillFragment) {
        this.listenSkillFragment = listenSkillFragment;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            listenSkillFragment.getMediaPlayer().setAudioStreamType(AudioManager.STREAM_MUSIC);
            listenSkillFragment.getMediaPlayer().setDataSource(strings[0]);
            listenSkillFragment.getMediaPlayer().prepare();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean) {
            listenSkillFragment.setFunctionForButton();
            listenSkillFragment.getFrameLayout().setVisibility(View.GONE);
            System.out.println("Done ======== mp3");
        }else
            System.out.println("Fail ===========");
    }
}
