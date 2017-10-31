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
    private int type = 0;

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
                    FindTask task = new FindTask(getContext());
                    task.setCallB(MainFragment.this);
                    Log.i("editText", etTitre.getText().toString());
                    task.execute(spinner.getSelectedItem().toString(), etTitre.getText().toString());
                }
            }
        });

        rv = (RecyclerView) v.findViewById(R.id.rv_fragmain_listeF);

        //Tentative de faire fonctionner que si le spiner change -> le Recyclerview disparait mais ne marche pas comme il faut
  /*      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {

                Toast.makeText(parent.getContext(),
                        "On Item Select : \n" + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                rv.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


            }
        });
*/
        setLayout();
        onClickList();



        return v;
    }


    public void onClickList(){

        Log.i("SpinnerFilmprevious?",spinner.getSelectedItem().toString());

        rv.setVisibility(View.VISIBLE);
        Log.i("Visible","Je passe bien au visible");

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.i("SpinnerFilm?",spinner.getSelectedItem().toString());
                        if (spinner.getSelectedItem().toString() == "Film") {

                            String id = dataF.get(position).getId() + "";
                            m = dataF.get(position);
                            Log.i("idValeurF", id);
                            Log.i("dataF", dataF.get(position).toString());
                            DetailsTask taskD = new DetailsTask(getContext());
                            taskD.setCallBDetails(MainFragment.this);
                            taskD.execute(spinner.getSelectedItem().toString(), id);

                            CastTask taskC = new CastTask((getContext()));
                            taskC.setCallBCast(MainFragment.this);
                            taskC.execute(spinner.getSelectedItem().toString(), id);
                        } else if (spinner.getSelectedItem().toString() == "Série") {

                            String id = dataS.get(position).getId() + "";
                            Log.i("idValeurS", id);
                            Log.i("dataS", dataS.get(position).toString());
                            s = dataS.get(position);
                            DetailsTask taskD = new DetailsTask(getContext());
                            taskD.setCallBDetails(MainFragment.this);
                            taskD.execute(spinner.getSelectedItem().toString(), id);

                            CastTask taskC = new CastTask((getContext()));
                            taskC.setCallBCast(MainFragment.this);

                            taskC.execute(spinner.getSelectedItem().toString(), id);
                        } else if(spinner.getSelectedItem().toString()== "Acteur") {
                            String id = dataA.get(position).getId() + "";
                            Log.i("idValeurA", id);
                            Log.i("dataA", dataA.get(position).toString());
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
        Log.i("VerifSpinner",spinner.getSelectedItem().toString());
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

                    Movie movie = new Movie(id,poster,title,originalTitle,rating,release);
                    Log.i("ObjectMovie",movie.toString());
                    dataF.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            type=0;
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
                    String firstairdate = object2.getString("first_air_date");

                    Serie serie= new Serie(id,poster,title,originalName,rating,firstairdate);
                    Log.i("ObjectMovie",serie.toString());
                    dataS.add(serie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
           type =1;
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

            for(int i = 0;i<dataA.size();i++){
                Log.i("VerifDataA",dataA.get(i).getName());
            }
           type=2;
        }
        setLayout();
        onClickList();
    }



    @Override
    public void getResultDetails(String result) throws JSONException {
        Log.i("testResult2",result);
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

            Log.i("AfficheGenresOverview",m.getGenreString()+" "+m.getCompanyString()+" "+m.getOverview());

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
            Log.i("AfficheGenresOverview",s.getGenreString()+" "+s.getCompanyString()+" "+s.getOverview());


        }else if(spinner.getSelectedItem().toString()== "Acteur"){
            Log.i("avant", "AfficherDétailsActeurs");

            JSONObject object3 = new JSONObject(result);
            a.setbirthday(object3.getString("birthday"));
            a.setdeathday(object3.getString("deathday"));
            if(a.getdeathday().equals("null")) a.setdeathday(". . .");
            a.setbiography(object3.getString("biography"));
            a.setplace_of_birth(object3.getString("place_of_birth"));

        }
    }

    @Override
    public void getResultCast(String result) throws JSONException {
        Log.i("testResult3",result);
        List<Actor> listActors = new ArrayList<>();
        if(spinner.getSelectedItem().toString()== "Film") {
            JSONObject object4 = new JSONObject(result);
            JSONArray jArrayCast = object4.getJSONArray("cast");
            for(int i =0;i<jArrayCast.length();i++) {
                JSONObject objectCast = jArrayCast.getJSONObject(i);
                Actor act = new Actor(objectCast.getString("name"),objectCast.getString("character"),objectCast.getString("profile_path"));
                listActors.add(act);
            }
            m.setActor(listActors);

            for(int i=0;i<m.getActors().size();i++) {
                Log.i("veriflistActors",m.getActors().get(i).toString());
            }
            OnObjectListener.UpdateMovie(m);

        }else if(spinner.getSelectedItem().toString()== "Série"){

            JSONObject object4 = new JSONObject(result);
            JSONArray jArrayCast = object4.getJSONArray("cast");
            for(int i =0;i<jArrayCast.length();i++) {
                JSONObject objectCast = jArrayCast.getJSONObject(i);
                Actor act = new Actor(objectCast.getString("name"),objectCast.getString("character"),objectCast.getString("profile_path"));
                s.addActor(act);
            }

            for(int i=0;i<s.getActors().size();i++) {
                Log.i("veriflistActors",s.getActors().toString());
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

                    Movie m = new Movie(objectCast.getLong("id"), objectCast.getString("poster_path"), objectCast.getString("title"), objectCast.getString("release_date"), objectCast.getString("character"));
                    // Object object = new Serie(objectCast.getLong("id"),objectCast.getString("poster_path"),objectCast.getString("firt_air_date"),objectCast.getString("title"));
                    listMovieCast.add(m);
                }else if(objectCast.getString("media_type").equals("tv")){
                    Serie s = new Serie(objectCast.getLong("id"),objectCast.getString("poster_path"),objectCast.getString("title"),objectCast.getString("first_air_date"),objectCast.getString("character"));
                    listSerieCast.add(s);
                }
            }
            a.setMovies(listMovieCast);
            a.setSeries(listSerieCast);
            OnObjectListener.UpdateActor(a);
        }
    }

    public void setLayout() {

        switch (type) {
            case 1:
                rv.setAdapter(new TVAdapter(getContext(), dataS));
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                break;
            case 2:
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

        }

    }
}