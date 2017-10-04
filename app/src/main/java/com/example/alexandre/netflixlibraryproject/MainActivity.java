package com.example.alexandre.netflixlibraryproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alexandre.netflixlibraryproject.asynctask.TitreTask;
import com.example.alexandre.netflixlibraryproject.model.Film;
import com.example.alexandre.netflixlibraryproject.model.Utils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements TitreTask.ICallback {
    private EditText etTitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitre = (EditText) findViewById(R.id.et_main_titre);
        Button btnRech = (Button) findViewById(R.id.btn_main_chercher);

        btnRech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTitre!=null){
                    TitreTask task = new TitreTask();
                    task.setCallB(MainActivity.this);
                    Log.i("editTextt",etTitre.getText().toString());

                    task.execute(etTitre.getText().toString());

                }
            }
        });



    }

    @Override
    public void getResult(String result) throws JSONException {
       // Log.i("alex", result);
        JSONObject object = new JSONObject(result);
        Gson gson = new Gson();
        Film f = gson.fromJson(object.toString(), Film.class);

        Intent i = new Intent(MainActivity.this,ShowFilm.class);


        i.putExtra(Utils.Intent.TAG_FILM,f);

        startActivity(i);
        //Toast.makeText(this, f.getReleaseYear()+ "", Toast.LENGTH_LONG).show();
    }
}
