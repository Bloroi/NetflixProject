package com.example.alexandre.netflixlibraryproject.Fragment;

import android.app.Fragment;
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
import com.example.alexandre.netflixlibraryproject.adapter.ListActorAdapter;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MovieDetailsFragment extends Fragment implements View.OnClickListener{

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
        ivBackPoster = (ImageView) v.findViewById(R.id.iv_film_details_backposter) ;
        tvCompany = (TextView) v.findViewById(R.id.tv_film_details_company);
        tvCategory = (TextView) v.findViewById(R.id.tv_film_details_category);
        tvReleaseYear = (TextView) v.findViewById(R.id.tv_film_details_releaseYear);
        tvShowTitle = (TextView) v.findViewById(R.id.tv_film_details_showTitle);
        tvRating = (TextView) v.findViewById(R.id.tv_film_details_rating);
        tvSummary = (TextView) v.findViewById(R.id.tv_film_details_summary);
        btnYoutube = (Button) v.findViewById(R.id.btn_film_details_youtube);
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

        rv = (RecyclerView) v.findViewById(R.id.rv_film_details_listeActors);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(new ListActorAdapter(getContext(), movie.getActors()));

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
}
