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
 * Created by Alexandre on 04-10-17.
 */

public class TradTask extends AsyncTask<String,Void,String>{

    private ICallbackTrad callBTrad;
    public void setCallBTrad(ICallbackTrad callBTrad){this.callBTrad=callBTrad;}

    @Override
    protected String doInBackground(String... strings) {
        String trad = strings[0];
       // String titre = strings[1];





            try {
                URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-fr&key=trnsl.1.1.20171011T153820Z.8eeb9ba9a8af81e0.72501a77e07f4555e23d3b456084406723429938&text="+trad);

                //Log.i("url","https://netflixroulette.net/api/api.php?" +type+"="+titre);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    Log.i("TestWhile", line);
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
        if(s != null){
            try {
                callBTrad.getResultTrad(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface ICallbackTrad {
        void getResultTrad(String result) throws JSONException;
    }
}
