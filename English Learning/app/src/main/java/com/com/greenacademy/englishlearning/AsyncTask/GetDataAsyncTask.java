package com.com.greenacademy.englishlearning.AsyncTask;

import android.os.AsyncTask;

import com.com.greenacademy.englishlearning.Interface.GetterData;
import com.com.greenacademy.englishlearning.Model.Lesson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetDataAsyncTask extends AsyncTask<Integer, Void, List<Lesson>> {

    GetterData getterData;

    public void setGetterData(GetterData getterData) {
        this.getterData = getterData;
    }

    @Override
    protected List<Lesson> doInBackground(Integer... integers) {
        try {
            int levelId = integers[0];
            String urlServer = "http://tamod.vn:8081/api/Lession/GetAllLession?levelId=" + levelId;
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
                        JSONArray lessonTranfers = responseServer.getJSONArray("LessionTranfers");
                        if (lessonTranfers.length() > 0) {
                            List<Lesson> list = new ArrayList<>();
                            for (int i = 0; i < 61; i++) {
                                JSONObject lessonJson = lessonTranfers.getJSONObject(i);
                                Lesson lesson = new Lesson();
                                lesson.setTitle(lessonJson.getString("Ten"));
                                lesson.setDesc(lessonJson.getString("MoTa"));

                                list.add(lesson);
                            }
                            return list;
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
    protected void onPostExecute(List<Lesson> lessons) {
        super.onPostExecute(lessons);
        getterData.getData(lessons);
    }
}
