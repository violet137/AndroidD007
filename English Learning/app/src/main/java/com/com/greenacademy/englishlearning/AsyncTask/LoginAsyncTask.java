package com.com.greenacademy.englishlearning.AsyncTask;

import android.os.AsyncTask;
import android.widget.Toast;

import com.com.greenacademy.englishlearning.Interface.Supporter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginAsyncTask extends AsyncTask<String, Void, Boolean> {

    Supporter supporter;

    public LoginAsyncTask(Supporter supporter) {
        this.supporter = supporter;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            String username = strings[0];
            String password = strings[1];
            String urlServer = "http://tamod.vn:8080/api/Auth/Login";
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
            jsonObject.put("Username", username);
            jsonObject.put("Pwd", password);
            jsonObject.put("AccountType", "Google");
            jsonObject.put("NenTang", "Android");
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
                    String token = responseServer.getString("Token");
                    int status = responseServer.getInt("Status");
                    String description = responseServer.getString("Description");
                    System.out.println("Description: " + description + "\n" + "Token: " + token);
                    if (status == 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        supporter.connectServer(aBoolean);
    }
}
