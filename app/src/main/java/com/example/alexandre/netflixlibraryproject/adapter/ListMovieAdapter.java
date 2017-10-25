package com.example.alexandre.netflixlibraryproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by Florian on 25-10-17.
 */

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MyViewHoler> {

    private List<Movie> listMovies = Collections.emptyList();
    private ListActorAdapter.ItemClickListener clickListener;
    private Context context;

    public ListMovieAdapter(List<Movie> listMovies, Context context) {
        this.listMovies = listMovies;
        this.context = context;
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_details_movie, parent, false);
        return new ListMovieAdapter.MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, int position) {
        Movie movie= listMovies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }





    public class MyViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView image;
        private final TextView character;


        public MyViewHoler(View itemView) {
            super(itemView);
            image = ((ImageView) itemView.findViewById(R.id.iv_image));
            character = ((TextView) itemView.findViewById(R.id.tv_character));
        }

        public void bind(Movie movie) {
            Picasso.with(context).load("http://image.tmdb.org/t/p/original" + movie.getPosterPath()).error(context.getDrawable(R.drawable.defaut)).centerCrop().fit().into(image);
            character.setText(movie.getCharacter());
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null){
            clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setClickListener(ListActorAdapter.ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }




}
