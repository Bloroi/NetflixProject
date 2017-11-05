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

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.MyViewHolder>{
    private List<Actor> actors;
    private Context context;

    public ActorAdapter(Context context, List<Actor> actors) {
        this.actors = actors;
        this.context = context;
    }
    @Override
    public ActorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cell_actor_tv, parent, false);
        return new ActorAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActorAdapter.MyViewHolder holder, int position) {
        Actor actor = actors.get(position);
        holder.bind(actor);
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = ((ImageView) itemView.findViewById(R.id.iv_image));
            tvName = ((TextView) itemView.findViewById(R.id.tv_name));
        }

        public void bind(Actor actor) {
            Picasso.with(context).load("http://image.tmdb.org/t/p/original"+actor.getProfilePath()).error(context.getDrawable(R.drawable.defaut)).centerCrop().fit().into(image);
            tvName.setText(actor.getName());
        }
    }
}
