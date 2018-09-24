package com.com.greenacademy.englishlearning.AsyncTask;

import android.os.AsyncTask;

import com.com.greenacademy.englishlearning.Fragment.WritingSkillFragment;
import com.com.greenacademy.englishlearning.Model.WritingQuestion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetWritingSkillAsyncTask extends AsyncTask<Integer, Void, List<WritingQuestion>> {

   WritingSkillFragment writingSkillFragment;

    public void setWritingSkillFragment(WritingSkillFragment writingSkillFragment) {
        this.writingSkillFragment = writingSkillFragment;
    }

    @Override
    protected List<WritingQuestion> doInBackground(Integer... integers) {
        try {
            int idLesson = integers[0];
            String urlServer = "http://tamod.vn:8081/api/LessionDetail/WritingById?idLession=" + idLesson;
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
                        JSONObject listeningTranfer = responseServer.getJSONObject("ListeningTranfer");
                        JSONArray wordDatas = listeningTranfer.getJSONArray("WordDatas");
                        if (wordDatas.length() > 0) {
                            List<WritingQuestion> listQs = new ArrayList<>();

                            for (int i = 0; i < wordDatas.length(); i++) {
                                JSONObject sentenData = wordDatas.getJSONObject(i);
                                String text = sentenData.getString("Text");
                                String audioUrl = sentenData.getString("AudioUrl");

                                listQs.add(new WritingQuestion(audioUrl, text));
                            }
                            return listQs;
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
    protected void onPostExecute(List<WritingQuestion> list) {
        super.onPostExecute(list);
        if (list != null) {
          writingSkillFragment.setUp(list);
        }
    }
}
