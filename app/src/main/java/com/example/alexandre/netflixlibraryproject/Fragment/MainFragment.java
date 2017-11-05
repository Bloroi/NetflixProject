package com.example.alexandre.netflixlibraryproject.Fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.RecyclerItemClickListener;
import com.example.alexandre.netflixlibraryproject.adapter.ActorAdapter;
import com.example.alexandre.netflixlibraryproject.adapter.MovieAdapter;
import com.example.alexandre.netflixlibraryproject.adapter.TVAdapter;
import com.example.alexandre.netflixlibraryproject.asynctask.CastTask;
import com.example.alexandre.netflixlibraryproject.asynctask.DetailsTask;
import com.example.alexandre.netflixlibraryproject.asynctask.FindTask;
import com.example.alexandre.netflixlibraryproject.model.Actor;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.example.alexandre.netflixlibraryproject.model.Serie;
import com.example.alexandre.netflixlibraryproject.model.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainFragment extends Fragment implements FindTask.ICallback,DetailsTask.ICallbackDetails,CastTask.ICallbackCast{

    private EditText etTitre;
    private Spinner spinner;
    private RecyclerView rv;
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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                onClickList();
                setLayout();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

                Button btnRech = (Button) v.findViewById(R.id.btn_Fragmain_chercher);

        btnRech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTitre!=null){
                    FindTask task = new FindTask(getContext());
                    task.setCallB(MainFragment.this);
                    task.execute(spinner.getSelectedItem().toString(), etTitre.getText().toString());
                }
            }
        });

        rv = (RecyclerView) v.findViewById(R.id.rv_fragmain_listeF);
        setLayout();
        onClickList();

        return v;
    }

    public void onClickList(){

        rv.setVisibility(View.VISIBLE);

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (spinner.getSelectedItem().toString() == "Film") {

                            String id = dataF.get(position).getId() + "";
                            m = dataF.get(position);
                            DetailsTask taskD = new DetailsTask(getContext());
                            taskD.setCallBDetails(MainFragment.this);
                            taskD.execute(spinner.getSelectedItem().toString(), id);

                            CastTask taskC = new CastTask((getContext()));
                            taskC.setCallBCast(MainFragment.this);
                            taskC.execute(spinner.getSelectedItem().toString(), id);
                        } else if (spinner.getSelectedItem().toString() == "Série") {

                            String id = dataS.get(position).getId() + "";
                            s = dataS.get(position);
                            DetailsTask taskD = new DetailsTask(getContext());
                            taskD.setCallBDetails(MainFragment.this);
                            taskD.execute(spinner.getSelectedItem().toString(), id);

                            CastTask taskC = new CastTask((getContext()));
                            taskC.setCallBCast(MainFragment.this);

                            taskC.execute(spinner.getSelectedItem().toString(), id);
                        } else if(spinner.getSelectedItem().toString()== "Acteur") {
                            String id = dataA.get(position).getId() + "";
                            a = dataA.get(position);
                            DetailsTask taskD = new DetailsTask(getContext());
                            taskD.setCallBDetails(MainFragment.this);
                            taskD.execute(spinner.getSelectedItem().toString(), id);

                            CastTask taskC = new CastTask((getContext()));
                            taskC.setCallBCast(MainFragment.this);
                            taskC.execute(spinner.getSelectedItem().toString(), id);
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }


    //GetResults

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
                    String release = Utils.formatDate(object2.getString("release_date"));

                    Movie movie = new Movie(id,poster,title,originalTitle,rating,release);
                    dataF.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(spinner.getSelectedItem().toString()== "Série"){
            dataS = new ArrayList<>();
            try {
                JSONObject object = new JSONObject(result);
                JSONArray jsonArray = object.getJSONArray("results");
                for(int i =0;i<jsonArray.length();i++){
                    JSONObject object2 = jsonArray.getJSONObject(i);
                    Long id = Long.parseLong(object2.getString("id"));
                    String title = object2.getString("name");
                    String originalName = object2.getString("original_name");
                    String poster = object2.getString("poster_path");
                    float rating = Float.parseFloat(object2.getString("vote_average"));
                    String  firstairdate = Utils.formatDate(object2.getString("first_air_date"));

                    Serie serie= new Serie(id,poster,title,originalName,rating,firstairdate);
                    dataS.add(serie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(spinner.getSelectedItem().toString()== "Acteur"){
            dataA = new ArrayList<>();
            try {
                JSONObject object = new JSONObject(result);
                JSONArray jsonArray = object.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object2 = jsonArray.getJSONObject(i);
                    Long id = Long.parseLong(object2.getString("id"));
                    String name = object2.getString("name");
                    String profile_path = object2.getString("profile_path");
                    Actor actor = new Actor(id, name,profile_path);
                    dataA.add(actor);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        setLayout();
        onClickList();
    }



    @Override
    public void getResultDetails(String result) throws JSONException {
        List<String> genres = new ArrayList<>();
        List<String>  companies = new ArrayList<>();
        if(spinner.getSelectedItem().toString()== "Film") {
            JSONObject object3 = new JSONObject(result);
            m.setBackdropPath(object3.getString("backdrop_path"));
            m.setOverview(object3.getString(("overview")));
            JSONArray jArrayGenre = object3.getJSONArray("genres");
            for(int i =0;i<jArrayGenre.length();i++) {
                JSONObject objectGenre = jArrayGenre.getJSONObject(i);
                String genre = objectGenre.getString("name");
                genres.add(genre);
            }
            m.setGenre(genres);
            JSONArray jArrayCompany = object3.getJSONArray("production_companies");
            for(int i =0;i<jArrayCompany.length();i++) {
                JSONObject objectCompany = jArrayCompany.getJSONObject(i);
                String comp = objectCompany.getString("name");
                companies.add(comp);
            }
            m.setCompany(companies);

        }else if(spinner.getSelectedItem().toString()== "Série"){
            JSONObject object3 = new JSONObject(result);
            s.setBackdropPath(object3.getString("backdrop_path"));
            s.setOverview(object3.getString("overview"));
            s.setnbSaison(object3.getInt("number_of_seasons"));
            s.setnbEpisodes(object3.getInt("number_of_episodes"));
            JSONArray jArrayGenre = object3.getJSONArray("genres");
            for(int i =0;i<jArrayGenre.length();i++) {
                JSONObject objectGenre = jArrayGenre.getJSONObject(i);
                String genre = objectGenre.getString("name");
                genres.add(genre);
            }
            s.setGenre(genres);
            JSONArray jArrayCompany = object3.getJSONArray("production_companies");
            for(int i =0;i<jArrayCompany.length();i++) {
                JSONObject objectCompany = jArrayCompany.getJSONObject(i);
                String comp = objectCompany.getString("name");
                companies.add(comp);
            }
            s.setCompany(companies);

        }else if(spinner.getSelectedItem().toString()== "Acteur"){
            JSONObject object3 = new JSONObject(result);

            String birthday = "";
            String deathday = "";

            if(!object3.has("birthday")){
                birthday = getString(R.string.notFind);
            }else{
                birthday = Utils.formatDate(object3.getString("birthday"));
            }

            if(!object3.has("deathday")){
                deathday = getString(R.string.notFind);
            }else{
                deathday = Utils.formatDate(object3.getString("deathday"));
            }

            a.setbirthday(birthday);
            a.setdeathday(deathday);
            a.setbiography(object3.getString("biography"));
            a.setplace_of_birth(object3.getString("place_of_birth"));
        }
    }

    @Override
    public void getResultCast(String result) throws JSONException {
        List<Actor> listActors = new ArrayList<>();
        if(spinner.getSelectedItem().toString()== "Film") {
            JSONObject object4 = new JSONObject(result);
            JSONArray jArrayCast = object4.getJSONArray("cast");
            for(int i =0;i<jArrayCast.length();i++) {
                JSONObject objectCast = jArrayCast.getJSONObject(i);
                Actor act = new Actor(objectCast.getLong("id"),objectCast.getString("name"),objectCast.getString("character"),objectCast.getString("profile_path"));
                listActors.add(act);
            }
            m.setActor(listActors);

            OnObjectListener.UpdateMovie(m);

        }else if(spinner.getSelectedItem().toString()== "Série"){

            JSONObject object4 = new JSONObject(result);
            JSONArray jArrayCast = object4.getJSONArray("cast");
            for(int i =0;i<jArrayCast.length();i++) {
                JSONObject objectCast = jArrayCast.getJSONObject(i);
                Actor act = new Actor(objectCast.getLong("id"),objectCast.getString("name"),objectCast.getString("character"),objectCast.getString("profile_path"));
                s.addActor(act);
            }

            OnObjectListener.UpdateSerie(s);

        }else if(spinner.getSelectedItem().toString()== "Acteur"){
            List<Movie> listMovieCast = new ArrayList<>();
            List<Serie> listSerieCast = new ArrayList<>();
            JSONObject object4 = new JSONObject(result);
            JSONArray jArrayCast = object4.getJSONArray("cast");
            for(int i =0;i<jArrayCast.length();i++) {
                JSONObject objectCast = jArrayCast.getJSONObject(i);
                if(objectCast.getString("media_type").equals("movie")) {

                        String release = "";
                        String poster= "";
                        String character = "";
                        String title ="";

                        if(!objectCast.has("release_date")){
                            release = getString(R.string.notFind);
                        }else{
                            release = Utils.formatDate(objectCast.getString("release_date"));
                        }

                        if(!objectCast.has("character")){
                            character=getString(R.string.notFind);
                        }else{
                            character=objectCast.getString("character");
                        }

                        Log.i(character,"perso");
                        if(!objectCast.has("poster_path")){
                            poster="";
                        }else{
                            poster=objectCast.getString("poster_path");
                        }

                        if(!objectCast.has("title")){
                            title=getString(R.string.notFind);
                        }else{
                            title=objectCast.getString("title");
                        }

                        Movie m = new Movie(objectCast.getLong("id"), poster, title, release, character);
                        listMovieCast.add(m);
                }
                else if(objectCast.getString("media_type").equals("tv")){

                    String first_air;
                    String characterS;
                    String posterS;
                    String nameS;

                    if(!objectCast.has("first_air_date")){
                        first_air = getString(R.string.notFind);
                    }else{
                        first_air = Utils.formatDate(objectCast.getString("first_air_date"));
                    }

                    if(!objectCast.has("character")){
                        characterS =getString(R.string.notFind);
                    }else{
                        characterS = objectCast.getString("character");
                    }

                    if(!objectCast.has("poster_path")){
                        posterS="";
                    }else{
                        posterS=objectCast.getString("poster_path");
                    }

                    if(!objectCast.has("name")){
                        nameS=getString(R.string.notFind);
                    }else{
                        nameS=objectCast.getString("name");
                    }

                    Serie s = new Serie(objectCast.getLong("id"),posterS,nameS,first_air,characterS);
                    listSerieCast.add(s);
                }
            }
            a.setSeries(listSerieCast);
            a.setMovies(listMovieCast);
            OnObjectListener.UpdateActor(a);
        }
    }

    public void setLayout() {

        switch (spinner.getSelectedItem().toString()) {
            case "Série":
                rv.setAdapter(new TVAdapter(getContext(), dataS));
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                break;
            case "Acteur":
                rv.setAdapter(new ActorAdapter(getContext(), dataA));
                rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                break;
            default:
                rv.setAdapter(new MovieAdapter(getContext(), dataF));
                rv.setLayoutManager(new LinearLayoutManager(getContext()));

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
            e.printStackTrace();
        }

    }
}