package com.com.greenacademy.englishlearning.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64;

import com.com.greenacademy.englishlearning.Fragment.ListenSkillFragment;
import com.com.greenacademy.englishlearning.Model.Recording;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SendRecordingAsyncTask extends AsyncTask<Recording, Void, Boolean> {

    private ListenSkillFragment listenSkillFragment;
    private int idLesson;

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public void setListenSkillFragment(ListenSkillFragment listenSkillFragment) {
        this.listenSkillFragment = listenSkillFragment;
    }

    @Override
    protected Boolean doInBackground(Recording... recordings) {

        String dataSoundBase64 = getData(recordings[0].getPathAudio());
        int index = recordings[0].getIndexSentence();

        if (dataSoundBase64 != null) {
            try {
                String urlServer = "http://tamod.vn:8081/api/Record/RecordAudio";
                URL url = new URL(urlServer);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                //data gui j len server
                connection.addRequestProperty("Content-Type", "application/json");
                // data nhan tu server la dang j
                connection.addRequestProperty("Accept", "application/json");
                // goi lenh ket noi den server
                connection.connect();


                JSONObject jsonObject = new JSONObject();
                jsonObject.put("IdLession", 0);
                jsonObject.put("LoaiBaiHoc", "Conversation");
                jsonObject.put("IndexSentence", 0);
                jsonObject.put("DataAudio64", dataSoundBase64);
                //gui data len server
                OutputStream outputStream = connection.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeBytes(jsonObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
                outputStream.flush();
                outputStream.close();
                //nhan kq

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

                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean) {
            System.out.println("Send successfully");
        } else
            System.out.println("Send fail");
    }

    private byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int read = 0;
        byte[] buffer = new byte[1024];
        while (read != -1) {
            read = in.read(buffer);
            if (read != -1)
                out.write(buffer, 0, read);
        }
        out.close();
        return out.toByteArray();
    }

    private String getData(String file) {
        String outputFile = file;
        String dataBase64 = null;

        try {
            InputStream inputStream = listenSkillFragment.getContext().getContentResolver().openInputStream(Uri.fromFile(new File(outputFile)));
            byte[] soundBytes;
            soundBytes = toByteArray(inputStream);
            dataBase64 = Base64.encodeToString(soundBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataBase64;
    }

}
