package com.example.alexandre.netflixlibraryproject.asynctask;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.model.Utils;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Locale;

import dmax.dialog.SpotsDialog;


public class FindTask extends AsyncTask<String,Void,String>{

    private ICallback callB;
    public void setCallB(ICallback callB){this.callB=callB;}
    private AlertDialog dialog;
    private Context context;

    public FindTask(Context context){
        this.context = context;
    }


    @Override
    protected void onPreExecute()
    {
        dialog = new SpotsDialog(context, R.style.Custom);
        dialog.show();
    }


    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
        String rech = strings[1];

        //Permet à certains téléphones de chercher des films séries ou acteurs avec espaces dans la recherche
        rech=rech.replaceAll(" ","%20");

        if (type == "Film") {

            type = "movie";

        } else if (type == "Série") {
            type = "tv";


        } else if (type == "Acteur") {
            type = "person";

        } else {
            type = "error";
        }


        if (type != "error") {
            try {
                //https://api.themoviedb.org/3/search/tv?query=game of&api_key=d3f617c2a1b78f7220853c4627424fe5&language=fr
                URL url = new URL("https://api.themoviedb.org/3/search/" + type + "?query=" + rech + "&api_key=" + Utils.Intent.TAG_APIKEY + "&language=" + Locale.getDefault().getLanguage());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                reader.close();
                connection.disconnect();
                return builder.toString();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        if(s != null){
            try {
                callB.getResult(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface ICallback {
        void getResult(String result) throws JSONException;
    }
}
