package com.com.greenacademy.englishlearning.Model;

import java.util.List;

public class ListenSkill {
    String videoUrl;
    List<String> listSub;
    List<Integer> listTime;

    public ListenSkill(String videoUrl, List<String> listSub, List<Integer> listTime) {
        this.videoUrl = videoUrl;
        this.listSub = listSub;
        this.listTime = listTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<String> getListSub() {
        return listSub;
    }

    public void setListSub(List<String> listSub) {
        this.listSub = listSub;
    }

    public List<Integer> getListTime() {
        return listTime;
    }

    public void setListTime(List<Integer> listTime) {
        this.listTime = listTime;
    }
}
