package com.example.alexandre.netflixlibraryproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alexandre.netflixlibraryproject.asynctask.TitreTask;
import com.example.alexandre.netflixlibraryproject.model.Film;
import com.example.alexandre.netflixlibraryproject.model.Utils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TitreTask.ICallback {
    private EditText etTitre;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitre = (EditText) findViewById(R.id.et_main_titre);

        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        spinner = (Spinner) findViewById(R.id.spinner);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List exempleList = new ArrayList();
        exempleList.add("Titre");
        exempleList.add("Acteur");
        exempleList.add("Réalisateur");

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList
        );


               /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);


        Button btnRech = (Button) findViewById(R.id.btn_main_chercher);

        btnRech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTitre!=null){
                    TitreTask task = new TitreTask();
                    task.setCallB(MainActivity.this);
                    Log.i("editTextt",etTitre.getText().toString());

                    task.execute(spinner.getSelectedItem().toString(),etTitre.getText().toString());

                }
            }
        });



    }

    @Override
    public void getResult(String result) throws JSONException {
       // Log.i("alex", result);
        JSONObject object = new JSONObject(result);
        Gson gson = new Gson();
        Film f = gson.fromJson(object.toString(), Film.class); // Erreur quand plusieurs objets films !

        Intent i = new Intent(MainActivity.this,ShowFilm.class);




        i.putExtra(Utils.Intent.TAG_FILM,f);

        startActivity(i);
        //Toast.makeText(this, f.getReleaseYear()+ "", Toast.LENGTH_LONG).show();
    }
}
