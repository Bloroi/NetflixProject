package com.example.alexandre.netflixlibraryproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FilmDetails extends AppCompatActivity {

    private ImageView ivPoster;
    private TextView tvCategory;
    private TextView tvDirector;
    private TextView tvMediaType;
    private TextView tvRating;
    private TextView tvReleaseYear;
    private TextView tvShowCast;
    private TextView tvShowId;
    private TextView tvShowTitle;
    private TextView tvSummary;
    private TextView tvUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);


        ivPoster = (ImageView) findViewById(R.id.iv_film_details_poster);
        tvDirector = (TextView) findViewById(R.id.tv_film_details_director);
        tvShowCast = (TextView) findViewById(R.id.tv_film_details_showCast);
        tvCategory = (TextView) findViewById(R.id.tv_film_details_category);
        tvReleaseYear = (TextView) findViewById(R.id.tv_film_details_releaseYear);
        tvShowTitle = (TextView) findViewById(R.id.tv_film_details_showTitle);
        tvRating = (TextView) findViewById(R.id.tv_film_details_rating);
        tvSummary = (TextView) findViewById(R.id.tv_film_details_summary);
        tvMediaType = (TextView) findViewById(R.id.tv_film_details_mediatype);
        tvShowId = (TextView) findViewById(R.id.tv_film_details_showId);
        tvUnit = (TextView) findViewById(R.id.tv_film_details_unit);



        ivPoster.setImageResource(getIntent().getIntExtra("poster", 00));
        tvDirector.setText("Director : "+getIntent().getStringExtra("director"));
        tvShowCast.setText("Cast : "+getIntent().getStringExtra("showCast"));
        tvCategory.setText("Category : "+getIntent().getStringExtra("category"));
        tvReleaseYear.setText("Release year : "+getIntent().getStringExtra("releaseYear"));
        tvShowTitle.setText("Title : "+getIntent().getStringExtra("showTitle"));
        tvRating.setText("Rating : "+getIntent().getStringExtra("rating"));
        tvSummary.setText("Summary : "+getIntent().getStringExtra("summary"));
        tvMediaType.setText("Media Type : "+getIntent().getStringExtra("mediaType"));
        tvShowId.setText("Id : "+getIntent().getStringExtra("id"));
        tvUnit.setText("Unit : "+getIntent().getStringExtra("unit"));
    }
}
