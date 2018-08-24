package com.com.greenacademy.englishlearning.Interface;

import com.com.greenacademy.englishlearning.Model.QuestionDone;

public interface HiddenRecord {
    void hideBtnRecord();
    void setUnableBtnRecord();
    void setAbleBtnRecord();
    void chooseQuestion(QuestionDone questionDone);
    void stopAll();
}
