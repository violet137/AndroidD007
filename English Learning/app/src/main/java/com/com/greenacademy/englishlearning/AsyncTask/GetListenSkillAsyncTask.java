package com.com.greenacademy.englishlearning.AsyncTask;

import android.os.AsyncTask;

import com.com.greenacademy.englishlearning.Fragment.VocabularySkillFragment;
import com.com.greenacademy.englishlearning.Model.Vocabulary;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetListenSkillAsyncTask extends AsyncTask<Integer, Void, Vocabulary> {

    VocabularySkillFragment vocabularySkillFragment;

    public void setVocabularySkillFragment(VocabularySkillFragment vocabularySkillFragment) {
        this.vocabularySkillFragment = vocabularySkillFragment;
    }

    @Override
    protected Vocabulary doInBackground(Integer... integers) {
        try {
            int idLesson = integers[0];
            String urlServer = "http://tamod.vn:8081/api/LessionDetail/ListeningById?idLession=" + idLesson;
            URL url = new URL(urlServer);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
            //data gui j len server
            connection.addRequestProperty("Content-Type", "application/json");
            // data nhan tu server la dang j
            connection.addRequestProperty("Accept", "application/json");
            // goi lenh ket noi den server
            connection.connect();


//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("levelId", levelId);
//            //gui data len server
//            OutputStream outputStream = connection.getOutputStream();
//            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//            dataOutputStream.writeBytes(jsonObject.toString());
//            dataOutputStream.flush();
//            dataOutputStream.close();
//            outputStream.flush();
//            outputStream.close();
//            //nhan kq

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String responseJson = "";
                String tmp = "";
                while (tmp != null) {
                    responseJson = responseJson + tmp;
                    tmp = bufferedReader.readLine();
                }
                if (!responseJson.isEmpty()) {
                    JSONObject responseServer = new JSONObject(responseJson);
                    int status = responseServer.getInt("Status");
                    if (status == 1) {
                        JSONObject vocaBig = responseServer.getJSONObject("VocabularyTranfer");
                        JSONArray vocabularyTranfers = vocaBig.getJSONArray("VocabularyTranfers");
                        if (vocabularyTranfers.length() > 0) {
                            List<String> listText = new ArrayList<>();
                            List<String> listMeaning = new ArrayList<>();
                            List<Integer> listTime = new ArrayList<>();

                            for (int i = 0; i < vocabularyTranfers.length(); i++) {
                                JSONObject sentenData = vocabularyTranfers.getJSONObject(i);
                                String text = sentenData.getString("Text");
                                String textTrans = sentenData.getString("TextTrans");
                                String meaningTrans = sentenData.getString("MeaningTrans");
                                int time = sentenData.getInt("Time");



                                listText.add(text + " - " + textTrans);
                                listMeaning.add(meaningTrans);
                                listTime.add(time);
                            }
                            String urlVideo = responseServer.getString("VideoUrl");

                            return new Vocabulary(urlVideo, listText, listMeaning, listTime);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Vocabulary vocabulary) {
        super.onPostExecute(vocabulary);
        if (vocabulary != null) {
          vocabularySkillFragment.playVideo(vocabulary);
        }
    }
}
