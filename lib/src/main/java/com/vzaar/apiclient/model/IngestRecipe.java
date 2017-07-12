package com.vzaar.apiclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class IngestRecipe implements VzaarModel {
    private int id;
    private String name;
    private String recipeType;
    private String description;
    private int accountId;
    private int userId;
    @SerializedName("default")
    private boolean isDefault; // 'default' is reserved in Java
    private boolean multipass;
    private String frameGrabTime;
    private boolean generateAnimatedThumb;
    private boolean generateSprite;
    private boolean useWatermark;
    private boolean sendToYoutube;
    private List<EncodingPreset> encodingPresets;
    private String createdAt;
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isMultipass() {
        return multipass;
    }

    public void setMultipass(boolean multipass) {
        this.multipass = multipass;
    }

    public String getFrameGrabTime() {
        return frameGrabTime;
    }

    public void setFrameGrabTime(String frameGrabTime) {
        this.frameGrabTime = frameGrabTime;
    }

    public boolean isGenerateAnimatedThumb() {
        return generateAnimatedThumb;
    }

    public void setGenerateAnimatedThumb(boolean generateAnimatedThumb) {
        this.generateAnimatedThumb = generateAnimatedThumb;
    }

    public boolean isGenerateSprite() {
        return generateSprite;
    }

    public void setGenerateSprite(boolean generateSprite) {
        this.generateSprite = generateSprite;
    }

    public boolean isUseWatermark() {
        return useWatermark;
    }

    public void setUseWatermark(boolean useWatermark) {
        this.useWatermark = useWatermark;
    }

    public boolean isSendToYoutube() {
        return sendToYoutube;
    }

    public void setSendToYoutube(boolean sendToYoutube) {
        this.sendToYoutube = sendToYoutube;
    }

    public List<EncodingPreset> getEncodingPresets() {
        return encodingPresets;
    }

    public void setEncodingPresets(List<EncodingPreset> encodingPresets) {
        this.encodingPresets = encodingPresets;
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
