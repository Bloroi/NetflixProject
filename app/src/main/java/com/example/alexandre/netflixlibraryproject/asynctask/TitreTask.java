package com.example.alexandre.netflixlibraryproject.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Alexandre on 03-10-17.
 */

public class TitreTask extends AsyncTask<String,Void,String> {

    private icallback callback;
    public void setCallback(icallback callback){
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
        String titre = strings[0];
       // String pays = "Belgique";
        try{
            URL url = new URL("http://netflixroulette.net/api/api.php?title="+titre);
            //URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ titre+ ","+ pays+ "&appid=24429ded848ec72a743564dbe43fe86d");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line = "";

            while((line = reader.readLine()) != null){
                builder.append(line);
            }

            reader.close();
            connection.disconnect();

            return builder.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("TestPost","Avant");
        if(s != null){
            try {
                Log.i("TestPost","Après");
                callback.getResult(s);
                Log.i("TestPost","Après2");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface icallback {
        void getResult(String result) throws JSONException;
    }
}
