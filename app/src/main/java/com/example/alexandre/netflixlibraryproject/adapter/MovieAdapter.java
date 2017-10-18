package com.example.alexandre.netflixlibraryproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.asynctask.TradTask;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.example.alexandre.netflixlibraryproject.model.Traduction;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Utilisateur on 26-09-17.
 */



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{
    private List<Movie> movies;
    private Context context;


    public MovieAdapter(Context context, List<Movie> data){
        this.context=context;
        movies = data;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cell_movie_tv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements TradTask.ICallbackTrad{

        private final TextView title;
        //private final TextView category;
        private final RatingBar rating;
        private final TextView releaseYear;
        private final ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

            title = ((TextView) itemView.findViewById(R.id.tv_title));
            //category = ((TextView) itemView.findViewById(R.id.tv_category));
            rating = ((RatingBar) itemView.findViewById(R.id.tv_rating));
            releaseYear = ((TextView) itemView.findViewById(R.id.tv_releaseYear));
            image = ((ImageView) itemView.findViewById(R.id.iv_image));
        }

        public void bind(Movie film){
          /*TradTask tradtask = new TradTask();
            tradtask.setCallBTrad(this);*/

            title.setText(film.getTitle());
            //category.setText(film.getGenreString());
            rating.setRating(film.getVoteAverage()/2);
            releaseYear.setText(film.getReleaseDate());
            Picasso.with(context).load("http://image.tmdb.org/t/p/original"+film.getPosterPath()).error(context.getDrawable(R.drawable.defaut)).centerCrop().fit().into(image);
        }


        //Unused
        @Override
        public void getResultTrad(String result) throws JSONException {
            JSONObject object = new JSONObject(result);
            Gson gson = new Gson();
            Traduction t = gson.fromJson(object.toString(), Traduction.class);

           // category.setText(t.getText().get(0));
        }
    }
}
