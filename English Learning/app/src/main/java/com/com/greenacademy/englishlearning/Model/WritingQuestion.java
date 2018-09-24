package com.com.greenacademy.englishlearning.Model;

public class WritingQuestion {
    String audioUrl;
    String text;

    public WritingQuestion(String audioUrl, String text) {
        this.audioUrl = audioUrl;
        this.text = text;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
