package com.example.alexandre.netflixlibraryproject;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandre.netflixlibraryproject.Fragment.MovieDetailsFragment;
import com.example.alexandre.netflixlibraryproject.Fragment.MainFragment;
import com.example.alexandre.netflixlibraryproject.model.Actor;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.example.alexandre.netflixlibraryproject.model.Serie;

public class MainActivity extends AppCompatActivity implements MainFragment.OnObjectSetListener {


    private MainFragment FragMain = MainFragment.getInstance();
    private MovieDetailsFragment FragDet = MovieDetailsFragment.getInstance();

    Movie movie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Fragment instance;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();


        MainFragment testFrag = new MainFragment();
        instance = testFrag;*/
        FragmentTransaction transaction = getFragmentManager().beginTransaction();


        transaction.add(R.id.main_container, FragMain);
        transaction.commit();




    }



    @Override
    public void UpdateMovie(Movie m) {

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FragDet)
                .addToBackStack(null)
                .commit();
        FragDet.setMovie(m);
    }

    @Override
    public void UpdateSerie(Serie s) {

    }

    @Override
    public void UpdateActor(Actor a) {

    }
}
