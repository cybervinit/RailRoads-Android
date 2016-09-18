package com.example.railroads.railroads;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Vinit Soni on 2016-09-17.
 */
public class NetRequest extends AsyncTask<String, String, String> {
    private int questionNumber;
    private PostAsync postTask;
    private ProgressDialog pd;

    public NetRequest(int questionNumber,  PostAsync postTask) {
        this.questionNumber = questionNumber;
        this.postTask = postTask;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = null;
        String dataURLparameters = "question="+"one";
        Log.d("-->-->-->", "WORKS!");
        try {
            URL url = new URL("http://railroads.azurewebsites.net/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //Send request here:
            DataOutputStream dos = new DataOutputStream(
                    connection.getOutputStream());
            dos.writeBytes(dataURLparameters);
            dos.flush();
            dos.close();
            //Get Response
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuffer stringBuffer = new StringBuffer();


            while ((data = br.readLine()) != null) {
                stringBuffer.append(data);
                stringBuffer.append('\r');
            }
            br.close();
            is.close();
            String finalResponse = stringBuffer.toString();
            Log.d("response-->-->-->",finalResponse);
            connection.disconnect();
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
