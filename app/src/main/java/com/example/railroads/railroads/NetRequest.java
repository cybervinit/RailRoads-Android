package com.example.railroads.railroads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Vinit Soni on 2016-09-17.
 */
public class NetRequest extends AsyncTask<String, String, String> {
    private int questionNumber;
    private PostAsync postTask;
    private ProgressDialog pd;
    String requestMethod;
    String stringData;

    public NetRequest(String requestMethod, int questionNumber,  PostAsync postTask) {
        this.questionNumber = questionNumber;
        this.postTask = postTask;
        this.requestMethod = requestMethod;
    }

    public NetRequest(String requestMethod, AdvQuestion questionData) {

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        String dataURLparameters = "question="+"one";
        if (requestMethod.equals("POST")) {
            try {
                URL url = new URL("http://railroads.azurewebsites.net/requests/a");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                //connection.setRequestProperty("Content-Length", "" + Integer.toString(dataURLparameters.getBytes().length));
                connection.setConnectTimeout(15000);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                //Send request here:
                DataOutputStream dos = new DataOutputStream(
                        connection.getOutputStream());
                String name = "Vinit is my name.";
                byte[] nameByte = name.getBytes(StandardCharsets.UTF_8);
                dos.write(nameByte);
                dos.writeBytes(dataURLparameters);
                dos.flush();
                dos.close();
                //Get Response
                Log.d("RESPONSE -->", connection.getResponseCode()+": "+connection.getResponseMessage());
                connection.getContent();
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = "";
                StringBuffer stringBuffer = new StringBuffer();

                while ((data = br.readLine()) != null) {
                    stringBuffer.append(data);
                    stringBuffer.append('\r');
                }
                String finalResponse = stringBuffer.toString();
                Log.d("BEFORE BUFFER", "POST");
                Log.d("response-->-->-->", stringBuffer.length()+"");
                Log.d("BEFORE BUFFER", "POST");
                stringData = stringBuffer.toString().substring(0, stringBuffer.length()-1);
                br.close();
                is.close();
                dos.close();

                connection.disconnect();
            } catch (Exception e){
                Log.d("VINIT", e.getMessage());
            }
        } else if (requestMethod.equals("GET")) {
            try {
                URL url = new URL("http://railroads.azurewebsites.net/requests/a");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "*");
                connection.setConnectTimeout(15000);
                connection.setUseCaches(false);
                connection.setDoOutput(true);
                //Send request here:
                DataOutputStream dos = new DataOutputStream(
                        connection.getOutputStream());
                dos.writeBytes(dataURLparameters);
                dos.flush();
                //dos.close();
                Log.d("response-->-->-->", "ola");
                //Get Response
                Log.d("RESPONSE -->", connection.getResponseCode()+": "+connection.getResponseMessage());
                connection.getContent();
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = "";
                StringBuffer stringBuffer = new StringBuffer();

                while ((data = br.readLine()) != null) {
                    stringBuffer.append(data);
                    stringBuffer.append('\r');
                }
                String finalResponse = stringBuffer.toString();
                for (int i = 0; i < stringBuffer.length(); i++) {
                    Log.d("Buffer -->", stringBuffer.toString().substring(i, i+1));
                }
                Log.d("BEFORE BUFFER", "GET");
                Log.d("response-->-->-->", stringBuffer.toString().substring(0, stringBuffer.length()-1));
                Log.d("BEFORE BUFFER", "GET");
                br.close();
                is.close();
                dos.close();

                connection.disconnect();
            } catch (Exception e){
                Log.d("VINIT", e.getMessage());
            }
        }




        return stringData;
    }

    @Override
    protected void onPostExecute(String aString) {
        super.onPostExecute(aString);
        postTask.PostAsyncTask(aString);

    }

}
