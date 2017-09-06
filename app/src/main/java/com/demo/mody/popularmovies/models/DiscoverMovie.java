package com.demo.mody.popularmovies.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mahmoud El-Metainy on 16-Dec-15.
 */
public class DiscoverMovie implements Serializable {

    @SerializedName("id")
    private int movieID;
    @SerializedName("poster_path")
    private String posterPath;

    public int getMovieID() {

        return movieID;
    }

    public void setMovieID(int movieID) {

        this.movieID = movieID;
    }

    public String getPosterPath() {

        return posterPath;
    }

    public void setPosterPath(String posterPath) {

        this.posterPath = posterPath;
    }
}
