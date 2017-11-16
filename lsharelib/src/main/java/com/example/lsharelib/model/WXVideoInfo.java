package com.example.lsharelib.model;

/**
 * Author: liujicheng
 * Package:com.example.lsharelib.model
 * Date:   2017/11/14
 * Desc: com.example.lsharelib.model
 */
public class WXVideoInfo extends WXBaseInfo{

    private String videoUrl;
    private String title;
    private String description;
    private String displayImg;

    public String getVideoUrl() {
        return videoUrl == null ? "" : videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? "" : videoUrl;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public String getDisplayImg() {
        return displayImg == null ? "" : displayImg;
    }

    public void setDisplayImg(String displayImg) {
        this.displayImg = displayImg == null ? "" : displayImg;
    }
}
