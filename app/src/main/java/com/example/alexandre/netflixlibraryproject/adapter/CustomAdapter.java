package com.example.alexandre.netflixlibraryproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.model.Film;

import java.util.List;

/**
 * Created by Utilisateur on 26-09-17.
 */



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private List<Film> movies;
    private Context context;
    private ImageView imgView;
    public CustomAdapter(Context context, List<Film> data){
        this.context=context;
        movies = data;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Film movie = movies.get(position);
        Log.i("testPhoto",movie.getPoster());
        //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imgView);

        //holder.image.setImage;
        holder.title.setText(movie.getShowTitle());
        holder.description.setText(movie.getSummary());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        //private final ImageView image;

        private Film movie;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = ((TextView) itemView.findViewById(R.id.tv_title));
            description = ((TextView) itemView.findViewById(R.id.tv_description));
            //image = ((ImageView) itemView.findViewById(R.id.iv_image));
        }

        public void display(Film movie) {
            this.movie = movie;
            title.setText(movie.getShowTitle());
            description.setText(movie.getSummary());
        }
    }
}
