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
import com.example.alexandre.netflixlibraryproject.model.Serie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Alexandre on 18-10-17.
 */


public class TVAdapter extends RecyclerView.Adapter<TVAdapter.MyViewHolder> {
    private List<Serie> series;
    private Context context;


    public TVAdapter(Context context, List<Serie> data) {
        this.context = context;
        series = data;
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cell_movie_tv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Serie serie = series.get(position);
        holder.bind(serie);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView originalTitle;
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
            originalTitle = (TextView) itemView.findViewById(R.id.tv_originalTitle);
        }

        public void bind(Serie serie) {

            title.setText(serie.getTitle());
            originalTitle.setText(serie.getOriginalTitle());
            rating.setRating(serie.getVoteAverage()/ 2);
            releaseYear.setText(serie.getReleaseDate());
            Picasso.with(context).load("http://image.tmdb.org/t/p/original" + serie.getPosterPath()).error(context.getDrawable(R.drawable.defaut)).centerCrop().fit().into(image);
        }

    }
}