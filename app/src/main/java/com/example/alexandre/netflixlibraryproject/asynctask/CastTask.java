package com.example.alexandre.netflixlibraryproject.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by Alexandre on 18-10-17.
 */

public class CastTask extends AsyncTask<String,Void,String> {
    private ICallbackCast callBCast;
    public void setCallBCast(ICallbackCast callBCast){this.callBCast=callBCast;}
    private ProgressDialog progressDialog;

    private Context context;

    public CastTask(Context context){
        this.context = context;
    }


    //Before running code in separate thread
    @Override
    protected void onPreExecute()
    {
        //Create a new progress dialog
        progressDialog = new ProgressDialog(context);
        //Set the progress dialog to display a horizontal progress bar
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //Set the dialog title to 'Loading...'
        progressDialog.setTitle("Loading...");
        //Set the dialog message to 'Loading application View, please wait...'
        progressDialog.setMessage("Chargement des infos en cours...");
        //This dialog can't be canceled by pressing the back key
        progressDialog.setCancelable(false);
        //This dialog isn't indeterminate
        progressDialog.setIndeterminate(false);
        //The maximum number of items is 100
        progressDialog.setMax(100);
        //Set the current progress to zero
        progressDialog.setProgress(0);
        //Display the progress dialog
        progressDialog.show();
    }


    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
        String id = strings[1];

        int i,j;


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
            progressDialog.setProgress(20);
            try {

                URL url = new URL("https://api.themoviedb.org/3/"+type+"/"+id+"/credits?api_key=" + Utils.Intent.TAG_APIKEY + "&language=" + Locale.getDefault().getLanguage());

                Log.i("urlCastTask", "https://api.themoviedb.org/3/"+type+"/"+id+"/credits?api_key=" + Utils.Intent.TAG_APIKEY + "&language=" + Locale.getDefault().toString());

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
        //close the progress dialog
        progressDialog.dismiss();
        if(s != null){
            try {
                callBCast.getResultCast(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface ICallbackCast {
        void getResultCast(String result) throws JSONException;
    }
}
