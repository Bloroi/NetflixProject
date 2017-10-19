package com.example.alexandre.netflixlibraryproject.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MovieDetailsFragment extends Fragment {

    private ImageView ivPoster;
    private TextView tvCategory;
    private TextView tvCompany;
    private TextView tvRating;
    private TextView tvReleaseYear;
    private TextView tvShowCast;
    private TextView tvShowTitle;
    private TextView tvSummary;
    private Movie movie;
    private View v;

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


        ivPoster = (ImageView) v.findViewById(R.id.iv_film_details_poster);
        tvCompany = (TextView) v.findViewById(R.id.tv_film_details_company);
        tvShowCast = (TextView) v.findViewById(R.id.tv_film_details_showCast);
        tvCategory = (TextView) v.findViewById(R.id.tv_film_details_category);
        tvReleaseYear = (TextView) v.findViewById(R.id.tv_film_details_releaseYear);
        tvShowTitle = (TextView) v.findViewById(R.id.tv_film_details_showTitle);
        tvRating = (TextView) v.findViewById(R.id.tv_film_details_rating);
        tvSummary = (TextView) v.findViewById(R.id.tv_film_details_summary);

        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original"+movie.getPosterPath()).error(getContext().getDrawable(R.drawable.defaut)).centerCrop().fit().into(ivPoster);
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


        return v;
    }


    public void setMovie(Movie m) {
        movie = m;
    }
}
