package com.example.alexandre.netflixlibraryproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandre.netflixlibraryproject.R;
import com.example.alexandre.netflixlibraryproject.model.Actor;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Alexandre on 18-10-17.
 */

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.MyViewHolder> {
    private List<Actor> listActors;
    private Context context;

    public ActorAdapter(List<Actor> listActors, Context context) {
        this.listActors = listActors;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_actor, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Actor actor = listActors.get(position);
        holder.bind(actor);
    }

    @Override
    public int getItemCount() {
        return listActors.size();
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView character;
        private final ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.tv_name));
            character = ((TextView) itemView.findViewById(R.id.tv_character));
            image = ((ImageView) itemView.findViewById(R.id.iv_image));
        }

        public void bind(Actor actor){
            name.setText(actor.getName());
            character.setText(actor.getCharacter());
            Picasso.with(context).load("http://image.tmdb.org/t/p/original" + actor.getProfilePath()).error(context.getDrawable(R.drawable.defaut)).centerCrop().fit().into(image);
        }


    }




}