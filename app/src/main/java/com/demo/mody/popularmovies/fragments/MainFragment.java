package com.demo.mody.popularmovies.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.mody.popularmovies.R;
import com.demo.mody.popularmovies.adapters.RecyclerMoviesAdapter;
import com.demo.mody.popularmovies.api.MoviesAPIImpl;
import com.demo.mody.popularmovies.database.MoviesDAO;
import com.demo.mody.popularmovies.models.DiscoverMovie;
import com.demo.mody.popularmovies.util.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements RecyclerMoviesAdapter.RecyclerViewClickListener {

    @Bind(R.id.tv_mainFragment_empty)
    TextView tvEmptyList;
    @Bind(R.id.recView_mainFragment_movies)
    RecyclerView recyclerViewMovies;
    @Bind(R.id.swipeRefresh_MainFragment)
    SwipeRefreshLayout swipeRefreshLayout;

    private MoviesAPIImpl moviesAPIImpl;
    private MoviesDAO moviesDAO;

    public MainFragment() {

    }

    @Override
    public void itemClicked(DiscoverMovie discoverMovie) {

        // Forward the listener to the MainActivity
        ((Communicator) getActivity()).onResponse(discoverMovie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Initializing views
        ButterKnife.bind(this, view);

        // Initializing MovieDAO
        moviesDAO = new MoviesDAO(getActivity());

        // Initiate the animation on Recycler View
        setRecyclerAnimation();

        // Handling SwipeRefreshLayout onRefresh Listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Refreshing the data by re-downloading or getting offline data
                if (getSortType().equals("favorites")) {

                    // Populating recycler view from database
                    populateRecycler(moviesDAO.getAllMovies());
                }
                else {

                    // Creating an instance, and invoking the getMoviesList call
                    moviesAPIImpl.getMoviesList(getSortType(), Constants.API_KEY, MainFragment.this);
                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        if (getSortType().equals("favorites")) {

            // Populating recycler view from database
            populateRecycler(moviesDAO.getAllMovies());
        }
        else {

            // Creating an instance, and invoking the getMoviesList call
            moviesAPIImpl = new MoviesAPIImpl();
            moviesAPIImpl.getMoviesList(getSortType(), Constants.API_KEY, this);
        }
    }

    // Invoked when data is downloaded successfully
    public void onMoviesSuccess(List<DiscoverMovie> movieList) {

        populateRecycler(movieList);
    }

    // Occurs when an item is clicked on the RecyclerView

    // Invoked when there's a serializing or deserializing error
    public void onMoviesError() {

    }

    // Invoked when there's a network error
    public void onMoviesNetworkError(String message) {

    }

    // Setting the Recycler View grid animation
    private void setRecyclerAnimation() {

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerViewMovies.setItemAnimator(itemAnimator);
    }

    // Populate the recycler view from downloaded movie or an offline database
    private void populateRecycler(List<DiscoverMovie> discoverMovieList) {

        // Showing a default empty text if the data set is empty
        if (discoverMovieList.isEmpty()) {

            recyclerViewMovies.setVisibility(View.GONE);
            tvEmptyList.setVisibility(View.VISIBLE);
        }
        else {

            recyclerViewMovies.setVisibility(View.VISIBLE);
            tvEmptyList.setVisibility(View.GONE);
        }

        RecyclerMoviesAdapter recyclerMoviesAdapter = new RecyclerMoviesAdapter(getContext(), discoverMovieList, this);
        recyclerViewMovies.setAdapter(recyclerMoviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Stop the spinner
        swipeRefreshLayout.setRefreshing(false);
    }

    // Return the sort type from shared preferences
    private String getSortType() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        return sharedPreferences.getString("sort", "popularity.desc");
    }

    public interface Communicator {

        void onResponse(DiscoverMovie discoverMovie);
    }
}
