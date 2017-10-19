package com.example.alexandre.netflixlibraryproject.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.model.Serie;
import com.squareup.picasso.Picasso;

public class SerieDetailsFragment extends Fragment{
    private ImageView ivPoster;
    private TextView tvCategory;
    private TextView tvDirector;
    private RatingBar tvRating;
    private TextView tvFirstAirDate;
    private TextView tvShowCast;
    private TextView tvShowTitle;
    private TextView tvSummary;
    private TextView tvNbSeason;
    private TextView tvNbEpisode;

    private Serie serie;


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

        View v = inflater.inflate(R.layout.fragment_movie_details, container, false);



        ivPoster = (ImageView) v.findViewById(R.id.iv_serie_details_poster);
        tvDirector = (TextView) v.findViewById(R.id.tv_serie_details_director);
        tvShowCast = (TextView) v.findViewById(R.id.tv_serie_details_showCast);
        tvCategory = (TextView) v.findViewById(R.id.tv_serie_details_category);
        tvFirstAirDate = (TextView) v.findViewById(R.id.tv_serie_details_firstAirDate);
        tvShowTitle = (TextView) v.findViewById(R.id.tv_serie_details_showTitle);
        tvRating = (RatingBar) v.findViewById(R.id.tv_serie_details_rating);
        tvSummary = (TextView) v.findViewById(R.id.tv_serie_details_summary);
        tvNbSeason = (TextView) v.findViewById(R.id.tv_serie_details_nbSeason);
        tvNbEpisode = (TextView) v.findViewById(R.id.tv_serie_details_nbEpisode);

        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+serie.getPosterPath()).error(getContext().getDrawable(R.drawable.defaut)).centerCrop().fit().into(ivPoster);
        //tvShowCast.setText(serie.getShowCast());
        tvCategory.setText(serie.getGenreString());
        tvFirstAirDate.setText(serie.getFirstAirDate());
        tvShowTitle.setText(serie.getName());
        tvRating.setRating(serie.getVoteAverage()/2);
        tvSummary.setText(serie.getOverview());
        tvNbSeason.setText(serie.getOverview());
        tvNbEpisode.setText(serie.getOverview());

        return v;
    }


    public void setSerie(Serie s) {
        serie = s;
    }
}
