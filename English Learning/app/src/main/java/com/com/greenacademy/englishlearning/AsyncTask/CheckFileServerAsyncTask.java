package com.com.greenacademy.englishlearning.AsyncTask;

import android.os.AsyncTask;
import android.os.Environment;

import com.com.greenacademy.englishlearning.Fragment.ListenSkillFragment;
import com.com.greenacademy.englishlearning.Model.QuestionDone;
import com.com.greenacademy.englishlearning.Model.Recording;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CheckFileServerAsyncTask extends AsyncTask<List<Recording>, Void, List<QuestionDone>> {

    ListenSkillFragment listenSkillFragment;
    int idLesson;

    public CheckFileServerAsyncTask(ListenSkillFragment listenSkillFragment, int idLesson) {
        this.listenSkillFragment = listenSkillFragment;
        this.idLesson = idLesson;
    }

    @Override
    protected List<QuestionDone> doInBackground(List<Recording>... lists) {
        List<Recording> list = lists[0];
        List<QuestionDone> listQSDone = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int count;
            try {
                URL url = new URL(list.get(i).getPathAudio());
                URLConnection connection = url.openConnection();
                connection.connect();

                String fileLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Lesson_" + idLesson + "_Question_" + i + "_audio_record.3gp";
                File file = new File(fileLocation);
                InputStream inputStream = new BufferedInputStream(url.openStream());
                OutputStream outputStream = new FileOutputStream(file);

                byte data[] = new byte[1024];

                while ((count = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, count);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();

                listQSDone.add(new QuestionDone(list.get(i).getIndexSentence(), fileLocation));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listQSDone;
    }

    @Override
    protected void onPostExecute(List<QuestionDone> list) {
        super.onPostExecute(list);


    }
}
