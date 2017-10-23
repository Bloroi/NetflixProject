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
import com.example.alexandre.netflixlibraryproject.adapter.ListActorAdapter;
import com.example.alexandre.netflixlibraryproject.model.Serie;
import com.squareup.picasso.Picasso;

public class SerieDetailsFragment extends Fragment{
    private ImageView ivPoster;
    private TextView tvCategory;
    private TextView tvRating;
    private TextView tvFirstAirDate;
    private TextView tvShowCast;
    private TextView tvShowTitle;
    private TextView tvSummary;
    private TextView tvNbSeason;
    private TextView tvNbEpisode;
    private TextView tvCompanys;
    private RecyclerView rv;
    private Serie serie;


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
        tvCompanys = (TextView) v.findViewById(R.id.tv_serie_details_companys) ;
        tvCategory = (TextView) v.findViewById(R.id.tv_serie_details_category);
        tvFirstAirDate = (TextView) v.findViewById(R.id.tv_serie_details_firstAirDate);
        tvShowTitle = (TextView) v.findViewById(R.id.tv_serie_details_showTitle);
        tvRating = (TextView) v.findViewById(R.id.tv_serie_details_rating);
        tvSummary = (TextView) v.findViewById(R.id.tv_serie_details_summary);
        tvNbSeason = (TextView) v.findViewById(R.id.tv_serie_details_nbSeason);
        tvNbEpisode = (TextView) v.findViewById(R.id.tv_serie_details_nbEpisode);


        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+serie.getPosterPath()).error(getContext().getDrawable(R.drawable.defaut))/*.centerCrop().fit()*/.into(ivPoster);

        tvCategory.setText(serie.getGenreString());
        tvFirstAirDate.setText(serie.getReleaseDate());
        tvShowTitle.setText(serie.getTitle());
        tvRating.setText(serie.getVoteAverage()+"/10");
        tvSummary.setText(serie.getOverview());
        tvNbSeason.setText(serie.getnbSaison()+"");
        tvNbEpisode.setText(serie.getnbEpisodes()+"");
        tvCompanys.setText(serie.getCompanyString());


        rv = (RecyclerView) v.findViewById(R.id.rv_serie_details_listeActors);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new ListActorAdapter(getContext(), serie.getActors()));


        return v;
    }


    public void setSerie(Serie s) {
        serie = s;
    }
}
