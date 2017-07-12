package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to update a playlist.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#updatePlaylist(UpdatePlaylistRequest, VzaarCallback)
 */
public class UpdatePlaylistRequest extends VzaarRequest {

    private UpdatePlaylistRequest() {
        super("api/v2/playlists/{id}", Method.PATCH);
    }

    public static class Builder {
        private final UpdatePlaylistRequest obj;

        public Builder(int id) {
            obj = new UpdatePlaylistRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public Builder setTitle(String value) {
            obj.addBodyParam("title", value);
            return this;
        }

        public Builder setSortBy(String value) {
            obj.addBodyParam("sort_by", value);
            return this;
        }

        public Builder setSortOrder(String value) {
            obj.addBodyParam("sort_order", value);
            return this;
        }

        public Builder setPrivate(boolean value) {
            obj.addBodyParam("private", value);
            return this;
        }

        public Builder setDimensions(int width, int height) {
            obj.addBodyParam("dimensions", width + "x" + height);
            return this;
        }

        public Builder setMaxVids(int value) {
            obj.addBodyParam("max_vids", value);
            return this;
        }

        public Builder setPosition(String value) {
            obj.addBodyParam("position", value);
            return this;
        }

        public Builder setAutoplay(boolean value) {
            obj.addBodyParam("autoplay", value);
            return this;
        }

        public Builder setContinuousPlay(boolean value) {
            obj.addBodyParam("continuous_play", value);
            return this;
        }

        public UpdatePlaylistRequest build() {
            return obj;
        }
    }
}
