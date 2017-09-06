package com.demo.mody.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.demo.mody.popularmovies.R;
import com.demo.mody.popularmovies.models.DiscoverMovie;
import com.demo.mody.popularmovies.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mahmoud El-Metainy on 17-Dec-15.
 */
public class RecyclerMoviesAdapter extends RecyclerView.Adapter<RecyclerMoviesAdapter.MyViewHolder> {

    private Context context;
    private RecyclerViewClickListener itemListener;
    private List<DiscoverMovie> discoverMovies = Collections.emptyList();

    public RecyclerMoviesAdapter(Context context, List<DiscoverMovie> discoverMovies, RecyclerViewClickListener itemListener) {

        this.context = context;
        this.discoverMovies = discoverMovies;
        this.itemListener = itemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflate the layout, initialize the View Holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_movies, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        // Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        final DiscoverMovie discoverMovie = discoverMovies.get(position);

        Picasso.with(context).load(Constants.MOVIE_POSTER_BASE_URL + Constants.MOVIE_POSTER_SIZE + discoverMovie.getPosterPath()).into(holder.imgPoster);

        // Add animation
        animate(holder);
    }

    @Override
    public int getItemCount() {

        return discoverMovies.size();
    }

    // Add animation to the Recycler View
    public void animate(RecyclerView.ViewHolder viewHolder) {

        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.recycler_view_push_left);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    // Remove a RecyclerView item containing a specified object
    public void delete(int position) {

        discoverMovies.remove(position);
        notifyItemRemoved(position);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, DiscoverMovie discoverResult) {

        discoverMovies.add(position, discoverResult);
        notifyItemInserted(position);
    }

    public interface RecyclerViewClickListener {

        void itemClicked(DiscoverMovie discoverMovie);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_recyclerMovies_poster) ImageView imgPoster;

        public MyViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);

            // Handling clicks on Image View
            imgPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Forwarding the listener to the MainFragment
                    itemListener.itemClicked(discoverMovies.get(getLayoutPosition()));
                }
            });
        }
    }
}
