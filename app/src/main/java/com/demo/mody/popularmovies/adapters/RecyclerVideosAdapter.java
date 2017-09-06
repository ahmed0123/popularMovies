package com.demo.mody.popularmovies.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.mody.popularmovies.R;
import com.demo.mody.popularmovies.models.Video;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mahmoud El-Metainy on 13-Jan-16.
 */
public class RecyclerVideosAdapter extends RecyclerView.Adapter<RecyclerVideosAdapter.MyViewHolder> {

    private Context context;
    private List<Video> videos = Collections.emptyList();
    private Activity activity;

    public RecyclerVideosAdapter(Context context, List<Video> videos, Activity activity) {

        this.context = context;
        this.videos = videos;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_videos, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Video video = videos.get(position);

        holder.youTubePlayer.setText(video.getName());

        holder.youTubePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Checking if Youtube app exist on device
                final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(context);

                if (result == YouTubeInitializationResult.SUCCESS) {

                    // Opens Youtube application
                    context.startActivity(YouTubeStandalonePlayer.createVideoIntent(activity, "AIzaSyDLBOOegwUDI62YPX6aClVvPonhHajqGYo", video.getVideoPath()));
                }
                else {
                    // Opens activity the youtube video in browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + video.getVideoPath()));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return videos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_recyclerReviews_title) TextView youTubePlayer;

        public MyViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
