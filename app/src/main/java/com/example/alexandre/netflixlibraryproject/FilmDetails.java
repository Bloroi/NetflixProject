package com.example.alexandre.netflixlibraryproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.model.Film;
import com.example.alexandre.netflixlibraryproject.model.Utils;
import com.squareup.picasso.Picasso;

public class FilmDetails extends AppCompatActivity {

    private ImageView ivPoster;
    private TextView tvCategory;
    private TextView tvDirector;
    private RatingBar tvRating;
    private TextView tvReleaseYear;
    private TextView tvShowCast;
    private TextView tvShowTitle;
    private TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);

        Film f = (Film) getIntent().getParcelableExtra(Utils.Intent.TAG_FILM);

        ivPoster = (ImageView) findViewById(R.id.iv_film_details_poster);
        tvDirector = (TextView) findViewById(R.id.tv_film_details_director);
        tvShowCast = (TextView) findViewById(R.id.tv_film_details_showCast);
        tvCategory = (TextView) findViewById(R.id.tv_film_details_category);
        tvReleaseYear = (TextView) findViewById(R.id.tv_film_details_releaseYear);
        tvShowTitle = (TextView) findViewById(R.id.tv_film_details_showTitle);
        tvRating = (RatingBar) findViewById(R.id.tv_film_details_rating);
        tvSummary = (TextView) findViewById(R.id.tv_film_details_summary);

        Picasso.with(this).load(f.getPoster()).error(this.getDrawable(R.drawable.defaut)).centerCrop().fit().into(ivPoster);
        tvDirector.setText(f.getDirector());
        tvShowCast.setText(f.getShowCast());
        tvCategory.setText(f.getCategory());
        tvReleaseYear.setText(f.getReleaseYear());
        tvShowTitle.setText(f.getShowTitle());
        tvRating.setRating(Float.parseFloat(f.getRating()));
        tvSummary.setText(f.getSummary());
    }
}
