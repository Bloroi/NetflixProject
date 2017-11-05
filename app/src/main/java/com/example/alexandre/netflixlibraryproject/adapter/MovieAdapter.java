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
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


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



    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView originalTitle;
        private final RatingBar rating;
        private final TextView releaseYear;
        private final ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

            title = ((TextView) itemView.findViewById(R.id.tv_title));
            originalTitle = ((TextView) itemView.findViewById(R.id.tv_originalTitle));
            rating = ((RatingBar) itemView.findViewById(R.id.tv_rating));
            releaseYear = ((TextView) itemView.findViewById(R.id.tv_releaseYear));
            image = ((ImageView) itemView.findViewById(R.id.iv_image));
        }

        public void bind(Movie film){
            title.setText(film.getTitle());
            originalTitle.setText(film.getOriginalTitle());
            rating.setRating(film.getVoteAverage()/2);
            releaseYear.setText(film.getReleaseDate());
            Picasso.with(context).load("http://image.tmdb.org/t/p/original"+film.getPosterPath()).error(context.getDrawable(R.drawable.defaut)).centerCrop().fit().into(image);
        }
    }
}
