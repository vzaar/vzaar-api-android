package com.vzaar.apiclient.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Playlist implements VzaarModel {
    private int id;
    private String title;
    private String sortOrder;
    private String sortBy;
    private int maxVids;
    private String position;
    @SerializedName("private")
    private boolean isPrivate;
    private String dimensions;
    private boolean autoplay;
    private boolean continuous_play;
    private int categoryId;
    private String embedCode;
    private String createdAt;
    private String updatedAt;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getMaxVids() {
        return maxVids;
    }

    public void setMaxVids(int maxVids) {
        this.maxVids = maxVids;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public boolean isAutoplay() {
        return autoplay;
    }

    public void setAutoplay(boolean autoplay) {
        this.autoplay = autoplay;
    }

    public boolean isContinuous_play() {
        return continuous_play;
    }

    public void setContinuous_play(boolean continuous_play) {
        this.continuous_play = continuous_play;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getEmbedCode() {
        return embedCode;
    }

    public void setEmbedCode(String embedCode) {
        this.embedCode = embedCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
