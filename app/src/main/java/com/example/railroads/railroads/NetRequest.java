package com.example.railroads.railroads;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Vinit Soni on 2016-09-17.
 */
public class NetRequest extends AsyncTask<String, String, String> {
    private int questionNumber;
    private PostAsync postTask;

    public NetRequest(int questionNumber, PostAsync postTask) {
        this.questionNumber = questionNumber;
        this.postTask = postTask;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = null;
        try {
            URL url = new URL("");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder stringBuilder = new StringBuilder();


            while ((data = br.readLine()) != null) {
                stringBuilder.append(data).append('\n');
            }

            is.close();

        } catch (Exception e){
            Log.d("VINIT", e.getMessage());
        }


        return data;
    }

    @Override
    protected void onPostExecute(String aString) {
        super.onPostExecute(aString);
        postTask.PostAsyncTask(aString);

    }
}
