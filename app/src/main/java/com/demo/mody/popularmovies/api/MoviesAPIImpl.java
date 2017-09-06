package com.demo.mody.popularmovies.api;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.demo.mody.popularmovies.fragments.DetailsFragment;
import com.demo.mody.popularmovies.fragments.MainFragment;
import com.demo.mody.popularmovies.models.DiscoverMovie;
import com.demo.mody.popularmovies.models.Movie;
import com.demo.mody.popularmovies.models.Review;
import com.demo.mody.popularmovies.models.Video;
import com.demo.mody.popularmovies.util.Constants;
import com.demo.mody.popularmovies.util.CustomDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Mahmoud El-Metainy on 16-Dec-15.
 */

/**
 * This class implement MoviesAPICallable interface, invoke all the API calls from MoviesAPI, and redirect the results to the UI
 */
public class MoviesAPIImpl implements MoviesAPICallable {

    private MoviesAPI moviesAPI;

    public MoviesAPIImpl() {

        // Creating custom Gson Object
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").registerTypeAdapter(List.class, new CustomDeserializer<List<DiscoverMovie>>()).create();

        // Creating the Retrofit instance, passing the base URL, and custom Gson object
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.SERVICE_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        moviesAPI = retrofit.create(MoviesAPI.class);
    }

    @Override
    public void getMoviesList(String sortBy, String apiKey, final Fragment fragment) {

        Call<List<DiscoverMovie>> call = moviesAPI.getMoviesList(sortBy, apiKey);
        call.enqueue(new Callback<List<DiscoverMovie>>() {
            @Override
            public void onResponse(Response<List<DiscoverMovie>> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    // On success pass the MoviesList to the MainFragment
                    ((MainFragment) fragment).onMoviesSuccess(response.body());
                }
                else {
                    // On serializing or deserializing error, log it, and send forward it
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Code:" + response.code());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Message:" + response.message());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Error Body:" + response.errorBody().toString());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Raw:" + response.raw());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Headers:" + response.headers());

                    ((MainFragment) fragment).onMoviesError();
                }
            }

            @Override
            public void onFailure(Throwable t) {

                // On network errors, pass the error message to the MainFragment
                ((MainFragment) fragment).onMoviesNetworkError(t.getMessage());
            }
        });
    }

    @Override
    public void getMovieByID(int movieID, String apiKey, final Fragment fragment) {

        Call<Movie> call = moviesAPI.getMovieByID(movieID, apiKey);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Response<Movie> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    // On success pass the Movie to the DetailsFragment
                    ((DetailsFragment) fragment).onMovieSuccess(response.body());

                }
                else {
                    // On serializing or deserializing error, log it, and send forward it
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Code:" + response.code());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Message:" + response.message());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Error Body:" + response.errorBody().toString());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Raw:" + response.raw());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Headers:" + response.headers());

                    ((DetailsFragment) fragment).onMovieError();
                }
            }

            @Override
            public void onFailure(Throwable t) {

                // On network errors, pass the error message to the DetailsFragment
                ((DetailsFragment) fragment).onMovieNetworkError(t.getMessage());
            }
        });
    }

    @Override
    public void getVideosByID(int movieID, String apiKey, final Fragment fragment) {

        Call<List<Video>> call = moviesAPI.getVideosByID(movieID, apiKey);
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Response<List<Video>> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    // On success pass the videos to the DetailsFragment
                    ((DetailsFragment) fragment).onVideosSuccess(response.body());
                }
                else {
                    // On serializing or deserializing error, log it, and send forward it
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Code:" + response.code());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Message:" + response.message());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Error Body:" + response.errorBody().toString());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Raw:" + response.raw());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Headers:" + response.headers());

                    ((DetailsFragment) fragment).onVideosError();
                }
            }

            @Override
            public void onFailure(Throwable t) {

                // On network errors, pass the error message to the DetailsFragment
                ((DetailsFragment) fragment).onVideosNetworkError(t.getMessage());
            }
        });
    }

    @Override
    public void getReviewsByID(int movieID, String apiKey, final Fragment fragment) {

        Call<List<Review>> call = moviesAPI.getReviewsByID(movieID, apiKey);
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Response<List<Review>> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    // On success pass the MoviesList to the DetailsFragment
                    ((DetailsFragment) fragment).onReviewsSuccess(response.body());
                }
                else {
                    // On serializing or deserializing error, log it, and send forward it
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Code:" + response.code());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Message:" + response.message());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Error Body:" + response.errorBody().toString());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Raw:" + response.raw());
                    Log.e(MoviesAPICallable.class.getSimpleName(), "Headers:" + response.headers());

                    ((DetailsFragment) fragment).onReviewsError();
                }
            }

            @Override
            public void onFailure(Throwable t) {

                // On network errors, pass the error message to the DetailsFragment
                ((DetailsFragment) fragment).onReviewsNetworkError(t.getMessage());
            }
        });
    }
}