package com.example.alexandre.netflixlibraryproject;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.alexandre.netflixlibraryproject.Fragment.ActorDetailsFragment;
import com.example.alexandre.netflixlibraryproject.Fragment.MovieDetailsFragment;
import com.example.alexandre.netflixlibraryproject.Fragment.MainFragment;
import com.example.alexandre.netflixlibraryproject.Fragment.SerieDetailsFragment;
import com.example.alexandre.netflixlibraryproject.model.Actor;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.example.alexandre.netflixlibraryproject.model.Serie;

public class MainActivity extends AppCompatActivity implements MainFragment.OnObjectSetListener {


    private MainFragment FragMain = MainFragment.getInstance();
    private MovieDetailsFragment FragDetMovie = MovieDetailsFragment.getInstance();
    private SerieDetailsFragment FragDetSerie = SerieDetailsFragment.getInstance();
    private ActorDetailsFragment FragDetActor = ActorDetailsFragment.getInstance();

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
        Log.i("TestUpdateMovie","Je passe dans UpdateMovie");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FragDetMovie)
                .addToBackStack(null)
                .commit();
        FragDetMovie.setMovie(m);
    }

    @Override
    public void UpdateSerie(Serie s) {
        Log.i("TestUpdateSerie","Je passe dans UpdateSerie");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FragDetSerie)
                .addToBackStack(null)
                .commit();
        FragDetSerie.setSerie(s);
    }

    @Override
    public void UpdateActor(Actor a) {
        Log.i("TestUpdateActor","Je passe dans UpdateActor");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FragDetActor)
                .addToBackStack(null)
                .commit();
        FragDetActor.setActor(a);
    }
}
