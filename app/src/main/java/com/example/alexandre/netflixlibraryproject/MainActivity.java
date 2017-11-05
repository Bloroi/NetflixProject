package com.example.alexandre.netflixlibraryproject;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.alexandre.netflixlibraryproject.Fragment.ActorDetailsFragment;
import com.example.alexandre.netflixlibraryproject.Fragment.MainFragment;
import com.example.alexandre.netflixlibraryproject.Fragment.MovieDetailsFragment;
import com.example.alexandre.netflixlibraryproject.Fragment.SerieDetailsFragment;
import com.example.alexandre.netflixlibraryproject.model.Actor;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.example.alexandre.netflixlibraryproject.model.Serie;


public class MainActivity extends AppCompatActivity implements MainFragment.OnObjectSetListener,MovieDetailsFragment.OnObjectSetListener,SerieDetailsFragment.OnObjectSetListener,ActorDetailsFragment.OnObjectSetListener {

    private MainFragment FragMain = MainFragment.getInstance();
    private MovieDetailsFragment FragDetMovie = MovieDetailsFragment.getInstance();
    private SerieDetailsFragment FragDetSerie = SerieDetailsFragment.getInstance();
    private ActorDetailsFragment FragDetActor = ActorDetailsFragment.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.main_container, FragMain);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_revert:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, FragMain)
                        .addToBackStack(null)
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void UpdateMovie(Movie m) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FragDetMovie)
                .addToBackStack(null)
                .commit();
        FragDetMovie.setMovie(m);
    }

    @Override
    public void UpdateSerie(Serie s) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FragDetSerie)
                .addToBackStack(null)
                .commit();
        FragDetSerie.setSerie(s);
    }

    @Override
    public void UpdateActor(Actor a) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FragDetActor)
                .addToBackStack(null)
                .commit();
        FragDetActor.setActor(a);
    }


    //Permet d'empêcher le retour en arrière si on est sur le MainFragment
    @Override
    public void onBackPressed()
    {
        if(FragMain.isAdded()){
        }else{
            super.onBackPressed();
        }
    }
}
