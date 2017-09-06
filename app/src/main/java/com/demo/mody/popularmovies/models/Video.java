package com.demo.mody.popularmovies.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahmoud El-Metainy on 17-Dec-15.
 */
public class Video {

    @SerializedName("id")
    private String videoID;
    @SerializedName("key")
    private String videoPath;
    private String name;
    private String site;
    private String type;

    public String getVideoID() {

        return videoID;
    }

    public void setVideoID(String videoID) {

        this.videoID = videoID;
    }

    public String getVideoPath() {

        return videoPath;
    }

    public void setVideoPath(String videoPath) {

        this.videoPath = videoPath;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSite() {

        return site;
    }

    public void setSite(String site) {

        this.site = site;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }
}
