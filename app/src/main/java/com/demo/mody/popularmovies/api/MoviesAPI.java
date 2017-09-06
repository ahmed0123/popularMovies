package com.demo.mody.popularmovies.api;

import com.demo.mody.popularmovies.models.DiscoverMovie;
import com.demo.mody.popularmovies.models.Movie;
import com.demo.mody.popularmovies.models.Review;
import com.demo.mody.popularmovies.models.Video;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Mahmoud El-Metainy on 16-Dec-15.
 */

/**
 * Defining all the API request methods
 */
public interface MoviesAPI {

    @GET("/3/discover/movie")
    Call<List<DiscoverMovie>> getMoviesList(@Query("sort_by") String sortBy, @Query("api_key") String apiKey);

    @GET("/3/movie/{id}")
    Call<Movie> getMovieByID(@Path("id") int movieID, @Query("api_key") String apiKey);

    @GET("/3/movie/{id}/videos")
    Call<List<Video>> getVideosByID(@Path("id") int movieID, @Query("api_key") String apiKey);

    @GET("/3/movie/{id}/reviews")
    Call<List<Review>> getReviewsByID(@Path("id") int movieID, @Query("api_key") String apiKey);
}
