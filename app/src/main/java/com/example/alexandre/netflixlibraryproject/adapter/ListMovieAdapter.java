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
import com.example.alexandre.netflixlibraryproject.model.Serie;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Florian on 25-10-17.
 */

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MyViewHoler> {
    private List<Object> listMoviesAndSeries = Collections.emptyList();
    private ListActorAdapter.ItemClickListener clickListener;
    private Context context;

    public ListMovieAdapter(Context context, List<Object> listMoviesAndSeries) {
        this.listMoviesAndSeries = listMoviesAndSeries;
        this.context = context;
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_details_movie, parent, false);
        return new ListMovieAdapter.MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, int position) {
        Object movieAndSerie= listMoviesAndSeries.get(position);
        holder.bind(movieAndSerie);
    }

    @Override
    public int getItemCount() {
        return listMoviesAndSeries.size();
    }





    public class MyViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvFilmOuSerie;
        private final ImageView ivImage;
        private final TextView tvCharacter;
        private final TextView tvTitle;
        private final TextView tvRelease_date;



        public MyViewHoler(View itemView) {
            super(itemView);
            tvFilmOuSerie = ((TextView) itemView.findViewById(R.id.tv_filmOuSerie));
            ivImage = ((ImageView) itemView.findViewById(R.id.iv_image));
            tvCharacter = ((TextView) itemView.findViewById(R.id.tv_character));
            tvTitle = ((TextView) itemView.findViewById(R.id.tv_title));
            tvRelease_date = ((TextView) itemView.findViewById(R.id.tv_release_date));
        }

        public void bind(Object moviesAndSeries) {

            if(moviesAndSeries instanceof Movie){
                Movie movie =(Movie) moviesAndSeries;
                Picasso.with(context).load("http://image.tmdb.org/t/p/original" + movie.getPosterPath()).error(context.getDrawable(R.drawable.defaut)).centerCrop().fit().into(ivImage);
                tvCharacter.setText(movie.getCharacter());
                tvTitle.setText(movie.getTitle());
                tvRelease_date.setText(movie.getReleaseDate());
            }
            else if(moviesAndSeries instanceof Serie){
                Serie serie = (Serie) moviesAndSeries;
                Picasso.with(context).load("http://image.tmdb.org/t/p/original" + serie.getPosterPath()).error(context.getDrawable(R.drawable.defaut)).centerCrop().fit().into(ivImage);
                tvTitle.setText(serie.getTitle());
                tvRelease_date.setText(serie.getReleaseDate());
            }

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
