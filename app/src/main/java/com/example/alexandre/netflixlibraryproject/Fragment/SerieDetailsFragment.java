package com.example.alexandre.netflixlibraryproject.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.RecyclerItemClickListener;
import com.example.alexandre.netflixlibraryproject.adapter.ListActorAdapter;
import com.example.alexandre.netflixlibraryproject.asynctask.CastTask;
import com.example.alexandre.netflixlibraryproject.asynctask.DetailsTask;
import com.example.alexandre.netflixlibraryproject.model.Actor;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.example.alexandre.netflixlibraryproject.model.Serie;
import com.example.alexandre.netflixlibraryproject.model.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SerieDetailsFragment extends Fragment implements View.OnClickListener,DetailsTask.ICallbackDetails,CastTask.ICallbackCast {
    private ImageView ivPoster;
    private ImageView ivBackPoster;
    private TextView tvCategory;
    private TextView tvRating;
    private TextView tvFirstAirDate;
    private TextView tvShowTitle;
    private TextView tvSummary;
    private TextView tvNbSeason;
    private TextView tvNbEpisode;
    private TextView tvCompanys;
    private RecyclerView rv;
    private Serie serie;
    private Button btnYoutube;
    private OnObjectSetListener OnObjectListener;
    private Actor a;

    private static SerieDetailsFragment instance = null;

    public static SerieDetailsFragment getInstance(){
        if(instance==null){
            instance = new SerieDetailsFragment();
            return instance;
        }
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_serie_details, container, false);

        ivPoster = (ImageView) v.findViewById(R.id.iv_serie_details_poster);
        ivBackPoster = (ImageView) v.findViewById(R.id.iv_serie_details_backposter) ;
        tvCompanys = (TextView) v.findViewById(R.id.tv_serie_details_companys) ;
        tvCategory = (TextView) v.findViewById(R.id.tv_serie_details_category);
        tvFirstAirDate = (TextView) v.findViewById(R.id.tv_serie_details_firstAirDate);
        tvShowTitle = (TextView) v.findViewById(R.id.tv_serie_details_showTitle);
        tvRating = (TextView) v.findViewById(R.id.tv_serie_details_rating);
        tvSummary = (TextView) v.findViewById(R.id.tv_serie_details_summary);
        tvNbSeason = (TextView) v.findViewById(R.id.tv_serie_details_nbSeason);
        tvNbEpisode = (TextView) v.findViewById(R.id.tv_serie_details_nbEpisode);
        btnYoutube = (Button) v.findViewById(R.id.btn_serie_details_youtube);
        btnYoutube.setOnClickListener(SerieDetailsFragment.this);

        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+serie.getPosterPath()).error(getContext().getDrawable(R.drawable.defaut))/*.centerCrop().fit()*/.into(ivPoster);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+serie.getBackdropPath()).error(getContext().getDrawable(R.drawable.defautback)).into(ivBackPoster);

        tvCategory.setText(serie.getGenreString());
        tvFirstAirDate.setText(serie.getReleaseDate());
        tvShowTitle.setText(serie.getTitle());
        tvRating.setText(serie.getVoteAverage()+"/10");
        tvSummary.setText(serie.getOverview());
        tvNbSeason.setText(serie.getnbSaison()+" ");
        tvNbEpisode.setText(serie.getnbEpisodes()+" ");
        tvCompanys.setText(serie.getCompanyString());
        tvFirstAirDate.setText(serie.getReleaseDate());

        rv = (RecyclerView) v.findViewById(R.id.rv_serie_details_listeActors);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new ListActorAdapter(getContext(), serie.getActors()));

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        a = serie.getActors().get(position);

                        DetailsTask taskD = new DetailsTask(getContext());
                        taskD.setCallBDetails(SerieDetailsFragment.this);
                        taskD.execute("Acteur", a.getId()+"");
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        return v;
    }

    public void setSerie(Serie s) {
        serie = s;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_serie_details_youtube:
                submitAction();
                break;
        }
    }

    public void submitAction() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.youtube.com/results?search_query="+serie.getTitle()+" trailer "+Locale.getDefault().getLanguage()));
        startActivity(i);
    }

    @Override
    public void getResultDetails(String result) throws JSONException {

        JSONObject object3 = new JSONObject(result);

        if(!object3.has("birthday")){
            a.setbirthday(getString(R.string.notFind));
        }else{
            a.setbirthday(Utils.formatDate(object3.getString("birthday")));
        }

        if(!object3.has("deathday")){
            a.setdeathday(getString(R.string.notFind));
        }else{
            if(object3.getString("deathday")=="null"){
                a.setdeathday("...");
            }else {
                a.setdeathday(object3.getString("deathday"));
            }
        }

        if(!object3.has("place_of_birth")){
            a.setplace_of_birth(getString(R.string.notFind));
        }else{
            if(object3.getString("place_of_birth")=="null"){
                a.setplace_of_birth(getString(R.string.notFind));
            }else {
                a.setplace_of_birth(object3.getString("place_of_birth"));
            }
        }

        if(!object3.has("biography")){
            a.setbiography(getString(R.string.notFind));
        }else{
            if(object3.getString("biography")=="null"){
                a.setbiography(getString(R.string.notFind));
            }else {
                a.setbiography(object3.getString("biography"));
            }
        }

        CastTask taskC = new CastTask((getContext()));
        taskC.setCallBCast(SerieDetailsFragment.this);
        taskC.execute("Acteur", a.getId()+"");
    }

    @Override
    public void getResultCast(String result) throws JSONException {
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


    public interface OnObjectSetListener {
        void UpdateActor(Actor a);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            OnObjectListener = (SerieDetailsFragment.OnObjectSetListener) context;
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
