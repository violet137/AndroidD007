package com.com.greenacademy.englishlearning.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.com.greenacademy.englishlearning.Interface.GetterData;
import com.com.greenacademy.englishlearning.Model.Lesson;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetBackgroundAsyncTask extends AsyncTask<List<Lesson>, Void, List<Bitmap>> {

    GetterData getterData;

    public void setGetterData(GetterData getterData) {
        this.getterData = getterData;
    }

    @Override
    protected List<Bitmap> doInBackground(List<Lesson>... lists) {
        List<Bitmap> listBackground = new ArrayList<>();

        if (!lists[0].isEmpty()) {
            for (int i = 0; i < 11; i++) {
                try {
                    URL url = new URL(lists[0].get(i).getImgLink());
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    listBackground.add(bitmap);

                    System.out.println(i + " =============");
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        }
        return listBackground;
    }

    @Override
    protected void onPostExecute(List<Bitmap> bitmaps) {
        super.onPostExecute(bitmaps);
        getterData.getBackground(bitmaps);
    }
}
