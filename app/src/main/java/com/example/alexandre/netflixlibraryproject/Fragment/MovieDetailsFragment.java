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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MovieDetailsFragment extends Fragment implements View.OnClickListener,DetailsTask.ICallbackDetails,CastTask.ICallbackCast{

    private ImageView ivPoster;
    private ImageView ivBackPoster;
    private TextView tvCategory;
    private TextView tvCompany;
    private TextView tvRating;
    private TextView tvReleaseYear;
    private TextView tvShowCast;
    private TextView tvShowTitle;
    private TextView tvSummary;
    private Movie movie;
    private View v;
    private RecyclerView rv;
    private Button btnYoutube;
    private Actor a;
    private OnObjectSetListener OnObjectListener;

    private static MovieDetailsFragment instance = null;

    public static MovieDetailsFragment getInstance(){
        if(instance==null){
            instance = new MovieDetailsFragment();
            return instance;
        }
        return null;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_movie_details, container, false);

        //v.setBackgroundColor ( (new Random()).nextInt() );
         /*   Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/original"+movie.getBackdropPath()).into(new Target() {

                 @Override
                 public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                     v.setBackground(new BitmapDrawable(getResources(),bitmap));
                 }

                 @Override
                 public void onBitmapFailed(Drawable errorDrawable) {

                 }

                 @Override public void onPrepareLoad(Drawable placeHolderDrawable) {}});*/


        ivPoster = v.findViewById(R.id.iv_film_details_poster);
        ivBackPoster = v.findViewById(R.id.iv_film_details_backposter);
        tvCompany = v.findViewById(R.id.tv_film_details_company);
        tvCategory = v.findViewById(R.id.tv_film_details_category);
        tvReleaseYear = v.findViewById(R.id.tv_film_details_releaseYear);
        tvShowTitle = v.findViewById(R.id.tv_film_details_showTitle);
        tvRating = v.findViewById(R.id.tv_film_details_rating);
        tvSummary = v.findViewById(R.id.tv_film_details_summary);
        btnYoutube = v.findViewById(R.id.btn_film_details_youtube);
        btnYoutube.setOnClickListener(MovieDetailsFragment.this);




        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+movie.getPosterPath()).error(getContext().getDrawable(R.drawable.defaut)).centerCrop().fit().into(ivPoster);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+movie.getBackdropPath()).error(getContext().getDrawable(R.drawable.defautback)).into(ivBackPoster);
        //v.setBackground(getContext().getDrawable(ivPoster.getDrawable()));

        //tvShowCast.setText(movie.getShowCast());
        tvCategory.setText(movie.getGenreString());
        //tvReleaseYear.setText(movie.getReleaseDate());
        tvShowTitle.setText(movie.getTitle());
        tvRating.setText(movie.getVoteAverage()+"/10");
        tvSummary.setText(movie.getOverview());
        tvCompany.setText(movie.getCompanyString());


        Log.i("ratingverifValeur", tvRating.getText()+"r");
       // rbRating.setRating(2);
       // Log.i("ratingverifValeur", rbRating.getRating()+" ");
        // Changement du format de la date
        String mStringDate = movie.getReleaseDate();
        if(!movie.getReleaseDate().isEmpty()) {
            String oldFormat = "yyyy-MM-dd";
            String newFormat = "dd-MM-yyyy";

            String formatedDate = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
            Date myDate = null;
            try {
                myDate = dateFormat.parse(mStringDate);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat timeFormat = new SimpleDateFormat(newFormat);
            formatedDate = timeFormat.format(myDate);

            tvReleaseYear.setText(formatedDate);
        }

        rv = v.findViewById(R.id.rv_film_details_listeActors);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new ListActorAdapter(getContext(), movie.getActors()));

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Log.i("Test RV acteur","Je suis clické num : "+position);

                        a = movie.getActors().get(position);

                        Log.i("testActeur",a.toString());
                        DetailsTask taskD = new DetailsTask(getContext());
                        taskD.setCallBDetails(MovieDetailsFragment.this);
                        Log.i("Execute en dessous","hop");
                        taskD.execute("Acteur", a.getId()+"");
                        Log.i("Execute au dessus","hop là");
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        return v;
    }


    public void setMovie(Movie m) {
        movie = m;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_film_details_youtube:
                submitAction();
                break;
        }
    }

    public void submitAction() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.youtube.com/results?search_query="+movie.getTitle()+" trailer "+Locale.getDefault().getLanguage()));
        startActivity(i);
    }


    @Override
    public void getResultDetails(String result) throws JSONException {
        Log.i("avant", "AfficherDétailsActeurs");
        JSONObject object3 = new JSONObject(result);

        if(!object3.has("birthday")){
            a.setbirthday(getString(R.string.notFind));
        }else{
            if(object3.getString("birthday")=="null"){
                a.setbirthday("...");
            }else {
                a.setbirthday(object3.getString("birthday"));
            }
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
        taskC.setCallBCast(MovieDetailsFragment.this);
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
                Log.i("isEmpty",objectCast.has("release_date")+"");


                String release = "";
                String poster= "";
                String character = "";
                String title ="";

                if(!objectCast.has("release_date")){
                    release ="00-00-0000";
                }else{
                    release = objectCast.getString("release_date");
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
                // Object object = new Serie(objectCast.getLong("id"),objectCast.getString("poster_path"),objectCast.getString("firt_air_date"),objectCast.getString("title"));
                listMovieCast.add(m);
                Log.i("listMovie", m.toString());

            }
            else if(objectCast.getString("media_type").equals("tv")){
                String first_air;
                String characterS;
                String posterS;
                String nameS;

                if(!objectCast.has("first_air_date")){
                    first_air ="00-00-0000";
                }else{
                    first_air = objectCast.getString("first_air_date");
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
                Log.i("listSerie",s.toString());
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
            OnObjectListener = (MovieDetailsFragment.OnObjectSetListener) context;
        }catch(Exception e){

        }

    }
}
