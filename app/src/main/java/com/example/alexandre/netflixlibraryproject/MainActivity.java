package com.example.alexandre.netflixlibraryproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.alexandre.netflixlibraryproject.asynctask.TitreTask;
import com.example.alexandre.netflixlibraryproject.model.Film;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements TitreTask.ICallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TitreTask task = new TitreTask();
        task.setCallB(this);
        task.execute("Hercules");

    }

    @Override
    public void getResult(String result) throws JSONException {
       // Log.i("alex", result);
        JSONObject object = new JSONObject(result);
        Gson gson = new Gson();
        Film f = gson.fromJson(object.toString(), Film.class);
        Toast.makeText(this, f.getCategory()+ "", Toast.LENGTH_LONG).show();
    }
}
