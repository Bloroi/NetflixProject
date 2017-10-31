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

/**
 * Created by Florian on 31-10-17.
 */

public class ListSerieAdapter extends RecyclerView.Adapter<ListSerieAdapter.MyViewHolder> {
    private List<Serie> listSeries = Collections.emptyList();
    private ListActorAdapter.ItemClickListener clickListener;
    private Context context;

    public ListSerieAdapter(Context context, List<Serie> listSeries) {
        this.context = context;
        this.listSeries = listSeries;
    }

    @Override
    public ListSerieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_details_serie, parent, false);
        return new ListSerieAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListSerieAdapter.MyViewHolder holder, int position) {
        Serie serie= listSeries.get(position);
        holder.bind(serie);
    }

    @Override
    public int getItemCount() {
        return listSeries.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivImage;
        private final TextView tvCharacter;
        private final TextView tvTitle;
        private final TextView tvFirstAirDate;


        public MyViewHolder(View itemView) {
            super(itemView);
            ivImage = ((ImageView) itemView.findViewById(R.id.iv_image));
            tvCharacter = ((TextView) itemView.findViewById(R.id.tv_character));
            tvTitle = ((TextView) itemView.findViewById(R.id.tv_title));
            tvFirstAirDate = ((TextView) itemView.findViewById(R.id.tv_first_air_date));
        }

        public void bind(Object moviesAndSeries) {
            Serie serie = (Serie) moviesAndSeries;
            Picasso.with(context).load("http://image.tmdb.org/t/p/original" + serie.getPosterPath()).error(context.getDrawable(R.drawable.defaut)).centerCrop().fit().into(ivImage);
            tvCharacter.setText(serie.getCharacter());
            tvTitle.setText(serie.getTitle());
            tvFirstAirDate.setText(serie.getFistAirDate());

        }




    }


}
