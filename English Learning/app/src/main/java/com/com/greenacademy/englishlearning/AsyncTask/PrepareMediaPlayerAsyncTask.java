package com.com.greenacademy.englishlearning.AsyncTask;

import android.media.AudioManager;
import android.os.AsyncTask;
import android.view.View;

import com.com.greenacademy.englishlearning.Fragment.ConversionSkillFragment;

import java.io.IOException;

public class PrepareMediaPlayerAsyncTask extends AsyncTask<String, Void, Boolean> {
    private ConversionSkillFragment conversionSkillFragment;

    public void setConversionSkillFragment(ConversionSkillFragment conversionSkillFragment) {
        this.conversionSkillFragment = conversionSkillFragment;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            conversionSkillFragment.getMediaPlayer().setAudioStreamType(AudioManager.STREAM_MUSIC);
            conversionSkillFragment.getMediaPlayer().setDataSource(strings[0]);
            conversionSkillFragment.getMediaPlayer().prepare();
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
            conversionSkillFragment.setFunctionForButton();
            conversionSkillFragment.getFrameLayout().setVisibility(View.GONE);
            System.out.println("Done ======== mp3");
        }else
            System.out.println("Fail ===========");
    }
}
