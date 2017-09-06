package com.demo.mody.popularmovies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.mody.popularmovies.R;
import com.demo.mody.popularmovies.adapters.RecyclerReviewsAdapter;
import com.demo.mody.popularmovies.adapters.RecyclerVideosAdapter;
import com.demo.mody.popularmovies.api.MoviesAPIImpl;
import com.demo.mody.popularmovies.database.MoviesDAO;
import com.demo.mody.popularmovies.models.DiscoverMovie;
import com.demo.mody.popularmovies.models.Movie;
import com.demo.mody.popularmovies.models.Review;
import com.demo.mody.popularmovies.models.Video;
import com.demo.mody.popularmovies.util.Constants;
import com.squareup.picasso.Picasso;

import org.solovyev.android.views.llm.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {

    @Bind(R.id.recView_detailsFragment_reviews) RecyclerView recyclerViewReviews;
    @Bind(R.id.recView_detailsFragment_videos) RecyclerView recyclerViewVideos;
    @Bind(R.id.txt_detailsFragment_overview) TextView txtOverview;
    @Bind(R.id.txt_detailsFragment_rate) TextView txtRate;
    @Bind(R.id.txt_detailsFragment_runTime) TextView txtRunTime;
    @Bind(R.id.txt_detailsFragment_year) TextView txtYear;
    @Bind(R.id.img_detailsFragment_poster) ImageView imgPoster;
    @Bind(R.id.progressBar_detailsFragment) ProgressBar progressBar;
    @Bind(R.id.nestedScroll_detailsFragment) NestedScrollView nestedScrollView;

    private ImageView imgBackDrop;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;

    private MoviesAPIImpl moviesAPIImpl;
    private MoviesDAO moviesDAO;
    private DiscoverMovie discoverMovie;
    private boolean isFavorite;
    private boolean twoPane;

    public DetailsFragment() {

    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        // Initializing MovieDAO
        moviesDAO = new MoviesDAO(getActivity());

        // Checking if it's a tablet or phone
        twoPane = getResources().getBoolean(R.bool.isTablet);

        // Load the extras from the parent activity if it's a phone
        if (!twoPane) {

            // Receiving the movie details from intent
            discoverMovie = (DiscoverMovie) getActivity().getIntent().getSerializableExtra("EXTRA_SESSION_ID");

        }
        else {
            // Load the extras from the MainActivity
            Bundle bundle = getArguments();
            if (bundle != null) {
                discoverMovie = (DiscoverMovie) bundle.getSerializable("EXTRA_SESSION_ID");
            }
        }

        // Checking if this particular movie is added to favorite
        isFavorite = moviesDAO.isAdded(discoverMovie.getMovieID());

        // Creating an instance, and invoking GET calls
        moviesAPIImpl = new MoviesAPIImpl();
        moviesAPIImpl.getMovieByID(discoverMovie.getMovieID(), Constants.API_KEY, this);
        moviesAPIImpl.getReviewsByID(discoverMovie.getMovieID(), Constants.API_KEY, this);
        moviesAPIImpl.getVideosByID(discoverMovie.getMovieID(), Constants.API_KEY, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // Initializing views
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        // Initializing activity's views
        imgBackDrop = (ImageView) getActivity().findViewById(R.id.img_activityDetails_backDrop);
        collapsingToolbar = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        // Check if the movie id added to favorite and display the proper icon
        if (isFavorite) {

            // Change FAB icon
            fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), android.R.drawable.btn_star_big_on));
        }
        else {

            // Change FAB icon
            fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), android.R.drawable.btn_star_big_off));
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFavorite) {

                    // Change FAB icon
                    fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), android.R.drawable.btn_star_big_off));

                    // Remove movie from favorites
                    moviesDAO.deleteMovie(discoverMovie.getMovieID());
                    isFavorite = false;

                    // Show a confirmation
                    Snackbar.make(view, "Removed from favorites", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                else {

                    // Change FAB icon
                    fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), android.R.drawable.btn_star_big_on));

                    // Add movie to favorites
                    moviesDAO.addMovie(discoverMovie);
                    isFavorite = true;

                    // Show a confirmation
                    Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
            }
        });

    }

    // Invoked when data is downloaded successfully
    public void onMovieSuccess(Movie movie) {

        // Setting movie's details into views

        Picasso.with(getActivity()).load(Constants.MOVIE_POSTER_BASE_URL + Constants.MOVIE_BACK_SIZE + movie.getBackdropPath()).into(imgBackDrop);

        collapsingToolbar.setTitle(movie.getOriginalTitle());

        Picasso.with(getActivity()).load(Constants.MOVIE_POSTER_BASE_URL + Constants.MOVIE_POSTER_SIZE + movie.getPosterPath()).into(imgPoster);

        txtOverview.setText(movie.getOverview());

        String movieYear = movie.getReleaseDate().split("-")[0];
        txtYear.setText(movieYear);

        String movieRunTime = String.format(getActivity().getString(R.string.txt_detailsFragment_runTime), movie.getRunTime());
        txtRunTime.setText(movieRunTime);

        String movieRate = String.format(getActivity().getString(R.string.txt_detailsFragment_rate), movie.getVoteAverage());
        txtRate.setText(movieRate);

        // Hiding progress bar and showing the content
        progressBar.setVisibility(View.GONE);
        nestedScrollView.setVisibility(View.VISIBLE);
    }

    // Invoked when there's a serializing or deserializing error
    public void onMovieError() {

    }

    // Invoked when there's a network error
    public void onMovieNetworkError(String message) {

    }

    // Invoked when data is downloaded successfully
    public void onReviewsSuccess(List<Review> reviewList) {

        final LinearLayoutManager layoutManager = new org.solovyev.android.views.llm.LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewReviews.setLayoutManager(layoutManager);
        recyclerViewReviews.addItemDecoration(new DividerItemDecoration(getContext(), null));
        RecyclerReviewsAdapter recyclerReviewsAdapter = new RecyclerReviewsAdapter(getContext(), reviewList);
        recyclerViewReviews.setAdapter(recyclerReviewsAdapter);
    }

    // Invoked when there's a serializing or deserializing error
    public void onReviewsError() {

    }

    // Invoked when there's a network error
    public void onReviewsNetworkError(String message) {

    }

    private void initReviewsRecycler() {

    }

    // Invoked when data is downloaded successfully
    public void onVideosSuccess(List<Video> videoList) {

        final LinearLayoutManager layoutManager = new org.solovyev.android.views.llm.LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewVideos.setLayoutManager(layoutManager);
        recyclerViewVideos.addItemDecoration(new DividerItemDecoration(getContext(), null));
        RecyclerVideosAdapter recyclerReviewsAdapter = new RecyclerVideosAdapter(getContext(), videoList, getActivity());
        recyclerViewVideos.setAdapter(recyclerReviewsAdapter);
    }

    // Invoked when there's a serializing or deserializing error
    public void onVideosError() {

    }

    // Invoked when there's a network error
    public void onVideosNetworkError(String message) {

    }
}