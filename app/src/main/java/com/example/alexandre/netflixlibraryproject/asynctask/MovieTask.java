package com.example.alexandre.netflixlibraryproject.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.alexandre.netflixlibraryproject.model.Genre;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.example.alexandre.netflixlibraryproject.model.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alexandre on 04-10-17.
 */

public class MovieTask extends AsyncTask<String,Void,List<Movie>>{

    private ICallback callB;
    public void setCallB(ICallback callB){this.callB=callB;}
    private ProgressDialog progressDialog;

    private Context context;

    public MovieTask(Context context){
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
    protected List<Movie> doInBackground(String... strings) {
        String type = strings[0];
        String rech = strings[1];
        List<Movie> movies = new ArrayList<>();
        int i,j;
        List<Genre> genres = new ArrayList<>();

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

                URL url = new URL("https://api.themoviedb.org/3/search/" + type + "?query=" + rech + "&api_key=" + Utils.Intent.TAG_APIKEY+"&language="+Locale.getDefault().toString());

                Log.i("url", "https://api.themoviedb.org/3/search/" + type + "?query=" + rech + "&api_key=" + Utils.Intent.TAG_APIKEY+"&language="+Locale.getDefault().toString());

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

                if(type=="movie"){


                JSONObject object = new JSONObject(builder.toString());
                JSONArray jsonArray = object.getJSONArray("results");
                Type listType = new TypeToken<List<Movie>>() {
                }.getType();
                movies= new Gson().fromJson(String.valueOf(jsonArray), listType);
                progressDialog.setProgress(40);
                for(i=0;i<movies.size();i++) {

                    try {
                        URL url2 = new URL("https://api.themoviedb.org/3/movie/" + movies.get(i).getId() + "?api_key=d3f617c2a1b78f7220853c4627424fe5&language=" + Locale.getDefault().toString());

                        Log.i("urlDetails", "https://api.themoviedb.org/3/movie/" + movies.get(i).getId() + "?api_key=d3f617c2a1b78f7220853c4627424fe5&language=" + Locale.getDefault().toString());
                        HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                        connection2.setRequestMethod("GET");
                        connection2.connect();

                        InputStream stream2 = connection2.getInputStream();
                        BufferedReader reader2 = new BufferedReader(new InputStreamReader(stream2));
                        StringBuilder builder2 = new StringBuilder();
                        String line2 = "";

                        while ((line2 = reader2.readLine()) != null) {
                            builder2.append(line2);
                        }

                        reader2.close();
                        connection2.disconnect();
                        progressDialog.setProgress(60);

                        JSONObject object2 = new JSONObject(builder2.toString());
                        JSONArray jsonArray2 = object2.getJSONArray("genres");
                        Type listType2 = new TypeToken<List<Genre>>() {
                        }.getType();
                        genres = new Gson().fromJson(String.valueOf(jsonArray2), listType2);

                        progressDialog.setProgress(80);


                        for (j = 0; j < genres.size(); j++) {
                            movies.get(i).addGenre(genres.get(j));
                            progressDialog.setProgress(progressDialog.getProgress() + 1);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                }
                progressDialog.setProgress(100);
                return movies;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPostExecute(List<Movie> s) {
        super.onPostExecute(s);
        //close the progress dialog
        progressDialog.dismiss();
        if(s != null){
            try {
                callB.getResult(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface ICallback {
        void getResult(List<Movie> result) throws JSONException;
    }
}
