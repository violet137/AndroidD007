package com.com.greenacademy.englishlearning.Model;

import java.util.List;

public class AudioLesson {
    List<String> listText;
    List<String> listTextTrans;
    List<Integer> listTime;
    String audioUrl;

    public AudioLesson() {
    }

    public AudioLesson(List<String> listText, List<String> listTextTrans, List<Integer> listTime, String audioUrl) {
        this.listText = listText;
        this.listTextTrans = listTextTrans;
        this.listTime = listTime;
        this.audioUrl = audioUrl;
    }

    public List<String> getListText() {
        return listText;
    }

    public void setListText(List<String> listText) {
        this.listText = listText;
    }

    public List<String> getListTextTrans() {
        return listTextTrans;
    }

    public void setListTextTrans(List<String> listTextTrans) {
        this.listTextTrans = listTextTrans;
    }

    public List<Integer> getListTime() {
        return listTime;
    }

    public void setListTime(List<Integer> listTime) {
        this.listTime = listTime;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
