package com.com.greenacademy.englishlearning.Model;

public class QuestionDone {
    int milisecord;
    int numberOfQuestion;
    String pathFile;

    public QuestionDone(int milisecord, int numberOfQuestion, String pathFile) {
        this.milisecord = milisecord;
        this.numberOfQuestion = numberOfQuestion;
        this.pathFile = pathFile;
    }

    public int getMilisecord() {
        return milisecord;
    }

    public void setMilisecord(int milisencord) {
        this.milisecord = milisencord;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
}
