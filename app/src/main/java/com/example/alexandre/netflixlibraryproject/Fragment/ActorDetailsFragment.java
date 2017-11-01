package com.example.alexandre.netflixlibraryproject.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.adapter.ListMovieAdapter;
import com.example.alexandre.netflixlibraryproject.adapter.ListSerieAdapter;
import com.example.alexandre.netflixlibraryproject.model.Actor;
import com.squareup.picasso.Picasso;

public class ActorDetailsFragment extends Fragment {
    private ImageView ivPoster;
    private TextView tvName;
    private TextView tvLife;    // Birthday and deathday
    private TextView tvBiography;
    private TextView tvPlaceOfBirth;
    private RecyclerView rvMovies;
    private RecyclerView rvSeries;
    Actor actor;

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

        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+actor.getProfilePath()).error(getContext().getDrawable(R.drawable.defaut))/*.centerCrop().fit()*/.into(ivPoster);

        tvName.setText(actor.getName());
        tvLife.setText(actor.getbirthday() + " - " + actor.getdeathday());
        tvPlaceOfBirth.setText(actor.getPlace_of_birth());
        tvBiography.setText(actor.getbiography());

        rvMovies = (RecyclerView) v.findViewById(R.id.rv_actor_details_listeMovies);
        rvMovies.setHasFixedSize(false);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMovies.setAdapter(new ListMovieAdapter(getContext(), actor.getMovies()));


        rvSeries = (RecyclerView) v.findViewById(R.id.rv_actor_details_listeSeries);
        rvSeries.setHasFixedSize(false);
        rvSeries.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvSeries.setAdapter(new ListSerieAdapter(getContext(), actor.getSeries()));


        return v;
    }

    public void setActor(Actor a){ actor = a; }
}
