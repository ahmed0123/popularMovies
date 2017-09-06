package com.demo.mody.popularmovies.api;

import android.support.v4.app.Fragment;

/**
 * Created by Mahmoud El-Metainy on 17-Dec-15.
 */

/**
 * Business layer interface representation for MoviesAPI methods, which will be implemented by MoviesAPIImpl
 */
public interface MoviesAPICallable {

    /**
     * Gets a list of popular movies by default, containing initial movie data
     *
     * @param sortBy   An optional query to change the sorting style
     * @param apiKey   TMDB API key
     * @param fragment A fragment which receive the callback
     */
    void getMoviesList(String sortBy, String apiKey, Fragment fragment);

    /**
     * Gets a specific movie's details by ID
     *
     * @param movieID  The movie ID
     * @param apiKey   TMDB API key
     * @param fragment A fragment which receive the callback
     */
    void getMovieByID(int movieID, String apiKey, Fragment fragment);

    /**
     * Gets a list of trailes and teasers for a specific movie by ID
     *
     * @param movieID  The movie ID
     * @param apiKey   TMDB API key
     * @param fragment A fragment which receive the callback
     */
    void getVideosByID(int movieID, String apiKey, Fragment fragment);

    /**
     * Gets a list of user's reviews for a specific movie by ID
     *
     * @param movieID  The movie ID
     * @param apiKey   TMDB API key
     * @param fragment A fragment which receive the callback
     */
    void getReviewsByID(int movieID, String apiKey, Fragment fragment);
}
