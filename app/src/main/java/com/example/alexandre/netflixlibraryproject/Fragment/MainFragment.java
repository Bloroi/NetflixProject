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
import com.example.alexandre.netflixlibraryproject.asynctask.CastTask;
import com.example.alexandre.netflixlibraryproject.asynctask.DetailsTask;
import com.example.alexandre.netflixlibraryproject.asynctask.FindTask;
import com.example.alexandre.netflixlibraryproject.model.Actor;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.example.alexandre.netflixlibraryproject.model.Serie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements FindTask.ICallback,DetailsTask.ICallbackDetails,CastTask.ICallbackCast{

    private EditText etTitre;
    private Spinner spinner;
    private RecyclerView rv;
    private List<Movie> results;
    private int pos;
    private Movie filmEnvoi;
    private static MainFragment instance = null;
    private List<Movie> dataF = new ArrayList<>();
    private List<Serie> dataS = new ArrayList<>();
    private List<Actor> dataA = new ArrayList<>();
    private Movie m;
    private Serie s;
    private Actor a;

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
                        FindTask task = new FindTask(getContext());
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
    public void getResult(String result){

        if(spinner.getSelectedItem().toString()== "Film") {
            dataF = new ArrayList<>();
            try {
                JSONObject object = new JSONObject(result);
                JSONArray jsonArray = object.getJSONArray("results");
                for(int i =0;i<jsonArray.length();i++){
                    JSONObject object2 = jsonArray.getJSONObject(i);
                    Long id = Long.parseLong(object2.getString("id"));
                    String title = object2.getString("title");
                    String originalTitle = object2.getString("original_title");
                    String poster = object2.getString("poster_path");
                    float rating = Float.parseFloat(object2.getString("vote_average"));
                    String release = object2.getString("release_date");
                    Movie m = new Movie(id,poster,title,originalTitle,rating,release);
                    Log.i("ObjectMovie",m.toString());
                    dataF.add(m);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }




            rv.addOnItemTouchListener(
                    new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            String id = dataF.get(position).getId()+"";
                            m = dataF.get(position);
                            DetailsTask taskD = new DetailsTask(getContext());
                            taskD.setCallBDetails(MainFragment.this);
                            taskD.execute(spinner.getSelectedItem().toString(),id);

                            CastTask taskC = new CastTask((getContext()));
                            taskC.setCallBCast(MainFragment.this);
                            taskC.execute(spinner.getSelectedItem().toString(),id);
                            //OnObjectListener.UpdateMovie(movie);
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            // do whatever
                        }
                    })
            );


            rv.setAdapter(new MovieAdapter(getContext(), dataF));
        }else if(spinner.getSelectedItem().toString()== "Série"){

        }else if(spinner.getSelectedItem().toString()== "Actor"){

        }

    }

    @Override
    public void getResultDetails(String result) throws JSONException {
        Log.i("testResult2",result);
        if(spinner.getSelectedItem().toString()== "Film") {
            JSONObject object3 = new JSONObject(result);
            m.setBackdropPath(object3.getString("backdrop_path"));
            m.setOverview(object3.getString(("overview")));
            JSONArray jArrayGenre = object3.getJSONArray("genres");
            for(int i =0;i<jArrayGenre.length();i++) {
                JSONObject objectGenre = jArrayGenre.getJSONObject(i);
                String genre = objectGenre.getString("name");
                m.addGenre(genre);
            }
            JSONArray jArrayCompany = object3.getJSONArray("production_companies");
            for(int i =0;i<jArrayCompany.length();i++) {
                JSONObject objectCompany = jArrayCompany.getJSONObject(i);
                String comp = objectCompany.getString("name");
                m.addCompany(comp);
            }

            Log.i("AfficheGenresOverview",m.getGenreString()+" "+m.getCompanyString()+" "+m.getOverview());




    }else if(spinner.getSelectedItem().toString()== "Série"){

    }else if(spinner.getSelectedItem().toString()== "Actor"){

    }
    }

    @Override
    public void getResultCast(String result) throws JSONException {
        Log.i("testResult3",result);
        if(spinner.getSelectedItem().toString()== "Film") {
            JSONObject object4 = new JSONObject(result);
            JSONArray jArrayCast = object4.getJSONArray("cast");
            for(int i =0;i<jArrayCast.length();i++) {
                JSONObject objectCast = jArrayCast.getJSONObject(i);
                Actor act = new Actor(objectCast.getString("name"),objectCast.getString("character"),objectCast.getString("profile_path"));
                m.addActor(act);
            }

            List<Actor> actors = m.getActors();

           /* for(int i=0;i<actors.size();i++) {
                Log.i("veriflistActors",actors.get(i).toString());
            }*/
            OnObjectListener.UpdateMovie(m);

        }else if(spinner.getSelectedItem().toString()== "Série"){

        }else if(spinner.getSelectedItem().toString()== "Actor"){

        }
    }



    public interface OnObjectSetListener {
        void UpdateMovie(Movie m);
        void UpdateSerie(Serie s);
        void UpdateActor(Actor a);
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