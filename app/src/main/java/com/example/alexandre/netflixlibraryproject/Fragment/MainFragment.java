package com.example.alexandre.netflixlibraryproject.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.asynctask.TitreTask;
import com.example.alexandre.netflixlibraryproject.model.Film;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements TitreTask.ICallback {

    private EditText etTitre;
    private Spinner spinner;
    private TextView tvTest;

    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main,container,false);


        etTitre = (EditText) v.findViewById(R.id.et_main_titre);
        tvTest = (TextView) v .findViewById(R.id.tv_Fragmain_test);


        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        spinner = (Spinner) v.findViewById(R.id.spin_fragMain_spinner1);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List exempleList = new ArrayList();
        exempleList.add("Titre");
        exempleList.add("Acteur");
        exempleList.add("Réalisateur");

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                exempleList
        );


               /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);


        Button btnRech = (Button) v.findViewById(R.id.btn_Fragmain_chercher);

        btnRech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTitre!=null){
                    TitreTask task = new TitreTask();
                    task.setCallB(MainFragment.this);
                    Log.i("editTextt",etTitre.getText().toString());

                    task.execute(spinner.getSelectedItem().toString(),etTitre.getText().toString());

                }
            }
        });


        return v;
    }


    @Override
    public void getResult(String result) throws JSONException {
        Log.i("alex", result);
        Log.i("bracket","++"+result.substring(0,1)+"++");
        if(result.substring(0,1).equals("[")){
            JSONArray jsonArray = new JSONArray(result);
            List<Film> films;
            Type listType = new TypeToken<List<Film>>() {
            }.getType();
            films= new Gson().fromJson(String.valueOf(jsonArray), listType);

            Log.i("testfilmsListe",films.get(0).getShowTitle());
           // Log.i("testfilmsListe",films.get(1).getShowTitle());

            tvTest.setText("Titre : "+films.get(0).getShowTitle()+" \nRéalisateur : "+films.get(0).getDirector()+"\nCatégorie : "+films.get(0).getCategory()+
                    "\nRésumé : "+films.get(0).getSummary());
        }
        else if(result.substring(0,1).equals("{")){
            JSONObject object = new JSONObject(result);
            Gson gson = new Gson();
            Film f = gson.fromJson(object.toString(), Film.class);

            tvTest.setText("Titre : "+f.getShowTitle()+" \nRéalisateur : "+f.getDirector()+"\nCatégorie : "+f.getCategory()+
                    "\nRésumé : "+f.getSummary());
        }










        //Log.i("TestArray",+"");

        /*
        Log.i("Testtitle",f.getShowTitle()+" ");

        tvTest.setText("Titre : "+f.getShowTitle()+" /nRéalisateur : "+f.getDirector()+"/nCatégorie : "+f.getCategory()+
                "/nRésumé : "+f.getSummary());*/

        //Intent i = new Intent(MainActivity.this,ShowFilm.class);




        //i.putExtra(Utils.Intent.TAG_FILM,f);

        //startActivity(i);
        //Toast.makeText(this, f.getReleaseYear()+ "", Toast.LENGTH_LONG).show();
    }

}