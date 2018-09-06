package com.com.greenacademy.englishlearning.AsyncTask;

import android.os.AsyncTask;
import android.os.Environment;
import com.com.greenacademy.englishlearning.Fragment.ListenSkillFragment;
import com.com.greenacademy.englishlearning.Model.QuestionDone;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CheckFileAsyncTask extends AsyncTask<Void, Void, List<QuestionDone>> {

    ListenSkillFragment listenSkillFragment;
    int idLesson;
    int sizeOfListQuestion;

    public CheckFileAsyncTask(ListenSkillFragment listenSkillFragment, int idLesson, int sizeOfListQuestion) {
        this.listenSkillFragment = listenSkillFragment;
        this.idLesson = idLesson;
        this.sizeOfListQuestion = sizeOfListQuestion;
    }

    @Override
    protected List<QuestionDone> doInBackground(Void... lists) {
        List<QuestionDone> listFileExist = new ArrayList<>();

        for (int i = 0; i < sizeOfListQuestion; i++) {
            String fileLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Lesson_" + idLesson + "_Question_" + i + "_audio_record.3gp";
            File file = new File(fileLocation);
            if (file.exists()) { // check file exists
                listFileExist.add(new QuestionDone(i, fileLocation));
            }
        }
        System.out.println(listFileExist.size());
        return listFileExist;
    }

    @Override
    protected void onPostExecute(List<QuestionDone> list) {
        super.onPostExecute(list);

        listenSkillFragment.getRecordingAdapter().addQuestionExist(list);
        listenSkillFragment.prepareAdapter();
    }
}
