package com.example.alexandre.netflixlibraryproject.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.alexandre.netflixlibraryproject.model.Utils;

import org.json.JSONException;

/**
 * Created by Alexandre on 04-10-17.
 */

public class TitreTask extends AsyncTask<String,Void,String>{

    private ICallback callB;
    public void setCallB(ICallback callB){this.callB=callB;}

    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
        String titre = strings[1];

        if (type == "Titre") {

            type = "title";
            return Utils.Intent.TAG_TITRE;
        }
        else if(type=="Acteur"){
            type ="actor";
            return Utils.Intent.TAG_ACTEUR;

        } else if(type=="Réalisateur"){
            type ="director";
            return Utils.Intent.TAG_REALISATEUR;
        }else{
            type ="error";
        }
        Log.i("type",type);
        if(type!="error") {


/*
            try {
                URL url = new URL("https://netflixroulette.net/api/api.php?" +type+"="+titre);

                Log.i("url","https://netflixroulette.net/api/api.php?" +type+"="+titre);

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

                return builder.toString();*/
                return null;
           /* } catch (IOException e) {
                e.printStackTrace();
            }*/

        }else{
            Log.i("Error Spinner","Attention mauvaise saisie Spinner");
        }


        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
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
