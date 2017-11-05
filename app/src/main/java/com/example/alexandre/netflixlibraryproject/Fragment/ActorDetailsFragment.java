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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.RecyclerItemClickListener;
import com.example.alexandre.netflixlibraryproject.adapter.ListMovieAdapter;
import com.example.alexandre.netflixlibraryproject.adapter.ListSerieAdapter;
import com.example.alexandre.netflixlibraryproject.asynctask.CastTask;
import com.example.alexandre.netflixlibraryproject.asynctask.DetailsTask;
import com.example.alexandre.netflixlibraryproject.model.Actor;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.example.alexandre.netflixlibraryproject.model.Serie;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActorDetailsFragment extends Fragment implements DetailsTask.ICallbackDetails,CastTask.ICallbackCast {
    private ImageView ivPoster;
    private TextView tvName;
    private TextView tvLife;    // Birthday and deathday
    private TextView tvBiography;
    private TextView tvPlaceOfBirth;
    private TextView tvFilm;
    private TextView tvSeries;
    private RecyclerView rvMovies;
    private RecyclerView rvSeries;
    private OnObjectSetListener OnObjectListener;
    private Movie m;
    private Serie s;
    private Actor actor;
    private String ListeChoisie;

    private static ActorDetailsFragment instance=null;

    public static ActorDetailsFragment getInstance(){
        if(instance == null){
            instance = new ActorDetailsFragment();
            return instance;
        }
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_actor_details, container, false);

        ivPoster = (ImageView) v.findViewById(R.id.iv_actor_details_poster);
        tvName = (TextView) v.findViewById(R.id.tv_actor_details_name) ;
        tvLife = (TextView) v.findViewById(R.id.tv_actor_details_birthdayAndDeathday) ;
        tvBiography = (TextView) v.findViewById(R.id.tv_actor_details_biography) ;
        tvPlaceOfBirth = (TextView) v.findViewById(R.id.tv_actor_details_placeOfBirth) ;
        tvFilm = (TextView) v.findViewById(R.id.tv_actor_details_movies);
        tvSeries = (TextView) v.findViewById(R.id.tv_actor_details_tvseries);

        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+actor.getProfilePath()).error(getContext().getDrawable(R.drawable.defaut))/*.centerCrop().fit()*/.into(ivPoster);

        tvName.setText(actor.getName());
        tvLife.setText(actor.getbirthday() + " - " + actor.getdeathday());
        tvPlaceOfBirth.setText(actor.getPlace_of_birth());
        tvBiography.setText(actor.getbiography());


        rvMovies = (RecyclerView) v.findViewById(R.id.rv_actor_details_listeMovies);
        rvMovies.setHasFixedSize(false);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMovies.setAdapter(new ListMovieAdapter(getContext(), actor.getMovies()));

        if(actor.getMovies().isEmpty()){
            rvMovies.setVisibility(View.INVISIBLE);
            tvFilm.setVisibility(View.INVISIBLE);
        }else{
            rvMovies.setVisibility(View.VISIBLE);
            tvFilm.setVisibility(View.VISIBLE);
        }


        rvMovies.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rvMovies, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        ListeChoisie = "Film";
                        m = actor.getMovies().get(position);

                        DetailsTask taskD = new DetailsTask(getContext());
                        taskD.setCallBDetails(ActorDetailsFragment.this);
                        taskD.execute(ListeChoisie, m.getId()+"");

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        rvSeries = (RecyclerView) v.findViewById(R.id.rv_actor_details_listeSeries);
        rvSeries.setHasFixedSize(false);
        rvSeries.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvSeries.setAdapter(new ListSerieAdapter(getContext(), actor.getSeries()));

        if(actor.getSeries().isEmpty()){
            rvSeries.setVisibility(View.INVISIBLE);
            tvSeries.setVisibility(View.INVISIBLE);
        }else{
            rvSeries.setVisibility(View.VISIBLE);
            tvSeries.setVisibility(View.VISIBLE);
        }

        rvSeries.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rvSeries, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        ListeChoisie = "Série";
                        s = actor.getSeries().get(position);

                        DetailsTask taskD = new DetailsTask(getContext());
                        taskD.setCallBDetails(ActorDetailsFragment.this);
                        taskD.execute(ListeChoisie, s.getId()+"");

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        return v;
    }

    public void setActor(Actor a){ actor = a; }

    @Override
    public void getResultDetails(String result) throws JSONException {
        List<String> genres = new ArrayList<>();
        List<String>  companies = new ArrayList<>();

        CastTask taskC = new CastTask((getContext()));
        taskC.setCallBCast(ActorDetailsFragment.this);

        if(ListeChoisie== "Film") {
            JSONObject object3 = new JSONObject(result);
            m.setOriginalTitle(object3.getString("original_title"));
            m.setVoteAverage(Float.parseFloat(object3.getString("vote_average")));
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

            taskC.execute(ListeChoisie, m.getId()+"");

        }else if(ListeChoisie == "Série"){
            JSONObject object3 = new JSONObject(result);
            s.setOriginalTitle(object3.getString("original_name"));
            s.setVoteAverage( Float.parseFloat(object3.getString("vote_average")));
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

            taskC.execute(ListeChoisie, s.getId()+"");

        }
    }

    @Override
    public void getResultCast(String result) throws JSONException {
        List<Actor> listActors = new ArrayList<>();
        if(ListeChoisie== "Film") {
            JSONObject object4 = new JSONObject(result);
            JSONArray jArrayCast = object4.getJSONArray("cast");
            for(int i =0;i<jArrayCast.length();i++) {
                JSONObject objectCast = jArrayCast.getJSONObject(i);
                Actor act = new Actor(objectCast.getLong("id"),objectCast.getString("name"),objectCast.getString("character"),objectCast.getString("profile_path"));
                listActors.add(act);
            }
            m.setActor(listActors);

            OnObjectListener.UpdateMovie(m);

        }else if(ListeChoisie== "Série"){

            JSONObject object4 = new JSONObject(result);
            JSONArray jArrayCast = object4.getJSONArray("cast");
            for(int i =0;i<jArrayCast.length();i++) {
                JSONObject objectCast = jArrayCast.getJSONObject(i);
                Actor act = new Actor(objectCast.getLong("id"),objectCast.getString("name"),objectCast.getString("character"),objectCast.getString("profile_path"));
                s.addActor(act);
            }

            OnObjectListener.UpdateSerie(s);
        }
    }

    public interface OnObjectSetListener {
        void UpdateMovie(Movie m);
        void UpdateSerie(Serie s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            OnObjectListener = (ActorDetailsFragment.OnObjectSetListener) context;
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
