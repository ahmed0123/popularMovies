package com.demo.mody.popularmovies.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahmoud El-Metainy on 17-Dec-15.
 */
public class Movie {

    @SerializedName("id")
    private Integer movieID;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private Double budget;
    private String overview;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("runtime")
    private Integer runTime;
    @SerializedName("vote_average")
    private Double voteAverage;

    public Integer getMovieID() {

        return movieID;
    }

    public void setMovieID(Integer movieID) {

        this.movieID = movieID;
    }

    public String getOriginalTitle() {

        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {

        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {

        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {

        this.backdropPath = backdropPath;
    }

    public Double getBudget() {

        return budget;
    }

    public void setBudget(Double budget) {

        this.budget = budget;
    }

    public String getOverview() {

        return overview;
    }

    public void setOverview(String overview) {

        this.overview = overview;
    }

    public String getPosterPath() {

        return posterPath;
    }

    public void setPosterPath(String posterPath) {

        this.posterPath = posterPath;
    }

    public String getReleaseDate() {

        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {

        this.releaseDate = releaseDate;
    }

    public Integer getRunTime() {

        return runTime;
    }

    public void setRunTime(Integer runTime) {

        this.runTime = runTime;
    }

    public Double getVoteAverage() {

        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {

        this.voteAverage = voteAverage;
    }
}
