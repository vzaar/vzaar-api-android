package com.vzaar.apiclient;

/**
 * Represents a request to Vzaar's video storage.
 */
@SuppressWarnings("CanBeFinal")
public class VzaarUploadRequest {

    private SourceVideo sourceVideo;
    private String videoTitle;
    private String videoDescription;

    private VzaarUploadRequest(SourceVideo sourceVideo, String videoTitle, String videoDescription) {
        this.sourceVideo = sourceVideo;
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
    }

    public SourceVideo getSourceVideo() {
        return sourceVideo;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public static class Builder {
        private SourceVideo sourceVideo;
        private String videoTitle;
        private String videoDescription;

        public Builder(SourceVideo sourceVideo) {
            this.sourceVideo = sourceVideo;
        }

        public Builder setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
            return this;
        }

        public Builder setVideoDescription(String videoDescription) {
            this.videoDescription = videoDescription;
            return this;
        }

        public VzaarUploadRequest build() {
            return new VzaarUploadRequest(
                    sourceVideo,
                    videoTitle,
                    videoDescription);
        }

    }
}
