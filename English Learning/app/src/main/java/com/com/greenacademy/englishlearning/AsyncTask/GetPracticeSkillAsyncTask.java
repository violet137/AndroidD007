package com.com.greenacademy.englishlearning.AsyncTask;

import android.os.AsyncTask;

import com.com.greenacademy.englishlearning.Fragment.ChattingFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetPracticeSkillAsyncTask extends AsyncTask<Void, Void, List<String>> {

    ChattingFragment chattingFragment;

    public void setChattingFragment(ChattingFragment chattingFragment) {
        this.chattingFragment = chattingFragment;
    }


    @Override
    protected List<String> doInBackground(Void... integers) {
        try {
//            int idLesson = integers[0];
            String urlServer = "http://tamod.vn:8081/api/LessionDetail/ConversationById?idLession=" + 1;
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
                        JSONArray sentenceDatas = responseServer.getJSONArray("SentenceDatas");
                        if (sentenceDatas.length() > 0) {
                            List<String> listText = new ArrayList<>();


                            for (int i = 0; i < sentenceDatas.length(); i++) {
                                JSONObject sentenData = sentenceDatas.getJSONObject(i);
                                String text = sentenData.getString("Text");
                                listText.add(text);
                            }
                            return listText;
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
    protected void onPostExecute(List<String> list) {
        super.onPostExecute(list);
        if (list != null) {
            chattingFragment.setSource(list);
        }
    }
}
