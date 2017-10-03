package com.example.alexandre.netflixlibraryproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.alexandre.netflixlibraryproject.asynctask.TitreTask;
import com.example.alexandre.netflixlibraryproject.model.Film;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements TitreTask.icallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("pokemon2", "coucou");

        TitreTask task = new TitreTask();
        task.setCallback(this);
        task.execute("Attack on titan");
    }

    @Override
    public void getResult(String result) throws JSONException {
        Log.i("pokemon", result);
        JSONObject object = new JSONObject(result);

        Gson gson = new Gson();
        Film f = gson.fromJson(object.toString(), Film.class);

//        MainWeather weather = new Gson().fromJson(object.getJSONObject("main"));
        Log.i("florian",f.getSummary());
        Toast.makeText(this, f.getCategory()+ "++++", Toast.LENGTH_LONG).show();
       // Toast.makeText(this,"Salut",Toast.LENGTH_LONG).show();
    }
}
