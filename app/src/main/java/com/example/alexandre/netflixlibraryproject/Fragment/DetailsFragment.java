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
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DetailsFragment extends Fragment {

    private ImageView ivPoster;
    private TextView tvCategory;
    private TextView tvDirector;
    private RatingBar tvRating;
    private TextView tvReleaseYear;
    private TextView tvShowCast;
    private TextView tvShowTitle;
    private TextView tvSummary;
    private Movie movie;


    private static DetailsFragment instance = null;

    public static DetailsFragment getInstance(){
        if(instance==null){
            instance = new DetailsFragment();
            return instance;
        }
        return null;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_details, container, false);



        ivPoster = (ImageView) v.findViewById(R.id.iv_film_details_poster);
        tvDirector = (TextView) v.findViewById(R.id.tv_film_details_director);
        tvShowCast = (TextView) v.findViewById(R.id.tv_film_details_showCast);
        tvCategory = (TextView) v.findViewById(R.id.tv_film_details_category);
        tvReleaseYear = (TextView) v.findViewById(R.id.tv_film_details_releaseYear);
        tvShowTitle = (TextView) v.findViewById(R.id.tv_film_details_showTitle);
        tvRating = (RatingBar) v.findViewById(R.id.tv_film_details_rating);
        tvSummary = (TextView) v.findViewById(R.id.tv_film_details_summary);

        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+movie.getPosterPath()).error(getContext().getDrawable(R.drawable.defaut)).centerCrop().fit().into(ivPoster);
        //tvShowCast.setText(movie.getShowCast());
        tvCategory.setText(movie.getGenreString());
        //tvReleaseYear.setText(movie.getReleaseDate());
        tvShowTitle.setText(movie.getTitle());
        tvRating.setRating(movie.getVoteAverage()/2);
        tvSummary.setText(movie.getOverview());



        // Changement du format de la date
        String mStringDate = movie.getReleaseDate();
        String oldFormat= "yyyy-MM-dd";
        String newFormat= "dd-MM-yyyy";

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

        tvReleaseYear.setText(formatedDate );



        return v;
    }


    public void setMovie(Movie m) {
        movie = m;
    }
}
