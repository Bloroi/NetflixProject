package com.example.alexandre.netflixlibraryproject;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandre.netflixlibraryproject.Fragment.DetailsFragment;
import com.example.alexandre.netflixlibraryproject.Fragment.MainFragment;
import com.example.alexandre.netflixlibraryproject.model.Movie;

public class MainActivity extends AppCompatActivity implements MainFragment.OnObjectSetListener, DetailsFragment.CallbackSetFilm {


    private MainFragment FragMain = MainFragment.getInstance();
    private DetailsFragment FragDet = DetailsFragment.getInstance();

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
    public void setEnvoiFilm() {
        FragDet.setMovie(movie);
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
}
