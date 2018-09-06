package com.com.greenacademy.englishlearning.Model;

public class QuestionDone {

    int numberOfQuestion;
    String pathFile;

    public QuestionDone(int numberOfQuestion, String pathFile) {
        this.numberOfQuestion = numberOfQuestion;
        this.pathFile = pathFile;
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
