package com.example.alexandre.netflixlibraryproject.unused;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.model.Film;
import com.example.alexandre.netflixlibraryproject.model.Utils;

public class ShowFilm extends Activity{
    TextView tvAffichage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_film);

        tvAffichage = (TextView) findViewById(R.id.tv_show_Affichage);

        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            Film f = extras.getParcelable(Utils.Intent.TAG_FILM);
            tvAffichage.setText("Title: "+f.getShowTitle()+" Summary : "+f.getSummary()+" Category : "+f.getCategory());
        }
    }
}
