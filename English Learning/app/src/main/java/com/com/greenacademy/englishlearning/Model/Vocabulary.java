package com.com.greenacademy.englishlearning.Model;

import java.util.List;

public class Vocabulary {
    String urlVideo;
    List<String> listNewWord;
    List<String> listMeaning;
    List<Integer> listTime;

    public Vocabulary() {
    }

    public Vocabulary(String urlVideo, List<String> listNewWord, List<String> listMeaning, List<Integer> listTime) {
        this.urlVideo = urlVideo;
        this.listNewWord = listNewWord;
        this.listMeaning = listMeaning;
        this.listTime = listTime;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public List<String> getListNewWord() {
        return listNewWord;
    }

    public void setListNewWord(List<String> listNewWord) {
        this.listNewWord = listNewWord;
    }

    public List<String> getListMeaning() {
        return listMeaning;
    }

    public void setListMeaning(List<String> listMeaning) {
        this.listMeaning = listMeaning;
    }

    public List<Integer> getListTime() {
        return listTime;
    }

    public void setListTime(List<Integer> listTime) {
        this.listTime = listTime;
    }
}
