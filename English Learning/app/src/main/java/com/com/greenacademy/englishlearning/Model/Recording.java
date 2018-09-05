package com.com.greenacademy.englishlearning.Model;

public class Recording {

    int indexSentence;
    String pathAudio;

    public Recording(int indexSentence, String pathAudio) {
        this.indexSentence = indexSentence;
        this.pathAudio = pathAudio;
    }

    public Recording() {
    }

    public int getIndexSentence() {
        return indexSentence;
    }

    public void setIndexSentence(int indexSentence) {
        this.indexSentence = indexSentence;
    }

    public String getPathAudio() {
        return pathAudio;
    }

    public void setPathAudio(String pathAudio) {
        this.pathAudio = pathAudio;
    }
}
