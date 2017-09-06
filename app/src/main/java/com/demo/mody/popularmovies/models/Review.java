package com.demo.mody.popularmovies.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahmoud El-Metainy on 17-Dec-15.
 */
public class Review {

    @SerializedName("id")
    private String reviewID;
    private String author;
    private String content;
    private String url;

    public String getReviewID() {

        return reviewID;
    }

    public void setReviewID(String reviewID) {

        this.reviewID = reviewID;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }
}
