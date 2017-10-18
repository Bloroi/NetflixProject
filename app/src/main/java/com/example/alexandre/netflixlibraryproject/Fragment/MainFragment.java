package com.example.alexandre.netflixlibraryproject.Fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.RecyclerItemClickListener;
import com.example.alexandre.netflixlibraryproject.adapter.MovieAdapter;
import com.example.alexandre.netflixlibraryproject.asynctask.MovieTask;
import com.example.alexandre.netflixlibraryproject.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements MovieTask.ICallback{

    private EditText etTitre;
    private Spinner spinner;
    private RecyclerView rv;
    private List<Movie> results;
    private int pos;
    private Movie filmEnvoi;
    private static MainFragment instance = null;

    private OnObjectSetListener OnObjectListener;



    public static MainFragment getInstance(){
        if(instance==null){
            instance = new MainFragment();
            return instance;
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main,container,false);

        etTitre = (EditText) v.findViewById(R.id.et_main_titre);
        spinner = (Spinner) v.findViewById(R.id.spin_fragMain_spinner1);


        //Création d'une liste d'élément à mettre dans le Spinner
        List exempleList = new ArrayList();
        exempleList.add("Film");
        exempleList.add("Série");
        exempleList.add("Acteur");

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut
		Avec la liste des elements */
        final ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                exempleList
        );


               /* On definit une présentation du spinner quand il est déroulé */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);


        Button btnRech = (Button) v.findViewById(R.id.btn_Fragmain_chercher);

        btnRech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTitre!=null){
                    if(spinner.getSelectedItem().toString()== "Film"){
                        MovieTask task = new MovieTask(getContext());
                        task.setCallB(MainFragment.this);
                        Log.i("editText", etTitre.getText().toString());

                        task.execute(spinner.getSelectedItem().toString(), etTitre.getText().toString());
                    }else if(spinner.getSelectedItem().toString()=="Série"){
                        Toast.makeText(getContext(), "Fonctionnalité très bientôt", Toast.LENGTH_SHORT).show();
                    }else if(spinner.getSelectedItem().toString()=="Acteur"){
                        Toast.makeText(getContext(), "Fonctionnalité bientôt", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        rv = (RecyclerView) v.findViewById(R.id.rv_fragmain_listeF);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }




    @Override
    public void getResult(List<Movie> result){

        results = result;

        for(int i=0;i<results.size();i++){
            Log.i("results",results.get(i).getTitle());
        }

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Movie movie = results.get(position);
                        Log.i("result1",results.get(position).getTitle());
                        Log.i("resultMovie",movie.toString());

                        OnObjectListener.UpdateMovie(movie);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


            rv.setAdapter(new MovieAdapter(getContext(), result));

    }


    public interface OnObjectSetListener {
        void UpdateMovie(Movie m);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            OnObjectListener = (OnObjectSetListener) context;
        }catch(Exception e){

        }

    }
}