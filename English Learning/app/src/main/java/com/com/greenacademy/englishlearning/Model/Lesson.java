package com.com.greenacademy.englishlearning.Model;

public class Lesson {
    private String title;
    private String desc;
     private String titleSub;
     private String descSub;
     private String imgLink;

    public Lesson(String title, String desc, String titleSub, String descSub, String imgLink) {
        this.title = title;
        this.desc = desc;
        this.titleSub = titleSub;
        this.descSub = descSub;
        this.imgLink = imgLink;
    }

    public Lesson() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitleSub() {
        return titleSub;
    }

    public void setTitleSub(String titleSub) {
        this.titleSub = titleSub;
    }

    public String getDescSub() {
        return descSub;
    }

    public void setDescSub(String descSub) {
        this.descSub = descSub;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
